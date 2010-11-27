/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mac;

import BlockCryptography.AESCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class CBCMacTest {

    public CBCMacTest() {
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
     * Test of encrypt method, of class CBCMac.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String plainText = "holaholaholaholaholaholahola";
        String expResult = ">R??(×vC?rÓ=¼";
        int[] key = new int[]{0x0f, 0x15, 0x71, 0xc9,
                                0x47, 0xd9, 0xe8, 0x59,
                                0x0c, 0xb7, 0xad, 0xd6,
                                0xaf, 0x7f, 0x67, 0x98};
        String result = CBCMac.encrypt(plainText, key);

        if(expResult.compareTo(result) != 0){
            System.out.println("Expecting: " + expResult);
            System.out.println("Got: " + result);
        }
        
        //String ag = AESCipher.decrypt(result, key);
    }

}