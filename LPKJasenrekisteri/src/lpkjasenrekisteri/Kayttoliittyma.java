
package lpkjasenrekisteri;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private LPKJasenrekisteri rekisteri;
    
    public Kayttoliittyma(){
        rekisteri = new LPKJasenrekisteri();
    }

    @Override
    public void run() {
        frame = new JFrame("Akkuna");
        frame.setPreferredSize(new Dimension(600, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(1,2);
        container.setLayout(layout);
        
        Container a = new Container();
        Container b = new Container();
        BoxLayout layoutY1 = new BoxLayout(a, BoxLayout.Y_AXIS);
        BoxLayout layoutY2 = new BoxLayout(b, BoxLayout.Y_AXIS);
        a.setLayout(layoutY1);
        b.setLayout(layoutY2);
        
        JButton hei = new JButton("hei");
        JButton mo = new JButton ("mo");
        JButton heip = new JButton ("heip");
        a.add(hei);
        a.add(mo);
        a.add(heip);
        container.add(a);
        
        JLabel kukka = new JLabel ("kukka");
        b.add(kukka);
        container.add(b);
    }

    public JFrame getFrame() {
        return frame;
    }
}
