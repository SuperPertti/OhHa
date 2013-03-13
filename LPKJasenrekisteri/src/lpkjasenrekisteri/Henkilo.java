
package lpkjasenrekisteri;

public class Henkilo {
    private String nimi;
    private Syntymaaika syntymaaika;

    public Henkilo(String nimi, int vuosi, int kuukausi, int paiva) {
        this.nimi = nimi;
        this.syntymaaika = new Syntymaaika(vuosi, kuukausi, paiva);
    }

    @Override
    public String toString() {
        return nimi+", "+syntymaaika.getPaiva()+"."+syntymaaika.getKuukausi()+"."+syntymaaika.getVuosi();
    }
    
    
    
    
    
}
