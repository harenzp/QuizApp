import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentEnter extends JFrame {
    private JPanel panel;
    private JPanel innerPanel;
    private JLabel nameLabel;
    private JTextField inputName;
    private JButton doneButton;

    public StudentEnter(String roomCode) {
        setTitle("Student Enter Screen");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel and set its layout to BorderLayout
        panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Create the inner panel and set its border
        innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(innerPanel, BorderLayout.CENTER);

        // Create the label
        nameLabel = new JLabel("Enter Your Name");
        GridBagConstraints nameLabelConstraints = new GridBagConstraints();
        nameLabelConstraints.gridx = 0;
        nameLabelConstraints.gridy = 0;
        nameLabelConstraints.anchor = GridBagConstraints.CENTER;
        nameLabelConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(nameLabel, nameLabelConstraints);

        // Create the text field for the name input
        inputName = new JTextField(20);
        GridBagConstraints inputNameConstraints = new GridBagConstraints();
        inputNameConstraints.gridx = 0;
        inputNameConstraints.gridy = 1;
        inputNameConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(inputName, inputNameConstraints);

        // Create the done button
        doneButton = new JButton("Done");
        doneButton.setBackground(Color.decode("#DD4A48"));
        GridBagConstraints doneButtonConstraints = new GridBagConstraints();
        doneButtonConstraints.gridx = 0;
        doneButtonConstraints.gridy = 2;
        doneButtonConstraints.gridwidth = 2;
        doneButtonConstraints.insets = new Insets(10, 10, 10, 10);
        innerPanel.add(doneButton, doneButtonConstraints);

        // Add ActionListener to the done button
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = inputName.getText();
                System.out.println("Room Code: " + roomCode);
                System.out.println("Name: " + name);
                // Logic to proceed to the room

                dispose(); // Close the StudentEnter screen
            }
        });

        setVisible(true);
    }
}
