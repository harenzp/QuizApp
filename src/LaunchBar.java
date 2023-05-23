import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LaunchBar {

    private LibraryPage libraryPage;
    private LaunchPage launchPage;
    private JPanel panel, page;
    public LaunchBar(){


        JFrame frame = new JFrame("QuizUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu launchMenu = new JMenu("Launch");

        JMenuItem launchItem = new JMenuItem("Launch");
        launchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPage(LaunchPage.class);
            }
        });
        JMenuItem quizItem = new JMenuItem("Quiz");
        JMenuItem teamVersusItem = new JMenuItem("Team Versus");
        JMenuItem feedbackItem = new JMenuItem("Feedback");

        launchMenu.add(launchItem);
        launchMenu.add(quizItem);
        launchMenu.add(teamVersusItem);
        launchMenu.add(feedbackItem);


        JMenu libraryMenu = new JMenu("Library");
        JMenuItem libraryItem = new JMenuItem("Library");
        libraryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPage(LibraryPage.class);
            }
        });

        libraryMenu.add(libraryItem);

        JMenu roomsMenu = new JMenu("Rooms");
        JMenuItem roomsItem = new JMenuItem("Rooms");
        roomsMenu.add(roomsItem);

        JMenu reportsMenu = new JMenu("Reports");
        JMenuItem reportsItem = new JMenuItem("Reports");
        reportsMenu.add(reportsItem);

        JMenu resultsMenu = new JMenu("Results");
        JMenuItem resultsItem = new JMenuItem("Results");
        resultsMenu.add(resultsItem);

        menuBar.add(launchMenu);
        menuBar.add(libraryMenu);
        menuBar.add(roomsMenu);
        menuBar.add(reportsMenu);
        menuBar.add(resultsMenu);

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

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new LaunchBar();
    }

//    private void showLibraryPage() {
//
//        // bag o
//        if (libraryPage == null) {
//            libraryPage = new LibraryPage();
//        }
//        panel.removeAll();
//        panel.add(libraryPage);
//        panel.revalidate();
//        panel.repaint();
//    }
//
//    private void showLaunchPage() {
//
//        if (launchPage == null) {
//            launchPage = new LaunchPage();
//        }
//        panel.removeAll();
//        panel.add(launchPage);
//        panel.revalidate();
//        panel.repaint();
//    }

    // instead sa mag buhat og method taga page (showLaunchPage, showLibraryPage, etc.)
    // kay kani nalang showPage method na modawat og class parameter - christian
    private void showPage(Class<? extends JPanel> pageClass) {

        try {
            page = pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (page != null) {
            panel.removeAll();
            panel.add(page);
            panel.revalidate();
            panel.repaint();
        }
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