import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame {

    BufferedImage theLogo = ImageIO.read(new File("myImages/1.png"));
    Image scaledLogo = theLogo.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
    JLabel homeLogo = new JLabel(new ImageIcon(scaledLogo));

    JButton teacherButton = new JButton("Teacher Login");
    JButton studentButton = new JButton("Student Login");
    JLabel signUpLink = new JLabel("Sign up.");


    public HomePage() throws IOException {
        setTitle("QuizUP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.white);

        panel.add(left);
        panel.add(right);

        JLabel welcome = new JLabel("Welcome back!");
        JLabel dont = new JLabel("Don't have an account?");

        teacherButton.setSize(new Dimension(100, 100));

        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        teacherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        dont.setAlignmentX(Component.CENTER_ALIGNMENT);

        teacherButton.setBackground(Color.decode("#DD4A48"));
        teacherButton.setForeground(Color.white);
        studentButton.setBackground(Color.decode("#DD4A48"));
        studentButton.setForeground(Color.white);
        signUpLink.setForeground(Color.decode("#DD4A48"));

        signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teacherButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        studentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        left.add(Box.createVerticalGlue());
        left.add(welcome);
        left.add(Box.createRigidArea(new Dimension(0, 50)));
        left.add(teacherButton);
        left.add(Box.createRigidArea(new Dimension(0, 20)));
        left.add(studentButton);
        left.add(Box.createRigidArea(new Dimension(0, 100)));
        left.add(dont);
        left.add(signUpLink);
        left.add(Box.createVerticalGlue());

        // For the right panel po
        right.add(homeLogo, BorderLayout.CENTER);
        right.setBackground(Color.decode("#DD4A48"));

        //para ig click sa create an account button -Leonel
        signUpLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TeacherSignUp tsu = new TeacherSignUp();
                tsu.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        add(panel);
        setVisible(true);
    }
}
