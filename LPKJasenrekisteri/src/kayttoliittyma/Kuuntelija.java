
package kayttoliittyma;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import lpkjasenrekisteri.LPKJasenrekisteri;


public class Kuuntelija implements ActionListener {
    private LPKJasenrekisteri rekisteri;
    private JTextArea tekstikentta;
    private JButton lisaaJasen;
    private JButton naytaJasenet;
    private Container pelikentta;
    
    Kuuntelija(LPKJasenrekisteri rekisteri, Container pelikentta, JTextArea tekstikentta, JButton lisaaJasen, JButton naytaJasenet) {
        this.rekisteri = rekisteri;
        this.pelikentta = pelikentta;
        this.tekstikentta = tekstikentta;
        this.lisaaJasen = lisaaJasen;
        this.naytaJasenet = naytaJasenet;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(naytaJasenet)){
            tekstikentta.setText(rekisteri.getJasenet());     
        }
        if(e.getSource().equals(lisaaJasen)){
            JButton nappi = new JButton("joou");
        }
        
        
    }
    
}
