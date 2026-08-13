package data;

import business.model.Appointment;
import business.model.Doctor;
import business.model.Patient;
import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAO {

    public boolean addAppointment(Appointment nobat) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, tarikh, saat, anjam_shode) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, nobat.getBimar().getId());
            statement.setInt(2, nobat.getDoctor().getId());
            statement.setDate(3, Date.valueOf(nobat.getTarikh()));
            statement.setInt(4, nobat.getSaat());
            statement.setBoolean(5, nobat.isAnjamShode());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" در ثبت نوبت خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    
    
    
    public boolean completeAppointment(int id) {
        String sql = "UPDATE appointments SET anjam_shode = true WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در تکمیل نوبت خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در حذف نوبت خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    
    
    
    public int getTedadNobat(int doctorId, java.time.LocalDate tarikh) {
        String sql = "SELECT COUNT(*) AS tedad FROM appointments WHERE doctor_id = ? AND tarikh = ? AND anjam_shode = false";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);
            statement.setDate(2, Date.valueOf(tarikh));
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("tedad");
            }

        } catch (SQLException e) {
            System.out.println("در شمارش نوبتهای پزشک خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }

    
    
    
    public ArrayList<Appointment> getAppointmentsByPatient(int patientId) {
        ArrayList<Appointment> nobatHa = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                PatientDAO patientDAO = new PatientDAO();
                DoctorDAO doctorDAO = new DoctorDAO();

                Patient bimar = patientDAO.getPatientById(result.getInt("patient_id"));
                Doctor pezeshk = doctorDAO.getDoctorById(result.getInt("doctor_id"));

                Appointment nobat = new Appointment(
                        result.getInt("id"),
                        bimar,
                        pezeshk,
                        result.getDate("tarikh").toLocalDate(),
                        result.getInt("saat")
                );

                if (result.getBoolean("anjam_shode")) {
                    nobat.anjamVizit();
                }

                nobatHa.add(nobat);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت نوبتها خطایی به وجود آمد: " + e.getMessage());
        }

        return nobatHa;
    }
}
