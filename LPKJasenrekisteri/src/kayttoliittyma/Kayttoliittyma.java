
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
        CardLayout kortitLayout = new CardLayout();
        Container kortit = new Container();
        kortit.setLayout(kortitLayout);
        
        Container alkuIkkuna = luoAlkuIkkuna(kortit, kortitLayout);
        Container paaIkkuna = luoPaaIkkuna();
        
        kortit.add(alkuIkkuna, "ALKUIKKUNA");
        kortit.add(paaIkkuna, "PAAIKKUNA");
        kortitLayout.show(kortit, "ALKUIKKUNA");
    }

    public JFrame getFrame() {
        return frame;
    }

    private Container luoAlkuIkkuna(Container kortit, CardLayout kortitLayout) {
        Container alkuIkkuna = new Container();
        BoxLayout alkuIkkunaLayout = new BoxLayout(alkuIkkuna, BoxLayout.Y_AXIS);
        alkuIkkuna.setLayout(alkuIkkunaLayout);
        
        JButton flippaus = new JButton("Flippaus");
        ToinenKuuntelija alkuKuuntelija = new ToinenKuuntelija(kortit, kortitLayout);
        flippaus.addActionListener(alkuKuuntelija);
        return alkuIkkuna;
    }

    private Container luoPaaIkkuna() {
        Container paaIkkuna = new Container();
        SpringLayout paaIkkunaLayout = new SpringLayout();
        paaIkkuna.setLayout(paaIkkunaLayout);
        
        CardLayout oikeaPaneliLayout = new CardLayout();
        Container oikeaPaneli = new Container();
        oikeaPaneli.setLayout(oikeaPaneliLayout);
   
        JButton naytaJasenet = new JButton ("Näytä jäsenet");
        JButton lisaaJasen = new JButton("Lisää jäsen");
        
        JTextArea tekstikentta = new JTextArea ();
        tekstikentta.setPreferredSize(new Dimension(200,25));
        
        Kuuntelija kuuntelija = new Kuuntelija(rekisteri, oikeaPaneli, tekstikentta, lisaaJasen, naytaJasenet); //Kaikki napit ja nupit samaan kuuntelijaan
        lisaaJasen.addActionListener(kuuntelija);
        naytaJasenet.addActionListener(kuuntelija);
        
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, naytaJasenet, 5, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, naytaJasenet, 5, SpringLayout.WEST, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, lisaaJasen, 5, SpringLayout.NORTH, naytaJasenet);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, lisaaJasen, 5, SpringLayout.WEST, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, oikeaPaneli, 5, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, oikeaPaneli, 25, SpringLayout.EAST, naytaJasenet);
        
        return paaIkkuna;
    }
    
}