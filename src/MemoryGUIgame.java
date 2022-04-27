import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MemoryGUIgame extends JFrame implements MouseListener {
    // Private fields
    private final Random randNum = new Random();
    private final JPanel dock = new JPanel();
    private final JButton[] buttons = new JButton[9];
    private final ArrayList<Integer> memorize = new ArrayList<>();
    private final ArrayList<Integer> playerGuess = new ArrayList<>();
    private boolean gameover = false;

    // Default constructor
    public MemoryGUIgame( ) throws InterruptedException {
        this.setTitle("Memory GUI Game -By Blaze");
        this.setBounds(-1200 , 300 , 500 , 500);
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
                                             - Random numbers will be generated 1 at a time and added in sequence with the previous numbers.
                                             - Example : First sequence = 1 #, Second = 2 #'s, Three = 3 #'s etc..
                                             - Wait for the sequence to finish, then click on the numbers generated in sequence.
                                             - You have 2 seconds plus the number of generated sequences to enter your sequences.

                                             That's it! Good Luck!""");
    PlayAgain:
        while (!gameover) {
            generate();
            playerTurn();
            if (gameover) {
                JOptionPane.showMessageDialog(this , "GAME OVER!\nYou memorized " + memorize.size() + " sequences." +
                        "\n\nTry again to beat your score!");
                memorize.removeAll(memorize);
                playerGuess.removeAll(playerGuess);
                gameover = false;
                continue PlayAgain;
            }
        }
    }

    // Timer to let the player make their sequences selection and compare to win, then reset guesses.
    private void playerTurn( ) throws InterruptedException {
        TimeUnit.SECONDS.sleep(memorize.size() + 2);
        if (!Arrays.toString(playerGuess.toArray()).equals(Arrays.toString(memorize.toArray()))) { gameover = true; }
        playerGuess.removeAll(playerGuess);
    }

    // Generate random numbers, and activate button sequences to memorize
    private void generate( ) throws InterruptedException {

        if (!memorize.isEmpty()) {
            for (int temp: memorize) {
                buttons[temp - 1].setForeground(Color.RED);
                buttons[temp - 1].doClick();
                TimeUnit.SECONDS.sleep(1);
                buttons[temp - 1].setForeground(Color.BLACK);
                TimeUnit.SECONDS.sleep(1);
            }
        }
        int nextNum = randNum.nextInt( 0,8);
        memorize.add(nextNum+1);
        buttons[nextNum].setForeground(Color.RED);
        buttons[nextNum].doClick();
        TimeUnit.SECONDS.sleep(1);
        buttons[nextNum].setForeground(Color.BLACK);
        TimeUnit.SECONDS.sleep(1);
    }
    // Initialize components
    private void initComponents( ) {
        dock.setLayout(new GridLayout(3 , 3 , 3 , 3));
        this.add(dock);
        for (int i = 0 ; i < buttons.length ; i++) {
            buttons[i] = new JButton();
            buttons[i].addMouseListener(this);
            buttons[i].setText(String.valueOf(i + 1));
            buttons[i].setFont(new Font("Arial" , Font.BOLD , 100));
            buttons[i].setForeground(Color.BLACK);
            dock.add(buttons[i]);
            buttons[i].setVisible(true);
        }
        dock.setVisible(true);
    }
    // Color button number red, and add player's number to array on click
    public void mousePressed(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        for (int i = 0 ; i < buttons.length ; i++) {
            if (source.equals(buttons[i])) {
                buttons[i].setForeground(Color.RED);
                playerGuess.add(i + 1);
            }
        }
    }
    // Color button number black when mouse click releases
    public void mouseReleased(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        for (JButton button: buttons) {
            if (source.equals(button)) {
                button.setForeground(Color.BLACK);
            }
        }
    }
    public void mouseClicked(MouseEvent e) {

        }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}


