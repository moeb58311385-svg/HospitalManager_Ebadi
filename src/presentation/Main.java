package presentation;

import java.util.Locale;
import javax.swing.SwingUtilities;
import presentation.view.MainFrame;
//import javax.swing.*;

public class Main {

    public static void main(String[] args) {
    	Locale.setDefault(new Locale("fa", "IR"));
    	//پشتیبانی از زبون فارسی
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            //نمایش فرم اصلی پروژه ام
            mainFrame.setVisible(true);
        });
    }
}