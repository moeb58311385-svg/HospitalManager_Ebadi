package data;


import business.model.Staff;
import java.sql.*;
import java.util.ArrayList;

public class StaffDAO {

    public boolean addStaff(Staff staff) {
        String sql = "INSERT INTO staff (naam, semat, bakhsh_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, staff.getNaam());
            statement.setString(2, staff.getSemat());
            if (staff.getBakhshId() > 0) {
                statement.setInt(3, staff.getBakhshId());
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت پرسنل خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }
    
    
    

    public ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> list = new ArrayList<>();
        String sql = "SELECT s.id, s.naam, s.semat, s.bakhsh_id, d.naam AS naam_bakhsh "
                + "FROM staff s LEFT JOIN departments d ON s.bakhsh_id = d.id";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Staff staff = new Staff(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getString("semat"),
                        result.getInt("bakhsh_id")
                );
                staff.setNaamBakhsh(result.getString("naam_bakhsh"));
                list.add(staff);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت لیست پرسنل خطایی به وجود آمد: " + e.getMessage());
        }

        return list;
    }

    
    public ArrayList<Staff> getStaffByDepartment(int bakhshId) {
        ArrayList<Staff> list = new ArrayList<>();
        String sql = "SELECT s.id, s.naam, s.semat, s.bakhsh_id, d.naam AS naam_bakhsh "
                + "FROM staff s LEFT JOIN departments d ON s.bakhsh_id = d.id WHERE s.bakhsh_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bakhshId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Staff staff = new Staff(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getString("semat"),
                        result.getInt("bakhsh_id")
                );
                staff.setNaamBakhsh(result.getString("naam_bakhsh"));
                list.add(staff);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت پرسنل بخش خطایی به وجود آمد: " + e.getMessage());
        }

        return list;
    }
}
