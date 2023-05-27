import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

// wala pa mahuman ang layout ug ang pag retrieve pero mo work na ang pag save sa db

public class QuestionsBank extends JPanel{
    private JFrame frame;
    private JPanel mainPanel, createPanel, selectTypePanel, multipleChoicePanel, trueFalsePanel, shortAnswerPanel;
    private DatabaseManager databaseManager;

    // Components for multiple choice question panel
    private JTextField mcQuestionField, mcOption1Field, mcOption2Field, mcOption3Field, mcOption4Field;
    private JComboBox<String> mcCorrectOptionBox;

    // Components for true or false question panel
    private JTextField tfQuestionField;
    private JRadioButton trueRadioButton, falseRadioButton;

    // Components for short answer question panel
    private JTextField saQuestionField, saAnswerField;

    public QuestionsBank() {
        try {
            databaseManager = new DatabaseManager();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        createPanel = new JPanel();
        JButton createButton = new JButton("Create a New Question");
        createButton.addActionListener(new CreateButtonListener());
        createPanel.add(createButton);

        selectTypePanel = createSelectTypePanel();
        multipleChoicePanel = createMultipleChoicePanel();
        trueFalsePanel = createTrueFalsePanel();
        shortAnswerPanel = createShortAnswerPanel();

        mainPanel.add(createPanel, "create");
        mainPanel.add(selectTypePanel, "selectType");
        mainPanel.add(multipleChoicePanel, "multipleChoice");
        mainPanel.add(trueFalsePanel, "trueFalse");
        mainPanel.add(shortAnswerPanel, "shortAnswer");

        add(mainPanel);
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

    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "selectType");
        }
    }

    private class SelectTypeButtonListener implements ActionListener {
        private String panelName;

        public SelectTypeButtonListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, panelName);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "create");
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

            databaseManager.saveMultipleChoiceQuestion(question, option1, option2, option3, option4, correctOption);
            JOptionPane.showMessageDialog(null, "Multiple choice question saved successfully!");
            clearFields();
        }

        private void saveTrueFalseQuestion() {
            String question = tfQuestionField.getText();
            boolean answer = trueRadioButton.isSelected();

            databaseManager.saveTrueFalseQuestion(question, answer);
            JOptionPane.showMessageDialog(null, "True/false question saved successfully!");
            clearFields();
        }

        private void saveShortAnswerQuestion() {
            String question = saQuestionField.getText();
            String answer = saAnswerField.getText();

            databaseManager.saveShortAnswerQuestion(question, answer);
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