/*
 * Universidad Nacional de Colombia - Sede Bogotá *
 *
 *      Cesar Colorado - cacolorador@unal.edu.co
 *      David Montaño - damontanof@unal.edu.co
 *      Laura Moreno - lvmorenoc@unal.edu.co
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
*/

package ClassicalCryptography;

import java.io.IOException;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class PermutationCipherTest {

    public PermutationCipherTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of permutationCipher method, of class PermutationCipher.
     */
    @Test
    public void testPermutationCipherWithoutCompletation() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "DCBAHGFE";
        String result = PermutationCipher.encrypt(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));
    }
    
    @Test
    public void testPermutationCipherWithCompletation() throws Exception {
        String plainText = "ABCDEFGHI";
        String keyNumberString = "4321";
        String expResult = "DCBAHGFEAAAI";
        String result = PermutationCipher.encrypt(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));
        
    }
    
    @Test
    public void testPermutationCipherWithCompletationAndLongerKey() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "43215";
        String expResult = "DCBAEAHGFA";
        String result = PermutationCipher.encrypt(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));
    }
    
    @Test
    public void testPermutationCipherWithErrors() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "43215";
        String expResult = "DCBAEAHFGA";
        String result = PermutationCipher.encrypt(plainText, keyNumberString);
        
        assertTrue(!result.equals(expResult));
    }
    
    
    @Test
    public void testAlternatePermutationCipherWithoutCompletation() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "GECAHFDB";
        String result = PermutationCipher.encryptAlternate(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));

    }
    
    @Test
    public void testAlternatePermutationCipherWithCompletation() throws Exception {
        String plainText = "ABCDEFGHIJ";
        String keyNumberString = "4321";
        String expResult = "JGDAAHEBAIFC";
        String result = PermutationCipher.encryptAlternate(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));

    }
    
    @Test
    public void testAlternatePermutationCipherWithCompletationAndLongerKey() throws Exception {
        String plainText = "ABCDEFGHIJKLM";
        String keyNumberString = "43215";
        String expResult = "JGDAMKHEBALIFCA";
        String result = PermutationCipher.encryptAlternate(plainText, keyNumberString);
        
        assertTrue(result.equals(expResult));

    }
    
    @Test
    public void testAlternatePermutationCipherWithErrors() throws Exception {
        String plainText = "ABCDEFGHIJ";
        String keyNumberString = "4321";
        String expResult = "GJDAAEHBAIFC";
        String result = PermutationCipher.encryptAlternate(plainText, keyNumberString);
        
        assertTrue(!result.equals(expResult));

    }
    
    @Test
    public void testDoubleAlternate() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "DHCGBFAE";
        String result = PermutationCipher.encryptAlternate(plainText, keyNumberString);
        
        result = PermutationCipher.encryptAlternate(result, keyNumberString);
        
        assertTrue(result.equals(expResult));

    }

}