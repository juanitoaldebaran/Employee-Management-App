package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseEmployeeDAO implements EmployeeDAO {

    private Connection connection;

    public BaseEmployeeDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addEmployee(BaseEmployee employee) throws SQLException {
        String insertQuery = "INSERT INTO employee_table (name, email, age, salary, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setInt(3, employee.getAge());
            statement.setFloat(4, employee.getSalary());
            statement.setString(5, employee.getRole());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee details successfully added!");
            } else {
                System.out.println("Failed to add employee details");
            }
        }
    }

    @Override
    public BaseEmployee getEmployeeByID(int id) throws SQLException{
        String listQuery = "SELECT * FROM employee_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(listQuery)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BaseEmployee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getFloat("salary"),
                            resultSet.getString("role")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateEmployee(int newId, String newName, String newEmail, int newAge, float newSalary, String newRole) throws SQLException {
        String updateQuery = "UPDATE employee_table SET id = ?, name = ?, email = ?, age = ?, salary = ?, role = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, newId);
            statement.setString(2, newName);
            statement.setString(3, newEmail);
            statement.setInt(4, newAge);
            statement.setFloat(5, newSalary);
            statement.setString(6, newRole);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee Details update successfully!");
            } else {
                System.out.println("Failed to update employee details");
            }
        }
    }

    @Override
    public List<BaseEmployee> getAllEmployee() throws SQLException{
        String getAllQuery = "SELECT * FROM employee_table";
        List<BaseEmployee> baseEmployees = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(getAllQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    baseEmployees.add(new BaseEmployee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("age"),
                            resultSet.getFloat("salary"),
                            resultSet.getString("role")
                        ));
                }

            }
        }

        return baseEmployees;
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String deleteQuery = "DELETE FROM employee_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted employee details");
            } else {
                System.out.println("Failed to delete employee details");
            }
        }
    }
}
