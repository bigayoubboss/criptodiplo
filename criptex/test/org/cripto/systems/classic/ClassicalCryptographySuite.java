/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cripto.systems.classic;

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
@Suite.SuiteClasses({org.cripto.systems.classic.HillCipherTest.class,org.cripto.systems.classic.SubstitutionCipherTest.class,org.cripto.systems.classic.ShiftCipherTest.class,org.cripto.systems.classic.VigenereCipherTest.class,org.cripto.systems.classic.PermutationCipherTest.class,org.cripto.systems.classic.AffineCipherTest.class})
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