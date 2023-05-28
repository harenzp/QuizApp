import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryPage extends JPanel {

    public LibraryPage() {
        createPanel();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        JLabel libraryLabel = new JLabel("Library");
        libraryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        libraryLabel.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(libraryLabel, constraints);

        JTextField searchField = new JTextField(20);
        searchField.setText("Search");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        JButton createQuizButton = new JButton("+ Create Quiz");
        createQuizButton.setBackground(Color.decode("#DD4A48"));
        createQuizButton.setForeground(Color.white);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchField);
        constraints.gridx = 1;
        add(searchPanel, constraints);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(createQuizButton);
        constraints.gridx = 2;
        add(buttonPanel, constraints);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(categoryLabel.getFont().deriveFont(Font.BOLD));
        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(dateLabel.getFont().deriveFont(Font.BOLD));

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        add(categoryLabel, constraints);

        constraints.gridx = 2;
        add(dateLabel, constraints);

        // Add ActionListener to the createQuizButton
        createQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LibraryPage.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new CreateQuiz());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Retrieve quizzes from the database
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            Connection connection = databaseManager.getDatabaseConnection();
            String query = "SELECT title, category, date FROM Quiz";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            int row = 2;
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String date = resultSet.getString("date");

                String formattedQuizInfo = String.format("<html><b>%s</b> - Category: %s - Date: %s</html>", title, category, date);

                JPanel quizPanel = new JPanel(new GridBagLayout());
                quizPanel.setBackground(Color.white);
                quizPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Handle click event
                        // Replace the code below with your desired logic
                        System.out.println("Clicked on quiz: " + title);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        quizPanel.setBackground(Color.decode("#FFDDDD")); // Light red background when hovered
                        quizPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        quizPanel.setBackground(Color.white);
                        quizPanel.setCursor(Cursor.getDefaultCursor());
                    }
                });

                JLabel quizInfoLbl = new JLabel(formattedQuizInfo);
                quizInfoLbl.setForeground(Color.black); // Set the color of the label to black

                GridBagConstraints panelConstraints = new GridBagConstraints();
                panelConstraints.gridx = 0;
                panelConstraints.gridy = 0;
                panelConstraints.anchor = GridBagConstraints.NORTHWEST;
                panelConstraints.insets = new Insets(5, 5, 5, 5);
                quizPanel.add(quizInfoLbl, panelConstraints);

                constraints.gridx = 0;
                constraints.gridy = row;
                constraints.gridwidth = 3;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                add(quizPanel, constraints);

                row++;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve quizzes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
