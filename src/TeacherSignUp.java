import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class TeacherSignUp extends JFrame {
    JTextField nameField;
    JTextField emailField;
    JPasswordField passwordField;
    JButton signUpButton;
    JLabel signInLabel, signInLink, studentLoginLabel, studentLoginLink;
    public TeacherSignUp() {
        // Set up the JFrame
        setTitle("New Teacher Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Create the input fields
        nameField = createTextFieldWithGuideText("Full Name");
        emailField = createTextFieldWithGuideText("Email");
        passwordField = createPasswordFieldWithGuideText("Password");

        // Create the "Sign Up" button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.decode("#DD4A48"));
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle sign up button click
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
            }
        });

        // Create the "Sign In" button
        signInLabel = new JLabel("Already have an account? ");
        signInLink = new JLabel("<html><u>Sign In</u></html>");
        signInLink.setForeground(Color.decode("#DD4A48"));
        signInLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HomePage loginPage = null;
                try {
                    loginPage = new HomePage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                loginPage.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        //Join Teacher's Room (For Student's Login)
        studentLoginLabel = new JLabel("Join a Teacher's Room here: ");
        studentLoginLink = new JLabel("<html><u>Student's Login</u></html>");
        studentLoginLink.setForeground(Color.decode("#DD4A48"));
        studentLoginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        studentLoginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // TODO: Implement redirection to sign in page
            }
        });

        // Add the input fields, buttons, and labels to the JFrame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signUpButton);
//        buttonPanel.setBackground(Color.decode("#F5EEDC"));

        JPanel signInPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signInPanel.add(signInLabel);
        signInPanel.add(signInLink);
//        signInPanel.setBackground(Color.decode("#F5EEDC"));

        JPanel studentLoginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        studentLoginPanel.add(studentLoginLabel);
        studentLoginPanel.add(studentLoginLink);
//        studentLoginPanel.setBackground(Color.decode("#F5EEDC"));

        JPanel inputPanel = new JPanel(new GridBagLayout());
//        inputPanel.setBackground(Color.decode("#F5EEDC"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        inputPanel.add(nameField, c);
        c.gridy = 1;
        inputPanel.add(emailField, c);
        c.gridy = 2;
        inputPanel.add(passwordField, c);
        c.gridy = 3;
        inputPanel.add(buttonPanel, c);
        c.gridy = 4;
        inputPanel.add(signInPanel, c);
        c.gridy = 5;
        inputPanel.add(studentLoginPanel, c);

        getContentPane().add(inputPanel, BorderLayout.CENTER);


        setVisible(true);
    }

    private JTextField createTextFieldWithGuideText(String guideText) {
        JTextField textField = new JTextField(20);
        textField.setText(guideText);
        textField.setForeground(Color.GRAY);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (textField.getText().equals(guideText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(guideText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private JPasswordField createPasswordFieldWithGuideText(String guideText) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setText(guideText);
        passwordField.setEchoChar((char) 0); // set echo char to 0 to show guide text as is
        passwordField.setForeground(Color.GRAY);
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(guideText)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*'); // set echo char to asterisk when user starts typing password
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(guideText)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*'); // set echo char to asterisk when user starts typing password
                    passwordField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(guideText);
                    passwordField.setEchoChar((char) 0); // set echo char to 0 to show guide text as is
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        return passwordField;
    }
//    public static void main(String[] args) {
//        new TeacherSignUp();
//    }
}
