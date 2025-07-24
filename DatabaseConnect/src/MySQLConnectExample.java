/*

//Connect Java to phpMyAdmin
import java.sql.*;

public class MySQLConnectExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Student"; // Port is 8889 (MAMP)
        String user = "root"; // default MAMP user
        String password = "root"; // default MAMP password

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Sample query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TbStudent");

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("Name") +
                                   ", Sex: " + rs.getString("Sex") +
                                   ", Score: " + rs.getInt("Score") +
                                   ", Address: " + rs.getString("Address"));
            }

            // Clean up
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Connect Java to phpMyAdmin and input data
import java.sql.*;

public class MySQLConnectExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Student"; // MAMP uses port 8889
        String user = "root";
        String password = "root";

        String insertQuery = "INSERT INTO TbStudent (Name, Sex, Score, Address) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, "Fa King");
            pstmt.setString(2, "Male");
            pstmt.setInt(3, 99);
            pstmt.setString(4, "Phnom Penh");

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Data inserted successfully!");
            } else {
                System.out.println("⚠️ Insert failed.");
            }

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Connect Java to phpMyAdmin and input data form keyboard
import java.sql.*;
import java.util.Scanner;

public class MySQLConnectExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Student"; // MAMP uses port 8889
        String user = "root";
        String password = "root";

        Scanner scanner = new Scanner(System.in);

        // Read input from keyboard
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Sex (Male/Female): ");
        String sex = scanner.nextLine();

        System.out.print("Enter Score (integer): ");
        int score = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        String insertQuery = "INSERT INTO TbStudent (Name, Sex, Score, Address) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, name);
            pstmt.setString(2, sex);
            pstmt.setInt(3, score);
            pstmt.setString(4, address);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Data inserted successfully!");
            } else {
                System.out.println("⚠️ Insert failed.");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
*/

//Connect Java to phpMyAdmin and input data form keyboard loop
import java.sql.*;
import java.util.Scanner;

public class MySQLConnectExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Student"; // MAMP port
        String user = "root";
        String password = "root";

        Scanner scanner = new Scanner(System.in);

        String insertQuery = "INSERT INTO TbStudent (Name, Sex, Score, Address) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String continueInput;

            do {
                // Read student data
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Sex (Male/Female): ");
                String sex = scanner.nextLine();

                System.out.print("Enter Score (integer): ");
                int score = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                System.out.print("Enter Address: ");
                String address = scanner.nextLine();

                // Insert into database
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, name);
                pstmt.setString(2, sex);
                pstmt.setInt(3, score);
                pstmt.setString(4, address);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("✅ Data inserted successfully!");
                } else {
                    System.out.println("⚠️ Insert failed.");
                }

                pstmt.close();

                // Ask if user wants to continue
                System.out.print("Do you want to insert another student? (yes/no): ");
                continueInput = scanner.nextLine().trim().toLowerCase();

            } while (continueInput.equals("yes"));

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}





