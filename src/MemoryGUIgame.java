import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGUIgame extends JFrame {
    // Private fields
    private final Random randNum = new Random();
    private final JPanel dock = new JPanel();
    private final JButton[] buttons = new JButton[9];
    private final ArrayList<Integer> memorize = new ArrayList<>();
    private final ArrayList<Integer> playerGuess = new ArrayList<>();
    private boolean gameover = false;
    private final MouseListener squeak = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) { }

        @Override
        public void mousePressed(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            for (int i = 0 ; i < buttons.length ; i++) {
                if (source.equals(buttons[i])) {
                    buttons[i].setForeground(Color.GREEN);
                    playerGuess.add(i + 1);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            for (JButton button: buttons) {
                if (source.equals(button)) {
                    button.setForeground(Color.BLACK);
                    button.setFocusPainted(false);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    };

    // Default constructor
    public MemoryGUIgame( ) throws InterruptedException {
        this.setTitle("Memory GUI Game -By Blaze");
        this.setBounds(500 , 300,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        initComponents();
        play();
    }

    // Start Program
    public static void main(String... DoMyBidding) throws InterruptedException { new MemoryGUIgame(); }

    // Starts the rules and play
    @SuppressWarnings ({"CollectionAddedToSelf"})
    private void play( ) throws InterruptedException {
        JOptionPane.showMessageDialog(this , """
                                             Welcome to Memory GUI!
                                             Rules:
                                             - RED Mode: Random numbers will be generated one at a time and saved in a sequence.
                                             - GREEN Mode: Click on the numbers generated in sequential order from 'RED Mode'.
                                             - You will have the total generated numbers in seconds to match the sequence. '1 sec/#'
                                             - All of your number entries must match to get to the next round.

                                             That's it! Good Luck!""");
        while (!gameover) {
            generate();
            playerTurn();
            if (gameover) {
                JOptionPane.showMessageDialog(this , "GAME OVER!\nYou memorized " + memorize.size() + " sequences." + "\n\nTry again to beat your score!");
                memorize.removeAll(memorize);
                playerGuess.removeAll(playerGuess);
                gameover = false;
            }
        }
    }

    // Timer to let the player make their sequences selection and compare to win, then reset guesses.
    private void playerTurn( ) throws InterruptedException {
        for (int i = 0 ; i < buttons.length ; i++) {
            buttons[i].addMouseListener(squeak);
        }
        dock.setBackground(Color.GREEN);
        TimeUnit.SECONDS.sleep(memorize.size());

        if (!Arrays.toString(playerGuess.toArray()).equals(Arrays.toString(memorize.toArray()))) { gameover = true; }
        playerGuess.removeAll(playerGuess);
        for (int i = 0 ; i < buttons.length ; i++) {
            buttons[i].removeMouseListener(squeak);
        }

    }

    // Generate random numbers, and activate button sequences to memorize
    private void generate( ) throws InterruptedException {
        dock.setBackground(ColorUIResource.RED);
        if (!memorize.isEmpty()) {
            for (int temp: memorize) {
                buttons[temp - 1].setForeground(Color.RED);
                buttons[temp - 1].doClick();
                TimeUnit.MILLISECONDS.sleep(700);
                buttons[temp - 1].setForeground(Color.BLACK);
                TimeUnit.MILLISECONDS.sleep(700);
            }
        }
        int nextNum = randNum.nextInt(0 , 8);
        memorize.add(nextNum + 1);
        buttons[nextNum].setForeground(Color.RED);
        buttons[nextNum].doClick();
        TimeUnit.MILLISECONDS.sleep(700);
        buttons[nextNum].setForeground(Color.BLACK);
    }

    // Initialize components
    private void initComponents( ) {
        dock.setLayout(new GridLayout(3 , 3,3,3 ));
        dock.setVisible(true);

        for (int i = 0 ; i < buttons.length ; i++) {
            buttons[i] = new JButton();
            buttons[i].setSize(200,200);
            buttons[i].setText(String.valueOf(i + 1));
            buttons[i].setFont(new Font("Arial" , Font.BOLD , 100));
            buttons[i].setForeground(Color.BLACK);
            dock.add(buttons[i]);
            buttons[i].setVisible(true);
        }
        dock.setVisible(true);
        this.add(dock);
        this.revalidate();
    }
}


