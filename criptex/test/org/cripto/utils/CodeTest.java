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
package org.cripto.utils;

import org.cripto.utils.Code;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class CodeTest {

    public CodeTest() {
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
     * Test of encodeMod26 method, of class Code.
     */
    @Test
    public void testEncodeMod26() {
        System.out.println("encode");
        String text = "aBcDefGhIJklmNopqRSTUVwxyZ";
        int[] expResult = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        int[] result = Code.encodeMod26(text);
        // TODO review the generated test code and remove the default call to fail.
        if (!Arrays.equals(expResult, result)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of decodeMod26 method, of class Code.
     */
    @Test
    public void testDecodeMod26() {
        System.out.println("decode");
        int[] numberArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        String expResult = "abcdefghijklmnopqrstuvwxyz";
        String result = Code.decodeMod26(numberArray);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of encodeMod189 method, of class Code.
     */
    @Test
    public void testEncodeMod189() {
        System.out.println("encodeMod189");
        String text = "AbCdEf45!.Áý¬ëÞ";
        int[] expResult = {33, 66, 35, 68, 37, 70, 20, 21, 1, 14, 126, 186, 106, 168, 155};
        int[] result = Code.encodeMod189(text);
        if (!Arrays.equals(expResult, result)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of decodeMod189 method, of class Code.
     */
    @Test
    public void testDecodeMod189() {
        System.out.println("decode");
        int[] numberArray = {33, 66, 35, 68, 37, 70, 20, 21, 1, 14, 126, 186, 106, 168, 155};
        String expResult = "AbCdEf45!.Áý¬ëÞ";
        String result = Code.decodeMod189(numberArray);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of removeSpecialCharactersMod189 method, of class Code.
     */
    @Test
    public void testRemoveSpecialCharactersMod189() {
        System.out.println("removeSpecialCharactersMod189");
        for (int i = 0; i < 256; i++){
            char o = (char) i;
            System.out.println(i + " : " + o);
        }
        String text = "AbCdEf45!.Áý¬ëÞ0";
        String expResult = "AbCdEf45!.Áý¬ëÞ0";
        String result = Code.removeCharactersOutOfMod189(text);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}