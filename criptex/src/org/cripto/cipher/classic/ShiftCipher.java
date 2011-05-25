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
package org.cripto.cipher.classic;

import org.cripto.cipher.Cipher;
import org.cripto.utils.Code;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class ShiftCipher implements Cipher {

    public String encode(Object objectPlainText, Object objectKey, Object[] params) {

        int key = (int) objectKey;
        String plainText = (String) objectPlainText;

        int[] encodedPlainText = Code.encodeMod189(plainText);

        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] += key;
            encodedPlainText[i] %= 189;
        }

        return Code.decodeMod189(encodedPlainText);
    }

    public String decode(String cipherText, Object objectKey) {

        int key = (int) objectKey;
        int[] encodedCipherText = Code.encodeMod189(cipherText);

        for (int j = 0; j < encodedCipherText.length; j++) {

            encodedCipherText[j] = encodedCipherText[j] - key;

            if (encodedCipherText[j] < 0) {
                encodedCipherText[j] += 189;
            }
        }

        return Code.decodeMod189(encodedCipherText);

    }

    /**
     * Returns an array of possible plain texts by decrypting Shift Cipher module 189
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    public  String[] cryptoAnalysis(String cipherText) {
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
