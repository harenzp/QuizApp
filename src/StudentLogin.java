import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StudentLogin extends JFrame {

    private JPanel panel;
    private JPanel innerPanel;
    private JLabel label;
    private JTextField codeField;
    private JButton joinButton;

    public StudentLogin() {
        setTitle("Student Login Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        //Create the main panel and set its layout to BorderLayout
        panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        //Create the inner panel and set its border
        innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(innerPanel, BorderLayout.CENTER);

        //Create the label
        label = new JLabel("Room Code:");
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(label, labelConstraints);

        //Create the text field for the room code input
        codeField = new JTextField(20);
        GridBagConstraints codeFieldConstraints = new GridBagConstraints();
        codeFieldConstraints.gridx = 0;
        codeFieldConstraints.gridy = 1;
        codeFieldConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(codeField, codeFieldConstraints);

        //Create the join button
        joinButton = new JButton("Join");
        joinButton.setPreferredSize(codeField.getPreferredSize()); //using the setPreferredSize method, passing the preferredSize of the codeField object as an argument.
        joinButton.setBackground(Color.decode("#DD4A48"));
        GridBagConstraints joinButtonConstraints = new GridBagConstraints();
        joinButtonConstraints.gridx = 0;
        joinButtonConstraints.gridy = 2;
        joinButtonConstraints.gridwidth = 2;
        joinButtonConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(joinButton, joinButtonConstraints);


        // Add ActionListener to the join button
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomCode = codeField.getText();
                StudentEnter enterScreen = new StudentEnter(roomCode);
                dispose(); // Close the Studentlogin screen
            }
        });


        setVisible(true);
    }

//    public static void main(String[] args) {
//        new StudentLogin();
//    }
}
