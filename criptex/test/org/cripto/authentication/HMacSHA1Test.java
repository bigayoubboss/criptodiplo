/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cripto.authentication;

import org.cripto.authentication.HMacSHA1;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mona
 */
public class HMacSHA1Test {

    public HMacSHA1Test() {
    }

    /**
     * Test of hash method, of class HMacSHA1.
     */
    @Test
    public void testHash() throws Exception {
        System.out.println("hash");
        String plainText = "abcd";
        String key = "1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef";
        String expResult = "0fafe16b01d772cd56948d9fde255062cbcdaf64";
        String result = HMacSHA1.hash(plainText, key);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of hexa2binary method, of class HMacSHA1.
     */
    @Test
    public void testHexa2Binary() {
        System.out.println("hexa2Binary");
        String hexa = "f01e";
        String expResult = "1111000000011110";
        String result = HMacSHA1.hexa2binary(hexa);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of hexa2binary method, of class HMacSHA1.
     */
    @Test
    public void testBinary2Hexa() {
        System.out.println("binary2hexa");
        String hexa = "1111000000011110";
        String expResult = "f01e";
        String result = HMacSHA1.binary2hexa(hexa);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of padsXORkey method, of class HMacSHA1.
     */
    @Test
    public void testPadsXORkey() {
        System.out.println("padsXORkey");
        String ipad = "3636363636";
        String opad = "5C5C5C5C5C";
        String key =  "0F1EF101F0";
        String[] expResult = {"3928c737c6", "5342ad5dac"};
        String[] result = HMacSHA1.padsXORkey(ipad, opad, key);
        if (!Arrays.equals(result, expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

}