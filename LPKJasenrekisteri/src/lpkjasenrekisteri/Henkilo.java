
package lpkjasenrekisteri;

import java.util.Calendar;

public class Henkilo {
    private String nimi;
    private Syntymaaika syntymaaika;

    public Henkilo(String nimi, int vuosi, int kuukausi, int paiva) {
        this.nimi = nimi;
        this.syntymaaika = new Syntymaaika(vuosi, kuukausi, paiva);
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public Syntymaaika getSyntymaaika(){
        return syntymaaika;
    }

    @Override
    public String toString() {
        return nimi+", "+syntymaaika.getPaiva()+"."+syntymaaika.getKuukausi()+"."+syntymaaika.getVuosi();
    }

    private void tarkastaSyntymaaika() {
        boolean aikaOikein = true;
        if(syntymaaika.getVuosi()<1900 || syntymaaika.getVuosi()>Calendar.getInstance().get(Calendar.YEAR)){
            aikaOikein=false;
        }
        if(syntymaaika.getKuukausi()<1 || syntymaaika.getPaiva()<1){
            aikaOikein=false;
        }
        if (syntymaaika.getKuukausi()>12 || syntymaaika.getPaiva()>31){
            aikaOikein=false;
        }
        if(syntymaaika.getVuosi()==Calendar.getInstance().get(Calendar.YEAR)
           && syntymaaika.getKuukausi()>Calendar.getInstance().get(Calendar.MONTH)+1){
            aikaOikein=false;
        }
        if(syntymaaika.getVuosi()==Calendar.getInstance().get(Calendar.YEAR)
           && syntymaaika.getKuukausi()==Calendar.getInstance().get(Calendar.MONTH)+1
           && syntymaaika.getPaiva()>Calendar.getInstance().get(Calendar.DATE)){
            aikaOikein=false;
        }
        if(!aikaOikein){
            System.out.println("Syntymäajassa jotain feelua...");
        }  
    }
    
    
    
    
    
}
