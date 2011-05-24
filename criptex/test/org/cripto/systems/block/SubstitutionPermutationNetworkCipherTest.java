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

package org.cripto.systems.block;

import org.cripto.systems.block.SubstitutionPermutationNetworkCipher;
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
public class SubstitutionPermutationNetworkCipherTest {

    public SubstitutionPermutationNetworkCipherTest() {
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
     * Test of encrypt method, of class SubstitutionPermutationNetworkCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt: TEST 1");
        String plainText = "cglh";
        int[] substitution = {0xe, 0x4, 0xd, 0x1, 0x2, 0xf, 0xb, 0x8, 0x3, 0xa, 0x6, 0xc, 0x5, 0x9, 0x0, 0x7};
        int[] permutation = {1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,16};
        
        String key = "dkjengdp";
        int Nr = 4;
        String expResult = "lmng";
        String result = SubstitutionPermutationNetworkCipher.encrypt(plainText, substitution, permutation, key, Nr);
        if(!expResult.equals(result)){
            fail("The result " + result + " is different from the expected result " + expResult);
        }

        System.out.println("-------------------------------------------------------------");

        System.out.println("encrypt: TEST 2");
        plainText = "a";
        int[] substitution2 = {0xe, 0x4, 0xd, 0x1, 0x2, 0xf, 0xb, 0x8, 0x3, 0xa, 0x6, 0xc, 0x5, 0x9, 0x0, 0x7};
        int[] permutation2 = {1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,16};
        key = "dkjengdp";
        Nr = 1;
        expResult = "b";
        result = SubstitutionPermutationNetworkCipher.encrypt(plainText, substitution2, permutation2, key, Nr);
        if(!expResult.equals(result)){
            fail("The result " + result + " is different from the expected result " + expResult);
        }

        System.out.println("-------------------------------------------------------------");

        System.out.println("encrypt: TEST 3");
        plainText = "p";
        int[] substitution3 = {0xe, 0x4, 0xd, 0x1, 0x2, 0xf, 0xb, 0x8, 0x3, 0xa, 0x6, 0xc, 0x5, 0x9, 0x0, 0x7};
        int[] permutation3 = {1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,16};
        key = "dkjengdp";
        Nr = 1;
        expResult = "f";
        result = SubstitutionPermutationNetworkCipher.encrypt(plainText, substitution3, permutation3, key, Nr);
        if(!expResult.equals(result)){
            fail("The result " + result + " is different from the expected result " + expResult);
        }
    }

}