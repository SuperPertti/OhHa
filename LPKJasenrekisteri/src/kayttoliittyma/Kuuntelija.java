


package kayttoliittyma;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import lpkjasenrekisteri.Henkilo;
import lpkjasenrekisteri.LPKJasenrekisteri;
import lpkjasenrekisteri.Syntymaaika;


public class Kuuntelija implements ActionListener {
    private LPKJasenrekisteri rekisteri;
    private JFrame kayttis;
    private JFrame frame;
    private JButton siirryNaytaJasenet;
    private JButton siirryLisaaJasen;
    private JButton siirryPoistaJasen;
    private CardLayout layout;
    private JButton siirryPaaIkkunaan;
    private JPanel ikkuna;
    private JTextField nimiKentta;
    private JTextField syntymaaikaKentta;
    private JTextField ryhmaKentta;
    private JButton lisaaJasen;
    private JButton sulje;
    private JButton tallenna;
    private JPanel naytaJasenetPanel;
    private Kayttoliittyma main;
    private JPanel oikeaPaneli;
    
    public Kuuntelija(LPKJasenrekisteri rekisteri, JButton lisaaJasen, JButton naytaJasenet) {
        this.rekisteri = rekisteri;
        this.siirryLisaaJasen = lisaaJasen;
        this.siirryNaytaJasenet = naytaJasenet;
        
    }
    public Kuuntelija(JPanel ikkuna, CardLayout layout, JButton siirryPaaIkkunaan){ //alkuIkkunan "OK"-napin kuuntelija, tarkoitus vaihtaa pää Panelin, kakun Layoutin seuraava kortti -> paaIkkuna
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.siirryPaaIkkunaan = siirryPaaIkkunaan;
    }
    public Kuuntelija(Kayttoliittyma main, JFrame kayttis, JPanel ikkuna, CardLayout layout, JButton naytaJasenet, JPanel naytaJasenetPanel, JButton lisaaJasen, JButton poistaJasen) { //VasemmanPanelin nappien kuuntelija
        this.main = main;
        this.kayttis = kayttis;
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.siirryNaytaJasenet = naytaJasenet;
        this.siirryLisaaJasen = lisaaJasen;
        this.siirryPoistaJasen = poistaJasen;
        this.naytaJasenetPanel = naytaJasenetPanel;
    }
    
    public Kuuntelija(Kayttoliittyma main, JPanel oikeaPaneli, JFrame kayttis, LPKJasenrekisteri rekisteri, JTextField nimiKentta, JTextField syntymaaikaKentta,JTextField ryhmaKentta, JButton lisaaJasen){ //Lisaa jasen nappi etc
        this.main = main;
        this.oikeaPaneli = oikeaPaneli;
        this.rekisteri = rekisteri;
        this.kayttis = kayttis;
        this.nimiKentta = nimiKentta;
        this.syntymaaikaKentta = syntymaaikaKentta;
        this.ryhmaKentta = ryhmaKentta;
        this.lisaaJasen = lisaaJasen;
    }
    
    public Kuuntelija (JFrame frame, JFrame kayttis, JButton sulje){
        this.frame = frame;
        this.kayttis = kayttis;
        this.sulje = sulje;
    }
    
    public Kuuntelija (LPKJasenrekisteri rekisteri, JButton tallenna){
        this.rekisteri = rekisteri;
        this.tallenna = tallenna;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(siirryPaaIkkunaan)){
            layout.next(ikkuna);
        }
        if(e.getSource().equals(siirryNaytaJasenet)){
            layout.show(ikkuna,"NAYTAJASENET");
        }
        if(e.getSource().equals(siirryLisaaJasen)){
            layout.show(ikkuna,"LISAAJASEN");
        }
        
        if(e.getSource().equals(siirryPoistaJasen)){
            layout.show(ikkuna,"POISTAJASEN");
        }
        if(e.getSource().equals(lisaaJasen)){
            String nimi = nimiKentta.getText();
            String ryhma = ryhmaKentta.getText();
            if(nimi.equals("")){
                return;
            }
            Syntymaaika syntymaaika;
            try{
                String [] ika = syntymaaikaKentta.getText().split(":");
                
                int vuosi = Integer.parseInt(ika[2]);
                int kuukausi = Integer.parseInt(ika[1]);
                int paiva = Integer.parseInt(ika[0]);
                
                syntymaaika = new Syntymaaika (vuosi,kuukausi,paiva,nimi);
            }
            catch (Exception ex){
//                frame.setEnabled(false);
                SwingUtilities.invokeLater(new VirheIkkuna(frame, "Paskat olet syntynyt "+ syntymaaikaKentta.getText()
                        + "\nyritäppä uudestaan"));
                return;
            }
            rekisteri.lisaa(new Henkilo(nimi, syntymaaika, ryhma));
            nimiKentta.setText("");
            ryhmaKentta.setText("");
            syntymaaikaKentta.setText("");
            oikeaPaneli.add(main.luoNaytaJasenetPanel(),"NAYTAJASENET");
        }
        if(e.getSource().equals(sulje)){
//            kayttis.setEnabled(true);
            frame.dispose();
        }
        if(e.getSource().equals(tallenna)){
            rekisteri.tallenna();
        }
    }
    
}
