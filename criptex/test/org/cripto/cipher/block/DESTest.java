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

        String plainText = "0123456789abcdef";
        String key = "133457799bbcdff1";
        String expResult = "85e813540f0ab405";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("DES Cipher Valid Encode 2");

        String plainText = "01224537888bcaaf";
        String key = "01224537888bcaaf";
        String expResult = "672e1079b4883520";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode3() {

        System.out.println("DES Cipher Valid Encode 3");

        String plainText = "4368726973746961";
        String key = "4c61757261204d6f";
        String expResult = "b52c4fc1118e0d8f";

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode1() {

        System.out.println("DES Cipher Valid Decode 1");

        String cipherText = "85e813540f0ab405";
        String key = "133457799bbcdff1";
        String expResult = "0123456789abcdef";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode2() {

        System.out.println("DES Cipher Valid Decode 2");

        String cipherText = "100da76b99a62cc8";
        String key = "70656e7461676f6e";
        String expResult = "616c63616c646961";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);
    }

    @Test
    public void testValideDecode3() {

        System.out.println("DES Cipher Valid Decode 3");

        String cipherText = "b52c4fc1118e0d8f";
        String key = "4c61757261204d6f";
        String expResult = "4368726973746961";

        String result = cipher.decode(cipherText, key);
        assertEquals(expResult, result);

    }

}
