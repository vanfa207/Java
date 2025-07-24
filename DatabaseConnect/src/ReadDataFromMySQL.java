import java.sql.*;

public class ReadDataFromMySQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Student"; // MAMP port
        String user = "root";
        String password = "root";

        String selectQuery = "SELECT * FROM TbStudent";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Create Statement
            Statement stmt = conn.createStatement();

            // Execute SELECT query
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Print header
            System.out.println("ID | Name       | Sex       | Score | Address");
            System.out.println("------------------------------------------");

            // Loop through result set
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String sex = rs.getString("Sex");
                int score = rs.getInt("Score");
                String address = rs.getString("Address");

                System.out.printf("%-2d | %-10s | %-9s | %-5d | %s%n", id, name, sex, score, address);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
