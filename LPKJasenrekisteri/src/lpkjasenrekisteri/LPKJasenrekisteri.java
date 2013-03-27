
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
                    + "\n\tpoista - poistaa henkilön rekisteristä" //kesken
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

    private void lisaa() {
        System.out.print("Anna henkilön nimi: ");
        String nimi = lukija.nextLine();
        System.out.print("Anna henkilön syntymapaiva (*paiva*:*kuukausi*:*vuosi*): ");
        String [] syntymaaika = lukija.nextLine().split(":");
        System.out.print("Aseta henkilö ryhmään: ");
        String ryhma = lukija.nextLine();
        System.out.print("\n");
        
        henkilot.add(new Henkilo(nimi,Integer.parseInt(syntymaaika[2]),Integer.parseInt(syntymaaika[1]),Integer.parseInt(syntymaaika[0]), ryhma));
        muutokset.add("Henkilö "+nimi+" lisätty.");
        System.out.print("Henkilö "+nimi+" lisätty."
                + "\nMuista että muutokset eivät ole pysyviä enne tallennusta."
                + "\n");
    }

    private void loppusanat() {
        System.out.println("\nKiva kun lopetit, ei siuu ois enää kestänykkää");;
    }

    private void tervehdys() {
        System.out.print("Teretulemast käyttämään AWESOME LPKJäsenrekisteri -ohjelmaa!"
                + "\nLet's see..."
                + "\n----------"
                + "\n");
    }

    private void naytaJasenet() {
        for (Henkilo henkilo : henkilot) {
            System.out.println(henkilo);
        }
        System.out.print("\n");
    }

    private void tallenna() {
        String komento = "";
        System.out.print("Olet tallentamassa tekemiäsi muutoksia:\n");
        for (String muutos : muutokset) {
            System.out.println("\t"+muutos);
        }
        while (!komento.equals("Y") && !komento.equals("N")){
        System.out.print("\nOletko varma että haluat tallentaa muutokset? Y/N :");
        komento = lukija.nextLine();
        }
        if(komento.equals("Y")){
            try {
                muisti.tallenna(henkilot);
            } catch (IOException ex) {
                Logger.getLogger(LPKJasenrekisteri.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
