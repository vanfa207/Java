package awtform;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Form4 extends Frame {
    private Label lblTitle = new Label("Book Store", Label.CENTER);
    
    private Label lblId = new Label("Book ID:");
    private TextField txtId = new TextField();
    
    private Label lblName = new Label("Book Name:");
    private TextField txtName = new TextField();
    
    private Label lblPrice = new Label("Book Price:");
    private TextField txtPrice = new TextField();
    
    private Label lblDiscount = new Label("Discount:");
    private CheckboxGroup discountGroup = new CheckboxGroup();
    private Checkbox chk5Percent = new Checkbox("5%", discountGroup, false);
    private Checkbox chk10Percent = new Checkbox("10%", discountGroup, false);
    
    private Button btnAdd = new Button("Add");
    private Button btnClear = new Button("Clear");
    private Button btnExit = new Button("Exit");
    private Button btnDelete = new Button("Delete");
    
    private TextArea txtDisplay = new TextArea();
    
    private ArrayList<String> bookEntries = new ArrayList<>();
    
    public Form4() {
        setLayout(null);
        setSize(800, 600);
        setTitle("Book Store");
        setLocationRelativeTo(null); // Center the window on screen

        int formCenter = 400; // 800px window ➔ center line is 400px

        // Title
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(formCenter - 100, 40, 200, 30);
        add(lblTitle);

        // Book ID
        lblId.setBounds(formCenter - 200, 100, 100, 30);
        add(lblId);
        txtId.setBounds(formCenter - 80, 100, 200, 30);
        add(txtId);

        // Book Name
        lblName.setBounds(formCenter - 200, 150, 100, 30);
        add(lblName);
        txtName.setBounds(formCenter - 80, 150, 200, 30);
        add(txtName);

        // Book Price
        lblPrice.setBounds(formCenter - 200, 200, 100, 30);
        add(lblPrice);
        txtPrice.setBounds(formCenter - 80, 200, 200, 30);
        add(txtPrice);

        // Discount Checkbox
        lblDiscount.setBounds(formCenter - 200, 250, 100, 30);
        add(lblDiscount);
        chk5Percent.setBounds(formCenter - 80, 250, 60, 30);
        add(chk5Percent);
        chk10Percent.setBounds(formCenter + 30, 250, 60, 30);
        add(chk10Percent);

        // Button Panel
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Center buttons with a gap of 20px
        buttonPanel.setBounds(formCenter - 150, 300, 400, 40);

        // Add buttons to the panel
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnExit);

        add(buttonPanel);

        // Display Area
        txtDisplay.setBounds(formCenter - 300, 360, 600, 220);
        txtDisplay.setEditable(false);
        add(txtDisplay);

        // Button Actions
        btnAdd.addActionListener(e -> addBook());
        btnClear.addActionListener(e -> clearDisplay());
        btnDelete.addActionListener(e -> deleteLastEntry());
        btnExit.addActionListener(e -> System.exit(0));

        // Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setResizable(false);
        setVisible(true);
    }

    private void addBook() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            double discount = 0;

            if (chk5Percent.getState()) {
                discount = 5;
            } else if (chk10Percent.getState()) {
                discount = 10;
            }

            double finalPrice = price - (price * discount / 100);

            String entry = "ID: " + id + ", Name: " + name + ", Price: " + price + ", Discount: " + discount + "%, Final Price: " + finalPrice;
            bookEntries.add(entry);
            txtDisplay.append(entry + "\n");
        } catch (NumberFormatException ex) {
            txtDisplay.append("Please enter valid numeric values for Price.\n");
        }
    }

    private void clearDisplay() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        chk5Percent.setState(false);
        chk10Percent.setState(false);
        txtDisplay.setText("");
        bookEntries.clear();
    }

    private void deleteLastEntry() {
        if (!bookEntries.isEmpty()) {
            bookEntries.remove(bookEntries.size() - 1);
            txtDisplay.setText("");
            for (String entry : bookEntries) {
                txtDisplay.append(entry + "\n");
            }
        } else {
            txtDisplay.append("No entries to delete.\n");
        }
    }

    public static void main(String[] args) {
        new Form4();
    }
}
