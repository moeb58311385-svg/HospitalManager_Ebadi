package data;

import business.model.Patient;
import java.sql.*;
import java.util.ArrayList;

public class PatientDAO {

    public boolean addPatient(Patient bimar) {
        String sql = "INSERT INTO patients (naam, sen, shomare_meli, emergency, bastari, bakhsh_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, bimar.getNaam());
            statement.setInt(2, bimar.getSen());
            statement.setString(3, bimar.getShomareMeli());
            statement.setBoolean(4, bimar.isEmergency());
            statement.setBoolean(5, bimar.isBastari());
            if (bimar.getBakhshId() > 0) {
                statement.setInt(6, bimar.getBakhshId());
            } else {
                statement.setNull(6, Types.INTEGER);
            }

            int result = statement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت بیمار خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }
    
    
    

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Patient bimar = new Patient(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getString("shomare_meli"),
                        result.getInt("sen")
                );

                bimar.setEmergency(result.getBoolean("emergency"));
                bimar.setBastari(result.getBoolean("bastari"));
                bimar.setBakhshId(result.getInt("bakhsh_id"));

                return bimar;
            }

        } catch (SQLException e) {
            System.out.println(" در یافتن بیمار خطایی به وجود آمد: " + e.getMessage());
        }

        return null;
    }
    
    
    

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> bimaran = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Patient bimar = new Patient(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getString("shomare_meli"),
                        result.getInt("sen")
                );

                bimar.setEmergency(result.getBoolean("emergency"));
                bimar.setBastari(result.getBoolean("bastari"));
                bimar.setBakhshId(result.getInt("bakhsh_id"));

                bimaran.add(bimar);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت بیماران خطایی به وجود آمد: " + e.getMessage());
        }

        return bimaran;
    }
    
    
    

    public boolean updatePatient(Patient bimar) {
        String sql = "UPDATE patients SET naam = ?, sen = ?, shomare_meli = ?, emergency = ?, bastari = ?, bakhsh_id = ? WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, bimar.getNaam());
            statement.setInt(2, bimar.getSen());
            statement.setString(3, bimar.getShomareMeli());
            statement.setBoolean(4, bimar.isEmergency());
            statement.setBoolean(5, bimar.isBastari());
            if (bimar.getBakhshId() > 0) {
                statement.setInt(6, bimar.getBakhshId());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            statement.setInt(7, bimar.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ویرایش بیمار خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    
    
    
    public int getTedadBimaranBastari(int bakhshId) {
        String sql = "SELECT COUNT(*) AS tedad FROM patients WHERE bakhsh_id = ? AND bastari = true";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bakhshId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("tedad");
            }

        } catch (SQLException e) {
            System.out.println("در شمارش بیماران بخش خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }

    
    
    public int getTedadBimaranBastari() {
        String sql = "SELECT COUNT(*) AS tedad FROM patients WHERE bastari = true";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getInt("tedad");
            }

        } catch (SQLException e) {
            System.out.println("در شمارش کل بیماران بستری خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }
}
