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

import org.cripto.utils.TrigramsOcurrence;
import java.util.ArrayList;
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
public class TrigramsOcurrenceTest {

    public TrigramsOcurrenceTest() {
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
     * Test of equals method, of class TrigramsOcurrence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new TrigramsOcurrence(5, 4, 6, 11);
        TrigramsOcurrence instance = new TrigramsOcurrence(5, 4, 6, 11);
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
     * Test of compare method, of class TrigramsOcurrence.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object o1 = new TrigramsOcurrence(6, 7, 8, 10);
        Object o2 = new TrigramsOcurrence(3, 4, 5, 12);
        TrigramsOcurrence instance = new TrigramsOcurrence();
        int expResult = 2;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of frequenciesMod26 method, of class TrigramsOcurrence.
     */
    @Test
    public void testFrequencies() {
        System.out.println("frequencies");
        String cipherText = "AXFMXVAXVKAXV";
        ArrayList<TrigramsOcurrence> expResult = new ArrayList<TrigramsOcurrence>();
        expResult.add(new TrigramsOcurrence(0, 23, 5, 1));
        expResult.add(new TrigramsOcurrence(23, 5, 12, 1));
        expResult.add(new TrigramsOcurrence(5, 12, 23, 1));
        expResult.add(new TrigramsOcurrence(12, 23, 21, 1));
        expResult.add(new TrigramsOcurrence(23, 21, 0, 1));
        expResult.add(new TrigramsOcurrence(21, 0, 23, 1));
        expResult.add(new TrigramsOcurrence(0, 23, 21, 2));
        expResult.add(new TrigramsOcurrence(23, 21, 10, 1));
        expResult.add(new TrigramsOcurrence(21, 10, 0, 1));
        expResult.add(new TrigramsOcurrence(10, 0, 23, 1));
        ArrayList<TrigramsOcurrence> result = TrigramsOcurrence.frequenciesMod26(cipherText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of frequenciesMod189 method, of class TrigramsOcurrence.
     */
    @Test
    public void testFrequenciesMod189() {
        System.out.println("frequenciesMod189");
        String cipherText = "ab.cdab.*ab.cdab.";
        ArrayList<TrigramsOcurrence> expResult = new ArrayList<TrigramsOcurrence>();
        expResult.add(new TrigramsOcurrence(65, 66, 14, 4));
        expResult.add(new TrigramsOcurrence(66, 14, 67, 2));
        expResult.add(new TrigramsOcurrence(14, 67, 68, 2));
        expResult.add(new TrigramsOcurrence(67, 68, 65, 2));
        expResult.add(new TrigramsOcurrence(68, 65, 66, 2));
        expResult.add(new TrigramsOcurrence(66, 14, 10, 1));
        expResult.add(new TrigramsOcurrence(14, 10, 65, 1));
        expResult.add(new TrigramsOcurrence(10, 65, 66, 1));
        ArrayList<TrigramsOcurrence> result = TrigramsOcurrence.frequenciesMod189(cipherText);
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}