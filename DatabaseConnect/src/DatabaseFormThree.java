/*
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class DatabaseFormThree extends JFrame {
   private JPanel pnlWrapper = new JPanel(new BorderLayout());
   private JPanel pnlButton = new JPanel(new GridLayout(1, 4));
   private JButton btnFirst = new JButton("First");
   private JButton btnPreviouse = new JButton("Previouse");
   private JButton btnNext = new JButton("Next");
   private JButton btnLast = new JButton("Last");
   private GridLayout grd = new GridLayout(4, 3);
   private JPanel pnlContian = new JPanel(grd);
   private JLabel lblId = new JLabel("Id");
   private JLabel lblName = new JLabel("Name");
   private JLabel lblSex = new JLabel("Sex");
   private JLabel lblScore = new JLabel("Score");
   private JLabel lblRec = new JLabel("Rec: 0/0", JLabel.CENTER);
   private JTextField txtId = new JTextField();
   private JTextField txtName = new JTextField();
   private JTextField txtScore = new JTextField();
   private JComboBox cboSex = new JComboBox();
   private JButton btnInsert = new JButton("Insert");
   private JButton btnUpdate = new JButton("Update");
   private JButton btnDelete = new JButton("Delete");
   private DefaultTableModel dtm = new DefaultTableModel(new Object[]{"Id", "Name", "Sex", "Score"}, 0);
   private JTable tblStudent = new JTable(dtm);
   private Connection con;
   private Statement stm;
   private ResultSet rss;
   private int rowCount = 0;
	
   public DatabaseFormThree() throws SQLException, ClassNotFoundException {
      setTitle("Updating Database");
      setSize(320, 280);		
      cboSex.addItem("Male");
      cboSex.addItem("Female");
      pnlContian.add(lblId);
      pnlContian.add(txtId);
      pnlContian.add(btnInsert);
      pnlContian.add(lblName);
      pnlContian.add(txtName);
      pnlContian.add(btnUpdate);
      pnlContian.add(lblSex);
      pnlContian.add(cboSex);
      pnlContian.add(btnDelete);
      pnlContian.add(lblScore);
      pnlContian.add(txtScore);
      pnlContian.add(lblRec);
      add(pnlContian, BorderLayout.NORTH);
      grd.setHgap(5);
      grd.setVgap(5);
      pnlButton.add(btnFirst);
      pnlButton.add(btnPreviouse);
      pnlButton.add(btnNext);
      pnlButton.add(btnLast);
      add(new JScrollPane(tblStudent), BorderLayout.CENTER);
      add(pnlButton, BorderLayout.SOUTH);
      try {
         String url = "jdbc:mysql://localhost:8889/Student"; // MAMP default
         String user = "root";
         String password = "root";

         Class.forName("com.mysql.cj.jdbc.Driver");
         stm =con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         rss = stm.executeQuery("SELECT * FROM Tbstudent;");
         rss.last();
         rowCount = rss.getRow();
         rss.first();
         txtId.setText(rss.getString(1));
         txtName.setText(rss.getString(2));
         cboSex.setSelectedItem(rss.getString(3));
         txtScore.setText("" + rss.getInt(4));
         lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
         rss.beforeFirst();
        while(rss.next()) {
            dtm.addRow(new Object[]{
            rss.getString(1),
            rss.getString(2),
            rss.getString(3),
            rss.getInt(4)
            });
         }     
        rss.first();
   } catch (SQLException e) {
      e.printStackTrace();
   }     

   btnFirst.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         try {
            rss.first();
            txtId.setText(rss.getString(1));
            txtName.setText(rss.getString(2));
            cboSex.setSelectedItem(rss.getString(3));
            txtScore.setText("" + rss.getInt(4));
            lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);            
           
         } catch (SQLException e) {
            e.printStackTrace();
         }        
      }      
   });
   btnPreviouse.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         try {
            rss.previous();
            if(rss.isBeforeFirst())
               rss.first();
            else {
               txtId.setText(rss.getString(1));
               txtName.setText(rss.getString(2));
               cboSex.setSelectedItem(rss.getString(3));
               txtScore.setText("" + rss.getInt(4));
               lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
                               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   });
      btnNext.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            try {
               rss.next();
               if(rss.isAfterLast())
	rss.last();
               else {
	txtId.setText(rss.getString(1));
	txtName.setText(rss.getString(2));
	cboSex.setSelectedItem(rss.getString(3));
	txtScore.setText("" + rss.getInt(4));
	lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
	}
            } catch (SQLException e) {
	e.printStackTrace();
               }
         }
      });
   btnLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         try {
            rss.last();
            if(rss.isAfterLast())
	rss.last();
               else {
	txtId.setText(rss.getString(1));
	txtName.setText(rss.getString(2));
	cboSex.setSelectedItem(rss.getString(3));
	txtScore.setText("" + rss.getInt(4));
	lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
	}
 
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   });

   btnInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         String id = txtId.getText();
         String name = txtName.getText();
         String sex = cboSex.getSelectedItem().toString();
         String score = txtScore.getText();
         String sql = "INSERT INTO Student (Id, Name, Sex, Score) VALUES ('" + id + "', '" + name + "', '" + sex + "', " + score + ");";
         try {
            System.out.println(sql);
            stm.execute(sql);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         try {
            rss = stm.executeQuery("SELECT * FROM Student;");
            rss.last();
            rowCount = rss.getRow();
            lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
            dtm.setRowCount(0);
            rss.beforeFirst();
            while(rss.next()) {
               dtm.addRow(new Object[]{
               rss.getString(1),
               rss.getString(2),
               rss.getString(3),
               rss.getInt(4)
               });
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   });
   
   btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         String id = txtId.getText();
         String name = txtName.getText();
         String sex = cboSex.getSelectedItem().toString();
         String score = txtScore.getText();
         String sql = "UPDATE Student SET Name = '" + name + "', Sex = '" + sex + "', Score = '" + score + "' WHERE Id = " + id + ";";
         try {
            System.out.println(sql);
            stm.execute(sql);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         try {
               rss = stm.executeQuery("SELECT * FROM Student");
               rss.last();
               rowCount = rss.getRow();
               lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
               dtm.setRowCount(0);
               rss.beforeFirst();
            while(rss.next()) {
               dtm.addRow(new Object[]{
               rss.getString(1),
               rss.getString(2),
               rss.getString(3),
               rss.getInt(4)
               });
            }            
         } catch (SQLException e) {
            e.printStackTrace();
         }     
      }      
   });
   btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
         String id = txtId.getText();
         String sql = "DELETE FROM Tbstudent WHERE Id = " + id + ";";
         try {
            System.out.println(sql);
            stm.execute(sql);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         try {
            rss = stm.executeQuery("SELECT * FROM Student;");
            rss.last();
            rowCount = rss.getRow();
            lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
            dtm.setRowCount(0);
            rss.beforeFirst();
            while(rss.next()) {
               dtm.addRow(new Object[]{
                     rss.getString(1),
                     rss.getString(2),
                     rss.getString(3),
                     rss.getInt(4)
               });
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   });
   addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
         try {
            stm.close();
            rss.close();
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   });
      //setDefaultCloseOperation(EXIT_ON_CLOSE);      
      setLocationRelativeTo(null);
      setVisible(true);
   }
   public static void main(String[] args) throws Exception {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      new DatabaseFormThree();
   }
}

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class DatabaseFormThree extends JFrame {
    private JPanel pnlWrapper = new JPanel(new BorderLayout());
    private JPanel pnlButton = new JPanel(new GridLayout(1, 3));
    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");
    private GridLayout grd = new GridLayout(5, 4);
    private JPanel pnlContainer = new JPanel(grd);
    private JLabel lblId = new JLabel("Id");
    private JLabel lblName = new JLabel("Name");
    private JLabel lblSex = new JLabel("Sex");
    private JLabel lblScore = new JLabel("Score");
    private JLabel lblAddress = new JLabel("Address");
    private JLabel lblRec = new JLabel("Rec: 0/0", JLabel.CENTER);
    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtScore = new JTextField();
    private JTextField txtAddress = new JTextField();
    private JComboBox<String> cboSex = new JComboBox<>();
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private DefaultTableModel dtm = new DefaultTableModel(new Object[]{"Id", "Name", "Sex", "Score", "Address"}, 0);
    private JTable tblStudent = new JTable(dtm);
    private Connection con;
    private Statement stm;
    private ResultSet rss;
    private int rowCount = 0;

    public DatabaseFormThree() throws SQLException, ClassNotFoundException {
        setTitle("Updating Database");
        setSize(320, 400);
        cboSex.addItem("Male");
        cboSex.addItem("Female");
        pnlContainer.add(lblId);
        pnlContainer.add(txtId);
        pnlContainer.add(btnInsert);
        pnlContainer.add(lblName);
        pnlContainer.add(txtName);
        pnlContainer.add(btnUpdate);
        pnlContainer.add(lblSex);
        pnlContainer.add(cboSex);
        pnlContainer.add(btnDelete);
        pnlContainer.add(lblScore);
        pnlContainer.add(txtScore);
        pnlContainer.add(lblAddress);
        pnlContainer.add(txtAddress);
        pnlContainer.add(lblRec);
        add(pnlContainer, BorderLayout.NORTH);
        grd.setHgap(5);
        grd.setVgap(5);
        pnlButton.add(btnFirst);
        pnlButton.add(btnPrevious);
        pnlButton.add(btnNext);
        pnlButton.add(btnLast);
        add(new JScrollPane(tblStudent), BorderLayout.CENTER);
        add(pnlButton, BorderLayout.SOUTH);

        try {
            String url = "jdbc:mysql://localhost:8889/Student"; // MAMP default
            String user = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password); // Initialize the connection
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rss = stm.executeQuery("SELECT * FROM Tbstudent"); // Ensure this matches your table name

            rss.last();
            rowCount = rss.getRow();
            rss.first();
            txtId.setText(rss.getString(1));
            txtName.setText(rss.getString(2));
            cboSex.setSelectedItem(rss.getString(3));
            txtScore.setText("" + rss.getInt(4));
            txtAddress.setText("" + rss.getString(5));
            lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
            rss.beforeFirst();
            while (rss.next()) {
                dtm.addRow(new Object[]{
                        rss.getString(1),
                        rss.getString(2),
                        rss.getString(3),
                        rss.getInt(4)
                });
            }
            rss.first();
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
                    if (!rss.isBeforeFirst() && rss.previous()) {
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!rss.isAfterLast() && rss.next()) {
                        updateUI();
                    }
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

        // Insert button action
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
                    refreshTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Update button action
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = txtId.getText();
                String name = txtName.getText();
                String sex = cboSex.getSelectedItem().toString();
                String score = txtScore.getText();
                String address = txtAddress.getText();
                String sql = "UPDATE Tbstudent SET Name = '" + name + "', Sex = '" + sex + "', Score = '" + score +"'Address '" + address + "  WHERE Id = " + id + ";";
                try {
                    stm.executeUpdate(sql);
                    refreshTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Delete button action
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String id = txtId.getText();
                String sql = "DELETE FROM Tbstudent WHERE Id = " + id + ";";
                try {
                    stm.executeUpdate(sql);
                    refreshTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Window listener to close resources
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
        txtId.setText(rss.getString(1));
        txtName.setText(rss.getString(2));
        cboSex.setSelectedItem(rss.getString(3));
        txtScore.setText("" + rss.getInt(4));
        txtAddress.setText("" + rss.getString(5));
        lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
    }

    private void refreshTable() throws SQLException {
        rss = stm.executeQuery("SELECT * FROM Tbstudent");
        dtm.setRowCount(0); // Clear the existing data
        while (rss.next()) {
            dtm.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getInt(4),
                    rss.getString(5),
            });
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new DatabaseFormThree();
    }
}

*/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class DatabaseFormThree extends JFrame {
    // Existing components
    private JPanel pnlWrapper = new JPanel(new BorderLayout());
    private JPanel pnlButton = new JPanel(new GridLayout(1, 4, 10, 0)); // Added horizontal gap
    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");

    // Changed to a more structured panel for input fields
    private JPanel pnlInputFields = new JPanel(new GridLayout(5, 2, 10, 5)); // 5 rows, 2 columns, gaps
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

    // Panel for CRUD buttons
    private JPanel pnlCrudButtons = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column, vertical gap
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

    private DefaultTableModel dtm = new DefaultTableModel(new Object[]{"Id", "Name", "Sex", "Score", "Address"}, 0);
    private JTable tblStudent = new JTable(dtm);
    private Connection con;
    private Statement stm;
    private ResultSet rss;
    private int rowCount = 0;

    public DatabaseFormThree() throws SQLException, ClassNotFoundException {
        setTitle("Updating Database");
        setSize(700, 500); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        cboSex.addItem("Male");
        cboSex.addItem("Female");

        // --- Input Fields Panel ---
        pnlInputFields.add(lblId);
        pnlInputFields.add(txtId);
        pnlInputFields.add(lblName);
        pnlInputFields.add(txtName);
        pnlInputFields.add(lblSex);
        pnlInputFields.add(cboSex);
        pnlInputFields.add(lblScore);
        pnlInputFields.add(txtScore);
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
            //String url = "jdbc:mysql://localhost:8889/Student"; // MAMP default
            String url = "jdbc:mysql://127.0.0.1:8889/Student";
            String user = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password); // Initialize the connection
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //rss = stm.executeQuery("SELECT * FROM Tbstudent"); // Ensure this matches your table name
            rss = stm.executeQuery("SELECT * FROM users");
            
            rss.last();
            rowCount = rss.getRow();
            rss.first();
            updateUI(); // Call updateUI to populate fields after initial data load
            refreshTable(); // Populate the table initially
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Action listeners for navigation buttons (existing code)
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rss.first()) {
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
                    if (!rss.isBeforeFirst() && rss.previous()) {
                        updateUI();
                    } else if (rss.isBeforeFirst()) {
                        // Optionally, if at the first record and trying to go previous, go to last
                        rss.last();
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!rss.isAfterLast() && rss.next()) {
                        updateUI();
                    } else if (rss.isAfterLast()) {
                        // Optionally, if at the last record and trying to go next, go to first
                        rss.first();
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (rss.last()) {
                        updateUI();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Insert button action
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText();
                    String name = txtName.getText();
                    String sex = cboSex.getSelectedItem().toString();
                    int score = Integer.parseInt(txtScore.getText()); // Parse score to int
                    String address = txtAddress.getText();

                    //String sql = "INSERT INTO Tbstudent (Id, Name, Sex, Score, Address) VALUES (?, ?, ?, ?, ?)";
                    String sql = "INSERT INTO users (Id, Name, Sex, Score, Address) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setString(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, sex);
                        pstmt.setInt(4, score);
                        pstmt.setString(5, address);
                        pstmt.executeUpdate();
                    }
                    refreshTable();
                    // Move to the newly inserted record
                    //rss = stm.executeQuery("SELECT * FROM Tbstudent");
                    rss = stm.executeQuery("SELECT * FROM users");
                    rss.last();
                    rowCount = rss.getRow();
                    updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inserting record: " + e.getMessage(), "Insert Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid score. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Update button action
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText();
                    String name = txtName.getText();
                    String sex = cboSex.getSelectedItem().toString();
                    int score = Integer.parseInt(txtScore.getText()); // Parse score to int
                    String address = txtAddress.getText();

                    //String sql = "UPDATE Tbstudent SET Name = ?, Sex = ?, Score = ?, Address = ? WHERE Id = ?";
                    String sql = "UPDATE users SET Name = ?, Sex = ?, Score = ?, Address = ? WHERE Id = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setString(1, name);
                        pstmt.setString(2, sex);
                        pstmt.setInt(3, score);
                        pstmt.setString(4, address);
                        pstmt.setString(5, id);
                        pstmt.executeUpdate();
                    }
                    refreshTable();
                    // Stay on the updated record
                    rss.absolute(rss.getRow()); // Re-position to the current row after refresh
                    updateUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating record: " + e.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid score. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete button action
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = txtId.getText();
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        //String sql = "DELETE FROM Tbstudent WHERE Id = ?";
                        String sql = "DELETE FROM users WHERE Id = ?";
                        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                            pstmt.setString(1, id);
                            pstmt.executeUpdate();
                        }
                        refreshTable();
                        // Adjust ResultSet position after deletion
                        if (rowCount > 0) {
                            //rss = stm.executeQuery("SELECT * FROM Tbstudent"); // Re-query after delete
                            rss = stm.executeQuery("SELECT * FROM users");
                            rowCount = 0; // Reset row count
                            if (rss.last()) { // Move to the last record if available
                                rowCount = rss.getRow();
                                updateUI();
                            } else { // If no records left, clear fields
                                clearFields();
                                lblRec.setText("Rec: 0/0");
                            }
                        } else {
                            clearFields();
                            lblRec.setText("Rec: 0/0");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Window listener to close resources
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
        txtId.setText(rss.getString("Id")); // Use column names for robustness
        txtName.setText(rss.getString("Name"));
        cboSex.setSelectedItem(rss.getString("Sex"));
        txtScore.setText(String.valueOf(rss.getInt("Score"))); // Convert int to String
        txtAddress.setText(rss.getString("Address"));
        lblRec.setText("Rec: " + rss.getRow() + "/" + rowCount);
    }

    private void refreshTable() throws SQLException {
        //rss = stm.executeQuery("SELECT * FROM Tbstudent");
        rss = stm.executeQuery("SELECT * FROM users");
        dtm.setRowCount(0); // Clear the existing data
        int currentSelectedRowId = -1;
        if (rss.getRow() > 0) {
            currentSelectedRowId = Integer.parseInt(txtId.getText()); // Store current ID
        }

        rowCount = 0; // Reset row count for refresh
        while (rss.next()) {
            rowCount++;
            dtm.addRow(new Object[]{
                    rss.getString("Id"),
                    rss.getString("Name"),
                    rss.getString("Sex"),
                    rss.getInt("Score"),
                    rss.getString("Address")
            });
        }
        lblRec.setText("Rec: " + (rss.getRow() == 0 ? 0 : rss.getRow()) + "/" + rowCount);

        // Try to re-select the row in the table if it still exists
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (dtm.getValueAt(i, 0).equals(String.valueOf(currentSelectedRowId))) {
                tblStudent.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        cboSex.setSelectedIndex(0);
        txtScore.setText("");
        txtAddress.setText("");
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new DatabaseFormThree();
    }
}