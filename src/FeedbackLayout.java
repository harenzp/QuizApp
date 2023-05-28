import javax.swing.*;
import java.awt.*;

public class FeedbackLayout extends JPanel {

    public FeedbackLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel titleLabel = new JLabel("QuizUp");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField nameField = new JTextField(20);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField subjectField = new JTextField(20);

        JLabel quizNameLabel = new JLabel("Quiz Name:");
        quizNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField quizNameField = new JTextField(20);

        JLabel feedbackLabel = new JLabel("Feedback Message:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextArea feedbackArea = new JTextArea(5, 20);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        gbc.gridy = 2;
        panel.add(subjectLabel, gbc);

        gbc.gridy = 3;
        panel.add(quizNameLabel, gbc);

        gbc.gridy = 4;
        panel.add(feedbackLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 0);
        panel.add(nameField, gbc);

        gbc.gridy = 2;
        panel.add(subjectField, gbc);

        gbc.gridy = 3;
        panel.add(quizNameField, gbc);

        gbc.gridy = 4;
        panel.add(new JScrollPane(feedbackArea), gbc);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#DD4A48"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String subject = subjectField.getText();
            String quizName = quizNameField.getText();
            String feedback = feedbackArea.getText();

            // Perform feedback submission logic here

            JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new FeedbackLayout());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
