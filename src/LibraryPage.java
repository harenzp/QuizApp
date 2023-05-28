import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LibraryPage extends JPanel{

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
        JLabel categoryLabel = new JLabel("Category");
        JLabel modifiedLabel = new JLabel("Modified");

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        add(categoryLabel, constraints);

        constraints.gridx = 2;
        add(modifiedLabel, constraints);

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


    }

}
