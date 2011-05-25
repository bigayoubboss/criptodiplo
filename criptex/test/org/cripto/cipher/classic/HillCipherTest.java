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

import org.cripto.cipher.classic.HillCipher;
import org.cripto.utils.jama.Matrix;
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
public class HillCipherTest {

    public HillCipherTest() {
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
     * Test of encrypt method, of class HillCipher.
     */
    @Test
    public void testHillCipher() {
        System.out.println("hillCipher");
        System.out.println("test 1:");
        System.out.println("plainText = 'july'");
        String plainText = "july";
        System.out.println("key =");
        Matrix key = new Matrix(2,2);
        key.set(0, 0, 11);
        key.set(0, 1, 8);
        key.set(1, 0, 3);
        key.set(1, 1, 7);
        key.print(2, 2);
        String expResult = "DELW";
        String result = HillCipher.encrypt(plainText, key);
        if( !result.equals(expResult)){
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult + " in test 1");
        }

        System.out.println("test 2:");
        System.out.println("plainText = 'david'");
        plainText = "david";
        System.out.println("key =");
        key = new Matrix(2,2);
        key.set(0, 0, 11);
        key.set(0, 1, 8);
        key.set(1, 0, 3);
        key.set(1, 1, 7);
        key.print(2, 2);
        expResult = "HYVQH";
        result = HillCipher.encrypt(plainText, key);
        if( !result.equals(expResult)){
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult + " in test 2");
        }

        System.out.println("test 3:");
        System.out.println("plainText = 'cristian'");
        plainText = "cristian";
        System.out.println("key =");
        key = new Matrix(3,3);
        key.set(0, 0, 11);
        key.set(0, 1, 8);
        key.set(0, 2, 12);
        key.set(1, 0, 3);
        key.set(1, 1, 7);
        key.set(1, 2, 32);
        key.set(2, 0, 34);
        key.set(2, 1, 5);
        key.set(2, 2, 28);
        key.print(2, 2);
        expResult = "HTMHFINN";
        result = HillCipher.encrypt(plainText, key);
        if( !result.equals(expResult)){
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult + " in test 3");
        }

        System.out.println("test 4:");
        System.out.println("plainText = 'abcdefghijklmnopqrstuvwxyz'");
        plainText = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("key =");
        key = new Matrix(4,4);
        key.set(0, 0, 82);
        key.set(0, 1, 20);
        key.set(0, 2, 40);
        key.set(0, 3, 16);
        key.set(1, 0, 79);
        key.set(1, 1, 3);
        key.set(1, 2, 46);
        key.set(1, 3, 37);
        key.set(2, 0, 64);
        key.set(2, 1, 71);
        key.set(2, 2, 6);
        key.set(2, 3, 29);
        key.set(3, 0, 4);
        key.set(3, 1, 3);
        key.set(3, 2, 2);
        key.set(3, 3, 1);
        key.print(2, 2);
        expResult = "LYMURWYOXUKIDSWCJQIWPOUQRJ";
        result = HillCipher.encrypt(plainText, key);
        if( !result.equals(expResult)){
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult + " in test 4");
        }
    }

}