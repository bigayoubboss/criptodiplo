/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cripto.cipher.block;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SirCristian
 */
public class DESTest {

    private static DES cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new DES();
    }

    @Test
    public void testValidEncode1() {

        System.out.println("DES Cipher Valid Encode 1");

        String plainText = "0123456789ABCDEF";
        String key = "133457799BBCDFF1";
        String expResult = "85E813540F0AB405";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("DES Cipher Valid Encode 2");

        String plainText = "01224537888BCAAF";
        String key = "01224537888BCAAF";
        String expResult = "672E1079B4883520";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode3() {

        System.out.println("DES Cipher Valid Encode 3");

        String plainText = "4368726973746961";
        String key = "4C61757261204D6F";
        String expResult = "B52C4FC1118E0D8F";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode1() {

        System.out.println("DES Cipher Valid Decode 1");

        String cipherText = "85E813540F0AB405";
        String key = "133457799BBCDFF1";
        String expResult = "0123456789ABCDEF";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode2() {

        System.out.println("DES Cipher Valid Decode 2");

        String cipherText = "100DA76B99A62CC8";
        String key = "70656E7461676F6E";
        String expResult = "616C63616C646961";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode3() {

        System.out.println("DES Cipher Valid Decode 3");

        String cipherText = "B52C4FC1118E0D8F";
        String key = "4C61757261204D6F";
        String expResult = "4368726973746961";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);

    }

}
