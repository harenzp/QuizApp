import java.sql.*;

public class DatabaseManager {
    private final String databaseUrl;
    private final String username;
    private final String password;
    Connection connection;

    public DatabaseManager() throws SQLException {
        this.databaseUrl = "jdbc:mysql://localhost:3306/quizup";
        this.username = "root";
        this.password = "Fsociety05";

        connection = getDatabaseConnection();
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerAccount(String name, String email, String password) {
        String sql = "INSERT INTO accounts(name, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to register account: " + e.getMessage());
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM accounts WHERE email = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Return true if a matching user was found
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean saveMultipleChoiceQuestion(String question, String option1, String option2, String option3, String option4, String correctOption) {
        String sql = "INSERT INTO multiple_choice(question, option1, option2, option3, option4, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, question);
            statement.setString(2, option1);
            statement.setString(3, option2);
            statement.setString(4, option3);
            statement.setString(5, option4);
            statement.setString(6, correctOption);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save multiple-choice question: " + e.getMessage());
            return false;
        }
    }

    public boolean saveTrueFalseQuestion(String question, boolean answer) {
        String sql = "INSERT INTO true_false(question, answer) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, question);
            statement.setBoolean(2, answer);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save true/false question: " + e.getMessage());
            return false;
        }
    }

    public boolean saveShortAnswerQuestion(String question, String answer) {
        String sql = "INSERT INTO short_answer(question, answer) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, question);
            statement.setString(2, answer);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save short answer question: " + e.getMessage());
            return false;
        }
    }


    public boolean saveQuizDetails(String title, String category, String date, String time) {
        String sql = "INSERT INTO Quiz (title, category, date, time) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, category);
            statement.setString(3, date);
            statement.setString(4, time);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save quiz details: " + e.getMessage());
            return false;
        }
    }
    public boolean saveQuestion(int quizId, String questionText) {
        String sql = "INSERT INTO Question (quiz_id, question_text) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quizId);
            statement.setString(2, questionText);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save question: " + e.getMessage());
            return false;
        }
    }
}

