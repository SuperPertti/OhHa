/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *Luo graaffisen virheikkunan
 * @author Pertti
 */
public class VirheIkkuna implements Runnable {
    private JFrame frame;
    private JFrame kayttis;
    private String virheTeksti;

    public VirheIkkuna(JFrame kayttis, String virheTeksti) {
        this.virheTeksti=virheTeksti;
        this.kayttis = kayttis;
    }
    
    /**
     * Luo graaffisen virheikkunan konstruktorille annetulla virhetekstillä
     */
    @Override
    public void run() {
        frame = new JFrame("Virhe");
        frame.setPreferredSize(new Dimension(600, 200));
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
         JPanel ikkuna = (JPanel) container;
         ikkuna.setLayout(new BorderLayout());
         JTextArea virheTekstiKentta = new JTextArea(virheTeksti);
         virheTekstiKentta.setFont(new Font("Sherif", Font.PLAIN,30) );
         ikkuna.add(virheTekstiKentta,BorderLayout.CENTER);
         JButton sulje = new JButton ("No okei, lupaan yrittää kovempaa");
         sulje.addActionListener(new Kuuntelija (frame, kayttis, sulje));
         ikkuna.add(sulje, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void suljeIkkuna() {
                frame.dispose();
        }
    
}
