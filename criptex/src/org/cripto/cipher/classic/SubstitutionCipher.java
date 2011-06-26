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

    @Override
    public String encode(Object plainText, Object key, Object[] params) {

        int[] mod26PlainText = Code.encodeMod26(String.valueOf(plainText));
        int[] mod26Key = Code.encodeMod26(String.valueOf(key));
        int[] mod26CipherText = new int[mod26PlainText.length];

        for (int i = 0; i < String.valueOf(plainText).length(); i++) {
            mod26CipherText[i] = mod26Key[mod26PlainText[i]];
        }

        String cipherText = Code.decodeMod26(mod26CipherText);
        cipherText = cipherText.toUpperCase();

        return cipherText;
    }

    @Override
    public String decode(String cipherText, Object oKey) {

        int[] mod26CipherText = Code.encodeMod26(String.valueOf(cipherText));
        String key = String.valueOf(oKey);
        int[] mod26PlainText = new int[mod26CipherText.length];

        for (int i = 0; i < String.valueOf(cipherText).length(); i++) {
            mod26PlainText[i] = key.indexOf(cipherText.charAt(i));
        }

        String plainText = Code.decodeMod26(mod26PlainText);
        plainText = plainText.toLowerCase();

        return plainText;
    }

    @Override
    public Object cryptoAnalysis(String cipherText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
    
}
