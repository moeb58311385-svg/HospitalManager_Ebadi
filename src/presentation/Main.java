Manapackage presentation;

import java.util.Locale;
import javax.swing.SwingUtilities;
import presentation.view.MainFrame;
//import javax.swing.*;

public class Main {

    public static void main(String[] args) {
    	Locale.setDefault(new Locale("fa", "IR"));
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}