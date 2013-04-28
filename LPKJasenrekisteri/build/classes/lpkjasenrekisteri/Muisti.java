
package lpkjasenrekisteri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Lukee lukee ja tallentaa henkilöiden tiedot massamuistiin
 * @author Pertti
 */

public class Muisti {
    private String polku;
    private String tiedostonNimi;
    private File defaultTiedosto;
    private File tiedosto;
    private PrintWriter kirjoittaja;

    public Muisti(){
        this.defaultTiedosto = new File("./muisti/henkilot.txt");
        this.tiedosto = defaultTiedosto;
        this.polku = "";
        this.tiedostonNimi = "";
        
    }
    
/**
 * Lukee tekstitiedostosta henkilöt ja palauttaa ne ArrayListinä
 * @return ArrayList<Henkilo>
 */
    public ArrayList lue (){
        ArrayList<Henkilo> henkilot = new ArrayList();
        Scanner lukija;
          
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            luoTiedosto();
            return henkilot;
        }
        
        while(lukija.hasNextLine()){
            try{
            String rivi = lukija.nextLine();
            String [] osat = rivi.split(";");
            
            String nimi = osat[0];
            String [] ika = osat[1].split(":");
            String ryhma = osat[2];
            
            int vuosi = Integer.parseInt(ika[2]);
            int kuukausi = Integer.parseInt(ika[1]);
            int paiva = Integer.parseInt(ika[0]);
            
            Henkilo henkilo = new Henkilo(nimi, vuosi, kuukausi, paiva, ryhma);
            henkilot.add(henkilo);
            } catch (Exception e){
                System.out.println("Henkilöiden latauksessa tiedostosta ongelma: "+e.getMessage()+" (tiedosto voi olla myös tyhjä.)");
            }
        }
        lukija.close();
        return henkilot;    
    }
/**
 * ylikirjoittaa vanhan tektitiedoston parametrina saaduilla henkilöillä
 * @param henkilot
 * @throws IOException 
 */
    void tallenna(ArrayList<Henkilo> henkilot) throws IOException {
        kirjoittaja = new PrintWriter(tiedosto);
        for (Henkilo henkilo : henkilot) {
            kirjoittaja.write(henkilo.getNimi()+";"+henkilo.getSyntymaaika().getPaiva()+":"+henkilo.getSyntymaaika().getKuukausi()+":"+henkilo.getSyntymaaika().getVuosi()+";"+henkilo.getRyhma()
                    +"\r\n");
        }
        kirjoittaja.close();
    }

    /**
     * luo uuden tekstitiedoston
     */
    private boolean luoTiedosto(){
        try {
            kirjoittaja = new PrintWriter(tiedosto);
        } catch (Exception c){
            return false;
        }
        return true;  
    }
    /**
     * METODI EI KÄYTÖSSÄ
     * tarkistaa onko tiedostoa olemassa
     * @return 
     */
    public boolean tiedostoOlemassa (){
        if(tiedosto.exists()){
            return true;
        }
        return false;
    }
    
    public String getTiedostoJaPolku(){
        return tiedosto.toString();
    }

    public String getPolku() {
        return polku;
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }


    public void setPolku(String polku) {
        this.polku = polku;
    }

    public void setTiedostonNimi(String tiedostonNimi) {
        this.tiedostonNimi = tiedostonNimi;
    }
    
    public void setTiedostoUser(String polku, String tiedostonNimi){
        this.tiedosto = new File (polku+tiedostonNimi);
    }
    
    public void setTiedostoDefault(){
        this.tiedosto = defaultTiedosto;
    }
}
