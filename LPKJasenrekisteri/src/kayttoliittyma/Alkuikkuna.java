
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;


public class Alkuikkuna implements Runnable {
    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("LPKJäsenrekisteri");
        frame.setPreferredSize(new Dimension(600, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);
        
        JLabel moro = new JLabel ("Lippukunnan Jäsenrekisteri"
                + "\n momomo");
        JButton ok = new JButton ("OK");
        ok.addActionListener(new ToinenKuuntelija(frame));
        
        layout.putConstraint(SpringLayout.WEST, moro, 5, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, moro, 5, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, ok, 5, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, ok, 5, SpringLayout.SOUTH, moro);
        
        container.add(ok);
        container.add(moro);
    }

    public JFrame getFrame() {
        return frame;
    }
    }
