package util;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//برای ساختن لاگ در مرحله آخر اضافه کردم
public class AppLogger {

    private static final String LOG_FILE_PATH = "hospital.log";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    private AppLogger() {
    }

    private static synchronized void write(String level, String message) {
        String zaman = LocalDateTime.now().format(FORMATTER);
        String line = "[" + zaman + "] [" + level + "] " + message;

        try (FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(line);
        } catch (IOException e) {
            System.out.println("خطا در نوشتن فایل لاگ: " + e.getMessage());
        }
    }

    public static void info(String message) {
        write("INFO", message);
    }

    public static void success(String message) {
        write("SUCCESS", message);
    }
    
    

    public static void warning(String message) {
        write("WARNING", message);
    }

    
    
    public static void error(String message) {
        write("ERROR", message);
    }
}
