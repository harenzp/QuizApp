import javax.swing.*;
import java.awt.*;

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
    }

}
