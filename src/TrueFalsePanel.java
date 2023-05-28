import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrueFalsePanel extends JPanel {
    private JTextField questionField;
    private JTextField pointsField;
    private JRadioButton trueButton;
    private JRadioButton falseButton;

    public TrueFalsePanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        JLabel questionLabel = new JLabel("Question:");
        questionField = new JTextField(30);

        JLabel pointsLabel = new JLabel("Points:");
        pointsField = new JTextField(10);

        JLabel optionsLabel = new JLabel("Options:");

        trueButton = new JRadioButton("True");
        falseButton = new JRadioButton("False");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueButton);
        buttonGroup.add(falseButton);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(questionLabel, constraints);

        constraints.gridx = 1;
        add(questionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(pointsLabel, constraints);

        constraints.gridx = 1;
        add(pointsField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(optionsLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(trueButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(falseButton, constraints);
    }

    public String getQuestion() {
        return questionField.getText();
    }

    public int getPoints() {
        try {
            return Integer.parseInt(pointsField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isTrue() {
        return trueButton.isSelected();
    }
}
