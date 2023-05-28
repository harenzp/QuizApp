import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ReportPage extends JPanel {
    private JLabel lblReports;
    private JTextField txtSearch;
    private JPanel topPanel, reportsPanel, searchPanel, middlePanel;

    public ReportPage() {
        createPanel();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        lblReports = new JLabel("Reports");
        lblReports.setFont(new Font("Arial", Font.BOLD, 24));
        lblReports.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblReports, constraints);

        txtSearch = new JTextField(20);
        txtSearch.setText("Search");
        txtSearch.setForeground(Color.GRAY);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Search")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Search");
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(txtSearch);
        constraints.gridx = 1;
        add(searchPanel, constraints);

        JLabel nameLabel = new JLabel("Name");
        JLabel dateLabel = new JLabel("Date");
        JLabel roomLabel = new JLabel("Room");
        JLabel typeLabel = new JLabel("Type");

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        add(dateLabel, constraints);

        constraints.gridx = 2;
        add(roomLabel, constraints);

        constraints.gridx = 3;
        add(typeLabel, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("QuizUP");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ReportPage());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
