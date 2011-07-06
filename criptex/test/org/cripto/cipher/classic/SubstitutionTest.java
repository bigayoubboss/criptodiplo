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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class SubstitutionTest {

    private static Substitution cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new Substitution();
    }

    @Test
    public void testValidEncode() {

        System.out.println("Substitution Cipher Valid Encode");

        String plainText = "thisciphertextcannotbedecrypted";
        String newAlphabet = "XNYAHPOGZQWBTSFLRCVMUEKJDI";
        String expResult = "MGZVYZLGHCMHJMYXSSFMNHAHYCDLMHA";
        
        String result = cipher.encode(plainText, newAlphabet, null);

        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidDecode() {

        System.out.println("Substitution Cipher Valid Decode");

        String cipherText = "MGZVYZLGHCMHJMYXSSFMNHAHYCDLMHA";
        String newAlphabet = "XNYAHPOGZQWBTSFLRCVMUEKJDI";
        String expResult = "thisciphertextcannotbedecrypted";

        String result = cipher.decode(cipherText, newAlphabet);

        assertEquals(expResult, result);
    }
}