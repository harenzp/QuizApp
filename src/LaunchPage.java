import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LaunchPage extends JPanel {
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
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LaunchBar launchBar = new LaunchBar();
                launchBar.showPage(CreateQuiz.class);
                dispose();
            }

            private void dispose() {
            }
        });
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
