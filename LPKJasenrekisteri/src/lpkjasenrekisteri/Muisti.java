
package lpkjasenrekisteri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class Muisti {
    private File tiedosto;
    private PrintWriter kirjoittaja;

    public Muisti(){
        this.tiedosto = new File("./muisti/henkilot.txt");
    }
    
    public ArrayList lue (){
        ArrayList<Henkilo> henkilot = new ArrayList();
        Scanner lukija;
          
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("ei aikaisempaa tallennusta. Luodaan uusi tiedosto");
            luoTiedosto();
            return henkilot;
        }
        catch (Exception e){
            System.out.println("Tiedoston lukemisessa virhe: " + e.getMessage());
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

    void tallenna(ArrayList<Henkilo> henkilot) throws IOException {
        kirjoittaja = new PrintWriter(tiedosto);
        for (Henkilo henkilo : henkilot) {
            kirjoittaja.write(henkilo.getNimi()+";"+henkilo.getSyntymaaika().getPaiva()+":"+henkilo.getSyntymaaika().getKuukausi()+":"+henkilo.getSyntymaaika().getVuosi()+";"+henkilo.getRyhma()+""
                    + "\n");
        }
        kirjoittaja.close();
    }

    private void luoTiedosto(){
        try {
            kirjoittaja = new PrintWriter(tiedosto);
        } catch (Exception c){
            System.out.println("Tiedoston luonnissa ongelma.");
        }
        
    }
}
