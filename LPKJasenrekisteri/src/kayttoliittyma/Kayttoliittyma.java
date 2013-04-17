/**
 * Graaffinen käyttöliittymä
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
/**
 * Luo käyttöliittymän komponentit
 * @param container 
 */
    private void luoKomponentit(Container container) {
         JPanel kakku = (JPanel) container;
         
        CardLayout mainLayout = new CardLayout();
        kakku.setLayout(mainLayout);
        
//        JPanel kortti1 = new JPanel();
//        JPanel kortti2 = new JPanel();
//        kortti1.add(new JLabel ("eka"));
//        kortti2.add(new JLabel ("toka"));
//        container.add(kortti1, "EKA");
//        container.add(kortti2, "TOKA");
//        kortitLayout.last(container);
            
            JPanel alkuIkkuna = luoAlkuIkkuna(kakku, mainLayout);
            JPanel paaIkkuna = luoPaaIkkuna();
            
            kakku.add(alkuIkkuna, "EKA");
            kakku.add(paaIkkuna, "TOKA");
            mainLayout.show(kakku, "EKA");
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel luoAlkuIkkuna(JPanel kakku, CardLayout mainLayout) {
        JPanel alkuIkkuna = new JPanel();
        BoxLayout alkuIkkunaLayout = new BoxLayout(alkuIkkuna, BoxLayout.Y_AXIS);
        alkuIkkuna.setLayout(alkuIkkunaLayout);
        
        JButton flippaus = new JButton("Flippaus");
        Kuuntelija alkuKuuntelija = new Kuuntelija(kakku, mainLayout, flippaus);
        flippaus.addActionListener(alkuKuuntelija);
        alkuIkkuna.add(flippaus);
        return alkuIkkuna;
    }

    public JPanel luoPaaIkkuna() {
        JPanel paaIkkuna = new JPanel();
        paaIkkuna.add(new JLabel("mooi"));
//        SpringLayout paaIkkunaLayout = new SpringLayout();
//        paaIkkuna.setLayout(paaIkkunaLayout);
//        
//        CardLayout oikeaPaneliLayout = new CardLayout();
//        Container oikeaPaneli = new Container();
//        oikeaPaneli.setLayout(oikeaPaneliLayout);
//   
//        JButton naytaJasenet = new JButton ("Näytä jäsenet");
//        JButton lisaaJasen = new JButton("Lisää jäsen");
//        
//        JTextArea tekstikentta = new JTextArea ();
//        tekstikentta.setPreferredSize(new Dimension(200,25));
//        
//        Kuuntelija kuuntelija = new Kuuntelija(rekisteri, oikeaPaneli, tekstikentta, lisaaJasen, naytaJasenet); //Kaikki napit ja nupit samaan kuuntelijaan
//        lisaaJasen.addActionListener(kuuntelija);
//        naytaJasenet.addActionListener(kuuntelija);
//        
//        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, naytaJasenet, 5, SpringLayout.NORTH, paaIkkuna);
//        paaIkkunaLayout.putConstraint(SpringLayout.WEST, naytaJasenet, 5, SpringLayout.WEST, paaIkkuna);
//        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, lisaaJasen, 5, SpringLayout.NORTH, naytaJasenet);
//        paaIkkunaLayout.putConstraint(SpringLayout.WEST, lisaaJasen, 5, SpringLayout.WEST, paaIkkuna);
//        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, oikeaPaneli, 5, SpringLayout.NORTH, paaIkkuna);
//        paaIkkunaLayout.putConstraint(SpringLayout.WEST, oikeaPaneli, 25, SpringLayout.EAST, naytaJasenet);
        
        return paaIkkuna;
    }
    
}