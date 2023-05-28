import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FeedbackLayout extends JFrame {

    private JTextField nameField;
    private JTextField subjectField;
    private JTextField quizNameField;
    private JTextArea feedbackArea;

    public FeedbackLayout() {
        // Existing code...

        // create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameField = new JTextField(20);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 12));
        subjectField = new JTextField(20);

        JLabel quizNameLabel = new JLabel("Quiz Name:");
        quizNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        quizNameField = new JTextField(20);

        JLabel feedbackLabel = new JLabel("Feedback Message:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 12));
        feedbackArea = new JTextArea(5, 20);

        // Existing code...

        // create an update button
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(Color.decode("#FF8C00"));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle update button action
                String name = nameField.getText();
                String subject = subjectField.getText();
                String quizName = quizNameField.getText();
                String feedback = feedbackArea.getText();

                // perform update logic here
                // For demonstration purposes, let's just display the updated feedback
                String updatedFeedback = "Name: " + name + "\n"
                        + "Subject: " + subject + "\n"
                        + "Quiz Name: " + quizName + "\n"
                        + "Feedback Message: " + feedback;

                JOptionPane.showMessageDialog(FeedbackLayout.this, "Feedback updated:\n\n" + updatedFeedback);
            }
        });

        // create a delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.decode("#DD4A48"));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle delete button action
                int choice = JOptionPane.showConfirmDialog(FeedbackLayout.this,
                        "Are you sure you want to delete this feedback?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // perform delete logic here
                    clearFields();
                    JOptionPane.showMessageDialog(FeedbackLayout.this, "Feedback deleted!");
                }
            }
        });

        // create a panel for the update and delete buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // add the button panel to the main panel
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // create a save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#008000"));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle save button action
                String name = nameField.getText();
                String subject = subjectField.getText();
                String quizName = quizNameField.getText();
                String feedback = feedbackArea.getText();

                // perform save logic here
                saveFeedbackToFile(name, subject, quizName, feedback);

                // display a confirmation message
                JOptionPane.showMessageDialog(FeedbackLayout.this, "Feedback saved!");
            }
        });

        // add the save button to the panel
        gbc.gridy = 7;
        panel.add(saveButton, gbc);
    }

    private void clearFields() {
        nameField.setText("");
        subjectField.setText("");
        quizNameField.setText("");
        feedbackArea.setText("");
    }

    private void saveFeedbackToFile(String name, String subject, String quizName, String feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Subject: " + subject);
            writer.newLine();
            writer.write("Quiz Name: " + quizName);
            writer.newLine();
            writer.write("Feedback Message: " + feedback);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Existing code...

    public static void main(String[] args) {
        // create an instance of the FeedbackLayout
        SwingUtilities.invokeLater(FeedbackLayout::new);
    }
}
