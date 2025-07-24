package AWTLayout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class DemoBorderLayout extends Frame {
    Button bt1, bt2, bt3, bt4, bt5 ;

    public DemoBorderLayout() {
        setLayout(new BorderLayout());

        bt1 = new Button("SOUTH");
        add(bt1, BorderLayout.SOUTH);

        bt2 = new Button("WEST");
        add(bt2, BorderLayout.WEST);

        bt3 = new Button("NORTH");
        add(bt3, BorderLayout.NORTH);

        bt4 = new Button("EAST");
        add(bt4, BorderLayout.EAST);

        bt5 = new Button("CENTER");
        add(bt5, BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DemoBorderLayout();
    }
}
