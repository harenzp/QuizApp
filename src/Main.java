import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize Laf hehe" );
        }

        new HomePage();
    }

}