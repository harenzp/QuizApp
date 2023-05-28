import javax.swing.*;
import java.awt.*;

public class Question extends JPanel {
    public enum QuestionType {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        IDENTIFICATION
    }

    private int questionNumber;
    private QuestionType questionType;
    private JPanel questionPanel;

    private String questionText;  // New field to store the question text
    private String answer;        // New field to store the answer

    public Question(int questionNumber) {
        this.questionNumber = questionNumber;
        this.questionType = QuestionType.MULTIPLE_CHOICE; // Set default question type to Multiple Choice
        createPanel();
    }

    public void createPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Create the main question frame panel
        JPanel questionFramePanel = new JPanel();
        questionFramePanel.setLayout(new BorderLayout());
        questionFramePanel.setBackground(Color.white);

        // Create the question type selection panel
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        typePanel.setBackground(Color.white);

        JLabel typeLabel = new JLabel("Type:");

        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Multiple Choice", "True/False", "Identification"});
        typePanel.add(typeLabel);
        typePanel.add(typeComboBox);

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            QuestionType type = getQuestionType(selectedType);
            setQuestionType(type);
            createQuestionPanel(type);
        });

        questionFramePanel.add(typePanel, BorderLayout.NORTH);

        // Create the question panel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setBackground(Color.white);

        questionFramePanel.add(questionPanel, BorderLayout.CENTER);

        // Create the save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#DD4A48"));
        saveButton.addActionListener(e -> saveQuestion());  // Save the question when the save button is clicked
        questionFramePanel.add(saveButton, BorderLayout.SOUTH);

        add(questionFramePanel);
    }

    private void createQuestionPanel(QuestionType type) {
        // Create the specific question panel based on the selected question type
        JPanel specificQuestionPanel;
        switch (type) {
            case MULTIPLE_CHOICE:
                specificQuestionPanel = new MultipleChoicePanel();
                break;
            case TRUE_FALSE:
                specificQuestionPanel = new TrueFalsePanel();
                break;
            case IDENTIFICATION:
                specificQuestionPanel = new IdentificationPanel();
                break;
            default:
                specificQuestionPanel = new JPanel(); // Default to a generic panel if the question type is not recognized
                break;
        }

        // Update the question panel with the specific question panel
        questionPanel.removeAll();
        questionPanel.add(specificQuestionPanel, BorderLayout.CENTER);
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    private QuestionType getQuestionType(String selectedType) {
        if (selectedType.equals("Multiple Choice")) {
            return QuestionType.MULTIPLE_CHOICE;
        } else if (selectedType.equals("True/False")) {
            return QuestionType.TRUE_FALSE;
        } else if (selectedType.equals("Identification")) {
            return QuestionType.IDENTIFICATION;
        }
        return null;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void saveQuestion() {
        // Save the question details based on the selected question type
//        if (questionPanel instanceof MultipleChoicePanel) {
//            MultipleChoicePanel mcPanel = (MultipleChoicePanel) questionPanel;
//            this.questionText = mcPanel.getQuestion();
//            this.answer = mcPanel.getAnswer();
//        } else if (questionPanel instanceof IdentificationPanel) {
//            IdentificationPanel idPanel = (IdentificationPanel) questionPanel;
//            this.questionText = idPanel.getQuestion();
//            this.answer = idPanel.getAnswer();
//        } else if (questionPanel instanceof TrueFalsePanel) {
//            TrueFalsePanel tfPanel = (TrueFalsePanel) questionPanel;
//            this.questionText = tfPanel.getQuestion();
//            this.answer = tfPanel.getAnswer();
//        }
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }
}
