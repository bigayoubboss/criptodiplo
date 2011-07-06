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
import org.cripto.cipher.Cipher;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class Affine implements Comparator, Cipher {
    
    // TODO Refactor Affinecipher delete set,get
    private String text;
    private int multiplicativeKey;
    private int additiveKey;
    private int mostFrequentLetter;

    public Affine() {
    }

    public Affine(String t, int m, int a, int l) {
        this.text = t;
        this.multiplicativeKey = m;
        this.additiveKey = a;
        this.mostFrequentLetter = l;
    }

    @Override
    public String decode(String cipherText, Object oKey) {
        // TODO Implement Affine
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String plainText = oPlainText.toString();
        int keyA = Integer.valueOf(params[0].toString());
        int keyB = Integer.valueOf(params[1].toString());

        int[] mod26PlainText = Code.encodeMod26(plainText);
        int[] mod26CipherText = new int[mod26PlainText.length];

        for (int i = 0; i < plainText.length(); i++) {

            int currentChar = mod26PlainText[i];
            currentChar *= keyA;
            currentChar += keyB;
            currentChar %= 26;

            mod26CipherText[i] = currentChar;
        }

        String cipherText = Code.decodeMod26(mod26CipherText);
        cipherText = cipherText.toUpperCase();

        return cipherText;
    }

    /**
     * Returns an arrayList of possible plain texts with its multiplicative and additive keys by decrypting Affine Cipher
     *
     * @param  cipherText the text to decrypt
     * @return arrayList of possible decrypted texts with with its multiplicative and additive keys
     */
    @Override
    public ArrayList<Affine> cryptoAnalysis(String cipherText) {
        int[] encodedCipherText = Code.encodeMod26(cipherText);
        ArrayList<LettersOcurrence> letterFrequencies = LettersOcurrence.frequencies(encodedCipherText);
        Collections.sort(letterFrequencies, new LettersOcurrence());
        int[] aux = new int[encodedCipherText.length];

        ArrayList<Affine> possiblePlainTexts = new ArrayList<Affine>();
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

                                int[] x = MathUtilFunctions.solveCongruenceSystem(coefA.get(0, 0), coefA.get(0, 1), coefB.get(0, 0), coefA.get(1, 0), coefA.get(1, 1), coefB.get(1, 0), 26.0);

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
                                    if (!possiblePlainTexts.contains(new Affine(possibility, a, b, va))) {
                                        possiblePlainTexts.add(new Affine(possibility, a, b, va));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return possiblePlainTexts;
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
    @Override
    public boolean equals(Object o) {
        Affine aux = (Affine) o;
        return (this.text.equals(aux.getText()) && this.multiplicativeKey == aux.getMultiplicativeKey() && this.additiveKey == aux.getAdditiveKey()) ? true : false;
    }

    /**
     * Compare its two arguments for order
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return a positive integer, zero, or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Affine ac1 = (Affine) o1;
        Affine ac2 = (Affine) o2;
        return (ac1.getMostFrequentLetter() - ac2.getMostFrequentLetter());
    }
}
