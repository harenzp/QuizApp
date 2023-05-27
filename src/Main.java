import com.formdev.flatlaf.FlatLightLaf;
import java.io.IOException;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            FlatLightLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#DD4A48" ) );
            FlatLightLaf.setup();
        } catch( Exception ex ) {
            System.err.println( "Failed" );
        }

        new HomePage();
    }

}