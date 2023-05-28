import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Question extends JPanel {
    private DefaultListModel<String> questionListModel;
    private JPanel mainPanel, selectTypePanel, multipleChoicePanel, trueFalsePanel, shortAnswerPanel;
    private DatabaseManager databaseManager;

    // Components for multiple choice question panel
    private JTextField mcQuestionField, mcOption1Field, mcOption2Field, mcOption3Field, mcOption4Field;
    private JComboBox<String> mcCorrectOptionBox;

    // Components for true or false question panel
    private JTextField tfQuestionField;
    private JRadioButton trueRadioButton, falseRadioButton;

    // Components for short answer question panel
    private JTextField saQuestionField, saAnswerField;

    public Question(DefaultListModel<String> questionListModel) {
        try {
            databaseManager = new DatabaseManager();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.questionListModel = questionListModel;
        setLayout(new CardLayout());
        setPreferredSize(new Dimension(500, 300));

        selectTypePanel = createSelectTypePanel();
        multipleChoicePanel = createMultipleChoicePanel();
        trueFalsePanel = createTrueFalsePanel();
        shortAnswerPanel = createShortAnswerPanel();

        add(selectTypePanel, "selectType");
        add(multipleChoicePanel, "multipleChoice");
        add(trueFalsePanel, "trueFalse");
        add(shortAnswerPanel, "shortAnswer");
    }

    private JPanel createSelectTypePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setName("selectType");

        JButton multipleChoiceButton = new JButton("Multiple Choice");
        multipleChoiceButton.addActionListener(new SelectTypeButtonListener("multipleChoice"));
        panel.add(multipleChoiceButton);

        JButton trueFalseButton = new JButton("True/False");
        trueFalseButton.addActionListener(new SelectTypeButtonListener("trueFalse"));
        panel.add(trueFalseButton);

        JButton shortAnswerButton = new JButton("Short Answer");
        shortAnswerButton.addActionListener(new SelectTypeButtonListener("shortAnswer"));
        panel.add(shortAnswerButton);

        return panel;
    }

    private JPanel createMultipleChoicePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setName("multipleChoice");

        mcQuestionField = new JTextField();
        panel.add(new JLabel("Question:"));
        panel.add(mcQuestionField);

        mcOption1Field = new JTextField();
        panel.add(new JLabel("Option 1:"));
        panel.add(mcOption1Field);

        mcOption2Field = new JTextField();
        panel.add(new JLabel("Option 2:"));
        panel.add(mcOption2Field);

        mcOption3Field = new JTextField();
        panel.add(new JLabel("Option 3:"));
        panel.add(mcOption3Field);

        mcOption4Field = new JTextField();
        panel.add(new JLabel("Option 4:"));
        panel.add(mcOption4Field);

        mcCorrectOptionBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3", "Option 4"});
        panel.add(new JLabel("Correct Option:"));
        panel.add(mcCorrectOptionBox);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        panel.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener(panel));
        panel.add(saveButton);

        return panel;
    }

    private JPanel createTrueFalsePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setName("trueFalse");

        tfQuestionField = new JTextField();
        panel.add(new JLabel("Question:"));
        panel.add(tfQuestionField);

        trueRadioButton = new JRadioButton("True");
        falseRadioButton = new JRadioButton("False");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueRadioButton);
        buttonGroup.add(falseRadioButton);
        panel.add(trueRadioButton);
        panel.add(falseRadioButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        panel.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener(panel));
        panel.add(saveButton);

        return panel;
    }

    private JPanel createShortAnswerPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setName("shortAnswer");

        saQuestionField = new JTextField();
        panel.add(new JLabel("Question:"));
        panel.add(saQuestionField);

        saAnswerField = new JTextField();
        panel.add(new JLabel("Answer:"));
        panel.add(saAnswerField);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        panel.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener(panel));
        panel.add(saveButton);

        return panel;
    }

    private class SelectTypeButtonListener implements ActionListener {
        private String panelName;

        public SelectTypeButtonListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) getLayout();
            cardLayout.show(Question.this, panelName);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) getLayout();
            cardLayout.show(Question.this, "selectType");
        }
    }

    private class SaveButtonListener implements ActionListener {
        private JPanel currentPanel;

        public SaveButtonListener(JPanel panel) {
            this.currentPanel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String panelName = currentPanel.getName();
            if (panelName.equals("multipleChoice")) {
                saveMultipleChoiceQuestion();
            } else if (panelName.equals("trueFalse")) {
                saveTrueFalseQuestion();
            } else if (panelName.equals("shortAnswer")) {
                saveShortAnswerQuestion();
            }
        }

        private void saveMultipleChoiceQuestion() {
            String question = mcQuestionField.getText();
            String option1 = mcOption1Field.getText();
            String option2 = mcOption2Field.getText();
            String option3 = mcOption3Field.getText();
            String option4 = mcOption4Field.getText();
            String correctOption = mcCorrectOptionBox.getSelectedItem().toString();

            // Save the question to the database or any other data storage mechanism
            databaseManager.saveMultipleChoiceQuestion(question, option1, option2, option3, option4, correctOption);

            // Add the question to the question list
            String questionText = " " + question; // Modify the format as per your requirement
            questionListModel.addElement(questionText);

            JOptionPane.showMessageDialog(null, "Multiple choice question saved successfully!");
            clearFields();
        }

        private void saveTrueFalseQuestion() {
            String question = tfQuestionField.getText();
            boolean answer = trueRadioButton.isSelected();

            // Save the question to the database or any other data storage mechanism
            databaseManager.saveTrueFalseQuestion(question, answer);

            // Add the question to the question list
            String questionText = " " + question; // Modify the format as per your requirement
            questionListModel.addElement(questionText);

            JOptionPane.showMessageDialog(null, "True/false question saved successfully!");
            clearFields();
        }

        private void saveShortAnswerQuestion() {
            String question = saQuestionField.getText();
            String answer = saAnswerField.getText();

            // Save the question to the database or any other data storage mechanism
            databaseManager.saveShortAnswerQuestion(question, answer);

            // Add the question to the question list
            String questionText = " " + question; // Modify the format as per your requirement
            questionListModel.addElement(questionText);

            JOptionPane.showMessageDialog(null, "Short answer question saved successfully!");
            clearFields();
        }

        private void clearFields() {
            Component[] components = currentPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
        }
    }

}
