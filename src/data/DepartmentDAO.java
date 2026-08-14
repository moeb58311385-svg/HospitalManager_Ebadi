package data;


import business.model.Department;
import java.sql.*;
import java.util.ArrayList;

public class DepartmentDAO {

    public boolean addDepartment(Department bakhsh) {
        String sql = "INSERT INTO departments (naam, zarfiat) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, bakhsh.getNaamBakhsh());
            statement.setInt(2, bakhsh.getZarfiat());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("در ثبت بخش خطایی به وجود آمد: " + e.getMessage());
            return false;
        }
    }

    
    
    
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Department(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getInt("zarfiat")
                );
            }

        } catch (SQLException e) {
            System.out.println("در یافتن بخش خطایی به وجود آمد: " + e.getMessage());
        }

        return null;
    }
    
    
    

    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> bakhshHa = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Department bakhsh = new Department(
                        result.getInt("id"),
                        result.getString("naam"),
                        result.getInt("zarfiat")
                );
                bakhshHa.add(bakhsh);
            }

        } catch (SQLException e) {
            System.out.println("در دریافت بخشها خطایی به وجود آمد: " + e.getMessage());
        }

        return bakhshHa;
    }

    
    
    
    public int getTedadBimaran(int bakhshId) {
        return new PatientDAO().getTedadBimaranBastari(bakhshId);
    }

    public int getTedadKolBimaranBastari() {
        return new PatientDAO().getTedadBimaranBastari();
    }

    public int getMajmoeZarfiat() {
        String sql = "SELECT COALESCE(SUM(zarfiat), 0) AS total FROM departments";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            if (result.next()) {
                return result.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("در محاسبه ظرفیت کل بیمارستان خطایی به وجود آمد: " + e.getMessage());
        }

        return 0;
    }

    
    
    public boolean hameBakhshHaPorHastand() {
        return getTedadKolBimaranBastari() >= getMajmoeZarfiat();
    }
}
