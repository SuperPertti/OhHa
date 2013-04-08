
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import lpkjasenrekisteri.LPKJasenrekisteri;


public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private LPKJasenrekisteri rekisteri;
    
    public Kayttoliittyma(){
        rekisteri = new LPKJasenrekisteri();
    }

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
        
        JLabel teksti = new JLabel ("Lippukunnan jäsenrekisteri ohjelma ver.0.1");
        JButton naytaJasenet = new JButton ("Näytä jäsenet");
        JTextArea tekstikentta = new JTextArea ();
        tekstikentta.setPreferredSize(new Dimension(200,25));
        
        naytaJasenet.addActionListener(new Kuuntelija(rekisteri, tekstikentta));
        
        layout.putConstraint(SpringLayout.WEST, teksti, 5, SpringLayout.WEST, container);        
        layout.putConstraint(SpringLayout.NORTH, naytaJasenet, 20, SpringLayout.SOUTH, teksti);      
        layout.putConstraint(SpringLayout.WEST, tekstikentta, 5, SpringLayout.EAST, naytaJasenet);
        layout.putConstraint(SpringLayout.NORTH, tekstikentta, 20, SpringLayout.SOUTH, teksti);
        
        container.add(teksti);
        container.add(naytaJasenet);
        container.add(tekstikentta);
        
        
        
    }

    public JFrame getFrame() {
        return frame;
    }
}
