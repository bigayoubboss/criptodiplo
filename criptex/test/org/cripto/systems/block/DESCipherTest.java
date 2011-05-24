/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cripto.systems.block;

import org.cripto.systems.block.DESCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SirCristian
 */
public class DESCipherTest {

    public DESCipherTest() {
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

    @Test
    public void testEncrypt() {
        System.out.println("DES Encrypt Test 1");
        String text = "0123456789abcdef";
        String keyString = "133457799bbcdff1";
        boolean mode = true;
        String expResult = "85e813540f0ab405";
        String result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);

        System.out.println("DES Encrypt Test 2");
        text = "01224537888bcaaf";
        keyString = "01224537888bcaaf";
        mode = true;
        expResult = "672e1079b4883520";
        result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);

        System.out.println("DES Encrypt Test 3");
        text = "4368726973746961";
        keyString = "4c61757261204d6f";
        mode = true;
        expResult = "b52c4fc1118e0d8f";
        result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptDecrypt method, of class DESCipher.
     */
    @Test
    public void testDecrypt() {
        System.out.println("\nDES Decrypt Test 1");
        String text = "85e813540f0ab405";
        String keyString = "133457799bbcdff1";
        boolean mode = false;
        String expResult = "0123456789abcdef";
        String result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);

        System.out.println("DES Decrypt Test 2");
        text = "100da76b99a62cc8";
        keyString = "70656e7461676f6e";
        mode = false;
        expResult = "616c63616c646961";
        result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);

        System.out.println("DES Decrypt Test 3");
        text = "b52c4fc1118e0d8f";
        keyString = "4c61757261204d6f";
        mode = false;
        expResult = "4368726973746961";
        result = DESCipher.encryptDecrypt(text, keyString, mode);
        assertEquals(expResult, result);

    }
}
