// STEP 3 : Making buttons do something (event handling)
// New concepts : ActionListener interface, anonymous inner class, lambda, fields

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class StudentApp3 extends JFrame {

    // NEW : components that listeners must reach become fields
    private JLabel message;
    private int clickCount = 0;

    public StudentApp3() {
        super("Student Grade Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        message = new JLabel("Welcome to the Student Grade Manager");
        JButton clickButton = new JButton("Click Me");
        JButton resetButton = new JButton("Reset");

        // NEW : Way 1, an anonymous inner class implementing ActionListener
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                message.setText("Button clicked " + clickCount + " times");
            }
        });

        // NEW : Way 2, a lambda (ActionListener has only one method)
        resetButton.addActionListener(e -> {
            clickCount = 0;
            message.setText("Counter reset");
        });

        add(message);
        add(clickButton);
        add(resetButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp3().setVisible(true));
    }
}
