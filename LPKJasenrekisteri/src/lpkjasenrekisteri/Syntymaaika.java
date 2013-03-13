
package lpkjasenrekisteri;

class Syntymaaika {
    private int vuosi;
    private int kuukausi;
    private int paiva;

    public Syntymaaika(int vuosi, int kuukausi, int paiva) {
        this.vuosi = vuosi;
        this.kuukausi = kuukausi;
        this.paiva = paiva;
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
        this.vuosi = vuosi;
    }

    public void setKuukausi(int kuukausi) {
        this.kuukausi = kuukausi;
    }

    public void setPaiva(int paiva) {
        this.paiva = paiva;
    }
    
    
}
