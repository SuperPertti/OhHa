

import java.util.Calendar;
import lpkjasenrekisteri.Syntymaaika;
import lpkjasenrekisteri.Henkilo;


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
public class LPKJasenrekisteriTest {
    
    public LPKJasenrekisteriTest() {
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
     public void syntymaaikaOikein() {
        Syntymaaika SA = new Syntymaaika(1990,1,1);
        assertEquals(1990, SA.getVuosi());
        assertEquals(1, SA.getKuukausi());
        assertEquals(1, SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
     }
     
     @Test
     public void syntymaaikaOikein2() {
        Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.DATE));
        assertEquals(Calendar.getInstance().get(Calendar.YEAR), SA.getVuosi());
        assertEquals(Calendar.getInstance().get(Calendar.MONTH)+1, SA.getKuukausi());
        assertEquals(Calendar.getInstance().get(Calendar.DATE), SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
      }
     
     @Test
     public void syntymaaikaOikein3() {
        Syntymaaika SA = new Syntymaaika(1932,11,27);
        assertEquals(1932, SA.getVuosi());
        assertEquals(11, SA.getKuukausi());
        assertEquals(27, SA.getPaiva());
        assertEquals(true, SA.tarkastaSyntymaaika());
      }
     
      @Test
     public void syntymavuodenTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(-1990,1,1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
     
      @Test
     public void syntymavuodenTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(Calendar.getInstance().get(Calendar.YEAR)+1,1,1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymakuukaudenTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(1990,-1,1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymakuukaudenTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(1990, Calendar.getInstance().get(Calendar.MONTH)+2,1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
    
      @Test
     public void syntymapaivanTarkistusToimiiNegatiivisella() {
          Syntymaaika SA = new Syntymaaika(1990,1,-1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
      
      @Test
     public void syntymapaivanTarkistusToimiiTulevaisuudelle() {
          Syntymaaika SA = new Syntymaaika(1990, 1, Calendar.getInstance().get(Calendar.DATE)+1);
          assertEquals(false, SA.tarkastaSyntymaaika());
      }
}
