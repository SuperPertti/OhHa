
package kayttoliittyma;

import javax.swing.SwingUtilities;
import lpkjasenrekisteri.LPKJasenrekisteri;
/**
 * Käynnistää graaffisen käyttöliittymän
 * @author Pertti
 */
public class Main {

    public static void main(String[] args){
        
//        LPKJasenrekisteri rekisteri = new LPKJasenrekisteri();
//        rekisteri.kaynnista();
        
        Kayttoliittyma kayttis = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttis);
        
//        Alkuikkuna alkuikkuna = new Alkuikkuna();
//        SwingUtilities.invokeLater(alkuikkuna);
        
        
    }
}
