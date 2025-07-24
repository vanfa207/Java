package awtform;

import java.awt.*;
import java.awt.event.*;

public class Pizza extends Frame {
    private Label lblTitle = new Label("Pizza v1.0");
    private Label lblKind = new Label("Kind:");
    private Choice choiceKind = new Choice();

    private CheckboxGroup sizeGroup = new CheckboxGroup();
    private Checkbox chkSmall = new Checkbox("Small", sizeGroup, true);
    private Checkbox chkMedium = new Checkbox("Medium", sizeGroup, false);
    private Checkbox chkLarge = new Checkbox("Large", sizeGroup, false);

    private Label lblPrice = new Label("Price:");
    private TextField txtPrice = new TextField();

    private Label lblQty = new Label("Qty:");
    private TextField txtQty = new TextField();

    private TextArea txtDisplay = new TextArea();
    private Button btnAdd = new Button("Add");

    private Label lblTotal = new Label("Total:");
    private TextField txtTotal = new TextField("0");

    private int total = 0;

    public Pizza() {
        setLayout(null);
        setSize(500, 600);
        setTitle("Pizza Order");

        // Title
        lblTitle.setBounds(200, 40, 100, 30);
        add(lblTitle);

        // Kind
        lblKind.setBounds(50, 100, 100, 30);
        add(lblKind);
        choiceKind.add("A");
        choiceKind.add("B");
        choiceKind.add("C");
        choiceKind.setBounds(150, 100, 150, 30);
        add(choiceKind);

        // Price
        lblPrice.setBounds(50, 150, 100, 30);
        add(lblPrice);
        txtPrice.setBounds(150, 150, 150, 30);
        add(txtPrice);

        // Size
        chkSmall.setBounds(50, 200, 70, 30);
        chkMedium.setBounds(130, 200, 80, 30);
        chkLarge.setBounds(220, 200, 70, 30);
        add(chkSmall);
        add(chkMedium);
        add(chkLarge);

        // Quantity
        lblQty.setBounds(50, 250, 100, 30);
        add(lblQty);
        txtQty.setBounds(150, 250, 150, 30);
        add(txtQty);

        // Add Button
        btnAdd.setBounds(100, 300, 100, 30);
        add(btnAdd);

        // Display Area
        txtDisplay.setBounds(50, 350, 400, 120);
        txtDisplay.setEditable(false);
        add(txtDisplay);

        // Total
        lblTotal.setBounds(50, 480, 100, 30);
        add(lblTotal);
        txtTotal.setBounds(150, 480, 150, 30);
        txtTotal.setEditable(false);
        add(txtTotal);

        // Button Action
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int price = Integer.parseInt(txtPrice.getText().trim());
                    int qty = Integer.parseInt(txtQty.getText().trim());
                    String size = sizeGroup.getSelectedCheckbox().getLabel();
                    String kind = choiceKind.getSelectedItem();

                    int subtotal = price * qty;
                    total += subtotal;

                    txtDisplay.append("Kind: " + kind + ", Size: " + size + ", Price: " + price + ", Qty: " + qty + ", Subtotal: " + subtotal + "\n");
                    txtTotal.setText(String.valueOf(total));
                } catch (NumberFormatException ex) {
                    txtDisplay.append("Please enter valid numeric values for Price and Quantity.\n");
                }
            }
        });

        // Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pizza();
    }
}
