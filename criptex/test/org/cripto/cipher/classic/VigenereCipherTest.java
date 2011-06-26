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

import org.cripto.cipher.classic.VigenereCipher;
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
public class VigenereCipherTest {

    private static VigenereCipher cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new VigenereCipher();
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
     * Test of cryptoAnalysis method, of class VigenereCipher.
     */
    @Test
    public void testCryptoanalysis() {

        System.out.println("TEST 2:");
        System.out.println("VigenereCipher: CryptoAnalysis");
        System.out.println("CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBW");
        System.out.println("RVXUOAKXAOSXXWEAHBWGJMMQMNKGRFVGXWTRZXWIAK");
        System.out.println("LXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELX");
        System.out.println("VRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHR");
        System.out.println("ZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJT");
        System.out.println("AMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBI");
        System.out.println("PEEWEVKAKOEWADREMXMTBHHCHRTKDNVRZCHRCLQOHP");
        System.out.println("WQAIIWXNRMGWOIIFKEE");
        String cipherText = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBW"
                + "RVXUOAKXAOSXXWEAHBWGJMMQMNKGRFVGXWTRZXWIAK"
                + "LXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELX"
                + "VRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHR"
                + "ZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJT"
                + "AMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBI"
                + "PEEWEVKAKOEWADREMXMTBHHCHRTKDNVRZCHRCLQOHP"
                + "WQAIIWXNRMGWOIIFKEE";
        ArrayList<VigenereCipher> obtainedResults = cipher.cryptoAnalysis(cipherText);
        //String[] obtainedResults = {"otrarespuesta","uy","nadaporaca"};
        //String[] obtainedResults = {"otrarespuesta","thiscryptosystemisnotsecure","nadaporaca"};
        String expectedResult = "thealmondtreewasintentativeblossomthedayswere"
                + "longeroftenendingwithmagnificenteveningsofcorrugated"
                + "pinkskiesthehuntingseasonwasoverwithhoundsandgunsput"
                + "awayforsixmonthsthevineyardswerebusyagainasthewellorganizedfarm"
                + "erstreatedtheirvinesandthemorelackadaisicalneighborshurriedto"
                + "dothepruningtheyshouldhavedoneinnovember";
        boolean found = false;
        int position = 0;
        for (int i = 0; i < obtainedResults.size(); i++) {
            if (obtainedResults.get(i).getText().equals(expectedResult)) {
                found = true;
                position = i;
            }
        }
        if (!found) {
            fail("Any Result matches the Expected Result " + expectedResult);
        }

        if (found) {
            System.out.println("The Obtained Result " + obtainedResults.get(position).getText() + " at index " + position + " matches the Expected Result "
                    + expectedResult);
        }

        System.out.println("TEST 3:");
        System.out.println("VigenereCipher: CryptoAnalysis");
        System.out.println("OAGNEYEKNLVLWSEZSBRGPUEJBWKEIJLWEKEFRWABRUVWWHRUIAZOXPHSRMIRWLBUWLWSLOYJLSGASUMKISEPXZXCEJXOXHPKLGYOMJOAGNHFQSGKXYIKISIEHGKHRUKDHYCFJZBZQRNWLACBMFZHLRWMXYYJSJWLVVHZBZULIWGCEJLLBASRTHXHVSIXHYIYMETUHYMKZBIJXKPLEIMFZOIIGJHDRKSVBZTCEQALVSISNAC");
        cipherText = "OAGNEYEKNLVLWSEZSBRGPUEJBWKEIJLWEKEFRWABRUVWWHRUIAZOXPHSRMIRWLBUWLWSLOYJLSGASUMKISEPXZXCEJXOXHPKLGYOMJOAGNHFQSGKXYIKISIEHGKHRUKDHYCFJZBZQRNWLACBMFZHLRWMXYYJSJWLVVHZBZULIWGCEJLLBASRTHXHVSIXHYIYMETUHYMKZBIJXKPLEIMFZOIIGJHDRKSVBZTCEQALVSISNAC";
        obtainedResults = cipher.cryptoAnalysis(cipherText);
        //String[] obtainedResults = {"otrarespuesta","uy","nadaporaca"};
        //String[] obtainedResults = {"otrarespuesta","thiscryptosystemisnotsecure","nadaporaca"};
        expectedResult = "kingahasuerusalsoknownasxerxesheldaonehundredandeightydayfeastinsusashushantodisplaythevastwealthofhiskingdomandthesplendorandgloryofhismajestykingahasuerusorderedhisqueenvashtitoappearbeforehimandhisguestswearinghercrowntodisplayherbeauty";
        found = false;
        position = 0;
        for (int i = 0; i < obtainedResults.size(); i++) {
            if (obtainedResults.get(i).getText().equals(expectedResult)) {
                found = true;
                position = i;
            }
        }
        if (!found) {
            fail("Any Result matches the Expected Result " + expectedResult);
        }

        if (found) {
            System.out.println("The Obtained Result " + obtainedResults.get(position).getText() + " at index " + position + " matches the Expected Result "
                    + expectedResult);
        }
    }
}