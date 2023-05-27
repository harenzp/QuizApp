import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JFrame {

    private JProgressBar progressBar;
    private JLabel label;
    private Timer timer;
    private int progress;

    public LoadingScreen() {
        // create a frame with title "Loading..."
        super("Loading...");

        // create a panel for the progress bar
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // create a progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.decode("#DD4A48"));

        // create a label with text "Please wait while the quiz is loading..."
        label = new JLabel("Please wait while the quiz is loading...");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // add the progress bar and label to the panel
        panel.add(progressBar, BorderLayout.NORTH);
        panel.add(label, BorderLayout.CENTER);

        // add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // set the size of the frame
        setSize(1000, 600);

        // center the frame on the screen
        setLocationRelativeTo(null);

        // set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make the frame visible
        setVisible(true);

        // create a timer to update the progress bar
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 2;
                if (progress >= 500) {
                    progress = 100;
                    timer.stop();
                    dispose();
                }
                progressBar.setValue(progress);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // create an instance of the loading screen
        LoadingScreen loadingScreen = new LoadingScreen();

        // simulate some loading time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // dispose the loading screen
        loadingScreen.timer.stop();
        loadingScreen.dispose();
    }
}
