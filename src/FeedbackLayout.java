import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FeedbackLayout extends JPanel {

    public FeedbackLayout() {
        // create a panel with grid layout for the components
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        add(titleLabel, gbc);

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
        add(nameLabel, gbc);

        gbc.gridy = 2;
        add(subjectLabel, gbc);

        gbc.gridy = 3;
        add(quizNameLabel, gbc);

        gbc.gridy = 4;
        add(feedbackLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 0); // Add left padding to text fields
        add(nameField, gbc);

        gbc.gridy = 2;
        add(subjectField, gbc);

        gbc.gridy = 3;
        add(quizNameField, gbc);

        gbc.gridy = 4;
        add(new JScrollPane(feedbackArea), gbc);

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
        add(submitButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Launch Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LaunchPage launchPage = new LaunchPage();
            FeedbackLayout feedbackLayout = new FeedbackLayout();
            launchPage.add(feedbackLayout);

            frame.add(launchPage);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class LaunchPage extends JPanel {
    ImageIcon image1 = new ImageIcon("myImages/quiz.png");
    ImageIcon image2 = new ImageIcon("myImages/versus.png");
    ImageIcon image3 = new ImageIcon("myImages/feedback.png");
    ImageIcon image1Hover = new ImageIcon("myImages/quizHover.png");
    ImageIcon image2Hover = new ImageIcon("myImages/versusHover.png");
    ImageIcon image3Hover = new ImageIcon("myImages/feedbackHover.png");
    JLabel quiz = new JLabel("Quiz");
    JLabel versus = new JLabel("Team Versus");
    JLabel feedback = new JLabel("Feedback");

    public LaunchPage() {
        setLayout(new GridBagLayout());

        Font font = quiz.getFont();
        quiz.setFont(new Font(font.getFontName(), Font.PLAIN, 28));
        versus.setFont(new Font(font.getFontName(), Font.PLAIN, 28));
        feedback.setFont(new Font(font.getFontName(), Font.PLAIN, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100, 60, 0, 60); //padding

        JLabel label1 = new JLabel(image1);
        label1.addMouseListener(new ImageHoverListener(label1, image1, image1Hover));
        label1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        add(label1, gbc);

        JLabel label2 = new JLabel(image2);
        label2.addMouseListener(new ImageHoverListener(label2, image2, image2Hover));
        label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        add(label2, gbc);

        JLabel label3 = new JLabel(image3);
        label3.addMouseListener(new ImageHoverListener(label3, image3, image3Hover));
        label3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 2;
        add(label3, gbc);

        gbc.insets = new Insets(40, 60, 0, 60);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(quiz, gbc);

        gbc.gridx = 1;
        add(versus, gbc);

        gbc.gridx = 2;
        add(feedback, gbc);
    }

    private static class ImageHoverListener extends MouseAdapter {
        private final JLabel label;
        private final ImageIcon defaultImage;
        private final ImageIcon hoverImage;

        public ImageHoverListener(JLabel label, ImageIcon defaultImage, ImageIcon hoverImage) {
            this.label = label;
            this.defaultImage = defaultImage;
            this.hoverImage = hoverImage;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setIcon(hoverImage);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setIcon(defaultImage);
        }
    }
}
