/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import javax.swing.JPanel;
import lpkjasenrekisteri.LPKJasenrekisteri;

/**
 *
 * @author Pertti
 */
public class NaytaJasenet extends JPanel {
    private LPKJasenrekisteri rekisteri;
    
    public NaytaJasenet (LPKJasenrekisteri rekisteri){
        this.rekisteri = rekisteri;
    }
}