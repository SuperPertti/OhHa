/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import lpkjasenrekisteri.LPKJasenrekisteri;

/**
 *Kuuntelee ikkunan toimintoja.
 * @author Pertti
 */
public class IkkunanKuuntelija extends WindowAdapter {
    private JFrame frame;
    private LPKJasenrekisteri rekisteri;
    
    

    public IkkunanKuuntelija(JFrame frame, LPKJasenrekisteri rekisteri) {
        this.frame = frame;
        this.rekisteri = rekisteri;
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        if(rekisteri.getMuutoksia()){
            SwingUtilities.invokeLater(new VirheIkkuna(frame, "Sinulla on tallentamattomia muutoskia URVELO"
                    + "\n Jos ei kiinnosta niin painappa ruksia toisen kerran."));
            rekisteri.setMuutoksia(false);
            return;
        }
        System.exit(0);
    }

}
