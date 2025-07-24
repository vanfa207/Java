package awtform;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TeamWork extends Frame {
    private Label lblTitle = new Label("Sale Information", Label.CENTER);

    // Sale Info Fields
    private Label lblSaleId = new Label("Sale ID:");
    private TextField txtSaleId = new TextField();

    private Label lblSaleDate = new Label("Sale Date:");
    private TextField txtSaleDate = new TextField();

    private Label lblCustomerName = new Label("Customer Name:");
    private TextField txtCustomerName = new TextField();

    private Label lblCustomerId = new Label("Customer ID:");
    private TextField txtCustomerId = new TextField();

    private Label lblStaffId = new Label("Staff ID:");
    private TextField txtStaffId = new TextField();

    private Label lblAddress = new Label("Address:");
    private TextField txtAddress = new TextField();

    // Product Info Fields
    private Label lblProductName = new Label("Product Name:");
    private TextField txtProductName = new TextField();

    private Label lblQuantity = new Label("Quantity:");
    private TextField txtQuantity = new TextField();

    private Label lblUnitPrice = new Label("Unit Price:");
    private TextField txtUnitPrice = new TextField();

    private Label lblDiscount = new Label("Discount:");
    private TextField txtDiscount = new TextField();

    // Buttons
    private Button btnAdd = new Button("Add");
    private Button btnClose = new Button("Close");

    // Table and Total Section
    private TextArea txtDisplay = new TextArea();
    private Label lblTotalAmount = new Label("Total Amount: ");
    private Label lblExchangeRate = new Label("Exchange Rate: R 4100");

    // Book entries to store the added products
    private ArrayList<String> bookEntries = new ArrayList<>();

    public TeamWork() {
        setLayout(null);
        setSize(800, 600);
        setTitle("Fa Shop");
        setLocationRelativeTo(null); // Center the window on screen
        setBackground(Color.PINK);  // Set background color to pink

        int formCenter = 400; // 800px window ➔ center line is 400px

        // Title
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(formCenter - 100, 40, 200, 30);
        add(lblTitle);

        // Sale ID
        lblSaleId.setBounds(formCenter - 200, 100, 100, 30);
        add(lblSaleId);
        txtSaleId.setBounds(formCenter - 80, 100, 200, 30);
        add(txtSaleId);

        // Sale Date
        lblSaleDate.setBounds(formCenter - 200, 150, 100, 30);
        add(lblSaleDate);
        txtSaleDate.setBounds(formCenter - 80, 150, 200, 30);
        add(txtSaleDate);

        // Customer Name
        lblCustomerName.setBounds(formCenter - 200, 200, 100, 30);
        add(lblCustomerName);
        txtCustomerName.setBounds(formCenter - 80, 200, 200, 30);
        add(txtCustomerName);

        // Customer ID
        lblCustomerId.setBounds(formCenter - 200, 250, 100, 30);
        add(lblCustomerId);
        txtCustomerId.setBounds(formCenter - 80, 250, 200, 30);
        add(txtCustomerId);

        // Staff ID
        lblStaffId.setBounds(formCenter - 200, 300, 100, 30);
        add(lblStaffId);
        txtStaffId.setBounds(formCenter - 80, 300, 200, 30);
        add(txtStaffId);

        // Address
        lblAddress.setBounds(formCenter - 200, 350, 100, 30);
        add(lblAddress);
        txtAddress.setBounds(formCenter - 80, 350, 200, 30);
        add(txtAddress);

        // Product Name
        lblProductName.setBounds(formCenter - 200, 400, 100, 30);
        add(lblProductName);
        txtProductName.setBounds(formCenter - 80, 400, 200, 30);
        add(txtProductName);

        // Quantity
        lblQuantity.setBounds(formCenter - 200, 450, 100, 30);
        add(lblQuantity);
        txtQuantity.setBounds(formCenter - 80, 450, 200, 30);
        add(txtQuantity);

        // Unit Price
        lblUnitPrice.setBounds(formCenter - 200, 500, 100, 30);
        add(lblUnitPrice);
        txtUnitPrice.setBounds(formCenter - 80, 500, 200, 30);
        add(txtUnitPrice);

        // Discount
        lblDiscount.setBounds(formCenter - 200, 550, 100, 30);
        add(lblDiscount);
        txtDiscount.setBounds(formCenter - 80, 550, 200, 30);
        add(txtDiscount);

        // Buttons
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Buttons with space between them
        buttonPanel.setBounds(formCenter - 150, 600, 400, 40);

        // Add buttons to the panel
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 40));
        btnClose.setPreferredSize(new java.awt.Dimension(80, 40));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClose);

        add(buttonPanel);

        // Display Area
        txtDisplay.setBounds(formCenter - 300, 660, 600, 200);
        txtDisplay.setEditable(false);
        add(txtDisplay);

        // Total Amount and Exchange Rate
        lblTotalAmount.setBounds(formCenter - 200, 880, 100, 30);
        lblExchangeRate.setBounds(formCenter - 200, 920, 150, 30);
        add(lblTotalAmount);
        add(lblExchangeRate);

        // Button Actions
        btnAdd.addActionListener(e -> addProduct());
        btnClose.addActionListener(e -> System.exit(0));

        // Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setResizable(false);
        setVisible(true);
    }

    private void addProduct() {
        try {
            String productName = txtProductName.getText().trim();
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText().trim());
            double discount = Double.parseDouble(txtDiscount.getText().trim());

            double amount = quantity * unitPrice * (1 - discount / 100);

            // Add the product info to the display and store it
            String entry = "Product: " + productName + ", Quantity: " + quantity + ", Unit Price: " + unitPrice + ", Discount: " + discount + "%, Amount: " + amount;
            bookEntries.add(entry);
            txtDisplay.append(entry + "\n");

            // Optionally, calculate and show the total amount
            double totalAmount = bookEntries.stream().mapToDouble(e -> {
                String[] parts = e.split(",");
                double amt = Double.parseDouble(parts[4].split(":")[1].trim());
                return amt;
            }).sum();

            lblTotalAmount.setText("Total Amount: " + totalAmount);
        } catch (NumberFormatException ex) {
            txtDisplay.append("Please enter valid numeric values.\n");
        }
    }

    public static void main(String[] args) {
        new TeamWork();
    }
}
