import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPage extends JFrame {
    BufferedImage theLogo = ImageIO.read(new File("myImages/QuizUP_wayNawng.png"));
    Image scaledLogo = theLogo.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
    JLabel homeLogo = new JLabel(new ImageIcon(scaledLogo));

    JButton teacherButton = new JButton("Teacher Login");
    JButton studentButton = new JButton("Student Login");
    JButton createAccount = new JButton("Sign up.");


    public LoginPage() throws IOException {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(Color.white);

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
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        dont.setAlignmentX(Component.CENTER_ALIGNMENT);

        teacherButton.setBackground(Color.decode("#DD4A48"));
        teacherButton.setForeground(Color.white);
        studentButton.setBackground(Color.decode("#DD4A48"));
        studentButton.setForeground(Color.white);
        createAccount.setBackground(Color.decode("#F5EEDC"));
        createAccount.setForeground(Color.decode("#DD4A48"));
        createAccount.setBorderPainted(false);

        left.setBackground(Color.decode("#F5EEDC"));
        left.add(Box.createVerticalGlue());
        left.add(welcome);
        left.add(Box.createRigidArea(new Dimension(0, 50)));
        left.add(teacherButton);
        left.add(Box.createRigidArea(new Dimension(0, 20)));
        left.add(studentButton);
        left.add(Box.createRigidArea(new Dimension(0, 100)));
        left.add(dont);
        left.add(createAccount);
        left.add(Box.createVerticalGlue());

        // For the right panel po

        right.add(homeLogo, BorderLayout.CENTER);
        right.setBackground(Color.decode("#F5EEDC"));

        add(panel);
        setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        new LoginPage();
    }
}
