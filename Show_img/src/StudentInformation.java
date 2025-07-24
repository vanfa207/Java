import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class StudentInformation extends JFrame {
    private JLabel nameLabel, genderLabel, birthdayLabel, departmentLabel, majorLabel, yearLabel, emailLabel, imageLabel;
    private JCheckBox checkBoxFaKing, checkBoxSoPheap, checkBoxVong, checkBoxChanNa, checkBoxSmey, checkBoxVaRit;
    private JPanel panel;

    public StudentInformation() {
        setTitle("Information Student");
        setSize(768, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set Layout Manager
        setLayout(new BorderLayout());

        // Create Checkboxes
        checkBoxFaKing = new JCheckBox("Fa King");
        checkBoxSoPheap = new JCheckBox("So Pheap");
        checkBoxVong = new JCheckBox("Vong");
        checkBoxChanNa = new JCheckBox("Chan Na");
        checkBoxSmey = new JCheckBox("Smey");
        checkBoxVaRit = new JCheckBox("Va Rit");

        // Student Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(8,3));

        nameLabel = new JLabel("Name: ");
        genderLabel = new JLabel("Gender: ");
        birthdayLabel = new JLabel("Birthday: ");
        departmentLabel = new JLabel("Department: ");
        majorLabel = new JLabel("Major: ");
        yearLabel = new JLabel("Year: ");
        emailLabel = new JLabel("Email: ");
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(60, 40));
        imageLabel.setBackground(Color.GRAY);
        imageLabel.setOpaque(true);

        infoPanel.add(nameLabel);
        infoPanel.add(genderLabel);
        infoPanel.add(birthdayLabel);
        infoPanel.add(departmentLabel);
        infoPanel.add(majorLabel);
        infoPanel.add(yearLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(imageLabel);

        // Add Checkboxes and Info Panel to main Panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(checkBoxFaKing);
        panel.add(checkBoxSoPheap);
        panel.add(checkBoxVong);
        panel.add(checkBoxChanNa);
        panel.add(checkBoxSmey);
        panel.add(checkBoxVaRit);
        panel.add(infoPanel);

        // Add panel to the Frame
        add(panel, BorderLayout.CENTER);

        // Action Listener to update info on checkbox click
        checkBoxFaKing.addActionListener(e -> {
            if (checkBoxFaKing.isSelected()) {
                updateInfo("Fa King", "Male", "29/October/2004", "IT", "Computer Science", "2", "faking007@gmail.com", "cambodia.png");
            }
        });

        checkBoxSoPheap.addActionListener(e -> {
            if (checkBoxSoPheap.isSelected()) {
                updateInfo("So Pheap", "Male", "20/January/2005", "IT", "Computer Science", "2", "sopheap999@email.com", "china.png");
            }
        });

        checkBoxVong.addActionListener(e -> {
            if (checkBoxVong.isSelected()) {
                updateInfo("Vong", "Male", "15/March/2005", "IT", "Computer Science", "2", "vong777@gmail.com", "usa.png");
            }
        });

        checkBoxChanNa.addActionListener(e -> {
            if (checkBoxChanNa.isSelected()) {
                updateInfo("Chan Na", "Male", "19/May/2005", "IT", "Computer Science", "2", "channa88@email.com", "cambodia.png");
            }
        });

        checkBoxSmey.addActionListener(e -> {
            if (checkBoxSmey.isSelected()) {
                updateInfo("Smey", "Male", "10/January/2005", "IT", "Computer Science", "2", "smey009@email.com", "china.png");
            }
        });

        checkBoxVaRit.addActionListener(e -> {
            if (checkBoxVaRit.isSelected()) {
                updateInfo("Va Rit", "Male", "30/January/2005", "IT", "Computer Science", "2", "varit123@email.com", "usa.png");
            }
        });
    }

    private ImageIcon loadImage(String fileName) {
        try {
            // Load the image file from the resources folder
            URL imgUrl = getClass().getResource("/show_image/resources/" + fileName);
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage(); // Get the image from the icon
                // Resize the image to a fixed height of 150px, maintaining aspect ratio
                Image scaledImg = img.getScaledInstance(150, 90, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        return null;
    }

    // Method to update student information and display image
    private void updateInfo(String name, String gender, String birthday, String department, String major, String year, String email, String imagePath) {
        nameLabel.setText("Name: " + name);
        genderLabel.setText("Gender: " + gender);
        birthdayLabel.setText("Birthday: " + birthday);
        departmentLabel.setText("Department: " + department);
        majorLabel.setText("Major: " + major);
        yearLabel.setText("Year: " + year);
        emailLabel.setText("Email: " + email);
        imageLabel.setIcon(loadImage(imagePath));  // Load the image using the loadImage method
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentInformation().setVisible(true));
    }
}
