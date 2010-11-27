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

package BlockCryptography;

import cpcommonmethods.Code;
import java.util.Random;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class SubstitutionPermutationNetworkCipher {

    /**
     * Returns an alphabetic ciphertext using SPN Cipher.
     *
     * @param  secret the text to encrypt
     * @param  substitution int array to use substitution
     * @param  permutation int array to use permutation
     * @param  key the keyWord used to encrypt using SPN
     * @param  Nr is the number of rotations
     * @return the cipher text string
     */
    public static String encrypt(String plainText, int[] substitution, int[] permutation, String key, int Nr) {
        System.out.println("SPN Cipher encrypt method running...");


        // initialize l and m
        int l, m;
        int c = 0;
        m = plainText.length();
        l = 4; // we use 4 bits to represent the alphabet

        // Plain Text, x
        int[] encodedPlainText = Code.encodeMod26(plainText);
        int x = 0;
        for (int i = 0; i < encodedPlainText.length; i++) {
            x += encodedPlainText[i] << (((l) * (m - 1)) - i * l);
        }
        ////System.out.println("x: " + Integer.toBinaryString(x));

        // Key
        int[] encodedKey = Code.encodeMod26(key);

        // SubKeys
        int[] subKeys = keySchedulingAlgorithm(encodedKey, Nr, l, m);
        for (int i = 0; i < subKeys.length; i++) {
            ////System.out.println("subkey["+i+"] = " + Integer.toBinaryString(subKeys[i]));
        }

        // Algorithm
        // initialize
        int w = x;

        for (int rotation = 0; rotation < Nr - 1; rotation++) {
            // rotation number
            ////System.out.println("Rotation" + (rotation + 1));
            // XOR with first subKey
            // //System.out.println("XOR");
            w ^= subKeys[rotation];
            ////System.out.println("w: " + Integer.toBinaryString(w));

            // substitution
            ////System.out.println("substitution");
            int[] v = substitution(l, m, w, substitution);

            c = toUnitedInt(l, v);
            ////System.out.println("after sustitution " + Integer.toBinaryString(c));


            // permutation
            ////System.out.println("permutation");
            int[] wFinal = permutation(l, permutation, v);

            // update w
            w = 0;
            for (int i = 0; i < wFinal.length; i++) {
                w += (int) (Math.pow(2, i) * wFinal[wFinal.length - i - 1]);
            }
        ////System.out.println("w after permutation: " + Integer.toBinaryString(w));
        }

        // Penúltimo XOR
        ////System.out.println("XOR");
        w ^= subKeys[Nr - 1];
        ////System.out.println("w after XOR: " + Integer.toBinaryString(w));

        // final substitution
        ////System.out.println("Final substitution");
        int[] v = substitution(l, m, w, substitution);
        for (int i = 0; i < v.length; i++) {
            ////System.out.println("v[" + i + "] = " + Integer.toBinaryString(v[i]));
        }

        // Final update w
        w = 0;
        int[] wFinal = individualize(l, v);
        for (int i = 0; i < wFinal.length; i++) {
            w += (int) (Math.pow(2, i) * wFinal[wFinal.length - i - 1]);
        }
        ////System.out.println("w before XOR: " + Integer.toBinaryString(w));

        // Last XOR
        ////System.out.println("Final XOR");
        w ^= subKeys[Nr];
        ////System.out.println("w Final: " + Integer.toBinaryString(w));

        // return secret
        String secret = decodeBinary(w, m);

        ////System.out.println(secret);
        return secret;
    }

    private static String decodeBinary(int w, int m) {
        int[] numberArray = new int[m];
        int copy = w;
        for (int i = 0; i < m; i++) {
            ////System.out.println("copy: " + Integer.toBinaryString(copy));
            w = w >> (12 - 4 * i);
            numberArray[i] = w;
            w = w << (12 - 4 * i);
            copy -= w;
            w = copy;
        }
        return Code.decodeMod26(numberArray);
    }

    private static int[] individualize(int l, int[] v) {
        int[] wDivided = new int[16];
        String wString = "";
        int zeros = 0;
        for (int i = 0; i < l; i++) {
            zeros = 4 - (Integer.toBinaryString(v[i])).length();
            while (zeros > 0) {
                wString += "0";
                zeros--;
            }
            wString += String.valueOf(Integer.toBinaryString(v[i]));
        }
        for (int i = 0; i < 16; i++) {
            wDivided[i] = Integer.valueOf(wString.substring(i, i + 1));
        }
        return wDivided;
    }

    /**
     * Returns an alphabetic ciphertext using SPN Cipher.
     *
     * @param  plaintext the text to encrypt
     * @param  substitution int array to use substitution
     * @return the cipher text string
     */
    private static int[] keySchedulingAlgorithm(int[] bigKey, int Nr, int l, int m) {
        int[] subKeys = new int[Nr + 1];

        for (int i = 0; i < Nr + 1; i++) {
            for (int y = 0; y < m; y++) {
                subKeys[i] += bigKey[i + y] << (l * (m - 1) - y * l);
            }
        //subKeys[i] = (bigKey[i]<<12)+(bigKey[i+1]<<8)+(bigKey[i+2]<<4)+bigKey[i+3];
        }

        return subKeys;
    }

    /**
     * Returns an alphabetic ciphertext using SPN Cipher.
     *
     * @param  l is the cantity of letters to represent the alphabet
     * @param  w is the current state of the binary we are working with
     * @param  substitution int array to use substitution
     * @return the cipher text string
     */
    private static int[] permutation(int l, int[] permutation, int[] v) {
        int[] wFinal = new int[16];
        int[] wDivided = individualize(l, v);

        for (int i = 0; i < 16; i++) {
            wFinal[i] = wDivided[permutation[i] - 1];
        }
        return wFinal;
    }

    /**
     * Returns an alphabetic ciphertext using SPN Cipher.
     *
     * @param  l is the cantity of letters to represent the alphabet
     * @param  w is the current state of the binary we are working with
     * @param  substitution int array to use substitution
     * @return the cipher text string
     */
    private static int[] substitution(int l, int m, int w, int[] substitution) {
        int[] v = new int[l];
        int u = w;
        for (int i = 0; i < l; i++) {
            w = w >> (((l) * (m - 1)) - i * l);
            //w = w>>(12-4*i);
            v[i] = substitution[w];
            w = w << (((l) * (m - 1)) - i * l);
            //w = w<<(12-4*i);
            u -= w;
            w = u;
        }
        return v;
    }

 
    private static int toUnitedInt(int l, int[] v) {

        int w = 0;
        int[] wFinal = individualize(l, v);
        for (int i = 0; i < wFinal.length; i++) {
            w += (int) (Math.pow(2, i) * wFinal[wFinal.length - i - 1]);
        }
        return w;
    }

      /**
     *
     * @param substitution substitution int array to use substitution
     * @param permutation int array to use permutation
     * @param key the keyWord used to encrypt using SPN
     * @param Nr is the number of rotations
     * @param n Samples amount
     * @return
     */
    static public String[][] generateSamples(int[] substitution, int[] permutation, String key, int Nr, int n) {
        String[][] matrix = new String[n][2];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            String plainText = new String();
            while (plainText.length() < 4) {
                plainText = plainText.concat(String.valueOf((char) (rnd.nextInt(16) + 97)));
            }
            matrix[i][0] = plainText;
            matrix[i][1] = SubstitutionPermutationNetworkCipher.encrypt(plainText, substitution, permutation, key, Nr);
        }
        return matrix;
    }

    /**
     *
     * @param T nx2 string matrix. The second-column strings are at least of lenght 4
     * @param sizeT The n value of T matrix
     * @param invSubstitution Sustitution corresponding to the inverse of the S-box
     * @return
     */
    public static int[] linearAttack(String[][] T, int[] invSubstitution) {
        double[][] count = new double[16][16];
        int sizeT = T.length;

        //Set every count matrix values to 0
        for (int L1 = 0x0; L1 <= 0xF; L1++) {
            for (int L2 = 0x0; L2 <= 0xF; L2++) {
                count[L1][L2] = 0;
            }
        }

        for (int i = 0; i < sizeT; i++) {
            for (int L1 = 0x0; L1 <= 0xF; L1++) {
                for (int L2 = 0x0; L2 <= 0xF; L2++) {
                    int y2 = Code.encodeMod26(T[i][1].substring(1, 2))[0];
                    int v2 = L1 ^ y2;
                    int y4 = Code.encodeMod26(T[i][1].substring(3, 4))[0];
                    int v4 = L2 ^ y4;
                    int u2 = invSubstitution[v2];
                    int u4 = invSubstitution[v4];
                    int x = Code.encodeMod26(T[i][0].substring(1, 3))[0];
                    int x5 = Integer.rotateRight(x & 0x8, 3);
                    int x7 = Integer.rotateRight(x & 0x2, 1);
                    int x8 = x & 0x1;
                    int u46 = Integer.rotateRight(u2 & 0x4, 2);
                    int u48 = u2 & 0x1;
                    int u414 = Integer.rotateRight(u4 & 0x4, 2);
                    int u416 = u4 & 0x1;
                    int z = x5 ^ x7 ^ x8 ^ u46 ^ u48 ^ u414 ^ u416;
                    if (z == 0) {
                        count[L1][L2] += 1;
                    }
                }
            }
        }

        double max = -1;
        int[] maxKey = new int[2];
        for (int L1 = 0x0; L1 < 0xF; L1++) {
            for (int L2 = 0x0; L2 < 0xF; L2++) {
                count[L1][L2] = Math.abs(count[L1][L2] - sizeT / 2);
                if (count[L1][L2] > max) {
                    max = count[L1][L2];
                    maxKey[0] = L1;
                    maxKey[1] = L2;
                }
            }
        }
        return maxKey;
    }
}
