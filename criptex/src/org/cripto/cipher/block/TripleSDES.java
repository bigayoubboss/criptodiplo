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
public class TripleSDES implements Cipher {

    private SDES sdes;

    public TripleSDES() {
        sdes = new SDES();
    }

    @Override
    public Object cryptoAnalysis(String cipherText) {
        // TODO Implement Triple SDES cipher cryptoanalysis
        throw new UnsupportedOperationException();
    }

    @Override
    public String decode(String cipherText, Object oKey) {

        String key = oKey.toString();

        String keyA = key.substring(0, 10);
        String keyB = key.substring(10, 20);
        String keyC = key.substring(20, 30);

        String plainText;
        cipherText = sdes.decode(cipherText, keyC);
        cipherText = sdes.encode(cipherText, keyB, null);
        plainText = sdes.decode(cipherText, keyA);

        return plainText;
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String key = oKey.toString();

        String keyA = key.substring(0, 10);
        String keyB = key.substring(10, 20);
        String keyC = key.substring(20, 30);

        String plainText = oPlainText.toString();

        String cipherText;
        plainText = sdes.encode(plainText, keyA, null);
        plainText = sdes.decode(plainText, keyB);
        cipherText = sdes.encode(plainText, keyC, null);

        return cipherText;
    }
}
