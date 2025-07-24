import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowData_InsertUpdateDelete extends JFrame {

    private Connection conn;
    private List<Student> students = new ArrayList<>();
    private int currentIndex = 0;

    private JLabel lblID = new JLabel("ID:");
    private JLabel lblName = new JLabel("Name:");
    private JLabel lblSex = new JLabel("Sex:");
    private JLabel lblScore = new JLabel("Score:");
    private JLabel lblAddress = new JLabel("Address:");

    private JTextField txtID = new JTextField(10);
    private JTextField txtName = new JTextField(10);
    private JTextField txtSex = new JTextField(10);
    private JTextField txtScore = new JTextField(10);
    private JTextField txtAddress = new JTextField(10);

    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

    public ShowData_InsertUpdateDelete() {
        super("Student Records Navigation + Insert + Update + Delete");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(lblID);
        formPanel.add(txtID);
        txtID.setEditable(false);  // ID is auto-generated

        formPanel.add(lblName);
        formPanel.add(txtName);

        formPanel.add(lblSex);
        formPanel.add(txtSex);

        formPanel.add(lblScore);
        formPanel.add(txtScore);

        formPanel.add(lblAddress);
        formPanel.add(txtAddress);

        contentPanel.add(formPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnFirst);
        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnLast);
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        contentPanel.add(buttonPanel);
        add(contentPanel);

        connectToDatabase();
        loadStudents();
        if (!students.isEmpty()) {
            showCurrentStudent();
        }

        btnFirst.addActionListener(e -> { currentIndex = 0; showCurrentStudent(); });
        btnLast.addActionListener(e -> { currentIndex = students.size() - 1; showCurrentStudent(); });
        btnNext.addActionListener(e -> {
            if (currentIndex < students.size() - 1) {
                currentIndex++;
                showCurrentStudent();
            } else {
                JOptionPane.showMessageDialog(this, "Already at last record.");
            }
        });
        btnPrevious.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                showCurrentStudent();
            } else {
                JOptionPane.showMessageDialog(this, "Already at first record.");
            }
        });

        btnInsert.addActionListener(e -> insertStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:8889/Student";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    private void loadStudents() {
        students.clear();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TbStudent");
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Sex"),
                        rs.getInt("Score"),
                        rs.getString("Address")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + e.getMessage());
        }
    }

    private void showCurrentStudent() {
        if (students.isEmpty()) {
            clearFields();
            return;
        }
        Student s = students.get(currentIndex);
        txtID.setText(String.valueOf(s.id));
        txtName.setText(s.name);
        txtSex.setText(s.sex);
        txtScore.setText(String.valueOf(s.score));
        txtAddress.setText(s.address);
    }

    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtSex.setText("");
        txtScore.setText("");
        txtAddress.setText("");
    }

    private void insertStudent() {
        String name = txtName.getText().trim();
        String sex = txtSex.getText().trim();
        String scoreStr = txtScore.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.isEmpty() || sex.isEmpty() || scoreStr.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields before inserting.");
            return;
        }

        int score;
        try {
            score = Integer.parseInt(scoreStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Score must be a number.");
            return;
        }

        try {
            String sql = "INSERT INTO TbStudent (Name, Sex, Score, Address) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setInt(3, score);
            stmt.setString(4, address);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted successfully.");
                loadStudents();
                currentIndex = students.size() - 1;
                showCurrentStudent();
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + e.getMessage());
        }
    }

    private void updateStudent() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No record to update.");
            return;
        }

        int id = students.get(currentIndex).id;
        String name = txtName.getText().trim();
        String sex = txtSex.getText().trim();
        String scoreStr = txtScore.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.isEmpty() || sex.isEmpty() || scoreStr.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields before updating.");
            return;
        }

        int score;
        try {
            score = Integer.parseInt(scoreStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Score must be a number.");
            return;
        }

        try {
            String sql = "UPDATE TbStudent SET Name=?, Sex=?, Score=?, Address=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setInt(3, score);
            stmt.setString(4, address);
            stmt.setInt(5, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully.");
                loadStudents();
                showCurrentStudent();
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No record to delete.");
            return;
        }

        int id = students.get(currentIndex).id;
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            String sql = "DELETE FROM TbStudent WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
                loadStudents();
                if (currentIndex >= students.size()) currentIndex = students.size() - 1;
                if (students.isEmpty()) clearFields();
                else showCurrentStudent();
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShowData_InsertUpdateDelete::new);
    }
}

// Student class
class Student {
    int id;
    String name;
    String sex;
    int score;
    String address;

    public Student(int id, String name, String sex, int score, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.score = score;
        this.address = address;
    }
}
