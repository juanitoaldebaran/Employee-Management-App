package code;

public class BaseEmployee implements Employee{

    private int id;
    private String name;
    private String email;
    private int age;
    private float salary;
    private String role;
    public BaseEmployee(int id, String name, String email, int age, float salary, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.role = role;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public float getSalary(){
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("[ ID : %d | Name : %s | Email : %s | Age : %d | Salary : %.4f | Role : %s", id, name, email, age, salary, role);
    }
}
