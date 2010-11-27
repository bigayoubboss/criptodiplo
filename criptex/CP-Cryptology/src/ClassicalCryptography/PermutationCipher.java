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

package ClassicalCryptography;

import cpcommonmethods.Code;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class PermutationCipher {

    /**
     * Returns an alphabetic ciphertext using Permutation Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  keyNumberString String of Integers, if there are no integer characters then
     * an IOException is thrown
     * @return the cipher text string
     */
    public static String encrypt(String plainText, String keyNumberString) throws IOException {
        System.out.println("permutationCipher running...");
        // Algorithm
        // encodeMod26 plainText and key
        int[] encodedPlainText = Code.encodeMod26(plainText);
        int[] encodedKey = toIntArray(keyNumberString);
        // el array de int que guardará el resultado de las operaciones
        int m = keyNumberString.length();
        int[] encodedSecret = null;
        if (plainText.length() % m == 0) {
            encodedSecret = new int[plainText.length()];
        } else {
            encodedSecret = new int[plainText.length() + m - (plainText.length() % m)];
        }

        int actualDivision = 0;
        // encrypts
        for (int i = 0; i < encodedSecret.length; i++) {
            actualDivision = (i / m);

            int encodePlaintTextIndex = actualDivision * m + encodedKey[i % keyNumberString.length()] - 1;
            if (encodePlaintTextIndex < plainText.length()) {
                encodedSecret[i] = encodedPlainText[encodePlaintTextIndex];
            }
        //System.out.println("actual: " + actualDivision + " encodePlainText " + (encodedKey[i % keyNumberString.length()] - 1) + " index " + encodePlaintTextIndex);

        }
        // decodeMod26 encodedPlainText
        String secret = Code.decodeMod26(encodedSecret);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * Returns an integer array from a String of numbers. If there is a character in the String
     * then the function throws an IOException.
     *
     * @param  numberString String to transform
     * @return array of integers
     */
    public static int[] toIntArray(String numberString) throws IOException {
        if (numberString.length() <= 0) {
            throw new IOException("No se permite una cadena de caracteres vacia...");
        }
        // la Regex se llena con todo lo que no sea números y con el número 0
        // Luego dependiendo de la longitud del numberString se agregan a la Regex más números
        String regex = "[\\D0";
        for (int i = numberString.length() + 1; i <= 9; i++) {
            regex += i;
        }
        regex += "]";

        // numbers es el arreglo de enteros que se va a retornar
        int[] numbers = new int[numberString.length()];
        // prepara el patron Regex que se va a buscar
        Pattern pattern = Pattern.compile(regex);
        // crea el matcher que recorre el numberString buscando las coincidencias con la Regex
        Matcher matcher = pattern.matcher(numberString);

        // Si encuentra coincidencia entre Regex y numberString lanza un IOException
        while (matcher.find()) {
            System.out.println("I found the text '" + matcher.group() + "' starting at " +
                    "index " + matcher.start() + " and ending at index " + matcher.end());
            throw new IOException("La clave debe ser una secuencia de 1 hasta n en desorden." +
                    " No se permiten letras en la cadena de caracteres...");
        }

        // Se mira usando indexOf y lastIndexOf si se repite algún número (no lo sé hacer con Regex)
        // Si no se repiten entonces va llenando el array numbers haciendo parseInt (pasa de
        // String a int)
        String actual = "";
        for (int i = 0; i < numberString.length(); i++) {
            actual = numberString.substring(i, i + 1);
            if (numberString.indexOf(actual) != numberString.lastIndexOf(actual)) {
                throw new IOException("La clave debe ser una secuencia de 1 hasta n en desorden." +
                        " No se permiten números repetidos en la cadena de caracteres...");
            }
            numbers[i] = Integer.parseInt(actual);
        }
        return numbers;
    }

    public static String encryptAlternate(String plainText, String keyNumberString) {
        // Algorithm
        // encodeMod26 plainText and key
        int[] encodedKey = null;
        int[] encodedPlainText = Code.encodeMod26(plainText);
        try {
            encodedKey = toIntArray(keyNumberString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // el array de int que guardará el resultado de las operaciones
        int m = keyNumberString.length();
        int[] encodedSecret = null;
        if (plainText.length() % m == 0) {
            encodedSecret = new int[plainText.length()];
        } else {
            encodedSecret = new int[plainText.length() + m - (plainText.length() % m)];
        }

        int actualDivision = 0;
        int keyIndex = 0;

        // encrypts
        for (int i = 0; i < encodedPlainText.length; i++) {
            actualDivision = (i / m);
            keyIndex = i % m;
            int lenghtRows = Math.round((float) encodedPlainText.length / (float) m);
            int encodePlaintTextIndex = encodedKey[keyIndex] * lenghtRows - (lenghtRows - actualDivision);
            encodedSecret[encodePlaintTextIndex] = encodedPlainText[i];
        }
        String secret = Code.decodeMod26(encodedSecret);
        secret = secret.toUpperCase();
        return secret;
    }    
}
