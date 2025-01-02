package agg;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLEmployeeRepository implements EmployeeRepository {
    @Override
    public void addEmployee(String companyName, Employee employee) {
        String query = "INSERT INTO employees (name, company) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, companyName);
            statement.executeUpdate();
            System.out.println(employee.getName() + " added to " + companyName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEmployee(String companyName, String employeeName) {
        String query = "DELETE FROM employees WHERE name = ? AND company = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeName);
            statement.setString(2, companyName);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(employeeName + " removed from " + companyName);
            } else {
                System.out.println(employeeName + " not found in " + companyName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployees(String companyName) {
        String query = "SELECT name FROM employees WHERE company = ?";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, companyName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
