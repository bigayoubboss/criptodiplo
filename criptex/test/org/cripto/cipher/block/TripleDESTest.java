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

        String plainText = "6368726973746961";
        String key = "294383EEA1D603C21CAF5531005B8035D6756C23CD823818";
        String expResult = "585372D07CB8D6E0";
        String result = cipherTDES.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncode2() {

        System.out.println("Triple DES Cipher Valid Encode 1");

        String plainText = "4348524953544941";
        String key = "87258FA12CB68BF6380687F9A71CCD56882DB7A081741ADD";
        String expResult = "C4559E95447323D4";
        String result = cipherTDES.encode(plainText, key, null);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode1() {

        System.out.println("Triple DES Cipher Valid Decode 1");

        String cipherText = "585372D07CB8D6E0";
        String key = "294383EEA1D603C21CAF5531005B8035D6756C23CD823818";
        String expResult = "6368726973746961";
        String result = cipherTDES.decode(cipherText, key);

        assertEquals(expResult, result);
    }

    @Test
    public void testValidDecode2() {

        System.out.println("Triple DES Cipher Valid Decode 2");

        String cipherText = "C4559E95447323D4";
        String key = "87258FA12CB68BF6380687F9A71CCD56882DB7A081741ADD";
        String expResult = "4348524953544941";
        String result = cipherTDES.decode(cipherText, key);

        assertEquals(expResult, result);

    }

    @Test
    public void testValideEncodeDecodeEncode() {

        System.out.println("DES Cipher Valid Decode 3");

        String plainText = "01224537888BCAAF";
        String keyA = "01224537888BCAAF";
        String expResult = "672E1079B4883520";

        String result = cipherDES.encode(plainText, keyA, null);
        assertEquals(expResult, result);

        String keyB = "C53865578BEDD5FB";
        expResult = "464F40710B5B3555";
        result = cipherDES.decode(result, keyB);
        assertEquals(expResult, result);

        String keyC = "B29FB588A0FA8203";
        expResult = "018A86338D2E4CBA";
        result = cipherDES.encode(result, keyC, null);
        assertEquals(expResult, result);

    }

    @Test
    public void testValideDecodeEncodeDecode() {

        System.out.println("DES Cipher Valid Decode 3");

        String cipherText = "018A86338D2E4CBA";
        String keyC = "B29FB588A0FA8203";
        String expResult = "464F40710B5B3555";

        String result = cipherDES.decode(cipherText, keyC);
        assertEquals(expResult, result);

        String keyB = "C53865578BEDD5FB";
        expResult = "672E1079B4883520";
        result = cipherDES.encode(result, keyB, null);
        assertEquals(expResult, result);

        String keyA = "01224537888BCAAF";
        expResult = "01224537888BCAAF";
        result = cipherDES.decode(result, keyA);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValideDecodeEncodeDecode2() {

        System.out.println("DES Cipher Valid Decode 3");

        String cipherText = "BFF1054F5F72613B";
        String keyC = "83BBFCA466D41AC4";
        String expResult = "5DFFEA4E6451AC94";

        String result = cipherDES.decode(cipherText, keyC);
        assertEquals(expResult, result);

        String keyB = "7A20EA8BFF9FCED0";
        expResult = "94DF059DCD51EEFD";
        result = cipherDES.encode(result, keyB, null);
        assertEquals(expResult, result);

        String keyA = "D315189B7C385B50";
        expResult = "234E8936ED4DE89A";
        result = cipherDES.decode(result, keyA);
        assertEquals(expResult, result);

    }
}
