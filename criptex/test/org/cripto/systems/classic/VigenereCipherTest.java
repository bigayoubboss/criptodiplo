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

import org.cripto.systems.classic.VigenereCipher;
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

    public VigenereCipherTest() {
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
     * Test of VigenereCipher method, of class VigenereCipher.
     */
    @Test
    public void testVigenereCipher() {
        System.out.println("vigenereCipher");
        String plainText = "thiscryptosystemisnotsecure";
        String keyWord = "CIPHER";
        String obtainedResult = VigenereCipher.encrypt(plainText, keyWord);
        String expectedResult = "VPXZGIAXIVWPUBTTMJPWIZITWZT";
        // TODO review the generated test code and remove the default call to fail.
        if( !obtainedResult.equals(expectedResult)){
            fail("The Obtained Result " + obtainedResult + " doesn´t match the Expected Result " +
                    expectedResult);
        }
    }

    /**
     * Test of cryptoAnalysis method, of class VigenereCipher.
     */
    @Test
    public void testCryptoanalysis() {
        /*System.out.println("TEST 1:");
        System.out.println("VigenereCipher: CryptoAnalysis");
        System.out.println("VPXZGIAXIVWPUBTTMJPWIZITWZT");
        String cipherText = "VPXZGIAXIVWPUBTTMJPWIZITWZT";
        ArrayList<VigenereCipher> obtainedResults = VigenereCipher.cryptoAnalysis(cipherText);
        //String[] obtainedResults = {"otrarespuesta","uy","nadaporaca"};
        //String[] obtainedResults = {"otrarespuesta","thiscryptosystemisnotsecure","nadaporaca"};
        String expectedResult = "thiscryptosystemisnotsecure";
        // TODO review the generated test code and remove the default call to fail.
        boolean found = false;
        int position = 0;
        for(int i = 0; i < obtainedResults.size(); i++){
            if( obtainedResults.get(i).getText().equals(expectedResult)){
                found = true;
                position = i;
            }
        }
        if(!found){
            fail("Any Result matches the Expected Result " + expectedResult);
        }

        if(found)
            System.out.println("The Obtained Result " + obtainedResults.get(position).getText() + " at index "+ position +" matches the Expected Result " +
                    expectedResult);*/
         

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
        String cipherText = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBW" +
                            "RVXUOAKXAOSXXWEAHBWGJMMQMNKGRFVGXWTRZXWIAK" +
                            "LXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELX" +
                            "VRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHR" +
                            "ZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJT" +
                            "AMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBI" +
                            "PEEWEVKAKOEWADREMXMTBHHCHRTKDNVRZCHRCLQOHP" +
                            "WQAIIWXNRMGWOIIFKEE";
        ArrayList<VigenereCipher> obtainedResults = VigenereCipher.cryptoAnalysis(cipherText);
        //String[] obtainedResults = {"otrarespuesta","uy","nadaporaca"};
        //String[] obtainedResults = {"otrarespuesta","thiscryptosystemisnotsecure","nadaporaca"};
        String expectedResult = "thealmondtreewasintentativeblossomthedayswere" +
                                "longeroftenendingwithmagnificenteveningsofcorrugated" +
                                "pinkskiesthehuntingseasonwasoverwithhoundsandgunsput" +
                                "awayforsixmonthsthevineyardswerebusyagainasthewellorganizedfarm" +
                                "erstreatedtheirvinesandthemorelackadaisicalneighborshurriedto" +
                                "dothepruningtheyshouldhavedoneinnovember";
        // TODO review the generated test code and remove the default call to fail.
        boolean found = false;
        int position = 0;
        for(int i = 0; i < obtainedResults.size(); i++){
            if( obtainedResults.get(i).getText().equals(expectedResult)){
                found = true;
                position = i;
            }
        }
        if(!found){
            fail("Any Result matches the Expected Result " + expectedResult);
        }

        if(found)
            System.out.println("The Obtained Result " + obtainedResults.get(position).getText() + " at index "+ position +" matches the Expected Result " +
                    expectedResult);

        System.out.println("TEST 3:");
        System.out.println("VigenereCipher: CryptoAnalysis");
        System.out.println("OAGNEYEKNLVLWSEZSBRGPUEJBWKEIJLWEKEFRWABRUVWWHRUIAZOXPHSRMIRWLBUWLWSLOYJLSGASUMKISEPXZXCEJXOXHPKLGYOMJOAGNHFQSGKXYIKISIEHGKHRUKDHYCFJZBZQRNWLACBMFZHLRWMXYYJSJWLVVHZBZULIWGCEJLLBASRTHXHVSIXHYIYMETUHYMKZBIJXKPLEIMFZOIIGJHDRKSVBZTCEQALVSISNAC");
        cipherText = "OAGNEYEKNLVLWSEZSBRGPUEJBWKEIJLWEKEFRWABRUVWWHRUIAZOXPHSRMIRWLBUWLWSLOYJLSGASUMKISEPXZXCEJXOXHPKLGYOMJOAGNHFQSGKXYIKISIEHGKHRUKDHYCFJZBZQRNWLACBMFZHLRWMXYYJSJWLVVHZBZULIWGCEJLLBASRTHXHVSIXHYIYMETUHYMKZBIJXKPLEIMFZOIIGJHDRKSVBZTCEQALVSISNAC";
        obtainedResults = VigenereCipher.cryptoAnalysis(cipherText);
        //String[] obtainedResults = {"otrarespuesta","uy","nadaporaca"};
        //String[] obtainedResults = {"otrarespuesta","thiscryptosystemisnotsecure","nadaporaca"};
        expectedResult = "kingahasuerusalsoknownasxerxesheldaonehundredandeightydayfeastinsusashushantodisplaythevastwealthofhiskingdomandthesplendorandgloryofhismajestykingahasuerusorderedhisqueenvashtitoappearbeforehimandhisguestswearinghercrowntodisplayherbeauty";
        // TODO review the generated test code and remove the default call to fail.
        found = false;
        position = 0;
        for(int i = 0; i < obtainedResults.size(); i++){
            if( obtainedResults.get(i).getText().equals(expectedResult)){
                found = true;
                position = i;
            }
        }
        if(!found){
            fail("Any Result matches the Expected Result " + expectedResult);
        }

        if(found)
            System.out.println("The Obtained Result " + obtainedResults.get(position).getText() + " at index "+ position +" matches the Expected Result " +
                    expectedResult);
    }

}