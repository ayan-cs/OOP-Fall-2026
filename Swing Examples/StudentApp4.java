// STEP 4 : Designing a real screen with layout managers
// New concepts : JPanel, BorderLayout, GridLayout, FlowLayout, nesting panels,
//                JTextField, fonts, empty borders for padding
// Buttons do nothing yet. This step is only about arranging things.

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StudentApp4 extends JFrame {

    private JTextField nameField;
    private JTextField marksField;
    private JLabel status;

    public StudentApp4() {
        super("Student Grade Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // NEW : the frame uses BorderLayout (NORTH, SOUTH, EAST, WEST, CENTER)
        setLayout(new BorderLayout(10, 10));

        // ---- NORTH : title ----
        JLabel title = new JLabel("Student Grade Manager", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(title, BorderLayout.NORTH);

        // ---- CENTER : form in a grid (any number of rows, 2 columns) ----
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        nameField = new JTextField(15);
        marksField = new JTextField(5);

        formPanel.add(new JLabel("Student Name"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Marks (0 to 100)"));
        formPanel.add(marksField);
        add(formPanel, BorderLayout.CENTER);

        // ---- SOUTH : a panel that itself contains two things ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton computeButton = new JButton("Compute Grade");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(computeButton);
        buttonPanel.add(clearButton);

        status = new JLabel("Ready");
        status.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(status, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // NEW : size the window to fit its contents, then centre it
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp4().setVisible(true));
    }
}
