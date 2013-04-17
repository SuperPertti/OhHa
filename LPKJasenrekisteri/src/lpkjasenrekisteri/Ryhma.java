/**
 * Luokka ei käytössä
 */
package lpkjasenrekisteri;

import java.util.ArrayList;

class Ryhma {
    private String nimi;
    private ArrayList<Henkilo> johtajat;
    private ArrayList<Henkilo> jasenet;

    public Ryhma(String nimi) {
        this.nimi = nimi;
        this.johtajat = new ArrayList();
        this.jasenet = new ArrayList();
    }
    
    public void lisaaJohtaja (Henkilo johtaja){
        johtajat.add(johtaja);
    }
}
