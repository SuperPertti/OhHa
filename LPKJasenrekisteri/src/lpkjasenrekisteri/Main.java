
package lpkjasenrekisteri;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args){
        
        Kayttoliittyma kayttis = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttis);
    }
}
