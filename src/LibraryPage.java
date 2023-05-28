import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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



        // Add ActionListener to the createQuizButton
        createQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentPage();
                LaunchBar launchBar = new LaunchBar();
                launchBar.showPage(CreateQuiz.class);
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

                String formattedQuizInfo = String.format("<html><div><strong>Title:</strong> <span style='float:left;'>%s</span></div><div><strong>Category: </strong><span style='text-align:center;'>%s</span></div><strong>Date: </strong><span style='float:right;'>%s</span></html>", title, category, date);

                JPanel quizPanel = new JPanel(new GridBagLayout());
                quizPanel.setBackground(Color.white);
                quizPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        editQuiz(title);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        quizPanel.setBackground(Color.decode("#FFDDDD"));
                        quizPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        quizPanel.setBackground(Color.white);
                        quizPanel.setCursor(Cursor.getDefaultCursor());
                    }
                });

                JLabel quizInfoLbl = new JLabel(formattedQuizInfo);
                quizInfoLbl.setForeground(Color.black);

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

        // Add ActionListener to the searchField
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                filterQuizzes(searchText);
            }
        });
    }

    private void closeCurrentPage() {
        Container container = getParent();
        while (container != null) {
            if (container instanceof JFrame) {
                ((JFrame) container).dispose();
                return;
            }
            container = container.getParent();
        }
    }
    private void filterQuizzes(String searchText) {
        Component[] components = getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel quizPanel = (JPanel) component;
                Component[] quizPanelComponents = quizPanel.getComponents();
                JLabel quizInfoLabel = null;

                for (Component quizPanelComponent : quizPanelComponents) {
                    if (quizPanelComponent instanceof JLabel) {
                        quizInfoLabel = (JLabel) quizPanelComponent;
                        break;
                    }
                }

                if (quizInfoLabel != null) {
                    String quizInfo = quizInfoLabel.getText();

                    if (quizInfo.toLowerCase().contains(searchText.toLowerCase())) {
                        quizPanel.setVisible(true);
                    } else {
                        quizPanel.setVisible(false);
                    }
                }
            }
        }
    }


    private void editQuiz(String title) {
        JDialog editDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Quiz", true);
        editDialog.setLayout(new BorderLayout());

        JTextField titleField = new JTextField(title);
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Category 1", "Category 2", "Category 3"});
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        dateSpinner.setEditor(dateEditor);
        timeSpinner.setEditor(timeEditor);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String editedTitle = titleField.getText();
                String editedCategory = (String) categoryComboBox.getSelectedItem();
                Date editedDate = (Date) dateSpinner.getValue();
                Date editedTime = (Date) timeSpinner.getValue();
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(editedDate);

                try {
                    DatabaseManager databaseManager = new DatabaseManager();
                    Connection connection = databaseManager.getDatabaseConnection();
                    String updateQuery = "UPDATE Quiz SET title = '" + editedTitle + "', category = '" + editedCategory + "', date = '" + formattedDate + "' WHERE title = '" + title + "'";
                    Statement updateStatement = connection.createStatement();
                    updateStatement.executeUpdate(updateQuery);

                    updateStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to update quiz.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Component[] components = getComponents();
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        JPanel quizPanel = (JPanel) component;
                        Component[] quizPanelComponents = quizPanel.getComponents();
                        for (Component panelComponent : quizPanelComponents) {
                            if (panelComponent instanceof JLabel) {
                                JLabel quizInfoLabel = (JLabel) panelComponent;
                                String quizInfo = quizInfoLabel.getText();
                                if (quizInfo.contains(title)) {
                                    String updatedQuizInfo = String.format("<html><div><strong>Title:</strong> <span style='float:left;'>%s</span></div><div><strong>Category: </strong><span style='text-align:center;'>%s</span></div><strong>Date: </strong><span style='float:right;'>%s</span></html>", editedTitle, editedCategory, formattedDate);
                                    quizInfoLabel.setText(updatedQuizInfo);
                                    break;
                                }
                            }
                        }
                    }
                }

                editDialog.dispose();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(editDialog, "Are you sure you want to delete this quiz?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        DatabaseManager databaseManager = new DatabaseManager();
                        Connection connection = databaseManager.getDatabaseConnection();
                        String deleteQuery = "DELETE FROM Quiz WHERE title = '" + title + "'";
                        Statement deleteStatement = connection.createStatement();
                        deleteStatement.executeUpdate(deleteQuery);

                        deleteStatement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to delete quiz.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Component[] components = getComponents();
                    for (Component component : components) {
                        if (component instanceof JPanel) {
                            JPanel quizPanel = (JPanel) component;
                            Component[] quizPanelComponents = quizPanel.getComponents();
                            for (Component panelComponent : quizPanelComponents) {
                                if (panelComponent instanceof JLabel) {
                                    JLabel quizInfoLabel = (JLabel) panelComponent;
                                    String quizInfo = quizInfoLabel.getText();
                                    if (quizInfo.contains(title)) {
                                        Container parent = quizPanel.getParent();
                                        parent.remove(quizPanel);
                                        parent.revalidate();
                                        parent.repaint();
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    editDialog.dispose();
                }
            }
        });

        JPanel editPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        editPanel.add(new JLabel("Title:"));
        editPanel.add(titleField);
        editPanel.add(new JLabel("Category:"));
        editPanel.add(categoryComboBox);
        editPanel.add(new JLabel("Date:"));
        editPanel.add(dateSpinner);
        editPanel.add(new JLabel("Time:"));
        editPanel.add(timeSpinner);
        editPanel.add(new JLabel());
        editPanel.add(saveButton);
        editPanel.add(new JLabel());
        editPanel.add(deleteButton);

        editDialog.add(editPanel, BorderLayout.CENTER);
        editDialog.pack();
        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }
}
