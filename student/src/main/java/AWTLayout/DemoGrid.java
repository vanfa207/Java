package AWTLayout;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

class DemoGrid extends Frame {

    Button bt1, bt2, bt3, bt4, bt5, bt6;

    public DemoGrid() {
        Panel p = new Panel();
        p.setLayout(new GridLayout(3, 2));

        bt1 = new Button("One");
        p.add(bt1);

        bt2 = new Button("Two");
        p.add(bt2);

        bt3 = new Button("Three");
        p.add(bt3);

        bt4 = new Button("Four");
        p.add(bt4);

        bt5 = new Button("Five");
        p.add(bt5);

        bt6 = new Button("Six");
        p.add(bt6);

        add(p);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DemoGrid();
    }
}
