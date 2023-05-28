import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LaunchBar {
    private LibraryPage libraryPage;
    private LaunchPage launchPage;
    private ReportPage reportPage;
    private JPanel panel, page;
    public LaunchBar() {

        JFrame frame = new JFrame("QuizUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenu launchMenu = new JMenu("Launch");
        launchMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                showPage(LaunchPage.class);
            }
        });

        JMenu libraryMenu = new JMenu("Library");
        libraryMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                showPage(LibraryPage.class);
            }
        });

        JMenu roomsMenu = new JMenu("Rooms");
        JMenu reportsMenu = new JMenu("Reports");
        reportsMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                showPage(ReportPage.class);
            }
        });
        JMenu resultsMenu = new JMenu("Results");

        JMenu questionsBank = new JMenu("Questions Bank");
        questionsBank.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                showPage(QuestionsBank.class);
            }
        });

        menuBar.add(launchMenu);
        menuBar.add(libraryMenu);
        menuBar.add(roomsMenu);
        menuBar.add(reportsMenu);
        menuBar.add(resultsMenu);
        menuBar.add(questionsBank);

        // Set cursors
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Component LaunchPage = new LaunchPage();
        panel.add(LaunchPage);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // instead sa mag buhat og method taga page (showLaunchPage, showLibraryPage, etc.)
    // kay kani nalang showPage method na modawat og class parameter
    public void showPage(Class<? extends JPanel> pageClass) {

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