/**
 * Graaffinen käyttöliittymä
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import lpkjasenrekisteri.Henkilo;
import lpkjasenrekisteri.LPKJasenrekisteri;


public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private LPKJasenrekisteri rekisteri;
    
    public Kayttoliittyma(){
        rekisteri = new LPKJasenrekisteri();
        rekisteri.kaynnista();
    }

    @Override
    public void run() {
        frame = new JFrame("LPKJäsenrekisteri");
        frame.setPreferredSize(new Dimension(800, 400));

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
/**
 * Luo alkuikkunan
 * @param kakku
 * @param mainLayout
 * @return 
 */
    public JPanel luoAlkuIkkuna(JPanel kakku, CardLayout mainLayout) {
        JPanel alkuIkkuna = new JPanel();
        SpringLayout alkuIkkunaLayout = new SpringLayout();
        alkuIkkuna.setLayout(alkuIkkunaLayout);
        
        JTextArea tekstiKentta = new JTextArea("Lippukunnan jäsenrekisteriohjelma."
                + "\n"
                + "\nTähän ikkunaan tulee rekisteritietojen tallennus- sekä lukupolun asetus"
                + "\nja muut esitiedot"
                + "\n"
                + "\nLuet rekisteritietoja alla olevasta osoitteesta."
                + "\nTiedostopolkua ei tämän hetkisessä ohjelmaversiossa voi vaihtaa.");
        tekstiKentta.setEditable(false);
        
        JButton vaihdaTiedostoPolkuNappi = new JButton ("Default");
        JTextField tiedostoPolkuKentta = new JTextField (rekisteri.getPolku());
        JButton siirryPaaIkkunaanNappi = new JButton("OK");
        Kuuntelija alkuKuuntelija = new Kuuntelija(kakku, mainLayout, siirryPaaIkkunaanNappi);
        
        siirryPaaIkkunaanNappi.addActionListener(alkuKuuntelija);
        
        alkuIkkunaLayout.putConstraint(SpringLayout.NORTH, tekstiKentta, 10, SpringLayout.NORTH, alkuIkkuna);
        alkuIkkunaLayout.putConstraint(SpringLayout.WEST, tekstiKentta, 10, SpringLayout.WEST, alkuIkkuna);
   
        alkuIkkunaLayout.putConstraint(SpringLayout.WEST, vaihdaTiedostoPolkuNappi, 10, SpringLayout.WEST, alkuIkkuna);
        alkuIkkunaLayout.putConstraint(SpringLayout.NORTH, vaihdaTiedostoPolkuNappi, 10, SpringLayout.SOUTH, tekstiKentta);
        alkuIkkunaLayout.putConstraint(SpringLayout.WEST, tiedostoPolkuKentta, 10, SpringLayout.EAST, vaihdaTiedostoPolkuNappi);
        alkuIkkunaLayout.putConstraint(SpringLayout.NORTH, tiedostoPolkuKentta, 10, SpringLayout.SOUTH, tekstiKentta);
        
        alkuIkkunaLayout.putConstraint(SpringLayout.SOUTH, siirryPaaIkkunaanNappi, -10, SpringLayout.SOUTH, alkuIkkuna);
        alkuIkkunaLayout.putConstraint(SpringLayout.WEST, siirryPaaIkkunaanNappi, 10, SpringLayout.WEST, alkuIkkuna);
        
        alkuIkkuna.add(tekstiKentta);
        alkuIkkuna.add(siirryPaaIkkunaanNappi);
        alkuIkkuna.add(vaihdaTiedostoPolkuNappi);
        alkuIkkuna.add(tiedostoPolkuKentta);
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
        JButton tallenna = new JButton ("Tallenna");

        
        vasenPaneli.add(naytaJasenetNappi);
        vasenPaneli.add(lisaaJasenNappi);
        vasenPaneli.add(poistaJasenNappi);
        vasenPaneli.add(tallenna);
        
//OIKEA PANEELI:::       
        CardLayout oikeaPaneliLayout = new CardLayout();
        JPanel oikeaPaneli = new JPanel();
        oikeaPaneli.setLayout(oikeaPaneliLayout);
        
        JPanel naytaJasenetPanel = luoNaytaJasenetPanel();
        JPanel lisaaJasenPanel = luoLisaaJasenPanel();
        JPanel poistaJasenPanel = poistaJasenPanel();
        
        oikeaPaneli.add(naytaJasenetPanel, "NAYTAJASENET");
        oikeaPaneli.add(lisaaJasenPanel, "LISAAJASEN");
        oikeaPaneli.add(poistaJasenPanel, "POISTAJASEN");
        
//VASEMMAN PANELIN NAPPIEN KUUNTELIJAT:::
        Kuuntelija vasenPaneliKuuntelija = new Kuuntelija (this, frame, oikeaPaneli, oikeaPaneliLayout, naytaJasenetNappi, naytaJasenetPanel, lisaaJasenNappi, poistaJasenNappi );
        naytaJasenetNappi.addActionListener(vasenPaneliKuuntelija);
        lisaaJasenNappi.addActionListener(vasenPaneliKuuntelija);
        poistaJasenNappi.addActionListener(vasenPaneliKuuntelija);
        tallenna.addActionListener(new Kuuntelija(rekisteri, tallenna));
        
//PÄÄIKKUNAN ELEMENTTIEN ASETTELU
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, vasenPaneli, 10, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, vasenPaneli, 10, SpringLayout.WEST, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.NORTH, oikeaPaneli, 10, SpringLayout.NORTH, paaIkkuna);
        paaIkkunaLayout.putConstraint(SpringLayout.WEST, oikeaPaneli, 10, SpringLayout.EAST, vasenPaneli);
        paaIkkuna.add(vasenPaneli);
        paaIkkuna.add(oikeaPaneli);
        return paaIkkuna;
    }

    public JPanel luoNaytaJasenetPanel() {
        JPanel naytaJasenetPanel = new JPanel();
        ArrayList<Henkilo> henkilot = rekisteri.getHenkilot();
//OTSIKKORIVI:::
        String [] otsikkorivi = {"Nimi", "Ikä", "Ryhmä"};
//HENKILÖIDEN LUKU LISTALTA JA RIVITYS
        Object [][] pahuudenTaulukko = new Object [henkilot.size()][];
        int rivinumero = 0;
        for (Henkilo henkilo : henkilot) {
            Object [] rivi = {henkilo.getNimi(), henkilo.getSyntymaaika().toString(), henkilo.getRyhma()};
            pahuudenTaulukko[rivinumero] = rivi;
            rivinumero ++;
        }
        JTable taulukko = new JTable(pahuudenTaulukko, otsikkorivi);
        JScrollPane liukutsydeemi = new JScrollPane(taulukko);
        taulukko.setFillsViewportHeight(true);
        naytaJasenetPanel.add(liukutsydeemi);
        return naytaJasenetPanel;
    }

    private JPanel luoLisaaJasenPanel() {  
        SpringLayout lisaaJasenLayout = new SpringLayout();
        JPanel lisaaJasenPanel = new JPanel(lisaaJasenLayout);
        
        JLabel nimi = new JLabel ("Nimi");
        JLabel syntymaaika = new JLabel ("Syntymäaika");
        JLabel ryhma = new JLabel ("Ryhmä");
        JTextField nimiKentta = new JTextField();
        JTextField syntymaaikaKentta = new JTextField();
        JTextField ryhmaKentta = new JTextField();
        
        JLabel syntymaajanMaarite = new JLabel ("Anna syntymäaika muotoa 'paiva:kuukausi:vuosi', esim '1:1:1990'");
        
//TEKSTIKENTTIEN KOKO:::        
        nimiKentta.setPreferredSize(new Dimension(100,25));
        syntymaaikaKentta.setPreferredSize(new Dimension(100,25));
        ryhmaKentta.setPreferredSize(new Dimension(100,25));
        
        JButton lisaaJasenNappi = new JButton("Lisää jäsen");
        Kuuntelija lisaaJasenKuuntelija = new Kuuntelija(frame, rekisteri, nimiKentta, syntymaaikaKentta, ryhmaKentta, lisaaJasenNappi);
        lisaaJasenNappi.addActionListener(lisaaJasenKuuntelija);
        
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, nimi, 10, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, nimi, 10, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, nimiKentta, 150, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, nimiKentta, 10, SpringLayout.NORTH, lisaaJasenPanel);
        
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, syntymaaika, 10, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, syntymaaika, 10, SpringLayout.SOUTH, nimi);
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, syntymaaikaKentta, 150, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, syntymaaikaKentta, 10, SpringLayout.SOUTH, nimi);
        
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, ryhma, 10, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, ryhma, 10, SpringLayout.SOUTH, syntymaaika);
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, ryhmaKentta, 150, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, ryhmaKentta, 10, SpringLayout.SOUTH, syntymaaika);
        
        lisaaJasenLayout.putConstraint(SpringLayout.WEST, lisaaJasenNappi, 10, SpringLayout.WEST, lisaaJasenPanel);
        lisaaJasenLayout.putConstraint(SpringLayout.NORTH, lisaaJasenNappi, 10, SpringLayout.SOUTH, ryhma);
        
        lisaaJasenPanel.add(nimi);
        lisaaJasenPanel.add(nimiKentta);
        lisaaJasenPanel.add(syntymaaika);
        lisaaJasenPanel.add(syntymaaikaKentta);
        lisaaJasenPanel.add(ryhma);
        lisaaJasenPanel.add(ryhmaKentta);
        lisaaJasenPanel.add(lisaaJasenNappi);
        return lisaaJasenPanel;
    }

    private JPanel poistaJasenPanel() {
        JPanel poistaJasenPanel = new JPanel();
        return poistaJasenPanel;
    }
}