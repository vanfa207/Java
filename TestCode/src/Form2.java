import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;

public class Form2 extends JFrame {
    private DefaultTableModel model;
    private JLabel winnerLabel;
    private javax.swing.Timer timer;
    private List<String[]> original;
    private List<String[]> selectedWinners = new ArrayList<>();
    private String[] currentWinner;

    public Form2(List<String[]> dataList) {
        setTitle("Lucky Draw");
        setSize(300, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        original = new ArrayList<>(dataList);

        winnerLabel = new JLabel("-----", SwingConstants.CENTER);
        winnerLabel.setBounds(50, 10, 200, 40);
        winnerLabel.setFont(winnerLabel.getFont().deriveFont(24f));
        add(winnerLabel);

        model = new DefaultTableModel(new String[]{"#", "Name", "Phone"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 260, 200);
        add(scrollPane);

        JButton startBtn = new JButton("START");
        startBtn.setBounds(30, 280, 100, 30);
        add(startBtn);

        JButton stopBtn = new JButton("STOP");
        stopBtn.setBounds(150, 280, 100, 30);
        add(stopBtn);

        timer = new javax.swing.Timer(100, e -> {
            Collections.shuffle(original);
            currentWinner = original.get(0);
            winnerLabel.setText(currentWinner[0]);
        });

        startBtn.addActionListener(e -> {
            if (selectedWinners.size() >= 3) {
                JOptionPane.showMessageDialog(this, "3 winners already selected!");
                return;
            }
            timer.start();
        });

        stopBtn.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();

                if (currentWinner != null) {
                    selectedWinners.add(currentWinner);
                    model.addRow(new Object[]{selectedWinners.size(), currentWinner[0], currentWinner[1]});
                    original.remove(currentWinner); // Prevent duplicate winners
                    winnerLabel.setText("Winner: " + currentWinner[0]);

                    // If 3 winners selected, show Form3
                    if (selectedWinners.size() == 3) {
                        new Form3(selectedWinners).setVisible(true);
                    }
                }
            }
        });
    }
}
