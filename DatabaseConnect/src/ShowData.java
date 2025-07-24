import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowData extends JFrame {

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

    public ShowData() {
        super("Student Records Navigation");

        // Main content panel with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel for form fields (labels + textfields)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(lblID);
        formPanel.add(txtID);
        txtID.setEditable(false);

        formPanel.add(lblName);
        formPanel.add(txtName);
        txtName.setEditable(false);

        formPanel.add(lblSex);
        formPanel.add(txtSex);
        txtSex.setEditable(false);

        formPanel.add(lblScore);
        formPanel.add(txtScore);
        txtScore.setEditable(false);

        formPanel.add(lblAddress);
        formPanel.add(txtAddress);
        txtAddress.setEditable(false);

        contentPanel.add(formPanel);

        // Button panel with FlowLayout (centered, like flex-center)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnFirst);
        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnLast);

        contentPanel.add(buttonPanel);

        // Add main panel to frame
        add(contentPanel);

        connectToDatabase();
        loadStudents();

        if (!students.isEmpty()) {
            showCurrentStudent();
        }

        btnFirst.addActionListener(e -> {
            currentIndex = 0;
            showCurrentStudent();
        });

        btnLast.addActionListener(e -> {
            currentIndex = students.size() - 1;
            showCurrentStudent();
        });

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // Center window on screen
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
        if (students.isEmpty()) return;
        Student s = students.get(currentIndex);
        txtID.setText(String.valueOf(s.id));
        txtName.setText(s.name);
        txtSex.setText(s.sex);
        txtScore.setText(String.valueOf(s.score));
        txtAddress.setText(s.address);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShowData::new);
    }
}

// Simple POJO class for Student
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
