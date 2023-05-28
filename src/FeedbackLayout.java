import javax.swing.*;
import java.awt.*;

public class FeedbackLayout extends JFrame {

    public FeedbackLayout() {
        // create a frame with title "Feedback"
        super("Feedback");

        // create a panel with grid layout for the components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // create a GridBagConstraints instance
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        // create the "QuizUp" label
        JLabel titleLabel = new JLabel("QuizUp");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // add the titleLabel to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // create labels and text fields
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

        // add labels and text fields to the panel using GridBagConstraints
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
        gbc.insets = new Insets(5, 10, 5, 0); // Add left padding to text fields
        panel.add(nameField, gbc);

        gbc.gridy = 2;
        panel.add(subjectField, gbc);

        gbc.gridy = 3;
        panel.add(quizNameField, gbc);

        gbc.gridy = 4;
        panel.add(new JScrollPane(feedbackArea), gbc);

        // create a submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#DD4A48"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            // handle submit button action
            String name = nameField.getText();
            String subject = subjectField.getText();
            String quizName = quizNameField.getText();
            String feedback = feedbackArea.getText();

            // perform feedback submission logic here

            // display a confirmation message
            JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
        });

        // add the submit button to the panel
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // add the panel to the frame
        add(panel);

        // set the size of the frame
        setSize(1000, 600);

        // set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // center the frame on the screen
        setLocationRelativeTo(null);

        // make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // create an instance of the FeedbackLayout
        SwingUtilities.invokeLater(FeedbackLayout::new);
    }
}
