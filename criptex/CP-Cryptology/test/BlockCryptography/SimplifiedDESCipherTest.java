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
package BlockCryptography;

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
public class SimplifiedDESCipherTest {

    public SimplifiedDESCipherTest() {
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
     * Test of encryptDecrypt method, of class SimplifiedDESCipher.
     */
    @Test
    public void testEncrypt() {

        System.out.println("Simplified DES Encrypt Test 2");
        String plainText = "bd";
        String key = "1010000010";
        String expResult = "75";
        String result = SimplifiedDESCipher.encryptDecrypt(plainText, key, true);
        assertEquals(expResult, result);

        System.out.println("Simplified DES Encrypt Test 2");
        String plainText2 = "a1";
        String key2 = "1010001110";
        String expResult2 = "56";
        String result2 = SimplifiedDESCipher.encryptDecrypt(plainText2, key2, true);
        assertEquals(expResult2, result2);

        System.out.println("Simplified DES Encrypt Test 3");
        String plainText3 = "a5";//{1, 1, 1, 0, 1, 0, 1, 0};
        String key3 = "0010010111";
        String expResult3 = "36";//{1, 0, 1, 0, 0, 0, 1, 0};
        String result3 = SimplifiedDESCipher.encryptDecrypt(plainText3, key3, true);
        assertEquals(expResult3, result3);
        
    }

    /**
     * Test of decrypt method, of class SimplifiedDESCipher.
     */
    @Test
    public void testDecrypt() {

        System.out.println("Simplified DES Decrypt Test 1");
        String plainText = "75";
        String key = "1010000010";
        String expResult = "bd";
        String result = SimplifiedDESCipher.encryptDecrypt(plainText, key, false);
        assertEquals(expResult, result);

        System.out.println("Simplified DES Decrypt Test 2");
        String plainText2 = "56";
        String key2 = "1010001110";
        String expResult2 = "a1";
        String result2 = SimplifiedDESCipher.encryptDecrypt(plainText2, key2, false);
        assertEquals(expResult2, result2);

    }
}
