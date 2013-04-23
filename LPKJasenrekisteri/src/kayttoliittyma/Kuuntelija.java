


package kayttoliittyma;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import lpkjasenrekisteri.LPKJasenrekisteri;


public class Kuuntelija implements ActionListener {
    private LPKJasenrekisteri rekisteri;
    private JButton naytaJasenet;
    private JButton lisaaJasen;
    private JButton poistaJasen;
    private CardLayout layout;
    private JButton nappi;
    private JPanel ikkuna;
    
    public Kuuntelija(LPKJasenrekisteri rekisteri, JButton lisaaJasen, JButton naytaJasenet) {
        this.rekisteri = rekisteri;
        this.lisaaJasen = lisaaJasen;
        this.naytaJasenet = naytaJasenet;
        
    }
    public Kuuntelija(JPanel ikkuna, CardLayout layout, JButton siirryPaaIkkunaan){
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.nappi = siirryPaaIkkunaan;
    }
    public Kuuntelija(JPanel ikkuna, CardLayout layout, JButton naytaJasenet, JButton lisaaJasen, JButton poistaJasen) {
        this.ikkuna = ikkuna;
        this.layout = layout;
        this.naytaJasenet = naytaJasenet;
        this.lisaaJasen = lisaaJasen;
        this.poistaJasen = poistaJasen;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(naytaJasenet)){
            layout.show(ikkuna,"NAYTAJASENET");
        }
        if(e.getSource().equals(lisaaJasen)){
            layout.show(ikkuna,"LISAAJASEN");
        }
        if(e.getSource().equals(nappi)){
            layout.next(ikkuna);
        }
        if(e.getSource().equals(poistaJasen)){
            layout.show(ikkuna,"POISTAJASEN");
        }
        
        
        
    }
    
}
