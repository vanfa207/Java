package awtform;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Assingement extends Frame {
    
    private Label lblTitle = new Label("Sale Information");
    private Label lblsaleid = new Label("Sale ID");
    private TextField txtsaleid = new TextField();
    private Label lblsaledate = new Label("Sale Date");
    private TextField txtsaledate = new TextField();
    private Label lblstaffid = new Label("Staff Id");
    private TextField txtstaffid = new TextField();
    private Label lblcustomername = new Label("Customer Name");
    private Choice chcusname = new Choice();
    
    private Label lblcostomerid = new Label("Customer ID");
    private TextField txtcostomerid = new TextField();
    private Label lbladdress = new Label("Address");
    private TextField txtaddress = new TextField();
    
    private Button btnNew = new Button("New");
    private Button btnClose = new Button("Close");
    private Button btnAdd = new Button("Add");
   
    private Label lblproductname = new Label("Product Name");
    private Choice chproductname = new Choice();
    
    private Label lblquantity = new Label("Quantity");
    private TextField txtquantity = new TextField();
    private Label lblunitprice = new Label("Unit Price");
    private TextField txtunitprice = new TextField();
    private Label lbldiscount = new Label("Discount");
    private TextField txtdiscount = new TextField();
    
    private TextArea txtDisplay = new TextArea("");
    
    private Font headerFont = new Font("Arial", Font.BOLD, 12);
    
    private Label lblProHead = new Label("Product Name");
    private Label lblQtyHead = new Label("Quantity");
    private Label lblUPHead = new Label("Unit Price");
    private Label lblDiscHead = new Label("Discount");
    private Label lblAmtHead = new Label("Amount");
        
    private Label lbltotalamount = new Label("Total Amount");
    private TextField txttotalamount = new TextField("0$");
    private Label lblexchangerate= new Label("Exchange Rate");
    private TextField txtexchangerate = new TextField("4100៛");
    
    float TotalAmount = 0;
    
    // Center component function
    private void centerComponent(int width, int height, Label label) {
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        label.setBounds(x, y, width, height);
    }
    
    Assingement(){
        setTitle("Information");
        setSize(900,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        // Title label (Centered)
        lblTitle.setBounds(370, 40, 100, 30);
        add(lblTitle);
        lblTitle.setBackground(Color.orange);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));

        // Sale ID
        lblsaleid.setBounds(100, 80, 100, 30);
        add(lblsaleid);
        txtsaleid.setBounds(200, 80, 100, 30);
        add(txtsaleid);

        // Customer Name Choice
        lblcustomername.setBounds(350, 80, 120, 30);
        add(lblcustomername);
        chcusname.setBounds(480, 80, 100, 30);
        add(chcusname);
        chcusname.add("fa king");
        chcusname.add("fa king");
        chcusname.add("fa king");

        // New button
        btnNew.setBounds(600, 80, 60, 30);
        add(btnNew);
        
        // Sale Date and Customer ID
        lblsaledate.setBounds(100, 140, 100, 30);
        add(lblsaledate);
        txtsaledate.setBounds(200, 140, 100, 30);
        add(txtsaledate);
        lblcostomerid.setBounds(350, 140, 100, 30);
        add(lblcostomerid);
        txtcostomerid.setBounds(480, 140, 100, 30);
        add(txtcostomerid);

        // Close button
        btnClose.setBounds(600, 140, 60, 30);
        add(btnClose);
        
        // Staff ID and Address
        lblstaffid.setBounds(100, 200, 100, 30);
        add(lblstaffid); 
        txtstaffid.setBounds(200, 200, 100, 30);
        add(txtstaffid);
        lbladdress.setBounds(350, 200, 100, 30);
        add(lbladdress);
        txtaddress.setBounds(480, 200, 100, 30);
        add(txtaddress);

        // Add button
        btnAdd.setBounds(600, 200, 60, 30);
        add(btnAdd);
        
        // Product details
        lblproductname.setBounds(70, 260, 150, 30);
        add(lblproductname);
        chproductname.setBounds(70, 290, 150, 30);
        add(chproductname);
        chproductname.add("Coca");
        chproductname.add("Sting");
        chproductname.add("Workz");

        // Quantity
        lblquantity.setBounds(250, 260, 150, 30);
        add(lblquantity);
        txtquantity.setBounds(250, 290, 150, 30);
        add(txtquantity);

        // Unit Price
        lblunitprice.setBounds(430, 260, 150, 30);
        add(lblunitprice);
        txtunitprice.setBounds(430, 290, 150, 30);
        add(txtunitprice);

        // Discount
        lbldiscount.setBounds(610, 260, 150, 30);
        add(lbldiscount);
        txtdiscount.setBounds(610, 290, 150, 30);
        add(txtdiscount);
        
        // Text Area to display product details
        txtDisplay.setBounds(70, 350, 700, 200);
        add(txtDisplay);
        
        // Column headers
        lblProHead.setBounds(70, 330, 150, 20);
        add(lblProHead);
        lblQtyHead.setBounds(220, 330, 150, 20);
        add(lblQtyHead);
        lblUPHead.setBounds(370, 330, 150, 20);
        add(lblUPHead);
        lblDiscHead.setBounds(520, 330, 150, 20);
        add(lblDiscHead);
        lblAmtHead.setBounds(670, 330, 100, 20);
        add(lblAmtHead);
        
        // Column header styling
        lblProHead.setFont(headerFont);
        lblQtyHead.setFont(headerFont);
        lblUPHead.setFont(headerFont);
        lblDiscHead.setFont(headerFont);
        lblAmtHead.setFont(headerFont);
        
        // Column header background color
        lblProHead.setBackground(Color.LIGHT_GRAY);
        lblQtyHead.setBackground(Color.LIGHT_GRAY);
        lblUPHead.setBackground(Color.LIGHT_GRAY);
        lblDiscHead.setBackground(Color.LIGHT_GRAY);
        lblAmtHead.setBackground(Color.LIGHT_GRAY);
        
        // Total Amount
        lbltotalamount.setBounds(280, 550, 100, 30);
        add(lbltotalamount);
        txttotalamount.setBounds(400, 550, 150, 30);
        add(txttotalamount);
        
        // Exchange Rate
        lblexchangerate.setBounds(280, 600, 100, 30);
        add(lblexchangerate);
        txtexchangerate.setBounds(400, 600, 150, 30);
        add(txtexchangerate);
        
        // Window close listener
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        
        // New button action
        btnNew.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                chproductname.select(0);
                txtquantity.setText("");
                txtunitprice.setText("");
                txtdiscount.setText("");
            }
        });

        // Close button action
        btnClose.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            } 
        });

        // Add button action
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int qty = Integer.parseInt(txtquantity.getText());
                int uprice = Integer.parseInt(txtunitprice.getText());
                int disc = Integer.parseInt(txtdiscount.getText());
                float dis = disc / 100f; 
                int total = qty * uprice;
                float Amount = total - (total * dis);
                
                txtDisplay.append(
                    chproductname.getSelectedItem() + "\t" +
                    txtquantity.getText() + "\t" +
                    txtunitprice.getText() + "\t" +
                    txtdiscount.getText() + "\t" +
                    Amount + "\n"
                );
                
                TotalAmount += Amount;
                txttotalamount.setText(String.format("%.2f $", TotalAmount));
                txttotalamount.setFont(new Font("Arial", Font.BOLD, 13));
                txttotalamount.setForeground(Color.BLACK);
    
                float ExchangeRate = TotalAmount * 4100;
                txtexchangerate.setText(String.format("%.2f ៛", ExchangeRate));
                txtexchangerate.setFont(new Font("Arial", Font.BOLD, 15));
                txtexchangerate.setForeground(Color.red); 
           }
        });
    }
    
    public static void main(String[] args){
        new Assingement();
    }
}
