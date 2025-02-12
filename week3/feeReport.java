import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class DBConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/FeeReportDB";
  private static final String USER = "root";
  private static final String PASSWORD = "root"; 
  public static Connection getConnection() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
}
class Student {
    private int id;
    private String name;
    private String email;
    private String course;
    private double fee;
    private double paid;
    private double due;
    private String address;
    private String phone;

    // Constructor
    public Student(int id, String name, String email, String course, double fee, double paid, double due, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.fee = fee;
        this.paid = paid;
        this.due = due;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public double getPaid() { return paid; }
    public void setPaid(double paid) { this.paid = paid; }

    public double getDue() { return due; }
    public void setDue(double due) { this.due = due; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // toString() for Displaying Student Information
    @Override
    public String toString() {
        return "Student { " +
               "ID: " + id + ", " +
               "Name: " + name + ", " +
               "Email: " + email + ", " +
               "Course: " + course + ", " +
               "Fee: " + fee + ", " +
               "Paid: " + paid + ", " +
               "Due: " + due + ", " +
               "Address: " + address + ", " +
               "Phone: " + phone +
               " }";
    }
}
class Accountant {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Constructor
    public Accountant(int id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // toString() for Displaying Accountant Information
    @Override
    public String toString() {
        return "Accountant { " +
               "ID: " + id + ", " +
               "Name: " + name + ", " +
               "Email: " + email + ", " +
               "Phone: " + phone +
               " }";
    }
}

class AdminDAO {
  public static boolean addAccountant(String name, String email, String phone, String password) {
    String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
       PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, name);
      pstmt.setString(2, email);
      pstmt.setString(3, phone);
      pstmt.setString(4, password);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  public static List<String> viewAccountants() {
    List<String> accountants = new ArrayList<>();
    String query = "SELECT * FROM accountant";
    try (Connection conn = DBConnection.getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        accountants.add(rs.getInt("id") + " - " + rs.getString("name") + " - " + rs.getString("email"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return accountants;
  }
  public static boolean deleteAccountant(int id) {
    String query = "DELETE FROM accountant WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
       PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, id);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
public static boolean editAccountant(int id, String name, String email, String phone, String password) {
  String query = "UPDATE accountant SET name = ?, email = ?, phone = ?, password = ? WHERE id = ?";
  try (Connection conn = DBConnection.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(query)) {
    pstmt.setString(1, name);
    pstmt.setString(2, email);
    pstmt.setString(3, phone);
    pstmt.setString(4, password);
    pstmt.setInt(5, id);
    return pstmt.executeUpdate() > 0;
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return false;
}
}

class AccountantDAO {
  public static boolean addStudent(String name, String email, String course, double fee, double paid,double due, String address, String phone) {
    String query = "INSERT INTO student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
       PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, name);
      pstmt.setString(2, email);
      pstmt.setString(3, course);
      pstmt.setDouble(4, fee);
      pstmt.setDouble(5, paid);
      pstmt.setDouble(6, due);
      pstmt.setString(7, address);
      pstmt.setString(8, phone);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  public static List<String> viewStudents() {
    List<String> students = new ArrayList<>();
    String query = "SELECT * FROM student";
    try (Connection conn = DBConnection.getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        students.add(rs.getInt("id") + " - " + rs.getString("name") + " - " + rs.getString("email") + " - Due: " + rs.getDouble("due"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }
public static boolean editStudent(int id, String name, String email, String course, double fee, double paid, String address, String phone) {
  double due = fee - paid;
  String query = "UPDATE student SET name = ?, email = ?, course = ?, fee = ?, paid = ?, due = ?, address = ?, phone = ? WHERE id = ?";
  try (Connection conn = DBConnection.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(query)) {
    pstmt.setString(1, name);
    pstmt.setString(2, email);
    pstmt.setString(3, course);
    pstmt.setDouble(4, fee);
    pstmt.setDouble(5, paid);
    pstmt.setDouble(6, due);
    pstmt.setString(7, address);
    pstmt.setString(8, phone);
    pstmt.setInt(9, id);
    return pstmt.executeUpdate() > 0;
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return false;
}
 public static boolean deleteStudent(int id) {
    String query = "DELETE FROM student WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
       PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, id);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  public static List<String> checkDueFees() {
    List<String> studentsWithDue = new ArrayList<>();
    String query = "SELECT * FROM student WHERE due > 0";
    try (Connection conn = DBConnection.getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        studentsWithDue.add(rs.getInt("id") + " - " + rs.getString("name") + " - Due: " + rs.getDouble("due"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return studentsWithDue;
  }
}
class AccountantService {
  public static boolean addStudent(String name, String email, String course, double fee, double paid, String address, String phone) {
    if (paid > fee) {
      System.out.println("Error: Paid amount cannot exceed total fee.");
      return false;
    }
    double due=fee-paid;
	return AccountantDAO.addStudent(name, email, course, fee, paid, due, address, phone);
  }
  public static List<String> viewStudents() {
    return AccountantDAO.viewStudents();
  }
  public static boolean deleteStudent(int id) {
    return AccountantDAO.deleteStudent(id);
  }
  public static List<String> checkDueFees() {
    return AccountantDAO.checkDueFees();
  }

 public static boolean editStudent(int id, String name, String email, String course, double fee, double paid, String address, String phone) {
    if (paid > fee) {
      System.out.println("Error: Paid amount cannot exceed total fee.");
      return false;
    }
    return AccountantDAO.editStudent(id, name, email, course, fee, paid, address, phone);
  }
public static void logout() {
  System.out.println("logged out");
}
}

class AdminService {
public static boolean addAccountant(String name, String email, String phone, String password) {
    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
      System.out.println("Error: All fields are required.");
      return false;
    }
    return AdminDAO.addAccountant(name, email, phone, password);
  }
  public static boolean editAccountant(int id, String name, String email, String phone, String password) {
    if (id <= 0 || name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
      System.out.println("Error: Invalid input values.");
      return false;
    }
    return AdminDAO.editAccountant(id, name, email, phone, password);
  }
  public static boolean deleteAccountant(int id) {
    if (id <= 0) {
      System.out.println("Error: Invalid accountant ID.");
      return false;
    }
    return AdminDAO.deleteAccountant(id);
  }
  public static List<String> viewAccountants() {
    return AdminDAO.viewAccountants();
  }
  public static void logout() {
	  System.out.println("Logged out");
  }
}
public class MainApp {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("\n==== Fee Report System ====");
      System.out.println("1. Admin Login");
      System.out.println("2. Accountant Login");
      System.out.println("3. Exit");
      System.out.print("Enter choice: ");
      int role = scanner.nextInt();
      switch (role) {
        case 1:
          adminPanel(scanner);
          break;
        case 2:
          accountantPanel(scanner);
          break;
        case 3:
          System.out.println("Exiting Fee Report System. Goodbye!");
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Invalid choice! Please try again.");
      }
    }
  }
  private static void adminPanel(Scanner scanner) {
    boolean adminLoggedIn = true;
    while (adminLoggedIn) {
      System.out.println("\n=== Admin Panel ===");
      System.out.println("1. Add Accountant");
      System.out.println("2. Edit Accountant");
      System.out.println("3. Delete Accountant");
      System.out.println("4. View Accountants");
      System.out.println("5. Logout");
      System.out.print("Enter choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); 
      switch (choice) {
        case 1:
          System.out.print("Enter Name: ");
          String name = scanner.nextLine();
          System.out.print("Enter Email: ");
          String email = scanner.nextLine();
          System.out.print("Enter Phone: ");
          String phone = scanner.nextLine();
          System.out.print("Enter Password: ");
          String password = scanner.nextLine();
          if (AdminService.addAccountant(name, email, phone, password)) {
            System.out.println("Accountant added successfully!");
          } else {
            System.out.println("Failed to add accountant.");
          }
          break;
        case 2:
          System.out.print("Enter Accountant ID to Edit: ");
          int idToEdit = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter New Name: ");
          String newName = scanner.nextLine();
          System.out.print("Enter New Email: ");
          String newEmail = scanner.nextLine();
          System.out.print("Enter New Phone: ");
          String newPhone = scanner.nextLine();
          System.out.print("Enter New Password: ");
          String newPassword = scanner.nextLine();
          if (AdminService.editAccountant(idToEdit, newName, newEmail, newPhone, newPassword)) {
            System.out.println("Accountant updated successfully!");
          } else {
            System.out.println("Failed to update accountant.");
          }
          break;
        case 3:
          System.out.print("Enter Accountant ID to Delete: ");
          int idToDelete = scanner.nextInt();
          if (AdminService.deleteAccountant(idToDelete)) {
            System.out.println("Accountant deleted successfully!");
          } else {
            System.out.println("Failed to delete accountant.");
          }
          break;
        case 4:
          List<String> accountants = AdminService.viewAccountants();
          if (accountants.isEmpty()) {
            System.out.println("No accountants found.");
          } else {
            for (String accountant : accountants) {
              System.out.println(accountant);
            }
          }
          break;
        case 5:
          AdminService.logout();
          adminLoggedIn = false;
          break;
        default:
          System.out.println("Invalid choice!");
      }
    }
  }
  private static void accountantPanel(Scanner scanner) {
    boolean accountantLoggedIn = true;
    while (accountantLoggedIn) {
      System.out.println("\n=== Accountant Panel ===");
      System.out.println("1. Add Student");
      System.out.println("2. Edit Student");
      System.out.println("3. Delete Student");
      System.out.println("4. View Students");
      System.out.println("5. View Due Fees");
      System.out.println("6. Logout");
      System.out.print("Enter choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          System.out.print("Enter Name: ");
          String name = scanner.nextLine();
          System.out.print("Enter Email: ");
          String email = scanner.nextLine();
          System.out.print("Enter Course: ");
          String course = scanner.nextLine();
          System.out.print("Enter Fee: ");
          double fee = scanner.nextDouble();
          System.out.print("Enter Paid Amount: ");
          double paid = scanner.nextDouble();
          double due = fee - paid;
          scanner.nextLine(); 
          System.out.print("Enter Address: ");
          String address = scanner.nextLine();
          System.out.print("Enter Phone: ");
          String phone = scanner.nextLine();
          if (AccountantService.addStudent(name, email, course, fee, paid, address, phone)) {
            System.out.println("Student added successfully!");
          } else {
            System.out.println("Failed to add student.");
          }
          break;
        case 2:
          System.out.print("Enter Student ID to Edit: ");
          int idToEdit = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter New Name: ");
          String newName = scanner.nextLine();
          System.out.print("Enter New Email: ");
          String newEmail = scanner.nextLine();
          System.out.print("Enter New Course: ");
          String newCourse = scanner.nextLine();
          System.out.print("Enter New Fee: ");
          double newFee = scanner.nextDouble();
          System.out.print("Enter New Paid Amount: ");
          double newPaid = scanner.nextDouble();
          double newDue = newFee - newPaid;
          scanner.nextLine();
          System.out.print("Enter New Address: ");
          String newAddress = scanner.nextLine();
          System.out.print("Enter New Phone: ");
          String newPhone = scanner.nextLine();
          if (AccountantService.editStudent(idToEdit, newName, newEmail, newCourse, newFee, newPaid, newAddress, newPhone)) {
            System.out.println("Student updated successfully!");
          } else {
            System.out.println("Failed to update student.");
          }
          break;
        case 3:
          System.out.print("Enter Student ID to Delete: ");
          int idToDelete = scanner.nextInt();
          if (AccountantService.deleteStudent(idToDelete)) {
            System.out.println("Student deleted successfully!");
          } else {
            System.out.println("Failed to delete student.");
          }
          break;
        case 4:
          List<String> students = AccountantService.viewStudents();
          if (students.isEmpty()) {
            System.out.println("No students found.");
          } else {
            for (String student : students) {
              System.out.println(student);
            }
          }
          break;
        case 5:
          List<String> dueFees = AccountantService.checkDueFees();
          if (dueFees.isEmpty()) {
            System.out.println("No due fees found.");
          } else {
            for (String student : dueFees) {
              System.out.println(student);
            }
          }
          break;
        case 6:
          AccountantService.logout();
          accountantLoggedIn = false;
          break;
        default:
          System.out.println("Invalid choice!");
      }
    }
  }
}
