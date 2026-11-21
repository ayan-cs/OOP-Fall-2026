// STEP 6 : More input components
// New concepts : JComboBox, JRadioButton + ButtonGroup, JCheckBox,
//                listening to a combo box, tooltips

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StudentApp6 extends JFrame {

    private JTextField nameField;
    private JTextField marksField;
    private JLabel status;

    // NEW components
    private JComboBox<String> deptBox;
    private JRadioButton btechRadio, mtechRadio, phdRadio;
    private JCheckBox hostelCheck;

    public StudentApp6() {
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
        nameField.setToolTipText("Full name of the student");       // NEW : tooltip
        marksField.setToolTipText("Whole number from 0 to 100");

        // NEW : drop-down list
        String[] departments = {"CSE", "EE", "ME", "Civil", "AI and DS"};
        deptBox = new JComboBox<>(departments);

        // NEW : radio buttons. The ButtonGroup allows only one to be selected.
        btechRadio = new JRadioButton("B.Tech", true);
        mtechRadio = new JRadioButton("M.Tech");
        phdRadio = new JRadioButton("PhD");
        ButtonGroup programGroup = new ButtonGroup();
        programGroup.add(btechRadio);
        programGroup.add(mtechRadio);
        programGroup.add(phdRadio);
        JPanel programPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        programPanel.add(btechRadio);
        programPanel.add(mtechRadio);
        programPanel.add(phdRadio);

        // NEW : check box (independent on or off)
        hostelCheck = new JCheckBox("Hostel resident");

        formPanel.add(new JLabel("Student Name"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Marks (0 to 100)"));
        formPanel.add(marksField);
        formPanel.add(new JLabel("Department"));
        formPanel.add(deptBox);
        formPanel.add(new JLabel("Program"));
        formPanel.add(programPanel);
        formPanel.add(new JLabel("Accommodation"));
        formPanel.add(hostelCheck);
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

        computeButton.addActionListener(e -> computeGrade());
        clearButton.addActionListener(e -> clearForm());
        marksField.addActionListener(e -> computeGrade());

        // NEW : react immediately when a choice changes
        deptBox.addActionListener(e ->
                status.setText("Department selected : " + deptBox.getSelectedItem()));
        hostelCheck.addActionListener(e ->
                status.setText(hostelCheck.isSelected() ? "Hostel resident" : "Day scholar"));

        pack();
        setLocationRelativeTo(null);
    }

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

        // NEW : read the new components
        String dept = (String) deptBox.getSelectedItem();
        String program = selectedProgram();
        String hostel = hostelCheck.isSelected() ? "Hostel" : "Day scholar";

        String grade = gradeFor(marks);
        String summary = name + " (" + program + ", " + dept + ", " + hostel + ")\n"
                + "Marks " + marks + "  Grade " + grade;
        status.setText(name + " gets grade " + grade);
        JOptionPane.showMessageDialog(this, summary, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private String selectedProgram() {
        if (mtechRadio.isSelected()) return "M.Tech";
        if (phdRadio.isSelected()) return "PhD";
        return "B.Tech";
    }

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
        deptBox.setSelectedIndex(0);
        btechRadio.setSelected(true);
        hostelCheck.setSelected(false);
        status.setText("Form cleared");
        nameField.requestFocus();
    }

    private void showError(String text) {
        JOptionPane.showMessageDialog(this, text, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp6().setVisible(true));
    }
}
