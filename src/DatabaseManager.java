import java.sql.*;

class DatabaseManager {
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
}