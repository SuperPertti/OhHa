/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Pertti
 */
public class VirheIkkuna implements Runnable {
    private JFrame frame;
    private String virheTeksti;

    public VirheIkkuna(String virheTeksti) {
        this.virheTeksti=virheTeksti;
    }
    
    
    @Override
    public void run() {
        frame = new JFrame("Virhe");
        frame.setPreferredSize(new Dimension(600, 200));
        
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
         JPanel ikkuna = (JPanel) container;
         ikkuna.setLayout(new BorderLayout());
         ikkuna.add(new JTextArea(virheTeksti),BorderLayout.CENTER);
         
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
