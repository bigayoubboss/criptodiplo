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
package org.cripto.systems.classic;

import org.cripto.utils.Code;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class SubstitutionCipher {

    /**
     * Returns an alphabetic ciphertext using Substitution Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  newAlphabet the traditional alphabet new values
     * @return the cipher text string
     */
    public static String encrypt(String plainText, String newAlphabet) {
        // System.out.println("SubstitutionCipher running...");

        // Algorithm
        // encodeMod26 plainText
        int[] encodedPlainText = Code.encodeMod26(plainText);
        int[] encodedNewAlphabet = Code.encodeMod26(newAlphabet);

        // find the value of each plainText letter on the newAlphabet
        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] = encodedNewAlphabet[encodedPlainText[i]];
        }
        // decodeMod26 encodedPlainText
        String secret = Code.decodeMod26(encodedPlainText);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * 
     */
    public static void cryptoAnalysis(String cipherText) {
    }
}


