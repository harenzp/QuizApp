import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoicePanel extends JPanel {
    private JTextField questionField;
    private JTextField pointsField;
    private List<JPanel> optionPanels;
    private List<JTextField> optionFields;
    private List<JButton> removeOptionButtons;
    private JButton addOptionButton;
    private ButtonGroup optionButtonGroup; // New addition

    public MultipleChoicePanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        optionButtonGroup = new ButtonGroup(); // Initialize the button group

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        JLabel questionLabel = new JLabel("Question:");
        questionField = new JTextField(30);

        JLabel pointsLabel = new JLabel("Points:");
        pointsField = new JTextField(10);

        JLabel optionsLabel = new JLabel("Options:");
        optionPanels = new ArrayList<>();
        optionFields = new ArrayList<>();
        removeOptionButtons = new ArrayList<>();

        addOptionButton = new JButton("Add Option");

        addOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOptionField();
            }
        });

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

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(addOptionButton, constraints);

        addOptionField(); // Add initial option field
        addOptionField(); // Add second option field
        addOptionField(); // Add third option field
        addOptionField();// Add fourth option field

    }

    private void addOptionField() {
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPanel.setBackground(Color.white);

        JTextField optionField = new JTextField(30);
        optionFields.add(optionField);

        JRadioButton optionRadioButton = new JRadioButton(); // Create a radio button for the option
        optionButtonGroup.add(optionRadioButton); // Add the radio button to the button group

        JButton removeOptionButton = new JButton("x");
        removeOptionButton.setMargin(new Insets(1, 4, 1, 4));
        removeOptionButton.setBackground(Color.RED);
        removeOptionButton.setForeground(Color.WHITE);
        removeOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeOptionField(optionPanel, optionField, optionRadioButton, removeOptionButton);
            }
        });
        removeOptionButtons.add(removeOptionButton);

        optionPanel.add(optionRadioButton); // Add the radio button to the option panel
        optionPanel.add(optionField);
        optionPanel.add(removeOptionButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = optionPanels.size() + 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(5, 0, 0, 0);

        add(optionPanel, constraints);

        optionPanels.add(optionPanel);

        revalidate();
        repaint();
    }

    private void removeOptionField(JPanel optionPanel, JTextField optionField,
                                   JRadioButton optionRadioButton, JButton removeOptionButton) {
        int index = optionPanels.indexOf(optionPanel);
        if (index >= 0) {
            optionPanels.remove(optionPanel);
            optionFields.remove(optionField);
            removeOptionButtons.remove(removeOptionButton);

            remove(optionPanel);

            optionButtonGroup.remove(optionRadioButton); // Remove the radio button from the button group

            revalidate();
            repaint();
        }
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

    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        for (JTextField optionField : optionFields) {
            options.add(optionField.getText());
        }
        return options;
    }

    public String getCorrectAnswer() {
        for (int i = 0; i < optionPanels.size(); i++) {
            JPanel optionPanel = optionPanels.get(i);
            JRadioButton optionRadioButton = (JRadioButton) optionPanel.getComponent(0);
            if (optionRadioButton.isSelected()) {
                return optionFields.get(i).getText();
            }
        }
        return null; // No correct answer selected
    }
}
