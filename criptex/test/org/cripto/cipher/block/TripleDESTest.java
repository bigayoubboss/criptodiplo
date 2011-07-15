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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class TripleDESTest {

    private static TripleDES cipherTDES;
    private static DES cipherDES;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipherTDES = new TripleDES();
        cipherDES = new DES();
    }

    @Test
    public void testValidEncode1() {

        System.out.println("Triple DES Cipher Valid Encode 1");

        String plainText = "63616c6c6261636b";
        String key = "B4AA37F5B02532B6B4AA37F5B02532B6B4AA37F5B02532B6";
        String expResult = "4ec41ce1c46f7e83";
        String result = cipherTDES.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("Triple DES Cipher Valid Encode 1");

        String plainText = "01224537888bcaaf";
        String key = "01224537888bcaafC53865578BEDD5FBB29FB588A0FA8203";
        String expResult = "018a86338d2e4cba";
        String result = cipherTDES.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode1() {

        System.out.println("Triple DES Cipher Valid Decode 1");

        String cipherText = "4ec41ce1c46f7e83";
        String key = "B4AA37F5B02532B6B4AA37F5B02532B6B4AA37F5B02532B6";
        String expResult = "63616c6c6261636b";
        String result = cipherTDES.decode(cipherText, key);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode2() {

        System.out.println("Triple DES Cipher Valid Decode 2");

        String cipherText = "018a86338d2e4cba";
        String key = "01224537888bcaafC53865578BEDD5FBB29FB588A0FA8203";
        String expResult = "01224537888bcaaf";
        String result = cipherTDES.decode(cipherText, key);

        assertEquals(expResult, result);

    }

    @Test
    public void testValideEncodeDecodeEncode() {

        System.out.println("DES Cipher Valid Decode 3");

        String plainText = "01224537888bcaaf";
        String keyA = "01224537888bcaaf";
        String expResult = "672e1079b4883520";

        String result = cipherDES.encode(plainText, keyA, null);
        assertEquals(expResult, result);

        String keyB = "C53865578BEDD5FB";
        expResult = "464f40710b5b3555";
        result = cipherDES.decode(result, keyB);
        assertEquals(expResult, result);

        String keyC = "B29FB588A0FA8203";
        expResult = "018a86338d2e4cba";
        result = cipherDES.encode(result, keyC, null);
        assertEquals(expResult, result);

    }

    @Test
    public void testValideDecodeEncodeDecode() {

        System.out.println("DES Cipher Valid Decode 3");

        String plainText = "018a86338d2e4cba";
        String keyA = "B29FB588A0FA8203";
        String expResult = "464f40710b5b3555";

        String result = cipherDES.decode(plainText, keyA);
        assertEquals(expResult, result);

        String keyB = "C53865578BEDD5FB";
        expResult = "672e1079b4883520";
        result = cipherDES.encode(result, keyB, null);
        assertEquals(expResult, result);

        String keyC = "01224537888bcaaf";
        expResult = "01224537888bcaaf";
        result = cipherDES.decode(result, keyC);
        assertEquals(expResult, result);

    }
}
