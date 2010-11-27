/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ClassicalCryptography;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author SirCristian
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ClassicalCryptography.HillCipherTest.class,ClassicalCryptography.SubstitutionCipherTest.class,ClassicalCryptography.ShiftCipherTest.class,ClassicalCryptography.VigenereCipherTest.class,ClassicalCryptography.PermutationCipherTest.class,ClassicalCryptography.AffineCipherTest.class})
public class ClassicalCryptographySuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}