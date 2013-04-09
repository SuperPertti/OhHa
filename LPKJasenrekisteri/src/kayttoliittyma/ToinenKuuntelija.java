
package kayttoliittyma;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class ToinenKuuntelija implements ActionListener {
    private JFrame frame;

    ToinenKuuntelija(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);  //Jes! Tämä sulkee 
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);      //annetun ikkunan 'frame'
        
        
    }
    
}
