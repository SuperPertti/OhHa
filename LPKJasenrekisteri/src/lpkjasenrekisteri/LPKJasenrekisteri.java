
package lpkjasenrekisteri;

import java.util.ArrayList;

class LPKJasenrekisteri {
    private Muisti muisti;
    private ArrayList<Henkilo> henkilot;
    
    public LPKJasenrekisteri (){
        this.muisti = new Muisti();
        this.henkilot = new ArrayList();
    }

    void kaynnista() {
        henkilot = muisti.lue();
        for (Henkilo henkilo : henkilot) {
            System.out.println(henkilo);
        }
        
    }
    
}
