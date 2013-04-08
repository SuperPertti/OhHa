
package kayttoliittyma;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args){
        
//        LPKJasenrekisteri rekisteri = new LPKJasenrekisteri();
//        rekisteri.kaynnista();
        
        Kayttoliittyma kayttis = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttis);
    }
}
