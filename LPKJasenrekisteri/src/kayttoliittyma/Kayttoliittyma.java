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
import javax.swing.JTextField;
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
            
            JPanel alkuIkkuna = luoAlkuIkkuna(kakku, mainLayout);
            JPanel paaIkkuna = luoPaaIkkuna();
            
            kakku.add(alkuIkkuna, "ALKUIKKUNA");
            kakku.add(paaIkkuna, "PAAIKKUNA");
            mainLayout.show(kakku, "ALKUIKKUNA");
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel luoAlkuIkkuna(JPanel kakku, CardLayout mainLayout) {
        JPanel alkuIkkuna = new JPanel();
        BoxLayout alkuIkkunaLayout = new BoxLayout(alkuIkkuna, BoxLayout.Y_AXIS);
        alkuIkkuna.setLayout(alkuIkkunaLayout);
        
        JTextArea kentta = new JTextArea("Lippukunnan jäsenrekisteriohjelma."
                + "\n"
                + "\nTähän ikkunaan tulee rekisteritietojen tallennus- sekä lukupolun asetus"
                + "\nja muut esitiedot");
        kentta.setEditable(false);
        
        JButton siirryPaaIkkunaan = new JButton("OK");
        Kuuntelija alkuKuuntelija = new Kuuntelija(kakku, mainLayout, siirryPaaIkkunaan);
        siirryPaaIkkunaan.addActionListener(alkuKuuntelija);
        alkuIkkuna.add(kentta);
        alkuIkkuna.add(siirryPaaIkkunaan);
        return alkuIkkuna;
    }

    public JPanel luoPaaIkkuna() {
        SpringLayout paaIkkunaLayout = new SpringLayout();
        JPanel paaIkkuna = new JPanel();
        paaIkkuna.setLayout(paaIkkunaLayout);
        
//VASEN PANEELI:::        
        GridLayout vasenPaneliLayout = new GridLayout(10,1);
        JPanel vasenPaneli = new JPanel();
        vasenPaneli.setLayout(vasenPaneliLayout);
        
        JButton naytaJasenetNappi = new JButton("Näytä jäsenet");
        JButton lisaaJasenNappi = new JButton("Lisää jäsen");
        JButton poistaJasenNappi = new JButton ("Poista Jäsen");

        
        vasenPaneli.add(naytaJasenetNappi);
        vasenPaneli.add(lisaaJasenNappi);
        vasenPaneli.add(poistaJasenNappi);
        
//OIKEA PANEELI:::       
        CardLayout oikeaPaneliLayout = new CardLayout();
        JPanel oikeaPaneli = new JPanel();
        oikeaPaneli.setLayout(oikeaPaneliLayout);
        
        JPanel naytaJasenetPanel = new JPanel();
        JPanel lisaaJasenPanel = new JPanel();
        lisaaJasenPanel.add(new JLabel("LISÄÄ JÄSEN"));
        naytaJasenetPanel.add(new JLabel("NAYTA JÄSEN"));
        
        oikeaPaneli.add(naytaJasenetPanel, "NAYTAJASENET");
        oikeaPaneli.add(lisaaJasenPanel, "LISAAJASEN");
        
//VASEMMAN PANELIN NAPPIEN KUUNTELIJAT:::
        Kuuntelija vasenPaneliKuuntelija = new Kuuntelija (oikeaPaneli, oikeaPaneliLayout, naytaJasenetNappi, lisaaJasenNappi, poistaJasenNappi );
        naytaJasenetNappi.addActionListener(vasenPaneliKuuntelija);
        lisaaJasenNappi.addActionListener(vasenPaneliKuuntelija);
        
//PÄÄIKKUNAN ELEMENTTIEN ASETTELU
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, vasenPaneli, 10, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, vasenPaneli, 10, SpringLayout.WEST, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, oikeaPaneli, 10, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, oikeaPaneli, 10, SpringLayout.EAST, vasenPaneli);
        paaIkkuna.add(vasenPaneli);
        paaIkkuna.add(oikeaPaneli);
        return paaIkkuna;
    }
    
}