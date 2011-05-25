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
package org.cripto.cipher.classic;

import javax.crypto.Cipher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class ShiftCipherTest {

    private static ShiftCipher cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new ShiftCipher();
    }

    @Test
    public void testEncode() {

        System.out.println("Shift Cipher Encode");

        int key = 11;
        String plainText = "Wewillmeetatmidnight!";
        String expResult = "bp¤twwxpp¡l¡xtoytrs¡,";

        String result = cipher.encode(plainText, key, null);
        
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testDecode() {

        System.out.println("Shift Cipher Decode");

        int key = 11;
        String cipherText = "bp¤twwxpp¡l¡xtoytrs¡,";
        String expResult = "Wewillmeetatmidnight!";

        String result = cipher.decode(cipherText, key);

        assertEquals(expResult, result);
    }

    @Test
    public void testBruteForce() {

        System.out.println("Shift Brute Force");

        String cipherText = "bp¤twwxpp¡l¡xtoytrs¡,";
        String expResult = "Wewillmeetatmidnight!";

        String[] result = cipher.bruteForce(cipherText);
        
         assertEquals(expResult, result[11]);

    }
}