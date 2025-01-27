package code;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static BaseEmployeeDAO baseEmployeeDAO;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aldebaran1711";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {

            baseEmployeeDAO = new BaseEmployeeDAO(connection);

            System.out.println("WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM");
            System.out.print("Enter your name: ");
            String username = scanner.nextLine();

            System.out.println("Hi " + username + ", Welcome to the employee management system");

            boolean condition = true;
            while (condition) {
                displayInput();
                System.out.println("Select your option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addDetails();
                        break;
                    case 2:
                        updateDetails();
                        break;
                    case 3:
                        listDetails();
                        break;
                    case 4:
                        deleteDetails();
                        break;
                    case 5:
                        listDetailsByID();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        condition = false;
                    default:
                        System.out.println("Failed to input employee details");
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }
    }

    public static void displayInput() {
        System.out.println("----------------------------");
        System.out.println("1) Add employee details");
        System.out.println("2) Update employee details");
        System.out.println("3) List all employee details");
        System.out.println("4) Delete employee details");
        System.out.println("5) List employee details by id");
        System.out.println("6) Exit");
        System.out.println("----------------------------");
    }

    public static void addDetails() {
        try {
            System.out.println("Enter employee name: ");
            String name = scanner.nextLine();

            System.out.println("Enter employee email: ");
            String email = scanner.nextLine();

            System.out.println("Enter employee age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter employee salary: ");
            float salary = scanner.nextFloat();
            scanner.nextLine();

            System.out.println("Enter employee role: ");
            String role = scanner.nextLine();

            BaseEmployee baseEmployee = new BaseEmployee(0, name, email, age, salary, role);
            baseEmployeeDAO.addEmployee(baseEmployee);
        } catch (Exception e) {
            System.out.println("Failed to connect to Database");
            e.printStackTrace();
        }
    }

    public static void updateDetails() {
        try {
            System.out.println("Enter employee id to be updated: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            BaseEmployee baseEmployee = baseEmployeeDAO.getEmployeeByID(id);
            if (baseEmployee != null) {

                System.out.print("Current employee name: " + baseEmployee.getName());
                System.out.println("Enter new employee name: ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    baseEmployee.setName(newName);
                }

                System.out.print("Current employee email: " + baseEmployee.getName());
                System.out.println("Enter new employee email: ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) {
                    baseEmployee.setEmail(newEmail);
                }

                System.out.print("Current employee age: " + baseEmployee.getAge());
                System.out.println("Enter new employee age: ");
                String newAge = scanner.nextLine();
                if (!newAge.isEmpty()) {
                    int newAgeInt = Integer.parseInt(newAge);
                    baseEmployee.setAge(newAgeInt);
                }

                System.out.print("Current employee salary: " + baseEmployee.getSalary());
                System.out.println("Enter new employee salary: ");
                String newSalary = scanner.nextLine();
                if (!newSalary.isEmpty()) {
                    float salaryFloat = Float.parseFloat(newSalary);
                    baseEmployee.setSalary(salaryFloat);
                }

                System.out.print("Current employee role: " + baseEmployee.getRole());
                System.out.println("Enter new employee role: ");
                String newRole = scanner.nextLine();
                if (!newRole.isEmpty()) {
                    baseEmployee.setRole(newRole);
                }

            }
        } catch (Exception e) {
            System.out.println("Failed to connect to Database");
            e.printStackTrace();
        }
    }

    public static void listDetails() {
        try{
            List<BaseEmployee> employeeList = baseEmployeeDAO.getAllEmployee();
            if (employeeList.isEmpty()) {
                System.out.println("The employee list is empty");
            } else {
                for (BaseEmployee employee : employeeList) {
                    System.out.println(employee);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to get employee list");
            e.printStackTrace();
        }
    }

    public static void deleteDetails() {
        try{
            System.out.print("Enter employee id to be deleted: ");
            int deleteID = scanner.nextInt();
            scanner.nextLine();
            baseEmployeeDAO.deleteEmployee(deleteID);
        } catch (Exception e) {
            System.out.println("Failed to delete employee details");
            e.printStackTrace();
        }

    }

    public static void listDetailsByID() {
        try{
            System.out.println("Enter employee id you want to view: ");
            int viewID = scanner.nextInt();
            scanner.nextLine();

            BaseEmployee baseEmployee = baseEmployeeDAO.getEmployeeByID(viewID);
            if (baseEmployee != null) {
                System.out.println(baseEmployee);
            } else {
                System.out.println("Employee not found");
            }
        } catch (Exception e) {
            System.out.println("Failed to list employee details");
            e.printStackTrace();
        }
    }

}
