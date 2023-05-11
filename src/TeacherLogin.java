import javax.swing.*;
import java.awt.*;

class TeacherLogin extends JFrame {

    JLabel QuizUP = new JLabel("QuizUP");
    JPanel mainPanel, loginPanel;
    JLabel teacherLoginLabel, studentLoginLabel, studentLoginLink;
    JLabel resetLabel;
    JTextField emailTF;
    JPasswordField passwordPF;
    JButton loginButton;

    public TeacherLogin() {
        setTitle("Teacher Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        //set components
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        teacherLoginLabel = new JLabel("Teacher Login");
        resetLabel = new JLabel("Forgot password?");
        emailTF = new JTextField(20);
        passwordPF = new JPasswordField(20);
        loginButton = new JButton("Login");

        //quizUp Logo
        Font defaultFont = QuizUP.getFont();
        QuizUP.setFont(new Font(defaultFont.getFontName(), Font.BOLD, 50));
        teacherLoginLabel.setFont(new Font(defaultFont.getFontName(), defaultFont.getStyle(), 16));

        //colors
        loginButton.setBackground(Color.decode("#DD4A48"));
        loginButton.setForeground(Color.white);
        resetLabel.setForeground(Color.decode("#DD4A48"));

        // alignments
        QuizUP.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //login panel
        Dimension loginSize = new Dimension(300,400);
        loginPanel.setMaximumSize(loginSize);

        // button size
        loginButton.setMaximumSize(new Dimension(200, loginButton.getPreferredSize().height));

        // Focus Listeners
        // for email
        emailTF.setForeground(Color.GRAY);
        emailTF.setText("Email");
        emailTF.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailTF.getText().equals("Email")) {
                    emailTF.setText("");
                    emailTF.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailTF.getText().isEmpty()) {
                    emailTF.setForeground(Color.GRAY);
                    emailTF.setText("Email");
                }
            }
        });

        // for password
        passwordPF.setForeground(Color.GRAY);
        passwordPF.setText("Password");
        Character bullet = passwordPF.getEchoChar();
        passwordPF.setEchoChar((char) 0);
        passwordPF.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (passwordPF.getText().equals("Password")) {
                    passwordPF.setText("");
                    passwordPF.setEchoChar(bullet);
                    passwordPF.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (passwordPF.getText().isEmpty()) {
                    passwordPF.setForeground(Color.GRAY);
                    passwordPF.setEchoChar((char) 0);
                    passwordPF.setText("Password");

                }
            }
        });

        // wait sa
        JPanel studentLoginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        studentLoginLabel = new JLabel("Are you a student? Simply join a teacher's room here: ");
        studentLoginLink = new JLabel("<html><u>Student's Login</u></html>");
        studentLoginLink.setForeground(Color.decode("#DD4A48"));
        studentLoginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        studentLoginPanel.add(studentLoginLabel);
        studentLoginPanel.add(studentLoginLink);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(QuizUP);
        headerPanel.add(studentLoginPanel);
        headerPanel.setMaximumSize(new Dimension(headerPanel.getPreferredSize().width, headerPanel.getPreferredSize().height));

        // add components
        loginPanel.add(teacherLoginLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(emailTF);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(passwordPF);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(resetLabel);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(loginPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
        setVisible(true);

    }


}
