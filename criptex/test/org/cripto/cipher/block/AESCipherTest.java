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

package org.cripto.cipher.block;

import org.cripto.cipher.block.AESCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanofe,lvmorenoc,carodriguezb
 */
public class AESCipherTest {

    public AESCipherTest() {
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
     * Test of encrypt method, of class AESCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");       
        String plainText = "TheAdvancedEncryptionStandardfinallyworks";
        String key = "0F1571C947D9E8590CB7ADD6AF7F6798";
        String expResult = "1fc0e163d663915424297f17668446b1950cbf0f3af12857ef61958f2cdb14e3fd44df266e26a935814e16c4b082023b";
        String result = AESCipher.longEncrypt(plainText, key);
        
        if(!expResult.equals(result)){
            fail("The result " + result + " is different from the expected result " + expResult);
        }
    }

    /**
     * Test of decrypt method, of class AESCipher.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String cipherText = "1fc0e163d663915424297f17668446b1950cbf0f3af12857ef61958f2cdb14e3fd44df266e26a935814e16c4b082023b";
        String key = "0F1571C947D9E8590CB7ADD6AF7F6798";
        String c = String.valueOf((char) 0);
        String expResult = "TheAdvancedEncryptionStandardfinallyworks".concat(c).concat(c).concat(c).concat(c).concat(c).concat(c).concat(c);
        String result = AESCipher.longDecrypt(cipherText, key);

        if(!expResult.equals(result)){
            fail("The result " + result + " is different from the expected result " + expResult);
        }
    }

}