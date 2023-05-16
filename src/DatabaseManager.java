import java.sql.*;

class DatabaseManager {
    private final String databaseUrl;
    private final String username;
    private final String password;

    public DatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerAccount(String name, String email, String password) {
        String sql = "INSERT INTO accounts(name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
}