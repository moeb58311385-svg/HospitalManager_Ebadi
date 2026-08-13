package data;

import business.model.Doctor;
import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO {

    public boolean addDoctor(Doctor pezeshk) {
        String sql = "INSERT INTO doctors (naam, takhasos, bakhsh, saat_shoru, saat_payan, zarfiat_nobat) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pezeshk.getNaam());
            statement.setString(2, pezeshk.getTakhasos());
            statement.setString(3, pezeshk.getNaamBakhsh());
            statement.setInt(4, pezeshk.getSaatShoru());
            statement.setInt(5, pezeshk.getSaatPayan());
            statement.setInt(6, pezeshk.getZarfiatNobat());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت پزشک خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }
    
    
    

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Doctor(
                        result.getInt("id"),
                        result.getString("naam"),
                        "",
                        0,
                        result.getString("takhasos"),
                        result.getString("bakhsh"),
                        result.getInt("saat_shoru"),
                        result.getInt("saat_payan"),
                        result.getInt("zarfiat_nobat")
                );
            }

        } catch (SQLException e) {
            System.out.println("در یافتن پزشک خطایی به وجود آمد: " + e.getMessage());
        }

        return null;
    }

    
    
    
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> pezeshkan = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Doctor pezeshk = new Doctor(
                        result.getInt("id"),
                        result.getString("naam"),
                        "",
                        0,
                        result.getString("takhasos"),
                        result.getString("bakhsh"),
                        result.getInt("saat_shoru"),
                        result.getInt("saat_payan"),
                        result.getInt("zarfiat_nobat")
                );
                pezeshkan.add(pezeshk);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت پزشکان خطایی به وجود آمد: " + e.getMessage());
        }

        return pezeshkan;
    }
}