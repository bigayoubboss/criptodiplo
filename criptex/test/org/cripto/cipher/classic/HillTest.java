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

import org.cripto.utils.jama.Matrix;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cacolorador,damontanof,lvmorenoc,carodriguezb
 */
public class HillTest {

    private static Hill cipher;

    @BeforeClass
    public static void setUpClass() throws Exception {
        cipher = new Hill();
    }

    @Test
    public void testValidEncodeKey2x2() {

        System.out.println("Hill Cipher Valid Encode Key 2x2");

        String plainText = "july";
        String expResult = "DELW";

        Matrix key = new Matrix(2, 2);
        key.set(0, 0, 11);
        key.set(0, 1, 8);
        key.set(1, 0, 3);
        key.set(1, 1, 7);

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidEncodeKey3x3() {

        System.out.println("Hill Cipher Valid Encode Key 3x3");

        String plainText = "cristian";
        String expResult = "HTMHFINN";

        Matrix key = new Matrix(3, 3);
        key.set(0, 0, 11);
        key.set(0, 1, 8);
        key.set(0, 2, 12);
        key.set(1, 0, 3);
        key.set(1, 1, 7);
        key.set(1, 2, 32);
        key.set(2, 0, 34);
        key.set(2, 1, 5);
        key.set(2, 2, 28);

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidEncodeKey4x4() {

        System.out.println("Hill Cipher Valid Encode Key 4x4");

        String plainText = "abcdefghijklmnopqrstuvwxyz";
        String expResult = "LYMURWYOXUKIDSWCJQIWPOUQRJ";

        Matrix key = new Matrix(4, 4);
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

        String result = cipher.encode(plainText, key, null);
        assertEquals(expResult, result);
    }
}