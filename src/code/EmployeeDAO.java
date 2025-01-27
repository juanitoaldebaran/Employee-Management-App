package code;

import java.sql.SQLException;
import java.util.List;
public interface EmployeeDAO {
     void addEmployee(BaseEmployee employee) throws SQLException;
    BaseEmployee getEmployeeByID(int id) throws SQLException;
     void updateEmployee(int newId, String newName, String newEmail, int newAge, float newSalary, String newRole) throws SQLException;

    List<BaseEmployee> getAllEmployee() throws SQLException;
     void deleteEmployee(int id) throws SQLException;
}
