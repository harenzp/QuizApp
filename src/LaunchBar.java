import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LaunchBar {

    private LibraryPage libraryPage;
    private JFrame frame;
    public LaunchBar(){
        libraryPage = new LibraryPage();


        JFrame frame = new JFrame("QuizUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JMenuBar menuBar = new JMenuBar();

        JMenu launchMenu = new JMenu("Launch");

        JMenuItem quizItem = new JMenuItem("Quiz");
        JMenuItem teamVersusItem = new JMenuItem("Team Versus");
        JMenuItem feedbackItem = new JMenuItem("Feedback");

        launchMenu.add(quizItem);
        launchMenu.add(teamVersusItem);
        launchMenu.add(feedbackItem);

        // Set colors
        menuBar.setBackground(Color.decode("#DD4A48"));
        launchMenu.setBackground(Color.decode("#DD4A48"));
        launchMenu.setForeground(Color.white);
        quizItem.setBackground(Color.decode("#DD4A48"));
        quizItem.setForeground(Color.white);
        teamVersusItem.setBackground(Color.decode("#DD4A48"));
        teamVersusItem.setForeground(Color.white);
        feedbackItem.setBackground(Color.decode("#DD4A48"));
        feedbackItem.setForeground(Color.white);

        // Set cursors
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        launchMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        quizItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teamVersusItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feedbackItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add hover effect to menu items
        addHoverEffect(quizItem);
        addHoverEffect(teamVersusItem);
        addHoverEffect(feedbackItem);

        JMenu libraryMenu = new JMenu("Library");
        JMenuItem libraryItem = new JMenuItem("Library");
        libraryItem.setMargin(new Insets(0, 100, 0, 90)); // Adjust the spacing here
        libraryMenu.add(libraryItem);

        JMenu roomsMenu = new JMenu("Rooms");
        JMenuItem roomsItem = new JMenuItem("Rooms");
        roomsItem.setMargin(new Insets(0, 100, 0, 90)); // Adjust the spacing here
        roomsMenu.add(roomsItem);

        JMenu reportsMenu = new JMenu("Reports");
        JMenuItem reportsItem = new JMenuItem("Reports");
        reportsItem.setMargin(new Insets(0, 100, 0, 90)); // Adjust the spacing here
        reportsMenu.add(reportsItem);

        JMenu resultsMenu = new JMenu("Results");
        JMenuItem resultsItem = new JMenuItem("Results");
        resultsItem.setMargin(new Insets(0, 100, 0, 90)); // Adjust the spacing here
        resultsMenu.add(resultsItem);

        menuBar.add(launchMenu);
        menuBar.add(libraryMenu);
        menuBar.add(roomsMenu);
        menuBar.add(reportsMenu);
        menuBar.add(resultsMenu);


        JPanel panel = new JPanel(new GridLayout(1, 2));
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.white);

        JLabel leftLabel = new JLabel("Left Panel");
        leftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(Box.createVerticalStrut(50));
        left.add(leftLabel);

        JLabel rightLabel = new JLabel("Right Panel");

        rightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        right.add(Box.createVerticalStrut(50));
        right.add(rightLabel, BorderLayout.NORTH);


        panel.add(left);
        panel.add(right);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void createLibraryPage() {
        libraryPage = new LibraryPage();
    }

    private void showLibraryPage() {
        frame.getContentPane().remove(1); // Remove the existing content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(libraryPage.getPanel(), BorderLayout.CENTER);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
    private static void addHoverEffect(JMenuItem menuItem) {
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(Color.decode("#FF6B6A"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(Color.decode("#DD4A48"));
            }
        });
    }
}