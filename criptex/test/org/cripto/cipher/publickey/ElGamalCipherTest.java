/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cripto.cipher.publickey;

import org.cripto.cipher.publickey.ElGamalCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d_montano
 */
public class ElGamalCipherTest {

    public ElGamalCipherTest() {
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
     * Test of encrypt method, of class ElGamalCipher.
     */
    //@Test
    public void testEncrypt() throws Exception {
        System.out.println("encrypt");
        //prime, generator, random_key number so that GCD(random_key,prime-1)==1
        ElGamalCipher.keyGeneration("2579", "2", "765");
        String plainText = "hola";
        String[][] result = ElGamalCipher.encrypt(plainText);
    }

     /**
     * Test of encrypt and decrypt methods, of class ElGamalCipher.
     */
    @Test
    public void testEncryptAndDecrypt() throws Exception {
        System.out.println("encrypt and decrypt");
        //prime, generator, random_key number so that GCD(random_key,prime-1)==1
        ElGamalCipher.keyGeneration("257", "30", "255");
        System.out.println(ElGamalCipher.key());
        String plainText = "hola enfermera";
        String[][] result = ElGamalCipher.encrypt(plainText);
        // Encryption Result:
        int i = 0;
        for(String[] pair:result){
            System.out.println("Letra: " + plainText.charAt(i) + " Dec: " + (int)plainText.charAt(i));
            System.out.println("W: " + pair[0]);
            System.out.println("X: " + pair[1]);
            i++;
        }

        String uyresult = ElGamalCipher.general_decrypt(result);
        System.out.println(uyresult);
    }

    /**
     * Test of keyGeneration method, of class ElGamalCipher.
     */
    //@Test
    public void testKeyGeneration_0args() {
        System.out.println("keyGeneration");
        ElGamalCipher.keyGeneration();
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyGeneration method, of class ElGamalCipher.
     */
    //@Test
    public void testKeyGeneration_3args() {
        System.out.println("keyGeneration");
        String p = "";
        String gen = "";
        String random = "";
        //ElGamalCipher.keyGeneration(p, gen, random);
        fail("The test case is a prototype.");
    }

}