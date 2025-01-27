package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeDatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aldebaran1711";

    public static void main(String[] args) {

      Connection connection = null;
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
          System.out.println("Database connected successfully!");
      } catch (SQLException e) {
          System.out.println("Failed to connect MySQL connection");
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          System.out.println("Failed to establish a database connection");
          e.printStackTrace();
      } finally {
          if(connection != null) {
              try {
                  connection.close();
                  System.out.println("Connection successfully closed!");
              } catch (SQLException e) {
                  System.out.println("Failed to closed database connection!");
                  e.printStackTrace();
              }
          }
      }
    }
}
