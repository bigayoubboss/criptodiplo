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

/**
 * 
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class PermutationCipher implements Cipher {

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String key = oKey.toString();
        String plainText = oPlainText.toString();

        int keySize = key.length();
        String secret = "";

        plainText = completeString(plainText, keySize);

        int blocks = plainText.length() / keySize;

        boolean isSimpleEncode = Boolean.parseBoolean(params[0].toString());

        if (isSimpleEncode) {
            secret = encodeSimple(plainText, key, blocks, keySize);
        } else {
            secret = encodeAlternate(plainText, key, blocks, keySize);
        }

        return secret.toUpperCase();
    }

    @Override
    public String decode(String cipherText, Object oKey) {
        // TODO Implement Permutation Cipher decode
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cryptoAnalysis(String cipherText) {
        // TODO Implement Permutation Cipher Cryptoanalysis
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String encodeSimple(String plainText, String keyNumberString, int blocks, int keySize) {

        String secret = "";

        for (int i = 0; i < blocks; i++) {
            String block = plainText.substring(i * keySize, i * keySize
                    + keySize);
            secret = secret.concat(permutateBlock(block, keyNumberString));
        }

        return secret.toUpperCase();

    }

    public String encodeAlternate(String plainText, String keyNumberString, int blocks, int keySize) {

        String secret = "";

        for (int i = 0; i < blocks; i++) {

            String matrixRow = "";
            for (int j = 0; j < (keySize * blocks); j = j + blocks) {
                matrixRow = matrixRow.concat(String.valueOf(plainText.charAt(i
                        + j)));
            }
            secret = secret.concat(permutateBlock(matrixRow, keyNumberString));
        }

        return secret.toUpperCase();
    }

    private static String permutateBlock(String blockToPermutate,
            String numberKey) {

        int key[] = new int[numberKey.length()];
        char block[] = blockToPermutate.toCharArray();
        String permutation = "";

        for (int i = 0; i < numberKey.length(); i++) {
            key[i] = Character.getNumericValue(numberKey.charAt(i));
        }

        for (int i = 0; i < key.length; i++) {
            permutation = permutation.concat(String.valueOf(block[key[i] - 1]));
        }

        return permutation;
    }

    private static String completeString(String string, int size) {
        while (string.length() % size != 0) {
            string = string.concat("A");
        }
        return string;
    }
}
