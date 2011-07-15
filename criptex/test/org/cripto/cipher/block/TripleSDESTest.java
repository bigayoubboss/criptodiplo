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
public class TripleSDESTest {

    private static TripleSDES cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new TripleSDES();
    }

    @Test
    public void testValidEncode1() {

        System.out.println("Triple SDES Cipher Valid Encode 1");

        String plainText = "4c";
        String key = "111000010110101010101001100100";
        String expResult = "72";
        String result = cipher.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("SDES Cipher Valid Encode 2");

        String plainText = "48";
        String key = "110000111111101101000100110000";
        String expResult = "11";
        String result = cipher.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode1() {

        System.out.println("SDES Cipher Valid Decode 1");

        String cipherText = "11";
        String key = "110000111111101101000100110000";
        String expResult = "48";
        String result = cipher.decode(cipherText, key);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode2() {

        System.out.println("SDES Cipher Valid Decode 2");

        String cipherText = "72";
        String key = "111000010110101010101001100100";
        String expResult = "4c";
        String result = cipher.decode(cipherText, key);

        assertEquals(expResult, result);

    }
}
