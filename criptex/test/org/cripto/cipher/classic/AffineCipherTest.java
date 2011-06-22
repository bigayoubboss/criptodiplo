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

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class AffineCipherTest {

    private static AffineCipher cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new AffineCipher();
    }

    @Test
    public void testValidEncode() {

        System.out.println("Affine Cipher Valid Encode");


        String plainText = "hot";
        String expResult = "AXG";

        int keyA = 7;
        int keyB = 3;

        String result = cipher.encode(plainText, null, new Object[]{keyA, keyB});

        assertEquals(expResult, result);
    }

    @Test
    public void testValidCryptoAnalysis() {

        System.out.println("Affine Cipher Valid CryptoAnalysis");

        String cipherText = "FMXVEDKAPHFERBNDKRXRSREFMORUDSDKDVSHVUFEDKAPRKDLYEVLRHHRH";
        AffineCipher expResult = new AffineCipher("algorithmsarequitegeneraldefinitionsofarithmeticprocesses", 3, 5, 4);

        ArrayList<AffineCipher> result = cipher.cryptoAnalysis(cipherText);

        assertTrue(result.contains(expResult));

    }
}