// STEP 1 : An empty window
// Concepts : JFrame, inheritance, setSize, close operation, Event Dispatch Thread

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class StudentApp1 extends JFrame {

    public StudentApp1() {
        super("Student Grade Manager");          // title of the window
        setSize(500, 400);                       // width x height in pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button ends the program
        setLocationRelativeTo(null);             // open in the centre of the screen
    }

    public static void main(String[] args) {
        // All Swing work must happen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            StudentApp1 app = new StudentApp1();
            app.setVisible(true);                // a frame is invisible until we say so
        });
    }
}
