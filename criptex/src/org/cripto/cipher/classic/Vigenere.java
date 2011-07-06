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
import java.util.ArrayList;
import org.cripto.cipher.Cipher;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class Vigenere implements Cipher {

    // TODO Refactor Vigenere cipher delete set,get
    private String text;
    private String key2;

    public Vigenere() {
    }

    public Vigenere(String t, String k) {
        this.text = t;
        this.key2 = k;
    }

    @Override
    public String encode(Object oPlainText, Object oKey, Object[] params) {

        String plainText = oPlainText.toString();
        String key = oKey.toString();

        int[] mod26PlainText = Code.encodeMod26(plainText);
        int[] mod26CipherText = new int[mod26PlainText.length];
        int[] mod26Key = Code.encodeMod26(key);

        for (int i = 0; i < plainText.length(); i++) {
            mod26CipherText[i] = mod26PlainText[i] + (mod26Key[i % key.length()]);
            mod26CipherText[i] = mod26CipherText[i] % 26;
        }

        String cipherText = Code.decodeMod26(mod26CipherText);
        cipherText = cipherText.toUpperCase();

        return cipherText;
    }

    @Override
    public String decode(String cipherText, Object oKey) {
        // TODO Implement Vigenere cipher decode
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns an array of possible plain texts by decoding Vigenere Cipher
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    @Override
    public ArrayList<Vigenere> cryptoAnalysis(String cipherText) {

        ArrayList<Vigenere> possiblePlainTexts = new ArrayList<Vigenere>();
        int[] mod26CipherText = Code.encodeMod26(cipherText);

        int keySize = friedManTest(cipherText);

        int subTextMaxLength = (int) Math.ceil((double) cipherText.length() / keySize);
        int numSubTextWithMaxLength = (cipherText.length() % keySize);

        int subtextCounter = 0;
        int[][] mod26SubTexts = new int[numSubTextWithMaxLength][subTextMaxLength];
        for (int i = 0; i < numSubTextWithMaxLength; i++) {
            subtextCounter = i;
            for (int j = 0; j < subTextMaxLength; j++) {
                mod26SubTexts[i][j] = mod26CipherText[subtextCounter];
                subtextCounter += keySize;
            }
        }

        subtextCounter = 0;
        int[][] mod26CipherTexts = new int[keySize - numSubTextWithMaxLength][subTextMaxLength - 1];
        for (int i = numSubTextWithMaxLength; i < keySize; i++) {
            subtextCounter = i;
            for (int j = 0; j < subTextMaxLength - 1; j++) {
                mod26CipherTexts[i - numSubTextWithMaxLength][j] = mod26CipherText[subtextCounter];
                subtextCounter += keySize;
            }
        }

        int[][] maximumFrequencies = maximumFrequenciesCalculator(mod26SubTexts);
        int[][] minimumFrequencies = minimumFrequenciesCalculator(mod26CipherTexts);
        float[][] Mg = frequenciesCalculator(keySize, subTextMaxLength, maximumFrequencies, minimumFrequencies);
        int[] mod26Key = keySelection(keySize, Mg);
        int[] mod26PlainText = plainTextSelection(mod26CipherText, mod26Key);

        String plainText = Code.decodeMod26(mod26PlainText);
        String key = Code.decodeMod26(mod26Key);

        Vigenere posibility = new Vigenere(plainText, key);
        possiblePlainTexts.add(posibility);

        return possiblePlainTexts;
    }

    private int[] plainTextSelection(int[] mod26CipherText, int[] mod26Key) {
       
        int[] mod26PlainText = new int[mod26CipherText.length];
        
        for (int i = 0; i < mod26CipherText.length; i++) {
            mod26PlainText[i] = (mod26CipherText[i] - (mod26Key[i % mod26Key.length]) > 0) ? mod26CipherText[i] - (mod26Key[i % mod26Key.length]) : mod26CipherText[i] - (mod26Key[i % mod26Key.length]) + 26;
            mod26PlainText[i] %= 26;
        }
        
        return mod26PlainText;
    }

    private int[] keySelection(int keySize, float[][] Mg) {

        int[] mod26Key = new int[keySize];
        
        for (int i = 0; i < Mg.length; i++) {
            for (int j = 0; j < Mg[0].length; j++) {
                if (Mg[i][j] > 0.055 && Mg[i][j] < 0.075) {
                    mod26Key[i] = j;
                }
            }
        }
        
        return mod26Key;
    }

    private float[][] frequenciesCalculator(int keySize, int subTextMaxLength, int[][] maximumFrequencies, int[][] minimumFrequencies) {

        float[][] Mg = new float[keySize][26];
        for (int i = 0; i < maximumFrequencies.length; i++) {
            Mg[i] = mGCalculator(maximumFrequencies[i], subTextMaxLength);
        }

        int counter = 0;

        for (int i = maximumFrequencies.length; i < maximumFrequencies.length + minimumFrequencies.length; i++) {
            Mg[i] = mGCalculator(minimumFrequencies[counter], subTextMaxLength - 1);
            counter++;
        }
        return Mg;
    }

    private int[][] maximumFrequenciesCalculator(int[][] mod26SubTexts) {

        int[][] maximumFrequencies = new int[mod26SubTexts.length][26];
        ArrayList<LettersOcurrence> lettersFrequencies = new ArrayList<LettersOcurrence>();

        for (int i = 0; i < mod26SubTexts.length; i++) {
            lettersFrequencies = LettersOcurrence.frequencies(mod26SubTexts[i]);
            for (int j = 0; j < 26; j++) {
                maximumFrequencies[i][j] = lettersFrequencies.get(j).getFrequency();
            }
        }

        return maximumFrequencies;
    }

    private int[][] minimumFrequenciesCalculator(int[][] mod26CipherTexts) {

        int[][] minimumFrequencies = new int[mod26CipherTexts.length][26];

        for (int i = 0; i < mod26CipherTexts.length; i++) {
            ArrayList<LettersOcurrence> lettersFrequencies = LettersOcurrence.frequencies(mod26CipherTexts[i]);
            for (int j = 0; j < 26; j++) {
                minimumFrequencies[i][j] = lettersFrequencies.get(j).getFrequency();
            }
        }
        return minimumFrequencies;
    }

    /**
     * Returns an array Mg of frecuencies sum of letters...
     *
     * @param  array of frecuencies
     * @return array of frecuencies sum
     */
    private static float[] mGCalculator(int[] f, int l) {
        float[] result = new float[26];
        float sum = 0;
        for (int g = 0; g < 26; g++) {
            sum = 0;
            for (int i = 0; i < 25; i++) {
                sum += LettersOcurrence.englishLettersProbabilities()[i] * f[(i + g) % 26];
            }
            result[g] = sum / l;
        }
        return result;
    }

    /**
     * Returns the posible length of key word
     *
     * @param  encoded cipher text array
     * @return length of key word
     */
    private static int friedManTest(String cipherText) {

        int keySize = 1;

        int[] mod26CiperTex = Code.encodeMod26(cipherText);

        float[][] coincidenceIndex = new float[8][8];

        int subTextLength = cipherText.length();
        String subText;

        ArrayList<LettersOcurrence> letterFrecuencies = new ArrayList<LettersOcurrence>();
        letterFrecuencies = LettersOcurrence.frequencies(mod26CiperTex);

        float sum = 0f;
        for (int j = 1; j < 9; j++) {
            subTextLength = cipherText.length() / j;
            for (int x = 1; x <= j; x++) {

                subText = nextSub(x, j, cipherText);
                sum = 0;
                mod26CiperTex = Code.encodeMod26(subText);
                letterFrecuencies = LettersOcurrence.frequencies(mod26CiperTex);
                
                for (int i = 0; i < 26; i++) {
                    sum += letterFrecuencies.get(i).getFrequency() * (letterFrecuencies.get(i).getFrequency() - 1);
                }
                sum /= subTextLength * (subTextLength - 1);
                coincidenceIndex[j - 1][x - 1] = sum;
            }
        }

        int[] counter = new int[8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= i; j++) {
                if (coincidenceIndex[i][j] > 0.055 && coincidenceIndex[i][j] < 0.075) {
                    counter[i]++;
                }
            }
        }

        int max = 0;
        int index = -1;
        for (int i = 0; i < 8; i++) {
            if (counter[i] > max) {
                max = counter[i];
                index = i;
            }
        }

        keySize = index + 1;

        return keySize;
    }

    /**
     * Returns next sub text
     *
     * @param  actual division of sub_text
     * @param  actual sub_text
     * @param  cipher text String
     * @return String sub text
     */
    private static String nextSub(int actual, int subText, String cipherText) {
        
        String nextSubText = new String();

        for (int i = (actual - 1); i < cipherText.length(); i += subText) {
            nextSubText += cipherText.charAt(i);
        }

        return nextSubText;
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
    public String getKey() {
        return key2;
    }
}
