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
package org.cripto.cipher.classic;

import org.cripto.cipher.Cipher;
import org.cripto.utils.Code;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class SubstitutionCipher implements Cipher {

    public String encode(Object plainText, Object key, Object[] params) {

        int[] encodedPlainText = Code.encodeMod26(String.valueOf(plainText));
        int[] encodedNewAlphabet = Code.encodeMod26(String.valueOf(key));
        int[] encodedCipherText = new int[encodedPlainText.length];

        for (int i = 0; i < String.valueOf(plainText).length(); i++) {
            encodedCipherText[i] = encodedNewAlphabet[encodedPlainText[i]];
        }

        String secret = Code.decodeMod26(encodedCipherText);
        secret = secret.toUpperCase();

        return secret;
    }

    public String decode(String cipherText, Object key) {

        int[] encodedCipherText = Code.encodeMod26(String.valueOf(cipherText));
        String newAlphabet = String.valueOf(key);
        int[] encodedPlainText = new int[encodedCipherText.length];

        for (int i = 0; i < String.valueOf(cipherText).length(); i++) {
            encodedPlainText[i] = newAlphabet.indexOf(cipherText.charAt(i));
        }

        String plainText = Code.decodeMod26(encodedPlainText);
        plainText = plainText.toLowerCase();

        return plainText;
    }
}
