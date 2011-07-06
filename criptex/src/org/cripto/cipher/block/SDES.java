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
public class SDES implements Cipher {

    private static int[][] S0 = {
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {0, 2, 1, 3},
        {3, 1, 3, 2}};
    private static int[][] S1 = {
        {0, 1, 2, 3},
        {2, 0, 1, 3},
        {3, 0, 1, 0},
        {2, 1, 0, 3}};

    @Override
    public Object cryptoAnalysis(String cipherText) {
        String[][] posibilities = new String[1024][2];
        for (int x = 0; x < 1024; x++) {

            String key = Integer.toBinaryString(x);
            String complement = "";
            int len = key.length();
            for (int y = 0; y < (10 - len); y++) {
                complement = complement.concat("0");
            }
            key = complement.concat(key);
            posibilities[x][0] = key;
            String plainText = "";
            for (int y = 0; y < cipherText.length(); y = y + 2) {
                plainText = plainText.concat(encryptDecrypt(cipherText.substring(y, y + 2), key, false));
            }
            posibilities[x][1] = org.cripto.utils.HexTools.fromHexStringToASCIIString(plainText);
        }
        return posibilities;
    }

    @Override
    public String decode(String cipherText, Object oKey) {

        String key = oKey.toString();
        String plainText = encryptDecrypt(cipherText, key, false);

        return plainText;
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String key = oKey.toString();
        String plainText = oPlainText.toString();
        String cipherText = encryptDecrypt(plainText, key, true);

        return cipherText;
    }

    private String encryptDecrypt(String text, String hexaKeyString, boolean mode) {

        int keys[][] = keySchedulingAlgorithm(hexaKeyString);

        int[] cipherTextMod2 = textSchedulingAlgorithm(text, keys, mode);

        String cipherTextMod16 = "";
        for (int x = 0; x < 8; x++) {
            cipherTextMod16 = cipherTextMod16.concat(String.valueOf(cipherTextMod2[x]));
        }
        String cipherText = "";
        for (int x = 0; x < 8; x = x + 4) {
            cipherText = cipherText.concat(Integer.toHexString(Integer.parseInt(cipherTextMod16.substring(x, x + 4), 2)));
        }

        return cipherText;

    }

    public byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }

    private int[] textSchedulingAlgorithm(String text, int[][] keys, boolean mode) {

        int[] binText = org.cripto.utils.HexTools.fromHexStringToBinArray(text);

        binText = perIP(binText);

        int[][] leftText = new int[2][5];
        int[][] rightText = new int[2][5];

        for (int x = 0; x < 4; x++) {
            leftText[0][x] = binText[x];
            rightText[0][x] = binText[x + 4];
        }

        int[] rightExpanded = new int[8];


        for (int x = 0; x < 2; x++) {

            int[] xorKeyAndRight = new int[48];

            rightExpanded = perEP(rightText[x]);
            if (mode) {
                xorKeyAndRight = XOR(keys[x], rightExpanded);
            } else {
                xorKeyAndRight = XOR(keys[1 - x], rightExpanded);
            }
            int[][] sBoxesValues = new int[2][2];

            int box = 0;

            for (int y = 0; y < 8; y = y + 4) {
                sBoxesValues[box] = boxS(xorKeyAndRight[y], xorKeyAndRight[y + 1],
                        xorKeyAndRight[y + 2], xorKeyAndRight[y + 3], box);
                box++;
            }

            int[] f = new int[4];
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    f[(y * 2) + z] = sBoxesValues[y][z];
                }
            }

            int[] per4 = per4(f);


            leftText[x] = XOR(per4, leftText[x]);

            if (x == 0) {
                int[] temp = leftText[x];
                leftText[x + 1] = rightText[x];
                rightText[x + 1] = temp;
            }
        }

        int[] secret = new int[8];
        for (int x = 0; x < 4; x++) {
            secret[x] = leftText[1][x];
            secret[x + 4] = rightText[1][x];
        }

        int[] secretIPinv = perIPinv(secret);

        return secretIPinv;

    }

    private int[][] keySchedulingAlgorithm(String binKeyString) {

        int[] keyBin = org.cripto.utils.HexTools.fromBinStringToBinArray(binKeyString);

        int[] keyPC10 = per10(keyBin);

        int[] keyRot = leftshift1(keyPC10);

        int[] keyK1 = per8(keyRot);

        int[] keyK1Rot = leftShift2(keyPC10);

        int[] keyK2 = per8(keyK1Rot);

        int[][] keys = new int[2][10];

        keys[0] = keyK1;
        keys[1] = keyK2;

        return keys;
    }

    private int[] leftshift1(int[] key) {
        int temp = key[0];
        for (int x = 0; x < (key.length / 2); x++) {
            key[x] = key[(x + 1)];
        }
        key[(key.length / 2) - 1] = temp;


        temp = key[(key.length / 2)];
        for (int x = (key.length / 2); x < (key.length - 1); x++) {
            key[x] = key[(x + 1)];

        }
        key[(key.length - 1)] = temp;
        return key;
    }

    private int[] leftShift2(int[] key) {
        key = leftshift1(leftshift1(key));
        return key;
    }

    private int[] per10(int[] key) {
        int[] p10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
        int[] per = new int[10];
        for (int x = 0; x < 10; x++) {
            per[x] = key[(p10[x] - 1)];
        }
        return per;
    }

    private int[] per8(int[] key) {
        int[] p8 = {6, 3, 7, 4, 8, 5, 10, 9};
        int[] per = new int[8];
        for (int x = 0; x < 8; x++) {
            per[x] = key[(p8[x] - 1)];
        }
        return per;
    }

    private int[] per4(int[] key) {
        int[] p48 = {2, 4, 3, 1};
        int[] per = new int[4];
        for (int x = 0; x < 4; x++) {
            per[x] = key[(p48[x] - 1)];
        }
        return per;
    }

    private int[] perIP(int[] key) {
        int[] pIP = {2, 6, 3, 1, 4, 8, 5, 7};
        int[] per = new int[8];
        for (int x = 0; x < 8; x++) {
            per[x] = key[(pIP[x] - 1)];
        }
        return per;
    }

    private int[] perIPinv(int[] key) {
        int[] pIPinv = {4, 1, 3, 5, 7, 2, 8, 6};
        int[] per = new int[8];
        for (int x = 0; x < 8; x++) {
            per[x] = key[(pIPinv[x] - 1)];
        }
        return per;
    }

    private int[] perEP(int[] key) {
        int[] pIP = {4, 1, 2, 3, 2, 3, 4, 1};
        int[] per = new int[8];
        for (int x = 0; x < 8; x++) {
            per[x] = key[(pIP[x] - 1)];
        }
        return per;
    }

    private int[] XOR(int[] a, int[] b) {

        int[] xor = new int[a.length];
        for (int x = 0; x < a.length; x++) {
            xor[x] = a[x] ^ b[x];
        }
        return xor;
    }

    public int[] encodeBoxPosition(String text) {
        //System.out.println("encoding... ");

        char[] textArray = new char[text.length()];
        int[] numberArray = new int[text.length()];

        text.getChars(0, text.length(), textArray, 0);
        for (int i = 0; i < textArray.length; i++) {
            numberArray[i] = Character.getNumericValue(textArray[i]) - 10;
        }

        return numberArray;
    }

    public int[] boxS(int v1, int v2, int v3, int v4, int box) {

        int rowIndex = Integer.parseInt(String.valueOf(v1).concat(String.valueOf(v4)), 2);
        int colIndex = Integer.parseInt(String.valueOf(v2).concat(String.valueOf(v3)), 2);
        int boxValue = 0;
        switch (box) {
            case 0:
                boxValue = S0[rowIndex][colIndex];
                break;
            case 1:
                boxValue = S1[rowIndex][colIndex];
                break;
        }
        String binBoxValue = Integer.toBinaryString(boxValue);
        while (binBoxValue.length() < 2) {
            binBoxValue = "0".concat(binBoxValue);
        }
        int[] intBoxValue = new int[2];
        for (int x = 0; x < 2; x++) {
            intBoxValue[x] = Integer.parseInt(binBoxValue.substring(x, x + 1));
        }
        return intBoxValue;
    }
}
