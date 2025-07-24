package show_image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Show_img extends JFrame implements ActionListener {
    JCheckBox jbx1, jbx2, jbx3;
    JLabel answer1, answer2, answer3;

    Show_img(String title) {
        super(title);

        // Set the window to close when the user clicks the "X" button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set the window size
        setSize(768, 480);

        // Create checkboxes
        jbx1 = new JCheckBox("USA");
        jbx2 = new JCheckBox("Cambodia");
        jbx3 = new JCheckBox("China");

        // Add action listeners to checkboxes
        jbx1.addActionListener(this);
        jbx2.addActionListener(this);
        jbx3.addActionListener(this);

        // Create labels to display images
        answer1 = new JLabel("", SwingConstants.CENTER);
        answer2 = new JLabel("", SwingConstants.CENTER);
        answer3 = new JLabel("", SwingConstants.CENTER);

        // Use GridBagLayout for flexible positioning
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set alignment and spacing for the checkboxes
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 20, 10); // Top, Left, Bottom, Right padding
        mainPanel.add(createCheckboxPanel(), gbc);

        // Set alignment and spacing for the images
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        mainPanel.add(answer1, gbc);

        gbc.gridx = 1;
        mainPanel.add(answer2, gbc);

        gbc.gridx = 2;
        mainPanel.add(answer3, gbc);

        // Add the main panel to the frame
        add(mainPanel);

        // Center the window on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to create the checkbox panel
    private JPanel createCheckboxPanel() {
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 30));
        checkboxPanel.add(jbx1);
        checkboxPanel.add(jbx2);
        checkboxPanel.add(jbx3);
        return checkboxPanel;
    }

    // Load and resize images from the resources folder
    private ImageIcon loadImage(String fileName) {
        try {
            // Load the image file from the resources folder
            URL imgUrl = getClass().getResource("/show_image/resources/" + fileName);
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage(); // Get the image from the icon
                // Resize the image to a fixed height of 150px, maintaining aspect ratio
                Image scaledImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        return null;
    }

    // Handle checkbox actions
    public void actionPerformed(ActionEvent ae) {
        // Set the images based on checkbox selections
        if (jbx1.isSelected()) {
            answer1.setIcon(loadImage("usa.png"));
        } else {
            answer1.setIcon(null);
        }

        if (jbx2.isSelected()) {
            answer2.setIcon(loadImage("cambodia.png"));
        } else {
            answer2.setIcon(null);
        }

        if (jbx3.isSelected()) {
            answer3.setIcon(loadImage("china.png"));
        } else {
            answer3.setIcon(null);
        }
    }

    // Main method to start the program
    public static void main(String[] args) throws Exception {
        // Use the system look and feel for the application
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Show_img("Country Flags Viewer");
    }
}
