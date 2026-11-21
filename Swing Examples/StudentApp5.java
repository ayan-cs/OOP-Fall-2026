// STEP 5 : Reading input, validating it, and responding
// New concepts : getText / setText, parsing input, JOptionPane dialogs,
//                Enter key on a text field, moving logic into methods

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StudentApp5 extends JFrame {

    private JTextField nameField;
    private JTextField marksField;
    private JLabel status;

    public StudentApp5() {
        super("Student Grade Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Student Grade Manager", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        nameField = new JTextField(15);
        marksField = new JTextField(5);
        formPanel.add(new JLabel("Student Name"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Marks (0 to 100)"));
        formPanel.add(marksField);
        add(formPanel, BorderLayout.CENTER);

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

        // NEW : wire the buttons to methods (method references)
        computeButton.addActionListener(e -> computeGrade());
        clearButton.addActionListener(e -> clearForm());

        // NEW : pressing Enter inside the marks field also computes
        marksField.addActionListener(e -> computeGrade());

        pack();
        setLocationRelativeTo(null);
    }

    // NEW : event handler logic lives in its own method
    private void computeGrade() {
        String name = nameField.getText().trim();
        String marksText = marksField.getText().trim();

        if (name.isEmpty()) {
            showError("Please enter the student's name.");
            nameField.requestFocus();
            return;
        }

        int marks;
        try {
            marks = Integer.parseInt(marksText);
        } catch (NumberFormatException ex) {
            showError("Marks must be a whole number.");
            marksField.requestFocus();
            return;
        }

        if (marks < 0 || marks > 100) {
            showError("Marks must be between 0 and 100.");
            marksField.requestFocus();
            return;
        }

        String grade = gradeFor(marks);
        status.setText(name + " scored " + marks + " and gets grade " + grade);
        JOptionPane.showMessageDialog(this,
                name + " gets grade " + grade,
                "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    // Pure logic, no Swing inside. Easy to test separately.
    private String gradeFor(int marks) {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    private void clearForm() {
        nameField.setText("");
        marksField.setText("");
        status.setText("Form cleared");
        nameField.requestFocus();
    }

    private void showError(String text) {
        JOptionPane.showMessageDialog(this, text, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp5().setVisible(true));
    }
}
