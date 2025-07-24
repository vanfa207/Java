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
*/

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

