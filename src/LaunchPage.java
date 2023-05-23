import javax.swing.*;
import java.awt.*;

public class LaunchPage extends JPanel{

    public LaunchPage() {
        setLayout(new GridLayout(1, 3));

        JLabel quiz = new JLabel("1");
        JLabel teamversus = new JLabel("2");
        JLabel feedback = new JLabel("333333");

        add(quiz);
        add(teamversus);
        add(feedback);
    }

}
