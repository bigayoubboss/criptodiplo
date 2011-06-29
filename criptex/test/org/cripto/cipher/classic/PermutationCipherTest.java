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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class PermutationCipherTest {

    private static PermutationCipher cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new PermutationCipher();
    }

    @Test
    public void testValidEncode() {

        System.out.println("Permutation Cipher Valid Encode");
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "DCBAHGFE";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{true});

        assertTrue(result.equals(expResult));
    }

    @Test
    public void testPermutationCipherWithCompletation() throws Exception {
        String plainText = "ABCDEFGHI";
        String keyNumberString = "4321";
        String expResult = "DCBAHGFEAAAI";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{true});

        assertTrue(result.equals(expResult));

    }

    @Test
    public void testPermutationCipherWithCompletationAndLongerKey() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "43215";
        String expResult = "DCBAEAHGFA";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{true});

        assertTrue(result.equals(expResult));
    }

    @Test
    public void testPermutationCipherWithErrors() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "43215";
        String expResult = "DCBAEAHFGA";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{true});

        assertTrue(!result.equals(expResult));
    }

    @Test
    public void testAlternatePermutationCipherWithoutCompletation() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "GECAHFDB";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{false});

        assertTrue(result.equals(expResult));

    }

    @Test
    public void testAlternatePermutationCipherWithCompletation() throws Exception {
        String plainText = "ABCDEFGHIJ";
        String keyNumberString = "4321";
        String expResult = "JGDAAHEBAIFC";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{false});

        assertTrue(result.equals(expResult));

    }

    @Test
    public void testAlternatePermutationCipherWithCompletationAndLongerKey() throws Exception {
        String plainText = "ABCDEFGHIJKLM";
        String keyNumberString = "43215";
        String expResult = "JGDAMKHEBALIFCA";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{false});

        assertTrue(result.equals(expResult));

    }

    @Test
    public void testAlternatePermutationCipherWithErrors() throws Exception {
        String plainText = "ABCDEFGHIJ";
        String keyNumberString = "4321";
        String expResult = "GJDAAEHBAIFC";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{false});

        assertTrue(!result.equals(expResult));

    }

    @Test
    public void testDoubleAlternate() throws Exception {
        String plainText = "ABCDEFGH";
        String keyNumberString = "4321";
        String expResult = "DHCGBFAE";
        String result = cipher.encode(plainText, keyNumberString, new Boolean[]{false});

        result = cipher.encode(result, keyNumberString, new Object[]{false});

        assertTrue(result.equals(expResult));

    }
}