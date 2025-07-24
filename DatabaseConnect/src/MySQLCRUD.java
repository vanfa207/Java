// Connect Java to phpMyAdmin 5 Option : insert, Update, Delete, Display, Exit

import java.sql.*;
import java.util.Scanner;

public class MySQLCRUD {

    private static Connection conn;

    // Method to connect to the database
    private static Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:8889/Student"; // MAMP port
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    // Display data from the database
    private static void displayData() {
        String selectQuery = "SELECT * FROM TbStudent";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("ID | Name       | Sex       | Score | Address");
            System.out.println("------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String sex = rs.getString("Sex");
                int score = rs.getInt("Score");
                String address = rs.getString("Address");

                System.out.printf("%-2d | %-10s | %-9s | %-5d | %s%n", id, name, sex, score, address);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new data into the database
    private static void insertData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Sex: ");
        String sex = scanner.nextLine();
        System.out.print("Enter Score: ");
        int score = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        String insertQuery = "INSERT INTO TbStudent (Name, Sex, Score, Address) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setInt(3, score);
            stmt.setString(4, address);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update data in the database
    private static void updateData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Sex: ");
        String sex = scanner.nextLine();
        System.out.print("Enter new Score: ");
        int score = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();

        String updateQuery = "UPDATE TbStudent SET Name = ?, Sex = ?, Score = ?, Address = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setInt(3, score);
            stmt.setString(4, address);
            stmt.setInt(5, id);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete data from the database
    private static void deleteData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        String deleteQuery = "DELETE FROM TbStudent WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Connect to the database
            conn = connectToDatabase();
            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                // Display menu
                System.out.println("\n1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Display");
                System.out.println("5. Exit");
                System.out.print("Enter your option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        insertData();
                        break;
                    case 2:
                        updateData();
                        break;
                    case 3:
                        deleteData();
                        break;
                    case 4:
                        displayData();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (option != 5);

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
