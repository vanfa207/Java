import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class User extends JFrame {
    // Existing components (adjusted for new fields)
    private JPanel pnlWrapper = new JPanel(new BorderLayout());
    private JPanel pnlButton = new JPanel(new GridLayout(1, 4, 10, 0)); // Added horizontal gap
    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");

    // Changed to a more structured panel for input fields
    // Adjusted GridLayout for 4 fields (Id, Name, Email, Address) + 1 for lblRec
    private JPanel pnlInputFields = new JPanel(new GridLayout(4, 2, 10, 5)); // 4 rows, 2 columns, gaps
    private JLabel lblId = new JLabel("Id:");
    private JLabel lblName = new JLabel("Name:");
    private JLabel lblEmail = new JLabel("Email:"); // New field
    private JLabel lblAddress = new JLabel("Address:");
    private JLabel lblRec = new JLabel("Rec: 0/0", JLabel.CENTER);

    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtEmail = new JTextField(); // New field
    private JTextField txtAddress = new JTextField();

    // Panel for CRUD buttons
    private JPanel pnlCrudButtons = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column, vertical gap
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

    // Updated DefaultTableModel for the new column names
    private DefaultTableModel dtm = new DefaultTableModel(new Object[]{"Id", "Name", "Email", "Address"}, 0);
    private JTable tblStudent = new JTable(dtm); // Keep table name as tblStudent or rename to tblUser if preferred

    private Connection con;
    private Statement stm;
    private ResultSet rss;
    private int rowCount = 0;

    public User() throws SQLException, ClassNotFoundException {
        setTitle("User Management"); // Changed title
        setSize(700, 500); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        // --- Input Fields Panel ---
        pnlInputFields.add(lblId);
        pnlInputFields.add(txtId);
        pnlInputFields.add(lblName);
        pnlInputFields.add(txtName);
        pnlInputFields.add(lblEmail); // Added Email label
        pnlInputFields.add(txtEmail); // Added Email text field
        pnlInputFields.add(lblAddress);
        pnlInputFields.add(txtAddress);
        // Add padding around the input fields
        pnlInputFields.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- CRUD Buttons Panel ---
        pnlCrudButtons.add(btnInsert);
        pnlCrudButtons.add(btnUpdate);
        pnlCrudButtons.add(btnDelete);
        // Add padding around the CRUD buttons
        pnlCrudButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top Panel combining Input Fields and CRUD Buttons ---
        JPanel pnlTop = new JPanel(new BorderLayout(10, 0)); // Horizontal gap between input and CRUD
        pnlTop.add(pnlInputFields, BorderLayout.CENTER);
        pnlTop.add(pnlCrudButtons, BorderLayout.EAST);
        pnlTop.add(lblRec, BorderLayout.SOUTH); // Add lblRec below input fields and buttons

        // --- Main Wrapper Panel Setup ---
        pnlWrapper.add(pnlTop, BorderLayout.NORTH); // Top section with input fields, CRUD, and record counter
        pnlWrapper.add(new JScrollPane(tblStudent), BorderLayout.CENTER); // Table in the center
        pnlWrapper.add(pnlButton, BorderLayout.SOUTH); // Navigation buttons at the bottom

        // Add navigation buttons to their panel
        pnlButton.add(btnFirst);
        pnlButton.add(btnPrevious);
        pnlButton.add(btnNext);
        pnlButton.add(btnLast);
        // Add padding around the navigation buttons
        pnlButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(pnlWrapper); // Add the main wrapper panel to the JFrame

        try {
            String url = "jdbc:mysql://127.0.0.1:8889/Student"; // MAMP default, ensure this is correct
            String user = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password); // Initialize the connection
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            // Initial data loading
            refreshTable(); // This will execute SELECT * FROM users, populate dtm, and set rowCount
            
            // Reposition rss to the first record for UI display if records exist
            if (rowCount > 0) {
                rss.first();
            }
            updateUI(); // Call updateUI to populate fields after initial data load based on current rss position

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Action Listeners for Navigation Buttons ---
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rowCount > 0 && rss.first()) {
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rowCount > 0) {
                        if (!rss.isBeforeFirst() && rss.previous()) {
                            updateUI();
                        } else if (rss.isBeforeFirst()) {
                            // Optionally, if at the first record and trying to go previous, go to last
                            rss.last();
                            updateUI();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rowCount > 0) {
                        if (!rss.isAfterLast() && rss.next()) {
                            updateUI();
                        } else if (rss.isAfterLast()) {
                            // Optionally, if at the last record and trying to go next, go to first
                            rss.first();
                            updateUI();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rowCount > 0 && rss.last()) {
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // --- Insert Button Action ---
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText().trim();
                    String name = txtName.getText().trim();
                    String email = txtEmail.getText().trim(); // Get Email
                    String address = txtAddress.getText().trim();

                    if (id.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields are required!", "Input Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String sql = "INSERT INTO User (Id, Name, Email, Address) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setString(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, email);
                        pstmt.setString(4, address);
                        pstmt.executeUpdate();
                    }
                    refreshTable();
                    // Move to the newly inserted record
                    rss = stm.executeQuery("SELECT * FROM User");
                    rss.last();
                    rowCount = rss.getRow(); // Update row count
                    updateUI();
                    JOptionPane.showMessageDialog(null, "Record inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inserting record: " + e.getMessage(), "Insert Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- Update Button Action ---
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText().trim();
                    String name = txtName.getText().trim();
                    String email = txtEmail.getText().trim(); // Get Email
                    String address = txtAddress.getText().trim();

                    if (id.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields are required for update!", "Input Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String sql = "UPDATE User SET Name = ?, Email = ?, Address = ? WHERE Id = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setString(1, name);
                        pstmt.setString(2, email);
                        pstmt.setString(3, address);
                        pstmt.setString(4, id);
                        int updatedRows = pstmt.executeUpdate();
                        if (updatedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Record with ID " + id + " not found.", "Update Failed", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    refreshTable();
                    // Stay on the updated record by re-positioning
                    // It's tricky to stay on the exact same record if sorting/filtering changes.
                    // A more robust way would be to search for the ID in the new ResultSet.
                    // For simplicity, we re-query and try to go to the last known row, or first.
                    rss = stm.executeQuery("SELECT * FROM User");
                    if (rowCount > 0 && rss.first()) { // Go to first record if available
                        updateUI();
                    } else {
                        clearFields();
                        lblRec.setText("Rec: 0/0");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating record: " + e.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- Delete Button Action ---
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText().trim();
                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter an ID to delete.", "Input Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String sql = "DELETE FROM User WHERE Id = ?";
                        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                            pstmt.setString(1, id);
                            int deletedRows = pstmt.executeUpdate();
                            if (deletedRows > 0) {
                                JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Record with ID " + id + " not found.", "Delete Failed", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        refreshTable();
                        // Adjust ResultSet position after deletion
                        rss = stm.executeQuery("SELECT * FROM User"); // Re-query after delete
                        if (rss.last()) { // Try to move to the last record if available
                            rowCount = rss.getRow();
                            updateUI();
                        } else { // If no records left, clear fields
                            clearFields();
                            rowCount = 0;
                            lblRec.setText("Rec: 0/0");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- Window Listener to Close Resources ---
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    if (rss != null) rss.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateUI() throws SQLException {
        if (rss.isBeforeFirst() || rss.isAfterLast() || rowCount == 0) {
            clearFields();
            lblRec.setText("Rec: 0/0");
            return;
        }
        txtId.setText(rss.getString("Id"));
        txtName.setText(rss.getString("Name"));
        txtEmail.setText(rss.getString("Email")); // Get Email from ResultSet
        txtAddress.setText(rss.getString("Address"));
        lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
    }

    private void refreshTable() throws SQLException {
        rss = stm.executeQuery("SELECT * FROM User");
        dtm.setRowCount(0); // Clear the existing data

        // Store current ID to re-select after refresh
        String currentSelectedId = txtId.getText();

        rowCount = 0; // Reset row count for refresh
        while (rss.next()) {
            rowCount++;
            dtm.addRow(new Object[]{
                    rss.getString("Id"),
                    rss.getString("Name"),
                    rss.getString("Email"), // Add Email to table model row
                    rss.getString("Address")
            });
        }
        lblRec.setText("Rec: " + (rss.getRow() == 0 ? 0 : rss.getRow()) + "/" + rowCount);

        // Try to re-select the row in the table if it still exists
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (dtm.getValueAt(i, 0).equals(currentSelectedId)) {
                tblStudent.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText(""); // Clear Email field
        txtAddress.setText("");
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new User(); // Changed from DatabaseFormThree to User
    }
}