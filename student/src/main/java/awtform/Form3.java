package awtform;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Form3 extends Frame {
    private Label lblTitle = new Label("Book Store", Label.CENTER);
    private Label lblKind = new Label("Book ID:");
    private Choice choiceKind = new Choice();

    private Label lblPrice = new Label("Book QTY:");
    private TextField txtPrice = new TextField();

    private Label lblQty = new Label("Book Price:");
    private TextField txtQty = new TextField();

    private TextArea txtDisplay = new TextArea();
    private Button btnAdd = new Button("Add");
    private Button btnTotal = new Button("Total");
    private Button btnExit = new Button("Exit");

    private ArrayList<Integer> subtotals = new ArrayList<>();

    public Form3() {
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
        lblKind.setBounds(formCenter - 200, 100, 100, 30);
        add(lblKind);
        choiceKind.add("0001");
        choiceKind.add("0002");
        choiceKind.add("0003");
        choiceKind.setBounds(formCenter - 80, 100, 200, 30);
        add(choiceKind);

        // Book QTY
        lblPrice.setBounds(formCenter - 200, 150, 100, 30);
        add(lblPrice);
        txtPrice.setBounds(formCenter - 80, 150, 200, 30);
        add(txtPrice);

        // Book Price
        lblQty.setBounds(formCenter - 200, 200, 100, 30);
        add(lblQty);
        txtQty.setBounds(formCenter - 80, 200, 200, 30);
        add(txtQty);

        // Buttons
        btnAdd.setBounds(formCenter - 170, 260, 100, 40);
        add(btnAdd);

        btnTotal.setBounds(formCenter - 50, 260, 100, 40);
        add(btnTotal);

        btnExit.setBounds(formCenter + 70, 260, 100, 40);
        add(btnExit);

        // Display Area
        txtDisplay.setBounds(formCenter - 300, 320, 600, 220);
        txtDisplay.setEditable(false);
        add(txtDisplay);

        // Button Actions
        btnAdd.addActionListener(e -> addBook());
        btnTotal.addActionListener(e -> showTotal());
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
            int price = Integer.parseInt(txtPrice.getText().trim());
            int qty = Integer.parseInt(txtQty.getText().trim());
            String kind = choiceKind.getSelectedItem();

            int subtotal = price * qty;
            subtotals.add(subtotal);

            txtDisplay.append("Kind: " + kind + ", Price: " + price + ", Qty: " + qty + ", Subtotal: " + subtotal + "\n");
        } catch (NumberFormatException ex) {
            txtDisplay.append("Please enter valid numeric values for Price and Quantity.\n");
        }
    }

    private void showTotal() {
        int grandTotal = subtotals.stream().mapToInt(Integer::intValue).sum();
        txtDisplay.append("\nGrand Total: " + grandTotal + "\n");
    }

    public static void main(String[] args) {
        new Form3();
    }
}
