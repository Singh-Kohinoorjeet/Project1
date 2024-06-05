import java.sql.*;
import java.util.Scanner;

public class studentTable {
    private String url = "jdbc:mysql://127.0.0.1:3306/student_schema";
    private String user = "root";
    private String password = "Kohinoormysql@01";

    // Method to insert a new student
    // Method to insert a new student with address
    public void insertStudent(String firstName, String lastName, String address) {
        String insertSQL = "INSERT INTO new_student (first_name, last_name, address) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student by ID
    public void deleteStudent(int studentId) {
        String deleteSQL = "DELETE FROM new_student WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to alter the student table to add a new column
    public void alterTable() {
        String alterTableSQL = "ALTER TABLE new_student ADD COLUMN age INT";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(alterTableSQL);
            System.out.println("Table altered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to print the entire table
    public void printTable() {
        String query = "SELECT * FROM new_student";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to print a specific student by last name
    public void printStudentByLastName(String lastName) {
        String query = "SELECT * FROM new_student WHERE last_name = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, lastName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Print column names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Print rows
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the functionalities
    public static void main(String[] args) {
        studentTable studentTable = new studentTable();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Insert Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Alter Table");
        System.out.println("4. Print Table");
        System.out.println("5. Print Student by Last Name");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1:
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();

                studentTable.insertStudent(firstName, lastName,address);
                break;
            case 2:
                System.out.print("Enter student ID to delete: ");
                int studentId = scanner.nextInt();
                studentTable.deleteStudent(studentId);
                break;
            case 3:
                studentTable.alterTable();
                break;
            case 4:
                studentTable.printTable();
                break;
            case 5:
                System.out.print("Enter last name to search: ");
                String searchLastName = scanner.nextLine();
                studentTable.printStudentByLastName(searchLastName);
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        scanner.close();
    }
}
