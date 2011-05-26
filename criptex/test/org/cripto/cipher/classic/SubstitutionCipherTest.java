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
public class SubstitutionCipherTest {

    private static SubstitutionCipher cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new SubstitutionCipher();
    }

    @Test
    public void testEncode() {

        System.out.println("Substitution Cipher Encode");

        String plainText = "thisciphertextcannotbedecrypted";
        String newAlphabet = "XNYAHPOGZQWBTSFLRCVMUEKJDI";
        String expResult = "MGZVYZLGHCMHJMYXSSFMNHAHYCDLMHA";
        
        String result = cipher.encode(plainText, newAlphabet, null);

        assertEquals(expResult, result);
    }
    
    @Test
    public void testDecode() {

        System.out.println("Substitution Cipher Decode");

        String cipherText = "MGZVYZLGHCMHJMYXSSFMNHAHYCDLMHA";
        String newAlphabet = "XNYAHPOGZQWBTSFLRCVMUEKJDI";
        String expResult = "thisciphertextcannotbedecrypted";

        String result = cipher.decode(cipherText, newAlphabet);

        assertEquals(expResult, result);
    }
}