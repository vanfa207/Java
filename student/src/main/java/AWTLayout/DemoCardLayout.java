package AWTLayout;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

class Card1 extends JPanel implements ActionListener {
    JPanel fruits, animals;
    JPanel cardholder = new JPanel();
    CardLayout cl;

    public Card1() {
        setLayout(new java.awt.FlowLayout());

        cl = new CardLayout();
        cardholder.setLayout(cl);

        // Create the first card: fruits
        fruits = new JPanel();
        fruits.setLayout(new GridLayout(2, 4));
        fruits.add(new JButton("Apple"));
        fruits.add(new JButton("Banana"));
        fruits.add(new JButton("Grapes"));
        fruits.add(new JButton("Jackfruit"));
        fruits.add(new JButton("Mango"));
        fruits.add(new JButton("Lemon"));
        fruits.add(new JButton("Orange"));
        fruits.add(new JButton("Coconut"));

        // Create the second card: animals
        animals = new JPanel();
        animals.setLayout(new GridLayout(2, 4));
        animals.add(new JButton("Elephant"));
        animals.add(new JButton("Cow"));
        animals.add(new JButton("Lion"));
        animals.add(new JButton("Tiger"));
        animals.add(new JButton("Dog"));
        animals.add(new JButton("Cat"));
        animals.add(new JButton("Zebra"));
        animals.add(new JButton("Rabbit"));

        // Add cards to the card holder
        cardholder.add(fruits, "Card1");
        cardholder.add(animals, "Card2");

        add(cardholder);

        // Show second card as initial
        cl.show(cardholder, "Card1");

        // Add next button
        JButton nxt = new JButton("Next card");
        nxt.addActionListener(this);
        add(nxt);
    }

    public void actionPerformed(ActionEvent ae) {
        cl.next(cardholder);
    }
}

public class DemoCardLayout extends JFrame {
    public DemoCardLayout(String str) {
        super(str);
        add(new Card1());
        setSize(400, 200);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame clfrm = new DemoCardLayout("CardLayout on JFrame");
        clfrm.setLocationRelativeTo(null);
        clfrm.setResizable(false);
        clfrm.setVisible(true);
    }
}
