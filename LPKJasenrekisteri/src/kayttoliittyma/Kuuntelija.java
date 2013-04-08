/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import lpkjasenrekisteri.LPKJasenrekisteri;

/**
 *
 * @author Pertti
 */
public class Kuuntelija implements ActionListener {
    private LPKJasenrekisteri rekisteri;
    private JTextArea jasenet;
    
    public Kuuntelija (LPKJasenrekisteri rekisteri, JTextArea jasenet){
        this.rekisteri = rekisteri;
        this.jasenet = jasenet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jasenet.setText(rekisteri.getJasenet());
    }
    
}
