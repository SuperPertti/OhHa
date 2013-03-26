

import java.util.Calendar;
import lpkjasenrekisteri.Syntymaaika;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pertti
 */
public class SyntymaaikaTest {
    
    public SyntymaaikaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void syntymaaikaTallentuuOikein() {
        Syntymaaika SA = new Syntymaaika(1990,1,1,"a");
        assertEquals(1990, SA.getVuosi());
        assertEquals(1, SA.getKuukausi());
        assertEquals(1, SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
     }
     
     @Test
     public void syntymaaikaTallentuuOikein2() {
        Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.DATE),"b");
        assertEquals(Calendar.getInstance().get(Calendar.YEAR), SA.getVuosi());
        assertEquals(Calendar.getInstance().get(Calendar.MONTH)+1, SA.getKuukausi());
        assertEquals(Calendar.getInstance().get(Calendar.DATE), SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
      }
     
     @Test
     public void syntymaaikaTallentuuOikein3() {
        Syntymaaika SA = new Syntymaaika(1900, 12, 31,"c");
        assertEquals(1900, SA.getVuosi());
        assertEquals(12, SA.getKuukausi());
        assertEquals(31, SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
      }
     
      @Test
     public void syntymavuodenTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(-1990,1,1,"d");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
     
      @Test
     public void syntymavuodenTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR)+1,1,1, "e");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymakuukaudenTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(1990,-1,1,"f");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymakuukaudenTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH)+2,1,"g");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
    
      @Test
     public void syntymapaivanTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(1990,1,-1,"h");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymapaivanTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.DATE)+1,"i");
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
      public void syntymaajanMuuttaminen(){
          Syntymaaika SA = new Syntymaaika(1990,1,1,"j");
          SA.setVuosi(1900);
          SA.setKuukausi(3);
          SA.setPaiva(15);
          assertEquals(1900, SA.getVuosi());
          assertEquals(3, SA.getKuukausi());
          assertEquals(15, SA.getPaiva());
      }
}
