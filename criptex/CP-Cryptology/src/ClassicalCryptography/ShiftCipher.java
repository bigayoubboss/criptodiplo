/*
 * Universidad Nacional de Colombia - Sede Bogotá 
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

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class ShiftCipher {

    /**
     * Returns an alphabetic ciphertext using Shift Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  key the number of positions to shift on the text
     * @return the cipher text string
     */
    @Deprecated
    public static String encrypt(String plainText, int key) {
        // System.out.println("ShiftCipher running..."+plainText);
        // Algorithm
        // encodeMod26 plainText
        int[] encodedPlainText = Code.encodeMod26(plainText);
        // add key to plainText
        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] += key;
            encodedPlainText[i] %= 26;
        }
        // decodeMod26 encodedPlainText
        String secret = Code.decodeMod26(encodedPlainText);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * Returns an array of possible plain texts by decrypting Shift Cipher
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    @ Deprecated
    public static String[] cryptoAnalysis(String cipherText) {
        //encodeMod26 cipherText
        int[] encodedCipherText = Code.encodeMod26(cipherText);
        //create the string array to store the possible decrypted texts
        String[] possibilities = new String[26];

        //calculate the string for each possible shift
        for (int i = 0; i < 26; i++) {
            int[] temporalEncodedCipherText = new int[encodedCipherText.length];
            for (int j = 0; j < encodedCipherText.length; j++) {
                temporalEncodedCipherText[j] = encodedCipherText[j] - i;
                if (temporalEncodedCipherText[j] < 0) {
                    temporalEncodedCipherText[j] += 26;
                }
                //temporalEncodedCipherText[j] %= 26;
            }
            possibilities[i] = Code.decodeMod26(temporalEncodedCipherText).toLowerCase();
        }
        return possibilities;
    }

    /**
     * Returns an alphabetic ciphertext using Shift Cipher module 189.
     *
     * @param  plainText the text to encrypt
     * @param  key the number of positions to shift on the text
     * @return the cipher text string
     */
    public static String encryptMod189(String plainText, int key) {
        // System.out.println("ShiftCipher running..." + plainText);
        // ALGORITHM
        // encodeMod189 plainText
        int[] encodedPlainText = Code.encodeMod189(plainText);
        // add key to plainText
        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] += key;
            encodedPlainText[i] %= 189;
        }
        // decode encodedPlainText
        String secret = Code.decodeMod189(encodedPlainText);
        // return secret
        return secret;
    }

    /**
     * Returns an array of possible plain texts by decrypting Shift Cipher module 189
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    public static String[] cryptoAnalysisMod189(String cipherText) {
        //encodeMod26 cipherText
        int[] encodedCipherText = Code.encodeMod189(cipherText);
        //create the string array to store the possible decrypted texts
        String[] possibilities = new String[189];

        //calculate the string for each possible shift
        for (int i = 0; i < 189; i++) {
            int[] temporalEncodedCipherText = new int[encodedCipherText.length];
            for (int j = 0; j < encodedCipherText.length; j++) {
                temporalEncodedCipherText[j] = encodedCipherText[j] - i;
                if (temporalEncodedCipherText[j] < 0) {
                    temporalEncodedCipherText[j] += 189;
                }
            }
            possibilities[i] = Code.decodeMod189(temporalEncodedCipherText);
        }
        return possibilities;
    }
}
