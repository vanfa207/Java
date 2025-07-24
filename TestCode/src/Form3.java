import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Form3 extends JFrame {
    public Form3(List<String[]> winners) {
        setTitle("🏆 The Winners");
        setSize(400, 500);
        setLayout(new GridLayout(3, 1, 10, 10));

        String[] prizeImages = {
            "imge/b2.png",
            "imge/car_bmw.png",
            "imge/moto_bmw.png",
        };

        for (int i = 0; i < winners.size(); i++) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Winner's name
            JLabel nameLabel = new JLabel(winners.get(i)[0], SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            nameLabel.setForeground(Color.RED);
            panel.add(nameLabel, BorderLayout.NORTH);

            // Image display
            ImageIcon imageIcon;
            try {
                imageIcon = new ImageIcon(getClass().getClassLoader().getResource(prizeImages[i]));
                if (imageIcon.getIconWidth() == -1) throw new Exception("Image not found in JAR/classpath.");
            } catch (Exception e) {
                System.err.println("Could not load image from classpath: " + prizeImages[i] + " — trying file path.");
                imageIcon = new ImageIcon(prizeImages[i]); // fallback
            }

            Image scaledImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            add(panel);
        }

        setLocationRelativeTo(null); // center on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
