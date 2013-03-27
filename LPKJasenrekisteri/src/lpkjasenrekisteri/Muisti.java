
package lpkjasenrekisteri;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



class Muisti {
    private Scanner lukija;
    private File tiedosto;

    public Muisti(){
        this.tiedosto = new File("./muisti/henkilot.txt");
        
        try {
        this.lukija = new Scanner(tiedosto, "UTF-8");
        } catch (Exception e){
            System.out.println("Tiedoston lukemisessa virhe: " + e.getMessage());
        }
        
    }
    
    public ArrayList lue (){
        ArrayList<Henkilo> henkilot = new ArrayList();
        
        
        while(lukija.hasNextLine()){
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
        }
        return henkilot;
        
    }

    void tallenna(ArrayList<Henkilo> henkilot) throws IOException {     
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.flush();
        for (Henkilo henkilo : henkilot) {
            kirjoittaja.write(henkilo.getNimi()+";"+henkilo.getSyntymaaika().getPaiva()+":"+henkilo.getSyntymaaika().getKuukausi()+":"+henkilo.getSyntymaaika().getVuosi()+";"+henkilo.getRyhma());
        }
        kirjoittaja.close();
    }

    
    
}
