package awtform;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Form1 extends Frame {
    private Label L1 = new Label("Input A");
    private Label L2 = new Label("Input B");
    private TextField txt1 = new TextField();
    private TextField txt2 = new TextField();
    private TextField txt3 = new TextField();
    private Button btns = new Button("Sum");
    private Button btne = new Button("Exit");

    public Form1() {
        // super("Calculate");
        L1.setBounds(50, 60, 50, 20);
        L2.setBounds(50, 100, 50, 20);
        txt1.setBounds(110, 60, 80, 20);
        txt2.setBounds(110, 100, 80, 20);
        txt3.setBounds(110, 140, 80, 20);
        btns.setBounds(50, 140, 50, 20);
        btne.setBounds(50, 180, 50, 20);

        add(L1);
        add(L2);
        add(txt1);
        add(txt2);
        add(txt3);
        add(btns);
        add(btne);

        btne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        setSize(400, 300);
        setTitle("Calculate");
        setLocationRelativeTo(null);
        setBackground(Color.pink);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Form1();
    }
}
