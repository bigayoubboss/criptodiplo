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
import org.cripto.utils.jama.Matrix;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class HillCipher implements Cipher {

    @Override
    public String[] cryptoAnalysis(String cipherText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String decode(String cipherText, Object key) {
        // TODO Implement Hill cipher decode        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String plainText = oPlainText.toString();
        Matrix key = (Matrix) oKey;

        int[] mod26Text = Code.encodeMod26(plainText);

        int keySize = key.getRowDimension();
        int numPlainTextBlocks = (int) Math.ceil((double) plainText.length() / keySize);

        Matrix[] mod26PlainText = mod26ArrayToMatrixArray(keySize, numPlainTextBlocks, mod26Text);

        Matrix[] mod26CipherText = new Matrix[numPlainTextBlocks];
        for (int block = 0; block < numPlainTextBlocks; block++) {
            mod26CipherText[block] = mod26PlainText[block].times(key);
        }

        int[] cipherTextArray = matrixArrayToMod26array(plainText.length(), keySize, mod26CipherText);

        String cipherText = Code.decodeMod26(cipherTextArray);
        cipherText = cipherText.toUpperCase();

        return cipherText;
    }

    private Matrix[] mod26ArrayToMatrixArray(int keySize, int numPlainTextBlocks, int[] mod26Text) {

        int actualPosition = 0;
        Matrix[] mod26Plain = new Matrix[numPlainTextBlocks];

        for (int i = 0; i < numPlainTextBlocks; i++) {
            mod26Plain[i] = new Matrix(1, keySize);
            for (int y = 0; y < keySize; y++) {
                if (actualPosition < mod26Text.length) {
                    mod26Plain[i].set(0, y, mod26Text[actualPosition]);
                } else {
                    mod26Plain[i].set(0, y, 0);
                }
                actualPosition++;
            }
        }
        return mod26Plain;
    }

    private int[] matrixArrayToMod26array(int plainTextSize, int keySize, Matrix[] mod26CipherText) {

        int[] cipherTextArray = new int[plainTextSize];
        int actualPosition = 0;
        
        for (int i = 0; i < plainTextSize; i++) {
            cipherTextArray[i] = ((int) mod26CipherText[actualPosition].get(0, i % keySize)) % 26;
            if (i > 0 && i % keySize == (keySize - 1)) {
                actualPosition++;
            }
        }

        return cipherTextArray;
    }
}
