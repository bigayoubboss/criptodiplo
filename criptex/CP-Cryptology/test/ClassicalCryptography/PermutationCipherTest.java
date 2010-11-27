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

package ClassicalCryptography;

import java.io.IOException;
import java.util.Arrays;
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
public class PermutationCipherTest {

    public PermutationCipherTest() {
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
     * Test of permutationCipher method, of class PermutationCipher.
     */
    @Test
    public void testPermutationCipher() throws Exception {
        System.out.println("permutationCipher");
        System.out.println("Test 1:");
        String plainText = "shesellsseashellsbytheseashore";
        String keyNumberString = "351642";
        String expResult = "EESLSHSALSESLSHBLEHSYEETHRAEOS";
        String result = PermutationCipher.encrypt(plainText, keyNumberString);
        // TODO review the generated test code and remove the default call to fail.
        if( !result.equals(expResult)){
            fail("The Obtained Result " + result + " doesn´t match the Expected Result " +
                    expResult + " in test 1");
        }
    }

    /**
     * Test of toIntArray method, of class PermutationCipher.
     */
    @Test
    public void testToIntArray() throws Exception {
        System.out.println("toIntArray");
        System.out.println("test 1: no es sucesión, el 8 sobra");
        String numberString = "13428";
        boolean exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }
        
        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 1");
        }

        System.out.println("test 2: no es sucesión, falta el 5");
        numberString = "36421";
        exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 2");
        }

        System.out.println("test 3: letra en numberString");
        numberString = "8523a64791";
        exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 3");
        }

        System.out.println("test 4: tiene el número 0");
        numberString = "8523640791";
        exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 4");
        }

        System.out.println("test 5: repite número 8");
        numberString = "852364781";
        exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 5");
        }

        System.out.println("test 6: repite número 6");
        numberString = "654321876";
        exception = false;
        try{
            int[] result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wasn`t thrown in test 6");
        }

        System.out.println("test 7:");
        System.out.println("number String 654321879");
        numberString = "654321879";
        exception = false;
        int[] expResult = {6,5,4,3,2,1,8,7,9};
        int[] result = null;
        try{
            result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(exception){
            fail("Exception was thrown in test 7");
        }
        if( !Arrays.equals(result,expResult)){
            fail("The Obtained Result " + printIntegerArray(result) + " doesn´t match the Expected Result " +
                    printIntegerArray(expResult) + " in test 7");
        }

        System.out.println("test 8:");
        System.out.println("number String 34615287");
        numberString = "34615287";
        exception = false;
        int[] expResult1 = {3, 4, 6, 1, 5, 2, 8, 7};
        result = null;
        try{
            result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(exception){
            fail("Exception was thrown in test 8");
        }
        if( !Arrays.equals(result,expResult1)){
            fail("The Obtained Result " + printIntegerArray(result) + " doesn´t match the Expected Result " +
                    printIntegerArray(expResult1) + " in test 8");
        }

        System.out.println("test 9:");
        System.out.println("no es secuencia, falta el 4");
        numberString = "56123";
        exception = false;
        result = null;
        try{
            result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(!exception){
            fail("Exception wassn´t thrown in test 9");
        }

        System.out.println("test 9:");
        System.out.println("number String 351642");
        numberString = "351642";
        exception = false;
        int[] expResult2 = {3,5,1,6,4,2};
        result = null;
        try{
            result = PermutationCipher.toIntArray(numberString);
        }catch(IOException e){
            System.out.println(e.getMessage());
            exception = true;
        }

        // TODO review the generated test code and remove the default call to fail.
        if(exception){
            fail("Exception was thrown in test 9");
        }
        if( !Arrays.equals(result,expResult2)){
            fail("The Obtained Result " + printIntegerArray(result) + " doesn´t match the Expected Result " +
                    printIntegerArray(expResult2) + " in test 9");
        }
    }

    public String printIntegerArray(int[] array){
        String description = "[";
        for(int i = 0; i < array.length; i++){
            description += (array[i])+"; ";
        }
        description += "]";
        return description;
    }

}