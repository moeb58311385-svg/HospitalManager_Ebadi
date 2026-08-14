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

  
    public boolean saveDailyIncome() {
        String sqlTotal = "SELECT COALESCE(SUM(mablagh), 0) AS total FROM finance WHERE DATE(tarikh) = CURRENT_DATE()";
        String sqlUpsert = "INSERT INTO daily_income (tarikh, mablagh_kol) VALUES (CURRENT_DATE(), ?) "
                + "ON DUPLICATE KEY UPDATE mablagh_kol = ?";

        try (Connection connection = DatabaseManager.getConnection()) {
            double emroozTotal = 0;

            try (Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery(sqlTotal)) {
                if (result.next()) {
                    emroozTotal = result.getDouble("total");
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(sqlUpsert)) {
                statement.setDouble(1, emroozTotal);
                statement.setDouble(2, emroozTotal);
                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("در ذخیره درآمد روزانه خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    
    
    public double getTodayIncome() {
        String sql = "SELECT mablagh_kol FROM daily_income WHERE tarikh = CURRENT_DATE()";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getDouble("mablagh_kol");
            }

        } catch (SQLException e) {
            System.out.println("در دریافت درآمد امروز خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }
}
