
package lpkjasenrekisteri;


public class Henkilo {
    private String nimi;
    private Syntymaaika syntymaaika;
    private String ryhma;

    public Henkilo(String nimi, int vuosi, int kuukausi, int paiva, String ryhma) {
        this.nimi = nimi;
        this.syntymaaika = new Syntymaaika(vuosi, kuukausi, paiva, nimi);
        this.ryhma = ryhma;
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

}