import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DatabaseFormTwo extends JFrame {

    private JPanel pnlButton = new JPanel(new GridLayout(1, 4));
    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");
    private GridLayout grd = new GridLayout(5, 2); // Adjusted to organize fields more neatly
    private JPanel pnlContainer = new JPanel(grd);
    private JLabel lblId = new JLabel("Id:");
    private JLabel lblName = new JLabel("Name:");
    private JLabel lblSex = new JLabel("Sex:");
    private JLabel lblScore = new JLabel("Score:");
    private JLabel lblAddress = new JLabel("Address:");
    private JLabel lblRec = new JLabel("Rec: 0/0", JLabel.CENTER);
    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtScore = new JTextField();
    private JTextField txtAddress = new JTextField();
    private JComboBox<String> cboSex = new JComboBox<>();
    private JButton btnInsert = new JButton("Insert");
    private Connection con;
    private Statement stm;
    private ResultSet rss;
    private int rowCount = 0;

    public DatabaseFormTwo() throws ClassNotFoundException {
        setTitle("Updating Database");
        setSize(400, 300); // Adjusted size for a better view
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding ComboBox items for sex
        cboSex.addItem("Male");
        cboSex.addItem("Female");

        // Organize form components using GridLayout
        pnlContainer.setLayout(new GridLayout(6, 2, 5, 5)); // Added gaps between rows and columns

        // Adding components to the panel
        pnlContainer.add(lblId);
        pnlContainer.add(txtId);
        pnlContainer.add(lblName);
        pnlContainer.add(txtName);
        pnlContainer.add(lblSex);
        pnlContainer.add(cboSex);
        pnlContainer.add(lblScore);
        pnlContainer.add(txtScore);
        pnlContainer.add(lblAddress);
        pnlContainer.add(txtAddress);
        pnlContainer.add(lblRec);
        pnlContainer.add(btnInsert);

        add(pnlContainer, BorderLayout.CENTER);

        // Button panel with navigation controls
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centering buttons
        pnlButton.add(btnFirst);
        pnlButton.add(btnPrevious);
        pnlButton.add(btnNext);
        pnlButton.add(btnLast);

        add(pnlButton, BorderLayout.SOUTH);

        // Database connection setup
        try {
            String url = "jdbc:mysql://localhost:8889/Student"; // MAMP default
            String user = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password); // Initialize the connection
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rss = stm.executeQuery("SELECT * FROM Tbstudent");

            // Populate the form with the first record
            rss.last();
            rowCount = rss.getRow();
            rss.first();
            txtId.setText(rss.getString(1));
            txtName.setText(rss.getString(2));
            cboSex.setSelectedItem(rss.getString(3));
            txtScore.setText("" + rss.getInt(4));
            txtAddress.setText("" + rss.getString(5));
            lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Action listeners for navigation buttons
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    rss.first();
                    updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    rss.previous();
                    if (rss.isBeforeFirst()) rss.first();
                    else updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    rss.next();
                    if (rss.isAfterLast()) rss.last();
                    else updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    rss.last();
                    updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Insert button to insert a new record
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = txtId.getText();
                String name = txtName.getText();
                String sex = cboSex.getSelectedItem().toString();
                String score = txtScore.getText();
                String address = txtAddress.getText();
                String sql = "INSERT INTO Tbstudent (Id, Name, Sex, Score, Address) VALUES ('" + id + "', '" + name + "', '" + sex + "', '" + score + "', '" + address + "');";
                try {
                    stm.executeUpdate(sql);
                    rss = stm.executeQuery("SELECT * FROM Tbstudent");
                    rss.last();
                    rowCount = rss.getRow();
                    lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to update the UI after moving through records
    private void updateUI() throws SQLException {
        txtId.setText(rss.getString(1));
        txtName.setText(rss.getString(2));
        cboSex.setSelectedItem(rss.getString(3));
        txtScore.setText("" + rss.getInt(4));
        txtAddress.setText("" + rss.getString(5));
        lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new DatabaseFormTwo();
    }
}
