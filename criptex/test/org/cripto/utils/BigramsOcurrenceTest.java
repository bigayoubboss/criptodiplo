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

import org.cripto.utils.BigramsOcurrence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class BigramsOcurrenceTest {

    public BigramsOcurrenceTest() {
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
     * Test of equals method, of class BigramsOcurrence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new BigramsOcurrence(4, 5, 7);
        BigramsOcurrence instance = new BigramsOcurrence(4, 5, 7);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of compare method, of class BigramsOcurrence.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object o1 = new BigramsOcurrence(11, 12, 13);
        Object o2 = new BigramsOcurrence(23, 12, 6);
        BigramsOcurrence instance = new BigramsOcurrence();
        int expResult = -7;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        if (result != expResult) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of frequenciesMod26 method, of class BigramsOcurrence.
     */
    @Test
    public void testFrequencies() {
        System.out.println("frequencies");
        String cipherText = "AXFMXVAXVKAXV";
        ArrayList<BigramsOcurrence> expResult = new ArrayList<BigramsOcurrence>();
        expResult.add(new BigramsOcurrence(0, 23, 3));
        expResult.add(new BigramsOcurrence(23, 5, 1));
        expResult.add(new BigramsOcurrence(5, 12, 1));
        expResult.add(new BigramsOcurrence(12, 23, 1));
        expResult.add(new BigramsOcurrence(23, 21, 3));
        expResult.add(new BigramsOcurrence(21, 0, 1));
        expResult.add(new BigramsOcurrence(21, 10, 1));
        expResult.add(new BigramsOcurrence(10, 0, 1));
        ArrayList<BigramsOcurrence> result = BigramsOcurrence.frequenciesMod26(cipherText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of frequenciesMod189 method, of class BigramsOcurrence.
     */
    @Test
    public void testFrequenciesMod189() {
        System.out.println("frequenciesMod189");
        String cipherText = "ab.cdab.*ab.cdfghiab.";
        ArrayList<BigramsOcurrence> expResult = new ArrayList<BigramsOcurrence>();
        expResult.add(new BigramsOcurrence(65, 66, 4));
        expResult.add(new BigramsOcurrence(66, 14, 4));
        expResult.add(new BigramsOcurrence(14, 67, 2));
        expResult.add(new BigramsOcurrence(67, 68, 2));
        expResult.add(new BigramsOcurrence(68, 65, 1));
        expResult.add(new BigramsOcurrence(14, 10, 1));
        expResult.add(new BigramsOcurrence(10, 65, 1));
        expResult.add(new BigramsOcurrence(68, 70, 1));
        expResult.add(new BigramsOcurrence(70, 71, 1));
        expResult.add(new BigramsOcurrence(71, 72, 1));
        expResult.add(new BigramsOcurrence(72, 73, 1));
        expResult.add(new BigramsOcurrence(73, 65, 1));
        ArrayList<BigramsOcurrence> result = BigramsOcurrence.frequenciesMod189(cipherText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}