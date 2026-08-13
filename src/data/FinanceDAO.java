package data;

import java.sql.*;

public class FinanceDAO {

    public boolean addFinance(double mablagh, String noe) {
        String sql = "INSERT INTO finance (mablagh, noe) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, mablagh);
            statement.setString(2, noe);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت اطلاعات مالی خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }
    
    
    

    public double getTotalFinance() {
        String sql = "SELECT COALESCE(SUM(mablagh), 0) AS total FROM finance";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("در دریافت بودجه خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }
    
    
    

    public double getMonthlyFinance() {
        String sql = "SELECT COALESCE(SUM(mablagh), 0) AS total FROM finance WHERE YEAR(tarikh) = YEAR(CURRENT_DATE()) AND MONTH(tarikh) = MONTH(CURRENT_DATE())";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("در دریافت درآمد ماهیانه خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }
}
