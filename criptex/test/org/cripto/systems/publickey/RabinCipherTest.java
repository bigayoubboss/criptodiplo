/*
 * Universidad Nacional de Colombia - Sede Bogotá *
 *
 *      David Montaño - damontanofe@unal.edu.co
 *      Laura Moreno - lvmorenoc@unal.edu.co
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package org.cripto.systems.publickey;

import org.cripto.systems.publickey.RabinCipher;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class RabinCipherTest {

    public RabinCipherTest() {
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
     * Test of encrypt method, of class RabinCipher.
     */
    @Test
    public void testEncrypt1() {
        System.out.println("Rabin encrypt");

        String plainText = "bu";
        String p = "101";
        String q = "3";
        String result;
        try {
            result = RabinCipher.encrypt(plainText, p, q);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            result = "ERROR";
        }
        System.out.println("Resultado al Encriptar: " + result);
    }

    /**
     * Test of encrypt method, of class RabinCipher.
     */
    @Test
    public void testDecrypt1() {
        System.out.println("Rabin decrypt");

        String cText = "d336";
        String p = "101";
        String q = "3";
        String result[];
        try {
            result = RabinCipher.cryptoAnalysis(cText, p, q);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            result = null;
        }
        System.out.println("Resultado al Desencriptar: ");
        System.out.println("cantidad filas: " + result.length);
        for(String i : result){
            System.out.println(i);
        }

    }
}