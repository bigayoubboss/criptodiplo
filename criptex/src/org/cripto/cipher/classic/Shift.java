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
public class Shift implements Cipher {

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

        int[] mod189PlainText = Code.encodeMod189(plainText);

        for (int i = 0; i < plainText.length(); i++) {
            mod189PlainText[i] += key;
            mod189PlainText[i] %= 189;
        }
        
        String cipherText = Code.decodeMod189(mod189PlainText);

        return cipherText;
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
        int[] mod189CipherText = Code.encodeMod189(cipherText);

        for (int j = 0; j < mod189CipherText.length; j++) {

            mod189CipherText[j] = mod189CipherText[j] - key;

            if (mod189CipherText[j] < 0) {
                mod189CipherText[j] += 189;
            }
        }
        
        String plainText = Code.decodeMod189(mod189CipherText);
        
        return plainText;
    }

    /**
     * Return the 189 posibilities for a encoded text  previously ciphered using
     * Shift Cipher the cipher text string
     * @param cipherText
     * @return the array with 189 decoded posibilities
     */
    @Override
    public String[] cryptoAnalysis(String cipherText) {

        String[] possibility = new String[189];

        for (int i = 0; i < 189; i++) {
            possibility[i] = this.decode(cipherText, i);
        }

        return possibility;
    }
}
