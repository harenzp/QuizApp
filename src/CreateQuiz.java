import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateQuiz extends JPanel {
    private DatabaseManager databaseManager;
    private List<Question> questions;
    private JTextField quizTitleField;
    private JComboBox<String> categoryComboBox;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private DefaultListModel<String> questionListModel;
    private JList<String> questionList;

    public CreateQuiz() {
        try {
            databaseManager = new DatabaseManager();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        categoryLabel.setFont(categoryLabel.getFont().deriveFont(Font.BOLD));
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

        // Question List Panel
        JPanel questionListPanel = new JPanel(new BorderLayout());
        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        JScrollPane questionScrollPane = new JScrollPane(questionList);
        questionListPanel.add(new JLabel("Questions"), BorderLayout.NORTH);
        questionListPanel.add(questionScrollPane, BorderLayout.CENTER);

        // Save Quiz Button
        JButton saveQuizButton = new JButton("Save Quiz");
        saveQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (areFieldsEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    saveQuiz();
                }
            }
        });
        // Add components to the main panel
        add(quizSetupPanel, BorderLayout.NORTH);
        add(questionListPanel, BorderLayout.CENTER);
        add(saveQuizButton, BorderLayout.SOUTH);
    }
    private boolean areFieldsEmpty() {
        String title = quizTitleField.getText().trim(); // Trim leading and trailing whitespace
        String category = categoryComboBox.getSelectedItem().toString();
        Date date = (Date) dateSpinner.getValue();
        Date time = (Date) timeSpinner.getValue();

        return title.isEmpty() || title.equals("Untitled Quiz") || category.isEmpty();
    }
    private void addQuestion() {
        JFrame questionFrame = new JFrame("Question");
        Question questionPanel = new Question(questionListModel);
        questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        questionFrame.getContentPane().add(questionPanel);
        questionFrame.setPreferredSize(new Dimension(500, 500));
        questionFrame.pack();
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setVisible(true);
    }

    private void saveQuiz() {
        String title = quizTitleField.getText();
        String category = categoryComboBox.getSelectedItem().toString();
        Date date = (Date) dateSpinner.getValue();
        Date time = (Date) timeSpinner.getValue();

        try {
            // Save quiz details to the database or any other data storage mechanism
            saveQuizDetails(title, category, date, time);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save quiz details.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if an error occurs
        }

//        // Save each question to the database or any other data storage mechanism
//        for (Question question : questions) {
//            saveQuestion(question);
//        }

        JOptionPane.showMessageDialog(null, "Quiz saved successfully!");
        resetQuiz();


    }

    private void saveQuizDetails(String title, String category, Date date, Date time) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        String formattedTime = timeFormat.format(time);

        databaseManager.saveQuizDetails(title, category, formattedDate, formattedTime);
    }

//    private void saveQuestion(Question question) {
//        try {
//            databaseManager.saveQuestion(question);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Failed to save question.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private void resetQuiz() {
        quizTitleField.setText("Untitled Quiz");
        quizTitleField.setForeground(Color.gray);
        categoryComboBox.setSelectedIndex(0);
        dateSpinner.setValue(new Date());
        timeSpinner.setValue(new Date());
        questions.clear();
        questionListModel.clear();
    }
}
