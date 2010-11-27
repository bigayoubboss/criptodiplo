/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HashFunctions;

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
public class SHA1Test {

    public SHA1Test() {
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
     * Test of hash method, of class SHA1.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        String plainText = "abcd";
        String expResult = "81fe8bfe87576c3ecb22426f8e57847382917acf";
        String result = SHA1.hash(plainText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of SHA1pad method, of class SHA1.
     */
    @Test
    public void testSHA1pad() {
        System.out.println("SHA1pad");
        String plainText = "abcd";
        String expResult = "01100001011000100110001101100100100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000";
        String result = SHA1.SHA1pad(plainText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of long2hexa8 method, of class SHA1.
     */
    @Test
    public void testLong2hexa8() {
        System.out.println("long2hexa8");
        long number = 0xFF4400L;
        String expResult = "00ff4400";
        String result = SHA1.long2hexa8(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of string2binary method, of class SHA1.
     */
    @Test
    public void testString2binary() {
        System.out.println("string2binary");
        String text = "abcd";
        String expResult = "01100001011000100110001101100100";
        String result = SHA1.string2binary(text);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of long2binary32 method, of class SHA1.
     */
    @Test
    public void testLong2binary32() {
        System.out.println("long2binary32");
        long number = 0xFF11L;
        String expResult = "00000000000000001111111100010001";
        String result = SHA1.long2binary32(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of long2binary64 method, of class SHA1.
     */
    @Test
    public void testLong2binary64() {
        System.out.println("long2binary64");
        long number = 0x11FF11FFL;
        String expResult = "0000000000000000000000000000000000010001111111110001000111111111";
        String result = SHA1.long2binary64(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of ft method, of class SHA1.
     */
    @Test
    public void testFt() {
        System.out.println("ft");
        long B = 0x0L;
        long C = 0x1L;
        long D = 0xFL;
        int t = 1;
        long expResult = 0xFL;
        long result = SHA1.ft(B, C, D, t);
        if (result != expResult) {
            fail("The Obtained Result " + Long.toHexString(result) + " doesn´t match the Expected Result " +
                    Long.toHexString(expResult));
        }
    }

    /**
     * Test of kt method, of class SHA1.
     */
    @Test
    public void testKt() {
        System.out.println("kt");
        int t = 22;
        long expResult = 0x6ED9EBA1L;
        long result = SHA1.kt(t);
        if (result != expResult) {
            fail("The Obtained Result " + Long.toHexString(result) + " doesn´t match the Expected Result " +
                    Long.toHexString(expResult));
        }
    }

    /**
     * Test of rotl1 method, of class SHA1.
     */
    @Test
    public void testRotl1() {
        System.out.println("rotl1");
        Long number = 0xF0F0F0F0L;
        Long expResult = 0xE1E1E1E1L;
        Long result = SHA1.rotl1(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + Long.toHexString(result) + " doesn´t match the Expected Result " +
                    Long.toHexString(expResult));
        }
    }

    /**
     * Test of rotl5 method, of class SHA1.
     */
    @Test
    public void testRotl5() {
        System.out.println("rotl5");
        Long number = 0xF0F0F0F0L;
        Long expResult = 0x1E1E1E1EL;
        Long result = SHA1.rotl5(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + Long.toHexString(result) + " doesn´t match the Expected Result " +
                    Long.toHexString(expResult));
        }
    }

    /**
     * Test of rotl30 method, of class SHA1.
     */
    @Test
    public void testRotl30() {
        System.out.println("rotl30");
        Long number = 0xF0F0F0F0L;
        Long expResult = 0x3C3C3C3CL;
        Long result = SHA1.rotl30(number);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + Long.toHexString(result) + " doesn´t match the Expected Result " +
                    Long.toHexString(expResult));
        }
    }

}