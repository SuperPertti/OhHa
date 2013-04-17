


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
    private JTextArea tekstikentta;
    private JButton lisaaJasen;
    private JButton naytaJasenet;
    private Container pelikentta;
    private CardLayout layout;
    private JButton flippaus;
    private JPanel kakku;
    
    public Kuuntelija(LPKJasenrekisteri rekisteri, Container pelikentta, JTextArea tekstikentta, JButton lisaaJasen, JButton naytaJasenet) {
        this.rekisteri = rekisteri;
        this.pelikentta = pelikentta;
        this.tekstikentta = tekstikentta;
        this.lisaaJasen = lisaaJasen;
        this.naytaJasenet = naytaJasenet;
        
    }

    public Kuuntelija(JPanel kakku, CardLayout layout, JButton flippaus) {
        this.kakku = kakku;
        this.layout = layout;
        this.flippaus = flippaus;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(naytaJasenet)){
            tekstikentta.setText(rekisteri.getJasenet());     
        }
        if(e.getSource().equals(lisaaJasen)){
            JButton nappi = new JButton("joou");
        }
        if(e.getSource().equals(flippaus)){
            layout.next(kakku);
        }
        
        
        
    }
    
}
