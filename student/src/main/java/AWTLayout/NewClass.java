package AWTLayout;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

public class NewClass extends Frame {
    Button bt1, bt2, bt3, bt4, bt5;

    public NewClass() {
        Panel p = new Panel();
        setLayout(new FlowLayout());

        bt1 = new Button("One");
        add(bt1);

        bt2 = new Button("Two");
        add(bt2);

        bt3 = new Button("Three");
        add(bt3);

        bt4 = new Button("Four");
        add(bt4);

        bt5 = new Button("Five");
        add(bt5);

        add(p);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NewClass();
    }
}
