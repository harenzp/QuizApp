import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RoomPage extends JPanel {
    private JTextField roomCodeField;
    private JLabel studentCountLabel;
    private DefaultListModel<String> pendingListModel;
    private DefaultListModel<String> admittedListModel;
    private JList<String> pendingStudentsList;
    private JList<String> admittedStudentsList;
    private List<String> pendingStudents;
    private List<String> admittedStudents;

    public RoomPage() {
        pendingStudents = new ArrayList<>();
        admittedStudents = new ArrayList<>();
        createPanel();
        addDefaultNames();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        // Room Code Panel
        JPanel roomCodePanel = new JPanel(new BorderLayout());
        roomCodePanel.setBackground(Color.white);

        JLabel roomCodeLabel = new JLabel("Room Code:");
        roomCodeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomCodeLabel.setForeground(Color.black);
        roomCodePanel.add(roomCodeLabel, BorderLayout.WEST);

        roomCodeField = new JTextField(10);
        roomCodeField.setEditable(false);
        roomCodeField.setFont(new Font("Arial", Font.PLAIN, 16));
        roomCodePanel.add(roomCodeField, BorderLayout.CENTER);

        JButton generateCodeButton = new JButton("Generate Code");
        generateCodeButton.setBackground(Color.decode("#DD4A48"));
        generateCodeButton.setForeground(Color.white);
        generateCodeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        generateCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRoomCode();
            }
        });
        roomCodePanel.add(generateCodeButton, BorderLayout.EAST);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(roomCodePanel, constraints);

        // Student Count Panel
        JPanel studentCountPanel = new JPanel(new BorderLayout());
        studentCountPanel.setBackground(Color.white);

        JLabel studentCountTextLabel = new JLabel("Student Count:");
        studentCountTextLabel.setFont(new Font("Arial", Font.BOLD, 16));
        studentCountTextLabel.setForeground(Color.black);
        studentCountPanel.add(studentCountTextLabel, BorderLayout.WEST);

        studentCountLabel = new JLabel("0");
        studentCountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        studentCountPanel.add(studentCountLabel, BorderLayout.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(studentCountPanel, constraints);

        // Admitted Students Panel
        JPanel admittedStudentsPanel = new JPanel(new BorderLayout());
        admittedStudentsPanel.setBorder(BorderFactory.createTitledBorder("Admitted Students"));
        admittedStudentsPanel.setPreferredSize(new Dimension(300, 300)); // Set preferred size for enlargement

        // Pending Students Panel
        JPanel pendingStudentsPanel = new JPanel(new BorderLayout());
        pendingStudentsPanel.setBorder(BorderFactory.createTitledBorder("Pending Students"));
        pendingStudentsPanel.setPreferredSize(new Dimension(300, 300)); // Set preferred size for enlargement

        // Bottom Buttons Panel for Pending Students
        JPanel pendingButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton admitButton = new JButton("Admit");
        admitButton.setBackground(Color.decode("#DD4A48"));
        admitButton.setForeground(Color.white);
        JButton declineButton = new JButton("Decline");
        declineButton.setBackground(Color.decode("#DD4A48"));
        declineButton.setForeground(Color.white);
        pendingButtonsPanel.add(admitButton);
        pendingButtonsPanel.add(declineButton);

        // Pending Students List
        pendingListModel = new DefaultListModel<>();
        pendingStudentsList = new JList<>(pendingListModel);
        pendingStudentsPanel.add(new JScrollPane(pendingStudentsList), BorderLayout.CENTER);
        pendingStudentsPanel.add(pendingButtonsPanel, BorderLayout.SOUTH);

        // Bottom Buttons Panel for Admitted Students
        JPanel admittedButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.decode("#DD4A48"));
        removeButton.setForeground(Color.white);
        admittedButtonsPanel.add(removeButton);

        // Admitted Students List
        admittedListModel = new DefaultListModel<>();
        admittedStudentsList = new JList<>(admittedListModel);
        admittedStudentsPanel.add(new JScrollPane(admittedStudentsList), BorderLayout.CENTER);
        admittedStudentsPanel.add(admittedButtonsPanel, BorderLayout.SOUTH);

        // Main layout constraints for panels
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        add(pendingStudentsPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(admittedStudentsPanel, constraints);

        // Button actions
        admitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admitStudent();
            }
        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declineStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAdmittedStudent();
            }
        });
    }

    private void generateRoomCode() {
        // Generate a random 4-digit room code
        Random random = new Random();
        int roomCode = 1000 + random.nextInt(9000);
        roomCodeField.setText(String.valueOf(roomCode));
    }

    private void admitStudent() {
        int selectedIndex = pendingStudentsList.getSelectedIndex();
        if (selectedIndex != -1) {
            String student = pendingListModel.getElementAt(selectedIndex);
            pendingListModel.removeElementAt(selectedIndex);
            admittedListModel.addElement(student);
            studentCountLabel.setText(String.valueOf(admittedListModel.size()));
        }
    }

    private void declineStudent() {
        int selectedIndex = pendingStudentsList.getSelectedIndex();
        if (selectedIndex != -1) {
            pendingListModel.removeElementAt(selectedIndex);
        }
    }

    private void removeAdmittedStudent() {
        int selectedIndex = admittedStudentsList.getSelectedIndex();
        if (selectedIndex != -1) {
            admittedListModel.removeElementAt(selectedIndex);
            studentCountLabel.setText(String.valueOf(admittedListModel.size()));
        }
    }

    private void addDefaultNames() {
        String[] pendingNames = {"Shakira", "Jane", "Ponyo", "Klie", "Bran"};
        String[] admittedNames = {"Sheila", "Nathan", "Josh"};

        for (String name : pendingNames) {
            pendingListModel.addElement(name);
            pendingStudents.add(name);
        }

        for (String name : admittedNames) {
            admittedListModel.addElement(name);
            admittedStudents.add(name);
        }

        studentCountLabel.setText(String.valueOf(admittedListModel.size()));
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Room Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(new RoomPage());
        frame.setVisible(true);
    }
}
