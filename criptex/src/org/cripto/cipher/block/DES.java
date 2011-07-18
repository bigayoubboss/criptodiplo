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
import org.cripto.utils.HexTools;

/**
 * 
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class DES implements Cipher {

    private int[][] SBox1 = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    private int[][] SBox2 = {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    private int[][] SBox3 = {
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
    private int[][] SBox4 = {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
    private int[][] SBox5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
    private int[][] SBox6 = {
        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
    private int[][] SBox7 = {
        {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
    private int[][] SBox8 = {
        {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    @Override
    public String[][] cryptoAnalysis(String cipherText) {

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
            posibilities[x][1] = decode(cipherText, key);
        }

        return posibilities;
    }

    @Override
    public String decode(String cipherText, Object oKey) {

        String key = oKey.toString();
        String plainText = encryptDecrypt(cipherText, key, false);

        return plainText.toUpperCase();
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String key = oKey.toString();
        String plainText = oPlainText.toString();
        String cipherText = encryptDecrypt(plainText, key, true);

        return cipherText.toUpperCase();
    }

    private String encryptDecrypt(String text, String keyMod16, boolean mode) {

        int keys[][] = keySchedulingAlgorithm(keyMod16);

        int[] textBin = textSchedulingAlgorithm(text, keys, mode);

        String textMod16 = "";
        for (int x = 0; x < 64; x++) {
            textMod16 = textMod16.concat(String.valueOf(textBin[x]));
        }

        String finalText = "";
        for (int x = 0; x < 64; x = x + 4) {
            finalText = finalText.concat(Integer.toHexString(Integer.parseInt(
                    textMod16.substring(x, x + 4), 2)));
        }

        return finalText;

    }

    private int[] textSchedulingAlgorithm(String text, int[][] keys, boolean mode) {

        int[] textMod2 = HexTools.fromHexStringToBinArray(text);

        textMod2 = IP(textMod2);

        int[][] leftText = new int[17][32];
        int[][] rightText = new int[17][32];

        for (int x = 0; x < 32; x++) {
            leftText[0][x] = textMod2[x];
            rightText[0][x] = textMod2[x + 32];
        }

        int[] rightExpanded = new int[48];

        for (int x = 1; x < 17; x++) {

            keyShedullingRound(leftText, x, rightText, mode, keys, rightExpanded);
        }

        int[] finalText = new int[64];
        for (int x = 0; x < 32; x++) {
            finalText[x] = rightText[16][x];
            finalText[x + 32] = leftText[16][x];
        }

        int[] finalTextIPinv = IPInv(finalText);
        return finalTextIPinv;
    }

    private int[][] keySchedulingAlgorithm(String keyString) {

        int[] keyBin = org.cripto.utils.HexTools.fromHexStringToBinArray(keyString);

        int[] keyPC1 = PC1(keyBin);

        int[][] key56 = ciComputing(keyPC1);

        int[][] key48 = new int[17][48];
        for (int x = 1; x < 17; x++) {
            key48[x] = PC2(key56[x]);
        }

        return key48;
    }

    private void keyShedullingRound(int[][] leftText, int x, int[][] rightText, boolean mode, int[][] keys, int[] rightExpanded) {

        leftText[x] = rightText[x - 1];

        rightExpanded = E(rightText[x - 1]);

        int[] xorKeyAndRight = new int[48];
        if (mode) {
            xorKeyAndRight = XOR(keys[x], rightExpanded);
        } else {
            xorKeyAndRight = XOR(keys[17 - x], rightExpanded);
        }

        int[][] sBoxesValues = new int[8][48];

        int box = 1;

        for (int y = 0; y < 48; y = y + 6) {
            sBoxesValues[box - 1] = boxS(xorKeyAndRight[y],
                    xorKeyAndRight[y + 1], xorKeyAndRight[y + 2],
                    xorKeyAndRight[y + 3], xorKeyAndRight[y + 4],
                    xorKeyAndRight[y + 5], box);
            box++;
        }

        int[] f = new int[32];
        for (int y = 0; y < 8; y++) {
            System.arraycopy(sBoxesValues[y], 0, f, y * 4, 4);
        }

        int[] perP = P(f);

        rightText[x] = XOR(perP, leftText[x - 1]);
    }

    private int[][] ciComputing(int[] key) {
        int[][] C = new int[17][56];

        System.arraycopy(key, 0, C[0], 0, 56);

        for (int x = 1; x < 17; x++) {
            int[] keyRot = new int[56];

            System.arraycopy(C[x - 1], 0, keyRot, 0, 56);

            if (x == 1 || x == 2 || x == 9 || x == 16) {
                keyRot = leftShift1(keyRot);
            } else {
                keyRot = leftShift2(keyRot);
            }

            System.arraycopy(keyRot, 0, C[x], 0, 56);

        }

        return C;
    }

    private int[] leftShift1(int[] key) {
        int temp = key[0];
        for (int x = 0; x < 28; x++) {
            key[x] = key[(x + 1)];
        }
        key[27] = temp;

        temp = key[28];
        for (int x = 28; x < 55; x++) {
            key[x] = key[(x + 1)];

        }
        key[55] = temp;
        return key;
    }

    private int[] leftShift2(int[] key) {
        key = leftShift1(leftShift1(key));
        return key;
    }

    private int[] PC1(int[] key) {

        int[] pc1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55,
            47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
            45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

        int[] per = new int[58];
        for (int x = 0; x < 56; x++) {
            per[x] = key[(pc1[x] - 1)];
        }

        return per;
    }

    private int[] PC2(int[] key) {

        int[] pc2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19,
            12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30,
            40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

        int[] per = new int[48];
        for (int x = 0; x < 48; x++) {
            per[x] = key[(pc2[x] - 1)];
        }

        return per;
    }

    private int[] IP(int[] key) {

        int[] ip = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20,
            12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24,
            16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
            11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23,
            15, 7};

        int[] per = new int[64];
        for (int x = 0; x < 64; x++) {
            per[x] = key[(ip[x] - 1)];
        }

        return per;
    }

    private int[] E(int[] key) {

        int[] e = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12,
            13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22,
            23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

        int[] per = new int[48];
        for (int x = 0; x < 48; x++) {
            per[x] = key[(e[x] - 1)];
        }

        return per;
    }

    private int[] P(int[] key) {

        int[] p = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31,
            10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

        int[] per = new int[32];
        for (int x = 0; x < 32; x++) {
            per[x] = key[(p[x] - 1)];
        }

        return per;
    }

    private int[] IPInv(int[] key) {

        int[] ipInv = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23,
            63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21,
            61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19,
            59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
            57, 25};

        int[] per = new int[64];
        for (int x = 0; x < 64; x++) {
            per[x] = key[(ipInv[x] - 1)];
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

    private int[] boxS(int v1, int v2, int v3, int v4, int v5, int v6,
            int box) {

        int rowIndex = Integer.parseInt(
                String.valueOf(v1).concat(String.valueOf(v6)), 2);
        int colIndex = Integer.parseInt(
                String.valueOf(v2).concat(String.valueOf(v3)).concat(String.valueOf(v4).concat(String.valueOf(v5))), 2);

        int boxValue = 0;
        switch (box) {
            case 1:
                boxValue = SBox1[rowIndex][colIndex];
                break;
            case 2:
                boxValue = SBox2[rowIndex][colIndex];
                break;
            case 3:
                boxValue = SBox3[rowIndex][colIndex];
                break;
            case 4:
                boxValue = SBox4[rowIndex][colIndex];
                break;
            case 5:
                boxValue = SBox5[rowIndex][colIndex];
                break;
            case 6:
                boxValue = SBox6[rowIndex][colIndex];
                break;
            case 7:
                boxValue = SBox7[rowIndex][colIndex];
                break;
            case 8:
                boxValue = SBox8[rowIndex][colIndex];
                break;
        }

        String boxValueMod2 = Integer.toBinaryString(boxValue);
        while (boxValueMod2.length() < 4) {
            boxValueMod2 = "0".concat(boxValueMod2);
        }

        int[] boxValueMod10 = new int[4];
        for (int x = 0; x < 4; x++) {
            boxValueMod10[x] = Integer.parseInt(boxValueMod2.substring(x, x + 1));
        }

        return boxValueMod10;
    }
}
