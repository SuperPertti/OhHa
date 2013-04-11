
package kayttoliittyma;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class ToinenKuuntelija implements ActionListener {
    private Container co;
    private CardLayout la;

    public ToinenKuuntelija(Container co, CardLayout la) {
        this.co = co;
        this.la = la;
    }
    
    


    @Override
    public void actionPerformed(ActionEvent e) {
            la.show(co, "PAAIKKUNA");
        
//        WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);  //Jes! Tämä sulkee 
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);      //annetun ikkunan 'frame'
        
        
    }
    
}
