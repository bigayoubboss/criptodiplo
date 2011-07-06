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
package org.cripto.cipher.block;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class SDESTest {

    private static SDES cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new SDES();
    }

    @Test
    public void testValidEncode1() {

        System.out.println("SDES Cipher Valid Encode 1");

        String plainText = "4c";
        String key = "1101101000";
        String expResult = "09";
        String result = cipher.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("SDES Cipher Valid Encode 2");

        String plainText = "a1";
        String key = "1010001110";
        String expResult = "56";
        String result = cipher.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode3() {

        System.out.println("SDES Cipher Valid Encode 3");

        String plainText = "a5";
        String key = "0010010111";
        String expResult = "36";
        String result3 = cipher.encode(plainText, key, null);

        assertEquals(expResult, result3);

    }

    @Test
    public void testValidDecode1() {

        System.out.println("SDES Cipher Valid Decode 1");

        String cipherText = "75";
        String key = "1010000010";
        String expResult = "bd";
        String result = cipher.decode(cipherText, key);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode2() {

        System.out.println("SDES Cipher Valid Decode 2");

        String cipherText = "56";
        String key = "1010001110";
        String expResult = "a1";
        String result = cipher.decode(cipherText, key);

        assertEquals(expResult, result);

    }
}
