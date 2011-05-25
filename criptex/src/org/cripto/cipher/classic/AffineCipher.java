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

import org.cripto.utils.Code;
import org.cripto.utils.LettersOcurrence;
import org.cripto.utils.MathUtilFunctions;
import java.util.ArrayList;
import java.util.Collections;
import org.cripto.utils.jama.Matrix;
import java.util.Comparator;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
@SuppressWarnings("rawtypes")
public class AffineCipher implements Comparator {

    private String text;
    private int multiplicativeKey;
    private int additiveKey;
    private int mostFrequentLetter;

    public AffineCipher() {
    }

    public AffineCipher(String t, int m, int a, int l) {
        this.text = t;
        this.multiplicativeKey = m;
        this.additiveKey = a;
        this.mostFrequentLetter = l;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the multiplicativeKey
     */
    public int getMultiplicativeKey() {
        return multiplicativeKey;
    }

    /**
     * @return the additiveKey
     */
    public int getAdditiveKey() {
        return additiveKey;
    }

    /**
     * @return the mostFrequentLetter
     */
    public int getMostFrequentLetter() {
        return mostFrequentLetter;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        AffineCipher aux = (AffineCipher) o;
        return (this.text.equals(aux.getText()) && this.multiplicativeKey == aux.getMultiplicativeKey() && this.additiveKey == aux.getAdditiveKey()) ? true : false;
    }

    /**
     * Compare its two arguments for order
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return a positive integer, zero, or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compare(Object o1, Object o2) {
        AffineCipher ac1 = (AffineCipher) o1;
        AffineCipher ac2 = (AffineCipher) o2;
        return (ac1.getMostFrequentLetter() - ac2.getMostFrequentLetter());
    }

    /**
     * Returns an alphabetic ciphertext using Affine Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  keyA part of the key used to encrypt using Vigenere Cipher
     * @param  keyB part of the key used to encrypt using Vigenere Cipher
     * @return the cipher text string
     */
    public static String encrypt(String plainText, int keyA, int keyB) {
        // System.out.println("affineCipher running...");

        // Algorithm
        // encodeMod26 plainText
        int[] encodedPlainText = Code.encodeMod26(plainText);
        // ( plainText * keyA + keyB ) mod 26
        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] *= keyA;
            encodedPlainText[i] += keyB;
            encodedPlainText[i] %= 26;
        }
        // decodeMod26 sum
        String secret = Code.decodeMod26(encodedPlainText);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * Returns an arrayList of possible plain texts with its multiplicative and additive keys by decrypting Affine Cipher
     *
     * @param  cipherText the text to decrypt
     * @return arrayList of possible decrypted texts with with its multiplicative and additive keys
     */
    public static ArrayList<AffineCipher> cryptoAnalysis(String cipherText) {
        int[] encodedCipherText = Code.encodeMod26(cipherText);
        ArrayList<LettersOcurrence> letterFrequencies = LettersOcurrence.frequencies(encodedCipherText);
        Collections.sort(letterFrequencies, new LettersOcurrence());
        int[] aux = new int[encodedCipherText.length];

        ArrayList<AffineCipher> possiblePlainTexts = new ArrayList<AffineCipher>();
        //    ax + by = c
        //    dx + ey = f
        for (int va = 0; va < LettersOcurrence.commonEnglisLetters().length; va++) {
            for (int vc = 0; vc < letterFrequencies.size(); vc++) {
                for (int vd = 0; vd < LettersOcurrence.commonEnglisLetters().length; vd++) {
                    for (int vf = 0; vf < letterFrequencies.size(); vf++) {
                        if (va != vd && vc != vf) {

                            Matrix coefA = new Matrix(2, 2);
                            coefA.set(0, 0, LettersOcurrence.commonEnglisLetters()[va]);
                            coefA.set(0, 1, 1);
                            coefA.set(1, 0, LettersOcurrence.commonEnglisLetters()[vd]);
                            coefA.set(1, 1, 1);
                            Matrix coefB = new Matrix(2, 1);
                            coefB.set(0, 0, letterFrequencies.get(vc).getLetter());
                            coefB.set(1, 0, letterFrequencies.get(vf).getLetter());

                            int delta = Math.abs(Math.round((float) coefA.det()));
                            if (MathUtilFunctions.GCD(delta, 26) == 1) {
                                
                                int[] x = solveCongruenceSystem(coefA.get(0, 0), coefA.get(0, 1), coefB.get(0, 0), coefA.get(1, 0), coefA.get(1, 1), coefB.get(1, 0), 26.0);

                                int a = x[0];
                                int b = x[1];

                                if (MathUtilFunctions.GCD(a, 26) == 1) {
                                    int invA = MathUtilFunctions.multiplicativeInverse(a, 26);
                                    for (int k = 0; k < encodedCipherText.length; k++) {
                                        aux[k] = (invA * (encodedCipherText[k] - b)) % 26;
                                        if (aux[k] < 0) {
                                            aux[k] += 26;
                                        }
                                    }
                                    String possibility = Code.decodeMod26(aux).trim().toLowerCase();
                                    if (!possiblePlainTexts.contains(new AffineCipher(possibility, a, b, va))) {
                                        possiblePlainTexts.add(new AffineCipher(possibility, a, b, va));
                                     }
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("tamaño = " + possiblePlainTexts.size());
        //Collections.sort(possiblePlainTexts, new AffineCipher());
        //for (Iterator<AffineCipher> it = possiblePlainTexts.iterator(); it.hasNext();) {
        //AffineCipher affineCipher = it.next();
        //System.out.println(affineCipher.getMostFrequentLetter() + " " +affineCipher.getText() + " " + affineCipher.getMultiplicativeKey() + " " + affineCipher.getAdditiveKey());
        //}
        return possiblePlainTexts;
    }

    /**
     * Returns the solution of congruencial linear system
     *
     *    ax + by = u mod n
     *    cx + dy = v mod n
     *
     * @param a coefficient of x in the first equation
     * @param b coefficient of y in the first equation
     * @param u result of the first equation
     * @param c coefficient of x in the second equation
     * @param d coefficient of y in the second equation
     * @param v result of the second equation
     * @param m system module
     * @return
     */
    public static int[] solveCongruenceSystem(double a1, double b1, double u1, double c1, double d1, double v1, double m) {
        int x1, y1, count, alpha, beta, dif, gamma, r;

        int bb, dd, uu, vv, eq1, eq2;
        int[] result = new int[2];
        int[] solY = new int[26];

        int n = Math.round((float) m);
        int a = Math.round((float) a1) % n;
        int b = Math.round((float) b1) % n;
        int u = Math.round((float) u1) % n;
        int c = Math.round((float) c1) % n;
        int d = Math.round((float) d1) % n;
        int v = Math.round((float) v1) % n;

        bb = (c * b) % n;
        uu = (c * u) % n;
        dd = (a * d) % n;
        vv = (a * v) % n;

        alpha = (bb - dd) % n;
        beta = (uu - vv) % n;
        if (alpha < 0) {
            alpha += 26;
        }
        if (beta < 0) {
            beta += 26;
        }
        
        count = 0;
        for (int y = 0; y <= n - 1; ++y) {
            y1 = (alpha * y) % n;
            if (y1 == beta) {
                count = count + 1;
                solY[count] = y;
            }

        }
        for (int i = 1; i <= count; ++i) {
            r = solY[i];
            gamma = (b * r) % n;
            dif = (u - gamma) % n;
            if (dif < 0) {
                dif += 26;
            }
            for (double x = 0; x <= n - 1; ++x) {
                x1 = Math.round((float) (a * x) % n);
                if (x1 == dif) {
                    eq1 = Math.round((float) (a * x + b * r) % n);
                    eq2 = Math.round((float) (c * x + d * r) % n);

                    if (eq1 == u && eq2 == v) {
                        result[0] = Math.round((float) x);
                        result[1] = Math.round((float) r);
                    }
                }
            }
        }
        return result;
    }
}
