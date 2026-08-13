package data;

import business.model.Bill;
import java.sql.*;
import java.util.ArrayList;

public class BillDAO {

    public boolean addBill(Bill bil) {
        String sql = "INSERT INTO bills (patient_id, noe_bil, hazine) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bil.getBimar().getId());
            statement.setString(2, bil.getNoeBil());
            statement.setDouble(3, bil.mohasebeHazine());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت صورتحساب خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }
    
    

    public double getTotalIncome() {
        String sql = "SELECT SUM(hazine) AS total FROM bills";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("در محاسبه درآمد خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }
    
    
    

    public ArrayList<String> getBillsByPatient(int patientId) {
        ArrayList<String> bilHa = new ArrayList<>();
        String sql = "SELECT id, noe_bil, hazine, tarikh FROM bills WHERE patient_id = ? ORDER BY tarikh DESC";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                bilHa.add("شماره صورتحساب: " + result.getInt("id")
                        + " | نوع: " + result.getString("noe_bil")
                        + " | مبلغ: " + result.getDouble("hazine")
                        + " تومان | تاریخ: " + result.getTimestamp("tarikh"));
            }

        } catch (SQLException e) {
            System.out.println("در دریافت صورتحسابها خطایی به وجود آمد: " + e.getMessage());
        }

        return bilHa;
    }
}
