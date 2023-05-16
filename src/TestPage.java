import javax.swing.*;
import java.awt.*;

public class TestPage extends JFrame {

    JLabel hi = new JLabel("hi po", SwingConstants.CENTER);

    public TestPage() {
        setTitle("Test Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(hi);

        setVisible(true);
    }

}
