


package kayttoliittyma;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import lpkjasenrekisteri.LPKJasenrekisteri;
import lpkjasenrekisteri.Syntymaaika;


public class Kuuntelija implements ActionListener {
    private LPKJasenrekisteri rekisteri;
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
    
    public Kuuntelija(LPKJasenrekisteri rekisteri, JButton lisaaJasen, JButton naytaJasenet) {
        this.rekisteri = rekisteri;
        this.siirryLisaaJasen = lisaaJasen;
        this.siirryNaytaJasenet = naytaJasenet;
        
    }
    public Kuuntelija(JPanel ikkuna, CardLayout layout, JButton siirryPaaIkkunaan){
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.siirryPaaIkkunaan = siirryPaaIkkunaan;
    }
    public Kuuntelija(JPanel ikkuna, CardLayout layout, JButton naytaJasenet, JButton lisaaJasen, JButton poistaJasen) {
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.siirryNaytaJasenet = naytaJasenet;
        this.siirryLisaaJasen = lisaaJasen;
        this.siirryPoistaJasen = poistaJasen;
    }
    
    public Kuuntelija(LPKJasenrekisteri rekisteri, JTextField nimiKentta,JTextField syntymaaikaKentta,JTextField ryhmaKentta, JButton lisaaJasen){
        this.rekisteri = rekisteri;
        this.nimiKentta = nimiKentta;
        this.syntymaaikaKentta = syntymaaikaKentta;
        this.ryhmaKentta = ryhmaKentta;
        this.lisaaJasen = lisaaJasen;
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
            
            try{
                String [] ika = syntymaaikaKentta.getText().split(":");
                
                int vuosi = Integer.parseInt(ika[2]);
                int kuukausi = Integer.parseInt(ika[1]);
                int paiva = Integer.parseInt(ika[0]);
                
                Syntymaaika lisattavaSyntymaaika = new Syntymaaika (vuosi,kuukausi,paiva,nimi);
            }
            catch (Exception ex){
                SwingUtilities.invokeLater(new VirheIkkuna("Paskat olet syntynyt "+ syntymaaikaKentta.getText()
                        + "\nyritäppä uudestaan"));
            }
            
        }
    }
    
}
