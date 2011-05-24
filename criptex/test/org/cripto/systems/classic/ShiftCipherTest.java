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

package org.cripto.systems.classic;

import org.cripto.systems.classic.ShiftCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class ShiftCipherTest {

    public ShiftCipherTest() {
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
     * Test of encrypt method, of class ShiftCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("shiftCipher");
        String plainText = "wewillmeetatmidnight";
        int key = 11;
        String expResult = "HPHTWWXPPELEXTOYTRSE";
        String result = ShiftCipher.encrypt(plainText, key);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of cryptoAnalysis method, of class ShiftCipher.
     */
    @Test
    public void testCryptoAnalysis() {
        System.out.println("cryptoAnalysis");
        String cipherText = "HPHTWWXPPELEXTOYTRSE";
        String[] expResult = {"hphtwwxppelextoytrse", "gogsvvwoodkdwsnxsqrd", "fnfruuvnncjcvrmwrpqc",
            "emeqttummbibuqlvqopb", "dldpsstllahatpkupnoa", "ckcorrskkzgzsojtomnz", "bjbnqqrjjyfyrnisnlmy",
            "aiamppqiixexqmhrmklx", "zhzloophhwdwplgqljkw", "ygyknnoggvcvokfpkijv", "xfxjmmnffubunjeojhiu",
            "wewillmeetatmidnight", "vdvhkklddszslhcmhfgs", "ucugjjkccryrkgblgefr", "tbtfiijbbqxqjfakfdeq",
            "sasehhiaapwpiezjecdp", "rzrdgghzzovohdyidbco", "qyqcffgyynungcxhcabn", "pxpbeefxxmtmfbwgbzam",
            "owoaddewwlsleavfayzl", "nvnzccdvvkrkdzuezxyk", "mumybbcuujqjcytdywxj", "ltlxaabttipibxscxvwi",
            "kskwzzasshohawrbwuvh", "jrjvyyzrrgngzvqavtug", "iqiuxxyqqfmfyupzustf"};
        String[] result = ShiftCipher.cryptoAnalysis(cipherText);
        // TODO review the generated test code and remove the default call to fail.
        if (!Arrays.equals(result, expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of encryptMod189 method, of class ShiftCipher.
     */
    @Test
    public void testEncryptMod189() {
        System.out.println("shiftCipher");
        String plainText = "Wewillmeetatmidnight!";
        int key = 11;
        String expResult = "bp¤twwxpp¡l¡xtoytrs¡,";
        String result = ShiftCipher.encryptMod189(plainText, key);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of cryptoAnalysisMod189 method, of class ShiftCipher.
     */
    @Test
    public void testCryptoAnalysisMod189() {
        System.out.println("cryptoAnalysis");
        String cipherText = "bp¤twwxpp¡l¡xtoytrs¡,";
        String expResult = "Wewillmeetatmidnight!";
        String[] result = ShiftCipher.cryptoAnalysisMod189(cipherText);
        if (!result[11].equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}