import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateQuiz extends JPanel {
    private List<Question> questions;
    private JTextField quizTitleField;
    private JComboBox<String> categoryComboBox;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JPanel editQuestionPanel;
    private JPanel questionListPanel;

    public CreateQuiz() {
        questions = new ArrayList<>();
        createPanel();
    }

    public void createPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Quiz Setup Panel
        JPanel quizSetupPanel = new JPanel(new GridBagLayout());
        quizSetupPanel.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        quizTitleField = new JTextField("Untitled Quiz", 30);
        quizTitleField.setForeground(Color.gray);
        quizTitleField.setHorizontalAlignment(JTextField.CENTER);
        quizTitleField.setFont(quizTitleField.getFont().deriveFont(Font.BOLD, 20));
        quizTitleField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                quizTitleField.setText("");
                quizTitleField.setForeground(Color.black);
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        quizSetupPanel.add(quizTitleField, constraints);

        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>(new String[]{"Category 1", "Category 2", "Category 3"});

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(dateLabel.getFont().deriveFont(Font.BOLD));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy");
        dateSpinner.setEditor(dateEditor);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(timeLabel.getFont().deriveFont(Font.BOLD));
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm a");
        timeSpinner.setEditor(timeEditor);

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.setBackground(Color.white);
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(Color.white);
        datePanel.add(dateLabel);
        datePanel.add(dateSpinner);

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel.setBackground(Color.white);
        timePanel.add(timeLabel);
        timePanel.add(timeSpinner);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        quizSetupPanel.add(categoryPanel, constraints);

        constraints.gridx = 1;
        quizSetupPanel.add(datePanel, constraints);

        constraints.gridx = 2;
        quizSetupPanel.add(timePanel, constraints);

        JButton addQuestionButton = new JButton("+ Add a question");
        addQuestionButton.setBackground(Color.decode("#DD4A48"));
        addQuestionButton.setForeground(Color.white);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        quizSetupPanel.add(addQuestionButton, constraints);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });

        add(quizSetupPanel, BorderLayout.NORTH);

        // Edit Question Panel
        editQuestionPanel = new JPanel();
        editQuestionPanel.setBackground(Color.white);
        add(editQuestionPanel, BorderLayout.CENTER);

        // Question List Panel
        questionListPanel = new JPanel();
        questionListPanel.setLayout(new BoxLayout(questionListPanel, BoxLayout.Y_AXIS));
        questionListPanel.setBackground(Color.white);

        JScrollPane questionScrollPane = new JScrollPane(questionListPanel);
        add(questionScrollPane, BorderLayout.EAST);
    }

    public void addQuestion() {
        Question newQuestion = new Question(questions.size() + 1);

        // Add the question panel to the editQuestionPanel
        editQuestionPanel.removeAll();
        editQuestionPanel.add(newQuestion);
        editQuestionPanel.revalidate();
        editQuestionPanel.repaint();

        questions.add(newQuestion);

        // Create a new panel for the question in the question list
        JPanel questionListItemPanel = new JPanel();
        questionListItemPanel.setBackground(Color.white);
        questionListItemPanel.setLayout(new BorderLayout());
        JLabel questionLabel = new JLabel("Question " + questions.size());
        questionListItemPanel.add(questionLabel, BorderLayout.NORTH);

        questionListPanel.add(questionListItemPanel); // Add the question list item panel to the question list panel
        questionListPanel.revalidate();
        questionListPanel.repaint();
    }
}
