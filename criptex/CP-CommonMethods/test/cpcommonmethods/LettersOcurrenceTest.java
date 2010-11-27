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

package cpcommonmethods;

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
public class LettersOcurrenceTest {

    public LettersOcurrenceTest() {
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
     * Test of equals method, of class LettersOcurrence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new LettersOcurrence(4, 5);
        LettersOcurrence instance = new LettersOcurrence(4, 5);
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
     * Test of compare method, of class LettersOcurrence.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object o1 = new LettersOcurrence(3,6);
        Object o2 = new LettersOcurrence(8,4);
        LettersOcurrence instance = new LettersOcurrence();
        int expResult = -2;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }

    /**
     * Test of frequencies method, of class LettersOcurrence.
     */
    @Test
    public void testFrequencies() {
        System.out.println("frequencies");
        int[] encodedText = Code.encodeMod26("FMXVEDKAPHFERBNDKRXRSREFMORUDSDKDVSHVUFEDKAPRKDLYEVLRHHRH");
        ArrayList<LettersOcurrence> expResult = new ArrayList<LettersOcurrence>();
        //2, 1, 0, 7, 5, 4, 0, 5, 0, 0, 5, 2, 2, 1, 1, 2, 0, 8, 3, 0, 2, 4, 0, 2, 1, 0
        expResult.add(new LettersOcurrence(0, 2));
        expResult.add(new LettersOcurrence(1, 1));
        expResult.add(new LettersOcurrence(2, 0));
        expResult.add(new LettersOcurrence(3, 7));
        expResult.add(new LettersOcurrence(4, 5));
        expResult.add(new LettersOcurrence(5, 4));
        expResult.add(new LettersOcurrence(6, 0));
        expResult.add(new LettersOcurrence(7, 5));
        expResult.add(new LettersOcurrence(8, 0));
        expResult.add(new LettersOcurrence(9, 0));
        expResult.add(new LettersOcurrence(10, 5));
        expResult.add(new LettersOcurrence(11, 2));
        expResult.add(new LettersOcurrence(12, 2));
        expResult.add(new LettersOcurrence(13, 1));
        expResult.add(new LettersOcurrence(14, 1));
        expResult.add(new LettersOcurrence(15, 2));
        expResult.add(new LettersOcurrence(16, 0));
        expResult.add(new LettersOcurrence(17, 8));
        expResult.add(new LettersOcurrence(18, 3));
        expResult.add(new LettersOcurrence(19, 0));
        expResult.add(new LettersOcurrence(20, 2));
        expResult.add(new LettersOcurrence(21, 4));
        expResult.add(new LettersOcurrence(22, 0));
        expResult.add(new LettersOcurrence(23, 2));
        expResult.add(new LettersOcurrence(24, 1));
        expResult.add(new LettersOcurrence(25, 0));

        ArrayList<LettersOcurrence> result = LettersOcurrence.frequencies(encodedText);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult);
        }
    }
}