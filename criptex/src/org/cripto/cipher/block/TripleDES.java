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
package org.cripto.cipher.block;

import org.cripto.cipher.Cipher;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class TripleDES implements Cipher {

    private DES des;

    public TripleDES() {
        des = new DES();
    }

    @Override
    public Object cryptoAnalysis(String cipherText) {
        // TODO Implement Triple DES cipher cryptoanalysis
        throw new UnsupportedOperationException();
    }

    @Override
    public String decode(String cipherText, Object oKey) {

        String key = oKey.toString();

        String keyA = key.substring(0, 16);
        String keyB = key.substring(16, 32);
        String keyC = key.substring(32, 48);

        String plainText;
        cipherText = des.decode(cipherText, keyC);
        cipherText = des.encode(cipherText, keyB, null);
        plainText = des.decode(cipherText, keyA);

        return plainText;
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String key = oKey.toString();

        String keyA = key.substring(0, 16);
        String keyB = key.substring(16, 32);
        String keyC = key.substring(32, 48);

        String plainText = oPlainText.toString();

        String cipherText;
        plainText = des.encode(plainText, keyA, null);
        plainText = des.decode(plainText, keyB);
        cipherText = des.encode(plainText, keyC, null);

        return cipherText;
    }
}
