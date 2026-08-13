package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("اتصال به دیتابیس با موفقیت انجام شد.");
            return connection;

        } catch (SQLException e) {
            System.out.println(" در اتصال به پایگاه داده خطایی به وجود آمد.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("در بستن اتصال پایگاه داده خطایی به وجود آمد.");
            }
        }
    }
}