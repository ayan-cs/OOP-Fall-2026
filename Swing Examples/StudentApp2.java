// STEP 2 : Putting components inside the window
// New concepts : JLabel, JButton, add(), a first look at layout (FlowLayout)

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class StudentApp2 extends JFrame {

    public StudentApp2() {
        super("Student Grade Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // NEW : choose how components are arranged.
        // DEMO : comment this line out and run again. Only the button appears.
        setLayout(new FlowLayout());

        // NEW : create components
        JLabel message = new JLabel("Welcome to the Student Grade Manager");
        JButton clickButton = new JButton("Click Me");

        // NEW : add them to the frame
        add(message);
        add(clickButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp2().setVisible(true));
    }
}
