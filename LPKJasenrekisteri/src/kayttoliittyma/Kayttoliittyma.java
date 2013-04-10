
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
        SpringLayout mainLayout = new SpringLayout();
        container.setLayout(mainLayout);
        Container pelikentta = new Container();
        BoxLayout peliLayout = new BoxLayout(pelikentta, BoxLayout.Y_AXIS);
        pelikentta.setLayout(peliLayout);
        
        
        JLabel teksti = new JLabel ("Lippukunnan jäsenrekisteri ohjelma ver.0.1");
        
        JButton naytaJasenet = new JButton ("Näytä jäsenet");
        JButton lisaaJasen = new JButton("Lisää jäsen");
        
        JTextArea tekstikentta = new JTextArea ();
        tekstikentta.setPreferredSize(new Dimension(200,25));
        
        Kuuntelija kuuntelija = new Kuuntelija(rekisteri, pelikentta, tekstikentta, lisaaJasen, naytaJasenet); //Kaikki napit ja nupit samaan kuuntelijaan
        lisaaJasen.addActionListener(kuuntelija);
        naytaJasenet.addActionListener(kuuntelija);
        
        mainLayout.putConstraint(SpringLayout.WEST, teksti, 5, SpringLayout.WEST, container);        
        mainLayout.putConstraint(SpringLayout.NORTH, naytaJasenet, 20, SpringLayout.SOUTH, teksti);      
        mainLayout.putConstraint(SpringLayout.WEST, pelikentta, 5, SpringLayout.EAST, naytaJasenet);
        mainLayout.putConstraint(SpringLayout.NORTH, pelikentta, 20, SpringLayout.SOUTH, teksti);
        mainLayout.putConstraint(SpringLayout.WEST, lisaaJasen, 5, SpringLayout.WEST, container);
        mainLayout.putConstraint(SpringLayout.NORTH, lisaaJasen, 5, SpringLayout.SOUTH, naytaJasenet);
        
        container.add(teksti);
        container.add(naytaJasenet);
        container.add(lisaaJasen);
        
        container.add(pelikentta);
    }

    public JFrame getFrame() {
        return frame;
    }
    
}