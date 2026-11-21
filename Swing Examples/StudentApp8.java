// STEP 8 : Polishing into a real desktop application
// New concepts : JMenuBar, JMenu, JMenuItem, keyboard shortcuts and mnemonics,
//                WindowListener (confirm before exit), MouseListener (double click),
//                Adapter classes

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StudentApp8 extends JFrame {

    private JTextField nameField;
    private JTextField marksField;
    private JLabel status;
    private JComboBox<String> deptBox;
    private JRadioButton btechRadio, mtechRadio, phdRadio;
    private JCheckBox hostelCheck;

    // NEW : the MODEL holds the data, the TABLE (view) only displays it
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentApp8() {
        super("Student Grade Manager");
        setLayout(new BorderLayout(10, 10));

        // NEW : do not close straight away. Ask the user first.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        // NEW : menu bar at the top of the window
        setJMenuBar(buildMenuBar());

        JLabel title = new JLabel("Student Grade Manager", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(title, BorderLayout.NORTH);

        // ---------- form (same as Step 6) ----------
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        nameField = new JTextField(15);
        marksField = new JTextField(5);
        nameField.setToolTipText("Full name of the student");
        marksField.setToolTipText("Whole number from 0 to 100");

        String[] departments = {"CSE", "EE", "ME", "Civil", "AI and DS"};
        deptBox = new JComboBox<>(departments);

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

        // CHANGED : buttons now sit under the form, on the left side
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 10, 0));
        JButton addButton = new JButton("Add Student");       // was "Compute Grade"
        JButton clearButton = new JButton("Clear");
        JButton deleteButton = new JButton("Delete Selected"); // NEW
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);

        JPanel formWithButtons = new JPanel(new BorderLayout(0, 15));
        formWithButtons.add(formPanel, BorderLayout.CENTER);
        formWithButtons.add(buttonPanel, BorderLayout.SOUTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
        leftPanel.add(formWithButtons, BorderLayout.NORTH);   // NORTH keeps it from stretching
        add(leftPanel, BorderLayout.WEST);

        // ---------- NEW : table in the CENTER ----------
        String[] columns = {"Name", "Marks", "Grade", "Department", "Program", "Hostel"};
        tableModel = new DefaultTableModel(columns, 0) {   // 0 rows to start
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;                              // read only table
            }
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? Integer.class : String.class; // marks sort as numbers
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);                // click a header to sort
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);   // gives scroll bars and headers
        scrollPane.setBorder(BorderFactory.createTitledBorder("Students"));
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 15));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // ---------- status bar ----------
        status = new JLabel("Ready");
        status.setBorder(BorderFactory.createEmptyBorder(5, 15, 8, 15));
        add(status, BorderLayout.SOUTH);

        // ---------- listeners ----------
        addButton.addActionListener(e -> addStudent());
        clearButton.addActionListener(e -> {
            clearForm();
            updateStatus("Form cleared");
        });
        deleteButton.addActionListener(e -> deleteSelected());
        marksField.addActionListener(e -> addStudent());
        deptBox.addActionListener(e ->
                status.setText("Department selected : " + deptBox.getSelectedItem()));

        // NEW : double click a row to see details.
        // MouseListener has 5 methods. MouseAdapter lets us override only the one we need.
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showDetails();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    // NEW : build the menus
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);                 // Alt + F opens this menu

        JMenuItem clearAllItem = new JMenuItem("Clear All Records");
        clearAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        clearAllItem.addActionListener(e -> clearAll());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(e -> confirmExit());

        fileMenu.add(clearAllItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Student Grade Manager\nBuilt with Java Swing", "About",
                JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    // NEW
    private void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();          // close this window
            System.exit(0);     // end the program
        }
    }

    // NEW
    private void clearAll() {
        if (tableModel.getRowCount() == 0) return;
        int choice = JOptionPane.showConfirmDialog(this,
                "Remove all records?", "Clear All", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            tableModel.setRowCount(0);
            updateStatus("All records cleared");
        }
    }

    // NEW
    private void showDetails() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) return;
        int row = table.convertRowIndexToModel(viewRow);
        StringBuilder sb = new StringBuilder();
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            sb.append(tableModel.getColumnName(col)).append(" : ")
              .append(tableModel.getValueAt(row, col)).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Student Details",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // CHANGED : computeGrade() became addStudent(). It now adds a row.
    private void addStudent() {
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

        String dept = (String) deptBox.getSelectedItem();
        String program = selectedProgram();
        String hostel = hostelCheck.isSelected() ? "Yes" : "No";
        String grade = gradeFor(marks);

        // NEW : add to the MODEL. The table updates itself automatically.
        tableModel.addRow(new Object[]{name, marks, grade, dept, program, hostel});

        clearForm();
        updateStatus("Added " + name);
    }

    // NEW
    private void deleteSelected() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) {
            showError("Select a row in the table first.");
            return;
        }
        // The table may be sorted, so convert the view index to the model index
        int modelRow = table.convertRowIndexToModel(viewRow);
        String name = (String) tableModel.getValueAt(modelRow, 0);

        int choice = JOptionPane.showConfirmDialog(this,
                "Delete " + name + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            tableModel.removeRow(modelRow);
            updateStatus("Deleted " + name);
        }
    }

    // NEW : summary computed from the model
    private void updateStatus(String prefix) {
        int count = tableModel.getRowCount();
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += (Integer) tableModel.getValueAt(i, 1);
        }
        String avg = count == 0 ? "-" : String.format("%.2f", total / count);
        status.setText(prefix + "   |   Students : " + count + "   |   Average marks : " + avg);
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
        nameField.requestFocus();
    }

    private void showError(String text) {
        JOptionPane.showMessageDialog(this, text, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentApp8().setVisible(true));
    }
}
