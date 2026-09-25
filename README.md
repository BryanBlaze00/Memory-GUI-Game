# Memory GUI Game - Java Swing Pattern Memory Application

An event-driven desktop application built in Java Swing designed to test memory recall using dynamically generated visual sequences. The application manages state-driven game modes, timed sequence loops, dynamic button layouts, and user input validation.

## Core Features & Architecture

* Graphical User Interface: Built using `JFrame` and a $3 \times 3$ `GridLayout` managing nine interactive `JButton` components.

* State Management Engine: Toggles between display ("RED Mode") and user interaction ("GREEN Mode") states to enforce strict sequence progression and rule compliance.

* Dynamic Sequence Generation: Generates pseudo-random numerical sequences stored in memory for runtime state comparison.

* Event-Driven Input Handling: Leverages `MouseListener` and event state controls to manage active response windows, prevent input spoofing during generation phases, and validate turn logic.

* Executable Packaging: Configured with pre-packaged output files (`.jar` and `.exe`) bundled with JRE configurations for standalone desktop operation.

## Key Technical Focus Areas

* GUI Layout & Component Lifecycle: Custom initialization (`initComponents()`), container repainting (`revalidate()`), and dynamic visual state updates.

* Input Validation & State Control: Disabling dynamic listeners during program-driven display phases to lock input handling until the evaluation phase.

* Programmatic Timing: Enforcing timed per-number input windows during evaluation states.

## Tech Stack

* Language: Java

* Framework: Java Swing / AWT

* Concepts: Event-Driven Architecture, GUI State Machines, Layout Managers, Standalone Distribution

# Memory-GUI-Game
 
I made a GUI game that creates a  GridLayout for nine JButton's! 
The game is to test your memory in seeing a sequence of random generated numbers that click numbered buttons and add them to a sequence that you have to keep track of, 
with limited time to respond. First the JFrame is constructed, which calls the initComponents() method to build and add the panel and buttons, 
and moves into the play() method that has a gameover false loop, that will call a random number generator I call this RED Mode, 
I have disabled the MouseListener so that no one can cheat. Then the players turn activates and I call it GREEN Mode, 
where you only have a second per number saved to match what the 'GUI Says' haha!

While making this I ran it often to make sure my changes were making progress, and I happened to get a really high score of 31! 

See if you can beat it! --->> Just download the [Memory GUI Says-Game By Blaze.exe](https://github.com/BryanBlaze00/Memory-GUI-Game/blob/eb85860d48133beab9eca80ef7dd2a53d3002480/Memory%20GUI%20Says-Game%20By%20Blaze.exe) file!

![](MemoryGUIgame.png)
