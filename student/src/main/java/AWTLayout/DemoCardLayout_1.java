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

//class Card1 extends JPanel implements ActionListener{
//    JPanel fruits ,animals;
//    JPanel cardholder =new JPanel();
//    CardLayout cl;
//
//    public Card1(){
//        setLayout(new java.awt.FlowLayout());
//        
//        cl= new CardLayout();
//        cardholder.setLayout(cl);
//        
//        fruits=new JPanel();
//        fruits.setLayout(new GridLayout(2, 4));
//        fruits.add(new JButton("Apple"));
//        fruits.add(new JButton("Banana"));
//        fruits.add(new JButton("Grapes"));
//        fruits.add(new JButton("Jackfruit"));
//        fruits.add(new JButton("Mango"));
//        fruits.add(new JButton("Lemon"));
//        fruits.add(new JButton("Orange"));
//        fruits.add(new JButton("Coconut"));
//
//        animals=new JPanel();
//        animals.setLayout(new GridLayout(2, 4));
//        animals.add(new JButton("Elephant"));
//        animals.add(new JButton("Cow"));
//        animals.add(new JButton("Lion"));
//        animals.add(new JButton("Tiger"));
//        animals.add(new JButton("Dog"));
//        animals.add(new JButton("Cat"));
//        animals.add(new JButton("Zebra"));
//        animals.add(new JButton("Rabbit"));
//        
//        cardholder.add(fruits,"Card1");
//        cardholder.add(animals,"Card2");
//
//        add(cardholder);
//
//        cl.show(cardholder, "Card2");
//
//        JButton nxt=new JButton("Next card");
//        nxt.addActionListener(this);
//        add(nxt);
//    }
//    
//    public void ActionPerformed(ActionEvent ae) {
//        cl.next(cardholder);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
public class DemoCardLayout_1 extends JFrame{
    public DemoCardLayout_1(String str){
        super(str);
        add(new Card1());
        setSize(450,230);

        addWindowListener(new WindowAdapter(){
            public void WindowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame clfrm = new DemoCardLayout_1("CardLayout on JFrame");
        clfrm.setLocationRelativeTo(null);
        clfrm.setResizable(false);
        clfrm.setVisible(true);
    }
}
