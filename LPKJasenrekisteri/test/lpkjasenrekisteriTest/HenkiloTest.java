package lpkjasenrekisteriTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lpkjasenrekisteri.Henkilo;
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
public class HenkiloTest {
         private Henkilo henkilo;

         
    public HenkiloTest() {
        this.henkilo = new Henkilo("Könnilä", 1990, 3, 3, "Superit");
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
     public void toStringToimii() {
         assertEquals("Könnilä, 3.3.1990, ryhma Superit", henkilo.toString());
     }
}
