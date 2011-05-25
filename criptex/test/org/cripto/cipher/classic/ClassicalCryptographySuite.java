/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cripto.cipher.classic;

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
@Suite.SuiteClasses({org.cripto.cipher.classic.HillCipherTest.class,org.cripto.cipher.classic.SubstitutionCipherTest.class,org.cripto.cipher.classic.ShiftCipherTest.class,org.cripto.cipher.classic.VigenereCipherTest.class,org.cripto.cipher.classic.PermutationCipherTest.class,org.cripto.cipher.classic.AffineCipherTest.class})
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