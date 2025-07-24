import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DatabaseFormOne extends JFrame {

    private JTextArea lblDisplay = new JTextArea();
    private JPanel pnlButton = new JPanel(new GridLayout(1, 4));
    private JButton btnFirst = new JButton("First");
    private JButton btnPrevious = new JButton("Previous");
    private JButton btnNext = new JButton("Next");
    private JButton btnLast = new JButton("Last");

    private Connection con;
    private Statement stm;
    private ResultSet rss;

    public DatabaseFormOne() {
        setTitle("Database");
        setSize(400, 250);
        setLayout(new BorderLayout());
        lblDisplay.setFont(new Font("Consolas", Font.BOLD, 18));
        lblDisplay.setEditable(false);

        pnlButton.add(btnFirst);
        pnlButton.add(btnPrevious);
        pnlButton.add(btnNext);
        pnlButton.add(btnLast);

        add(new JScrollPane(lblDisplay), BorderLayout.CENTER);
        add(pnlButton, BorderLayout.SOUTH);

        // Setup DB and load first record
        try {
            String url = "jdbc:mysql://localhost:8889/Student"; // MAMP default
            String user = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password); // FIXED: init connection
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rss = stm.executeQuery("SELECT * FROM Tbstudent");

            if (rss.first()) {
                showRecord();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Connection Error:\n" + e.getMessage());
        }

        // Button events
        btnFirst.addActionListener(e -> {
            try {
                if (rss.first()) showRecord();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        btnPrevious.addActionListener(e -> {
            try {
                if (!rss.isBeforeFirst() && rss.previous()) {
                    showRecord();
                } else {
                    // Handle the case where there are no previous records
                    rss.first(); // Optional: return to first record when going past the start
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        btnNext.addActionListener(e -> {
            try {
                if (!rss.isAfterLast() && rss.next()) {
                    showRecord();
                } else {
                    // Handle the case where we are at the last record
                    rss.last(); // Optional: return to last record when going past the end
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        btnLast.addActionListener(e -> {
            try {
                if (rss.last()) showRecord();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Cleanup on close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    if (rss != null) rss.close();
                    if (stm != null) stm.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to display the current record from the ResultSet
    private void showRecord() throws SQLException {
        lblDisplay.setText(
            "Id\t= " + rss.getString(1) +
            "\nName\t= " + rss.getString(2) +
            "\nSex\t= " + rss.getString(3) +
            "\nScore\t= " + rss.getInt(4)+
            "\nAddress\t= " + rss.getString(5)
        );
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new DatabaseFormOne();
    }
}
