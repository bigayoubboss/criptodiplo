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

import java.util.Arrays;
import java.math.BigInteger;
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
public class MathUtilFunctionsTest {

    public MathUtilFunctionsTest() {
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
     * Test of GCD method, of class MathUtilFunctions.
     */
    @Test
    public void testGCD() {
        System.out.println("GCD");
        int a = 3;
        int b = 9;
        int expResult = 3;
        int result = MathUtilFunctions.GCD(a, b);
        if (expResult != result) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result "
                    + expResult);
        }
    }

    /**
     * Test of greatestCommonDivisor method, of class MathUtilFunctions.
     */
    @Test
    public void testGreatestCommonDivisor() {
        System.out.println("greatestCommonDivisor");
        int a = 165;
        int[] b = {235, 275, 285};
        int expResult = 5;
        int result = MathUtilFunctions.greatestCommonDivisor(a, b);
        if (expResult != result) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result "
                    + expResult);
        }
    }

    /**
     * Test of multiplicativeInverse method, of class MathUtilFunctions.
     */
    @Test
    public void testMultiplicativeInverse() {
        System.out.println("multiplicativeInverse");
        int a = 15;
        int b = 26;
        double expResult = 7;
        double result = MathUtilFunctions.multiplicativeInverse(a, b);
        if (expResult != result) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result "
                    + expResult);
        }
    }

    /**
     * Test of solveCRT method, of class MathUtilFunctions.
     */
    @Test
    public void testSolveCRT() {
        System.out.println("SolveCRT");
        BigInteger[] residue = {new BigInteger("3"), new BigInteger("0"), new BigInteger("0"), new BigInteger("8")};
        BigInteger[] modulus = {new BigInteger("4"), new BigInteger("5"), new BigInteger("7"), new BigInteger("9")};
        BigInteger[] expResult = {new BigInteger("35"), new BigInteger("1260")};
        BigInteger[] result = MathUtilFunctions.solveCRT(residue, modulus);

        for (int i = 0; i < 2; i++) {
            System.out.println("Result: " + result[i]);
        }

        for (int i = 0; i < 2; i++) {
            System.out.println("ExpResult: " + expResult[i]);
        }
    }

    @Test
    public void testSolveCongruenceSystem() {
        System.out.println("solveCongruenceSystem");
        double a = 4.0;
        double b = 1.0;
        double u = 17.0;
        double c = 19.0;
        double d = 1.0;
        double v = 10.0;
        double n = 26.0;
        int[] expResult = {3, 5};
        int[] result = MathUtilFunctions.solveCongruenceSystem(a, b, u, c, d, v, n);
        if (!Arrays.equals(result, expResult)) {
            fail("The Obtained Result " + result + " doesn´t match the Expected Result "
                    + expResult);
        }
    }
}