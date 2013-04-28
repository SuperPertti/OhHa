


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

/**
 * Nappuloiden ja tekstikenttien kuuntelija
 * Konstruktori helvetti, vaatii oikeastaan kaiken refakrotointia...
 * @author Pertti
 */
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
    private JPanel paaIkkuna;
    private JTextField annaNimiTekstikentta;
    private JButton poista;
    private JPanel kakku;
    
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
    
    public Kuuntelija (JPanel oikeaPaneli, JPanel kakku, Kayttoliittyma main, LPKJasenrekisteri rekisteri, JPanel paaIkkuna, JTextField annaNimiTekstikentta, JButton poista){ //POISTA IKKUNAN KUUNTELIJA
        this.oikeaPaneli = oikeaPaneli;
        this.kakku = kakku;
        this.main = main;
        this.rekisteri = rekisteri;
        this.paaIkkuna = paaIkkuna;
        this.annaNimiTekstikentta = annaNimiTekstikentta;
        this.poista = poista;
        
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
    
    
/**
 * Katsoo mitä nappia painetaan ja toimii sen mukaan
 * @param e 
 */
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
            if(nimi.equals("") || syntymaaikaKentta.getText().equals("")){
                SwingUtilities.invokeLater(new VirheIkkuna(frame, "Henkilöllä täytyy olla nimi ja syntymaaika"));
                return;
            }
            Syntymaaika syntymaaika;
            try{
                String [] ika = syntymaaikaKentta.getText().split(":");
                
                int vuosi = Integer.parseInt(ika[2]);
                int kuukausi = Integer.parseInt(ika[1]);
                int paiva = Integer.parseInt(ika[0]);
                
                syntymaaika = new Syntymaaika (vuosi,kuukausi,paiva,nimi);
                if(!syntymaaika.tarkastaSyntymaaika()){
                    SwingUtilities.invokeLater(new VirheIkkuna(frame, "Et varmaan ole syntynyt "+syntymaaika.toString()
                            + "\nMistä tiedän? Olen selvännäkijä!"));
                    return;
                }
            }
            catch (Exception ex){
//                frame.setEnabled(false);
                SwingUtilities.invokeLater(new VirheIkkuna(frame, "Annoit syntymäajan väärässä muodossa."
                        + "\nEikö äiti opettanut noudattamaan käyttöohjeita?"));
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
        if(e.getSource().equals(poista)){
            if(annaNimiTekstikentta.getText().equals("")){
                SwingUtilities.invokeLater(new VirheIkkuna(kayttis,"Anna nimi poistaaksesi henkilö"));
                return;
            }
            if(rekisteri.poista(annaNimiTekstikentta.getText())){
                annaNimiTekstikentta.setText("");
                paaIkkuna = main.luoPaaIkkuna();
                oikeaPaneli.add(main.luoNaytaJasenetPanel(), "NAYTAJASENET");
                return;
            }
            SwingUtilities.invokeLater(new VirheIkkuna(kayttis, "Henkilöä '"+annaNimiTekstikentta.getText()+"' ei ole listalla"
                    + "\nsenkin torvi..."
                    + "\nProTip: kopiopasaa nimi suoraa taulukosta"));
        }
    }
    
}
