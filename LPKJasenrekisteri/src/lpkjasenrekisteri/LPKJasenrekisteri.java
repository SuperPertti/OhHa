
package lpkjasenrekisteri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class LPKJasenrekisteri {
    private Muisti muisti;
    private ArrayList<Henkilo> henkilot;
    private ArrayList<String> muutokset;
    private Scanner lukija;
    
    public LPKJasenrekisteri (){
        this.muisti = new Muisti();
        this.henkilot = new ArrayList();
        this.muutokset = new ArrayList();
        this.lukija = new Scanner(System.in);
    }

    void kaynnista() {
        tervehdys();
        henkilot = muisti.lue();
        String komento = "";
        
        while(!komento.equals("lopeta")){
            System.out.print("Komennot:"
                    + "\n\tlisaa - lisää henkilö rekisteriin"
                    + "\n\tpoista - poistaa henkilön rekisteristä"
                    + "\n\tjasenet - näyttää listatut jäsenet"
                    + "\n\ttallenna - tallentaa muutokset pysyvästi"
                    + "\n\tlopeta - sulkee ohjelman"
                    + "\n"
                    + "\nAnna komento:");
            komento = lukija.nextLine();
            System.out.print("\n");
            
            if(komento.equals("lisaa")){
                lisaa();
            }
            if(komento.equals("poista")){
                poista();
            }
            if(komento.equals("jasenet")){
                naytaJasenet();
            }
            if(komento.equals("tallenna")){
                tallenna();
            }
            System.out.println("-----");
        }
        
        loppusanat();
    }
    
    private void tervehdys() {
        System.out.print("Teretulemast käyttämään AWESOME LPKJäsenrekisteri -ohjelmaa!"
                + "\nLet's see..."
                + "\n----------"
                + "\n");
    }

    private void lisaa() {
        System.out.print("Anna henkilön nimi: ");
        String nimi = lukija.nextLine();
        
        System.out.print("Anna henkilön syntymapaiva (*paiva*:*kuukausi*:*vuosi*): ");
        String [] syntymaaika = lukija.nextLine().split(":");
        
        System.out.print("Aseta henkilö ryhmään: ");
        String ryhma = lukija.nextLine();
        System.out.print("\n");
        
        try{
            henkilot.add(new Henkilo(nimi,Integer.parseInt(syntymaaika[2]),Integer.parseInt(syntymaaika[1]),Integer.parseInt(syntymaaika[0]), ryhma));
        muutokset.add("Henkilö "+nimi+" lisätty.");
        System.out.print("Henkilö "+nimi+" lisätty."
                + "\nMuutokset eivät ole pysyviä ohjelman sulkemisen jälkeen ilman tallennusta."
                + "\n");
        } catch(Exception e){
            System.out.println("Henkilön lissäys meni pielleen, ou noou: "+e.getMessage());
        }
    }

    private void poista() {
        if(henkilot.isEmpty()){
            System.out.println("Ei jäsenia listalla");
            return;
        }
        String poistettava = "";
        boolean listalla = false;
        String poistetaanko = "";
        String jatketaanko = "";
        while(!listalla){
            System.out.print("Anna poistettavan henkilön nimi: ");
            poistettava = lukija.nextLine();
            for (Henkilo henkilo : henkilot) {
                if(henkilo.getNimi().equals(poistettava)){
                    listalla = true;
                    while (!poistetaanko.equals("Y") && !poistetaanko.equals("N")){
                    System.out.print("Henkilö '"+poistettava+"' löytyi!"
                            + "\nPoistetaanko varmasti? Y/N :");
                    poistetaanko = lukija.nextLine();
                    if (poistetaanko.equals("Y")){
                        muutokset.add("Henkilö "+henkilo.getNimi()+" poistettu.");
                        henkilot.remove(henkilo);
                        return;
                    }
                    }
                }
            }
            if(!listalla){
                System.out.print("Antamaasi henkilöä ei ole listalla. Kirjoititko nimen oikein?"
                        + "\nAnna uusi nimi? Y/N :");
                jatketaanko = lukija.nextLine();
                if(jatketaanko.equals("N")){
                    return;
                }
            }
        }
        
    }

    private void naytaJasenet() {
        if(henkilot.isEmpty()){
            System.out.println("Ei oole jaasenia.");
            return;
        }
        for (Henkilo henkilo : henkilot) {
            System.out.println(henkilo);
        }
        System.out.print("\n");
    }

    private void tallenna() {
        String komento = "";
        System.out.print("Olet tallentamassa tekemiäsi muutoksia:\n");
        
        if(muutokset.isEmpty()){
            System.out.println("Ei muutoksia.");
            return;
        }
        
        for (String muutos : muutokset) {
            System.out.println("\t"+muutos);
        }
        while (!komento.equals("Y") && !komento.equals("N")){
        System.out.print("\nOletko varma että haluat tallentaa muutokset? Y/N :");
        komento = lukija.nextLine();
        }
        if(komento.equals("Y")){
            muutokset.clear();
            try {
                muisti.tallenna(henkilot);
            } catch (IOException ex) {
                Logger.getLogger(LPKJasenrekisteri.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void loppusanat() {
        System.out.println("Tallennetaampa enne lopetusta...");
        tallenna();
        System.out.println("\nKiva kun lopetit, ei siuu ois enää kestänykkää");;
    }
}
