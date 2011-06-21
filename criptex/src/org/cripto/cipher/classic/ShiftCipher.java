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

    /**
     * Returns the encoded plain text shifted <i>n</i> positions 
     * to the right according to the 189 alphabet
     * @param oPlainText the plain Text string
     * @param oKey the key represented by a value between 0 to 189 integer
     * @param params  must be null
     * @return the encode plain text
     */
    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        int key = Integer.parseInt(oKey.toString());
        String plainText = (String) oPlainText;

        int[] encodedPlainText = Code.encodeMod189(plainText);

        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] += key;
            encodedPlainText[i] %= 189;
        }

        return Code.decodeMod189(encodedPlainText);
    }

    /**
     * Return the decoded cipher text previously shifted <i>n</i> positions 
     * to the right according to the 189 alphabet using Shift Cipher
     * @param cipherText the cipher text string
     * @param oKey the key represented by a value between 0 to 189 integer
     * @return the decoded cipher text
     */
    @Override
    public String decode(String cipherText, Object oKey) {

        int key = Integer.parseInt(oKey.toString());
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
     * Return the 189 posibilities for a encoded text  previously ciphered using
     * Shift Cipher the cipher text string
     * @param cipherText
     * @return the array with 189 decoded posibilities
     */
    @Override
    public String[] cryptoAnalysis(String cipherText) {

        String[] possibilities = new String[189];

        for (int i = 0; i < 189; i++) {
            possibilities[i] = this.decode(cipherText, i);
        }

        return possibilities;
    }
}
