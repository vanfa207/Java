import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class Form1 extends JFrame {
    private JTextField nameField, phoneField;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<String[]> dataList = new ArrayList<>();

    public Form1() {
        setTitle("Entry Form");
        setSize(520, 380);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 20, 80, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 30);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(20, 60, 100, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 60, 180, 30);
        add(phoneField);

        JButton addBtn = new JButton("ADD");
        addBtn.setBounds(320, 20, 80, 30);
        add(addBtn);

        JButton goBtn = new JButton("GO");
        goBtn.setBounds(320, 60, 80, 30);
        add(goBtn);

        model = new DefaultTableModel(new String[]{"Name", "Phone Number"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 460, 200);
        add(scrollPane);

        // ✅ Add new row
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty()) {
                model.addRow(new String[]{name, phone});
                dataList.add(new String[]{name, phone});
                nameField.setText("");
                phoneField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both name and phone.");
            }
        });

        // ✅ Remove row when clicking it
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                if (selected != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Remove this entry?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(selected);
                        dataList.remove(selected);
                    }
                }
            }
        });

        // ✅ Go to Form2
        goBtn.addActionListener(e -> {
            if (dataList.size() >= 3) {
                new Form2(dataList).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "At least 3 entries required.");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Form1();
    }
}
