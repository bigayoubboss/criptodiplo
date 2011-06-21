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

import org.cripto.cipher.classic.AffineCipher;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AffineCipherTest {

    public AffineCipherTest() {
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
     * Test of encrypt method, of class AffineCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("affineCipher");
        String plainText = "hot";
        int keyA = 7;
        int keyB = 3;
        String expResult = "AXG";
        String result = AffineCipher.encrypt(plainText, keyA, keyB);
        assertEquals(expResult, result);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of cryptoAnalysis method, of class AffineCipher.
     */
    @Test
    public void testCryptoAnalysis() {
        System.out.println("cryptoAnalysis");
        String cipherText = "FMXVEDKAPHFERBNDKRXRSREFMORUDSDKDVSHVUFEDKAPRKDLYEVLRHHRH";
        AffineCipher expResult = new AffineCipher("algorithmsarequitegeneraldefinitionsofarithmeticprocesses", 3, 5, 4);
        ArrayList<AffineCipher> result = AffineCipher.cryptoAnalysis(cipherText);
        if (!result.contains(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of solveCongruenceSystem method, of class AffineCipher.
     */
    @Test
    public void testSolveCongruenceSystem() {
        System.out.println("solveCongruenceSystem");
        double a = 4.0;
        double b = 1.0;
        double u = 17.0;
        double c = 19.0;
        double d = 1.0;
        double v = 10.0;
        double n = 26.0;
        int[] expResult = {3, 5};
        int[] result = AffineCipher.solveCongruenceSystem(a, b, u, c, d, v, n);
        if (!Arrays.equals(result, expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}