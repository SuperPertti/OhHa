package lpkjasenrekisteri;

import java.util.Calendar;

public class Syntymaaika {
    private int vuosi;
    private int kuukausi;
    private int paiva;
    private String omistaja;

    public Syntymaaika(int vuosi, int kuukausi, int paiva, String nimi) {
        this.vuosi = vuosi;
        this.kuukausi = kuukausi;
        this.paiva = paiva;
        this.omistaja = nimi;
        tarkastaSyntymaaika();
    }

    public int getVuosi() {
        return vuosi;
    }

    public int getKuukausi() {
        return kuukausi;
    }

    public int getPaiva() {
        return paiva;
    }

    public void setVuosi(int vuosi) {
        tarkastaSyntymaaika();
        this.vuosi = vuosi;
    }

    public void setKuukausi(int kuukausi) {
        tarkastaSyntymaaika();
        this.kuukausi = kuukausi;
    }

    public void setPaiva(int paiva) {
        tarkastaSyntymaaika();
        this.paiva = paiva;
    } 
    
    public boolean tarkastaSyntymaaika() {
        boolean aikaOikein = true;
        if(vuosi<1900 || vuosi>Calendar.getInstance().get(Calendar.YEAR)){
            aikaOikein=false;
        }
        if(kuukausi<1 || paiva<1){
            aikaOikein=false;
        }
        if (kuukausi>12 || paiva>31){
            aikaOikein=false;
        }
        if(vuosi==Calendar.getInstance().get(Calendar.YEAR)
           && kuukausi>Calendar.getInstance().get(Calendar.MONTH)+1){
            aikaOikein=false;
        }
        if(vuosi==Calendar.getInstance().get(Calendar.YEAR)
           && kuukausi==Calendar.getInstance().get(Calendar.MONTH)+1
           && paiva>Calendar.getInstance().get(Calendar.DATE)){
            aikaOikein=false;
        }
        if(!aikaOikein){
            System.out.println("Jotain feelua syntymäajassa henkilöllä: "+omistaja);
        }
        return aikaOikein;
    }
}
