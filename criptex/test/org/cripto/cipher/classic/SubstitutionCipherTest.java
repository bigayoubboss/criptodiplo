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

import org.cripto.cipher.classic.SubstitutionCipher;
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
public class SubstitutionCipherTest {

    public SubstitutionCipherTest() {
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
     * Test of encrypt method, of class SubstitutionCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("substitutionCipher");
        String plainText = "thisciphertextcannotbedecrypted";
        String newAlphabet = "XNYAHPOGZQWBTSFLRCVMUEKJDI";
        String expResult = "MGZVYZLGHCMHJMYXSSFMNHAHYCDLMHA";
        String result = SubstitutionCipher.encrypt(plainText, newAlphabet);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of cryptoAnalysis method, of class SubstitutionCipher.
     */
    @Test
    public void testCryptoAnalysis() {
        System.out.println("cryptoAnalysis");
        String cipherText = "YIFQFMZRWQFYVECFMDZPCVMRZWNMDZVEJBTXCDDUMJ" +
                "NDIFEFMDZCMDQZKCEYFCJMYRNCWJCSZREXCHZUNMXZ" +
                "NZUCDRJXYYSMRTMEYIFZWDYVZVYFZUMRZCRWNZDZJJ" +
                "XZWGCHSMRNMDHNCMFGCHZJMXJZWIEJYUCFWDJNZDIR";
        String expResult = "ourfriendfromparisexaminedhisemptyglasswit" +
                "hsurpriseasifevaporationhadtakenplacewhile" +
                "hewasntlookingipouredsomemorewineandhesett" +
                "ledbackinhischairfacetilteduptowardsthesun";
        /*String[] result = SubstitutionCipher.cryptoAnalysis(cipherText);
        // TODO review the generated test code and remove the default call to fail.
        Arrays.sort(result);
        if (Arrays.binarySearch(result, expResult) >= 0) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }*/
    }
}