package util;



import java.awt.Toolkit;

// برای مدیریت صدا نوشتم 
public class SoundUtil {

    private SoundUtil() {
    }

    public static void success() {
        try {
            Toolkit.getDefaultToolkit().beep();
            Thread.sleep(120);
            Toolkit.getDefaultToolkit().beep();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    

    public static void failure() {
        try {
            Toolkit.getDefaultToolkit().beep();
            Thread.sleep(90);
            Toolkit.getDefaultToolkit().beep();
            Thread.sleep(90);
            Toolkit.getDefaultToolkit().beep();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
