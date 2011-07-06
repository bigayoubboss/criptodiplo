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

import org.cripto.cipher.classic.Vigenere;
import java.util.ArrayList;
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
public class VigenereTest {

    private static Vigenere cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new Vigenere();
    }

    @Test
    public void testValidVigenereEncode() {

        System.out.println("Vigenere Cipher Valid Encode");

        String plainText = "thiscryptosystemisnotsecure";
        String key = "CIPHER";
        String expectedResult = "VPXZGIAXIVWPUBTTMJPWIZITWZT";

        String obtainedResult = cipher.encode(plainText, key, new Object[]{});

        assertEquals(expectedResult, obtainedResult);
    }

    /**
     * Test of cryptoAnalysis method, of class Vigenere.
     */
    @Test
    public void testValidCryptoanalysis1() {

        System.out.println("Vigenere Cipher Valid Cryptoanalysis 1");

        String cipherText = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBW"
                + "RVXUOAKXAOSXXWEAHBWGJMMQMNKGRFVGXWTRZXWIAK"
                + "LXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELX"
                + "VRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHR"
                + "ZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJT"
                + "AMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBI"
                + "PEEWEVKAKOEWADREMXMTBHHCHRTKDNVRZCHRCLQOHP"
                + "WQAIIWXNRMGWOIIFKEE";
        String expectedResult = "thealmondtreewasintentativeblossomthedayswere"
                + "longeroftenendingwithmagnificenteveningsofcorrugated"
                + "pinkskiesthehuntingseasonwasoverwithhoundsandgunsput"
                + "awayforsixmonthsthevineyardswerebusyagainasthewellorganizedfarm"
                + "erstreatedtheirvinesandthemorelackadaisicalneighborshurriedto"
                + "dothepruningtheyshouldhavedoneinnovember";

        ArrayList<Vigenere> obtainedResults = cipher.cryptoAnalysis(cipherText);

        boolean found = false;

        for (int i = 0; i < obtainedResults.size(); i++) {
            if (obtainedResults.get(i).getText().equals(expectedResult)) {
                found = true;
            }
        }

        if (!found) {
            fail("Any Result matches the Expected Result " + expectedResult);
        }

    }

    @Test
    public void testValidCryptoanalysis2() {

        System.out.println("Vigenere Cipher Valid Cryptoanalysis 2");

        String cipherText = cipherText = "OAGNEYEKNLVLWSEZSBRGPUEJBWKEIJLWEKEFRWABRUVWWHRUIAZOXPHSRMIRWLBUWLWSLOYJLSGASUMKISEPXZXCEJXOXHPKLGYOMJOAGNHFQSGKXYIKISIEHGKHRUKDHYCFJZBZQRNWLACBMFZHLRWMXYYJSJWLVVHZBZULIWGCEJLLBASRTHXHVSIXHYIYMETUHYMKZBIJXKPLEIMFZOIIGJHDRKSVBZTCEQALVSISNAC";
        String expectedResult = expectedResult = "kingahasuerusalsoknownasxerxesheldaonehundredandeightydayfeastinsusashushantodisplaythevastwealthofhiskingdomandthesplendorandgloryofhismajestykingahasuerusorderedhisqueenvashtitoappearbeforehimandhisguestswearinghercrowntodisplayherbeauty";

        ArrayList<Vigenere> obtainedResults = cipher.cryptoAnalysis(cipherText);

        boolean found = false;

        for (int i = 0; i < obtainedResults.size(); i++) {
            if (obtainedResults.get(i).getText().equals(expectedResult)) {
                found = true;
            }
        }

        if (!found) {
            fail("Any Result matches the Expected Result " + expectedResult);
        }

    }
}