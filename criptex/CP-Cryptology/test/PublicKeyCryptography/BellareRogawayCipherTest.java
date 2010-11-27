/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PublicKeyCryptography;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mona
 */
public class BellareRogawayCipherTest {

    public BellareRogawayCipherTest() {
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
     * Test of encryptBR method, of class BellareRogawayCipher.
     */
    @Test
    public void testEncryptAndDecrypt() throws Exception {
        System.out.println("encrypt and decrypt");
        String sp = "2489033945973905196062566009";
        String sq = "2489033945973905196062566159";
        String sb = "1660196197047258567239184383";
        String sa = "239577614845539696844938053674770643594584991645724543";
        String plainText = "withaheavyheartiaccompaniedmyfriendwestartedaboutfouroclocklegrandjupiterthedogandmyselfjupiterhadwithhimthescytheandspadesthewholeofwhichheinsisteduponcarryingmorethroughfearitseemedtomeoftrustingeitheroftheimplementswithinreachofhismasterthanfromanyexcessofindustryorcomplaisancehisdemeanorwasdoggedintheextremeanddatdeucedbugwerethesolewordswhichescapedhislipsduringthejourneyformyownpartihadchargeofacoupleofdarklanternswhilelegrandcontentedhimselfwiththescarabaeuswhichhecarriedattachedtotheendofabitofwhipcordtwirlingittoandfrowiththeairofaconjurorashewent";
        String sn = RSACipher.calculateN(sp, sq);
        String cipherText = BellareRogawayCipher.encrypt(plainText, sn, sb);
        String result = BellareRogawayCipher.decrypt(cipherText, sn, sa);
        if (!plainText.equals(result)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    plainText);
        }
    }
}