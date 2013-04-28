
package lpkjasenrekisteri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Itse rekisteri, joka säilyttää rekisterissä sillä hetkellä olevat tiedot
 * sekä toimii linkkinä muistin ja käyttöliittymän välillä.
 * @author Pertti
 */

public class LPKJasenrekisteri {
    private Muisti muisti;
    private String defaultPolku;
    private String polku;
    private ArrayList<Henkilo> henkilot;
    private ArrayList<String> muutokset;
    private boolean muutoksia;
    
    public LPKJasenrekisteri (){
        this.defaultPolku = "./muisti/henkilot.txt";
        this.polku = defaultPolku;
        this.muisti = new Muisti();
        this.henkilot = new ArrayList();
        this.muutokset = new ArrayList();
        this.muutoksia = false;
    }

/**
 * Lukee muistiin tallennetut tiedot rekisterin omaan muistiin
 * ArrayList<Henkilo>
 */
    public void kaynnista() {
        henkilot = muisti.lue();
    }
    
/**
 * Lisää parametrina annetun henkilön listaan henkilot
 */
      public void lisaa (Henkilo henkilo){
          muutoksia = true;
          henkilot.add(henkilo);
          muutokset.add("Henkilö "+henkilo.getNimi()+" lisätty");
      }
      
/**
 * Tarkistaa, onko parametrina annetun nimistä henkilöä listalla, jos on
 * poistaa henkilön ja palauttaa true,
 * jos ei, palauttaa false.
 * @param nimi
 * @return 
 */
    public boolean poista(String nimi) {
        muutoksia = true;
        for (Henkilo henkilo : henkilot) {
            if(henkilo.getNimi().equals(nimi)){
                henkilot.remove(henkilo);
                muutokset.add("Henkilö "+henkilo.getNimi()+" poistettu");
                return true;
            }
        }
        return false;
    }
    
/**
 * Antaa rekisterin tämän hetkisen listan muistille tallennettavaksi.
 */
    public void tallenna() {
         try {
                muisti.tallenna(henkilot);
            } catch (IOException ex) {
                Logger.getLogger(LPKJasenrekisteri.class.getName()).log(Level.SEVERE, null, ex);
            }
         muutoksia = false;
         muutokset.clear();
    }
    
    public String getPolku (){
        return muisti.getTiedostoJaPolku();
    }
 
    public ArrayList<Henkilo> getHenkilot() {
        return henkilot;
    }

/**
 * Palauttaa arvon, joka kertoo onko rekisteriin tehty tallentamattomia muutoksia.
 * @return true jos tallentamattomiamuutoksia, false jos ei tallentamattomia muutoksia
 */
    public boolean getMuutoksia (){
        return muutoksia;
    }

    public void setMuutoksia(boolean muutoksia) {
        this.muutoksia = muutoksia;
    }
    
    public void setPolku(String polku, String tiedostonNimi){
        muisti.setTiedostoUser(polku, tiedostonNimi);
    }    

    
}
