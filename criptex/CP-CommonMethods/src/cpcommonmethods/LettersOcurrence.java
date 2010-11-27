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
package cpcommonmethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
@SuppressWarnings("rawtypes")
public class LettersOcurrence implements Comparator {

    private int letter;
    private int frequency;

    public LettersOcurrence() {
    }

    public LettersOcurrence(int l, int f) {
        letter = l;
        frequency = f;
    }

    /**
     * @return the letter
     */
    public int getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(int letter) {
        this.letter = letter;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the english probabilities
     */
    public static double[] englishLettersProbabilities() {
        final double[] englishProbabilities = {0.082, 0.015, 0.028, 0.043, 0.127, 0.022, 0.02, 0.061, 0.07, 0.002, 0.008, 0.04, 0.024,
            0.067, 0.075, 0.019, 0.001, 0.06, 0.063, 0.091, 0.028, 0.01, 0.023, 0.01, 0.020, 0.001};
        return englishProbabilities;
    }

    public static int[] commonEnglisLetters() {
        final int[] common = {4, 19, 0, 14, 8, 13, 18, 7, 17, 3, 11, 2, 20, 12, 22, 5, 6, 24, 15, 1, 21, 10, 9, 23, 16, 25};
        return common;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        LettersOcurrence aux = (LettersOcurrence) o;
        return (this.letter == aux.getLetter() && this.frequency == aux.getFrequency()) ? true : false;
    }

    /**
     * Compare its two arguments for order
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return a positive integer, zero, or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compare(Object o1, Object o2) {
        LettersOcurrence lo1 = (LettersOcurrence) o1;
        LettersOcurrence lo2 = (LettersOcurrence) o2;
        return lo2.getFrequency() - lo1.getFrequency();
    }

    /**
     * Indicates whether some other object is "greater" this one.
     *
     * @param o the object to be compared
     * @return true if this object is greater than the obj argument; false otherwise.
     */
    public boolean isGreater(Object o) {
        LettersOcurrence aux = (LettersOcurrence) o;
        return (this.frequency > aux.getFrequency()) ? true : false;

    }

    /**
     * Returns greatest object of ocurrence of set of letters.
     *
     * @param ArrayList of ocurrences
     * @return Greatest ocurrence object
     */
    public static LettersOcurrence greatest(ArrayList<LettersOcurrence> frequencies) {
        LettersOcurrence greatest = frequencies.get(0);
        for (LettersOcurrence frecuency : frequencies) {
            if (greatest.getFrequency() < frecuency.getFrequency()) {
                greatest = frecuency;
            }
        }
        return greatest;
    }

    /**
     * Returns the frequency of ocurrence of each encoded text value.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequencies
     */
    public static ArrayList<LettersOcurrence> frequencies(int[] encodedText) {
        ArrayList<LettersOcurrence> frequenciesList = new ArrayList<LettersOcurrence>();

        for (int i = 0; i < 26; i++) {
            frequenciesList.add(new LettersOcurrence(i, 0));
        }

        for (int i = 0; i < encodedText.length; i++) {
            int newFreq = frequenciesList.get(encodedText[i]).getFrequency() + 1;
            frequenciesList.set(encodedText[i], new LettersOcurrence(encodedText[i], newFreq));
        }

        return frequenciesList;
    }

    /**
     * Returns the frequency of ocurrence of each encoded text value.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequencies
     */
    public static ArrayList<LettersOcurrence> frequenciesMod189(int[] encodedText) {
        ArrayList<LettersOcurrence> frequenciesList = new ArrayList<LettersOcurrence>();

        for (int i = 0; i < 189; i++) {
            frequenciesList.add(new LettersOcurrence(i, 0));
        }

        for (int i = 0; i < encodedText.length; i++) {
            int newFreq = frequenciesList.get(encodedText[i]).getFrequency() + 1;
            frequenciesList.set(encodedText[i], new LettersOcurrence(encodedText[i], newFreq));
        }

        return frequenciesList;
    }

    /**
     * Return the index of first letter that meets any o f the
     * waated characteristics in the ordered BigramsOcurrence list
     *
     * @param frequencies the BigramsOcurrence list
     * @param bigram  the bigram wanted
     * @return int index of bigram , -1 if don't find
     */
    public static int findIndex(ArrayList<LettersOcurrence> frequencies, LettersOcurrence letter) {

        Iterator<LettersOcurrence> itLetters = frequencies.iterator();
        int index = 0;
        LettersOcurrence aux = null;
        while (itLetters.hasNext()) {
            aux = itLetters.next();

            if (letter.getLetter() == aux.getLetter() && letter.getFrequency() == -1) {
                return index;
            }

            if (letter.getLetter() == -1 &&
                    letter.getFrequency() == aux.getFrequency()) {
                return index;
            }
            index++;
        }
        return -1;


    }

    /**
     * 
     * @return list of letters ordered by probabilities
     * to apear. 
     */
    @SuppressWarnings("empty-statement")
    public static ArrayList<String> OrderedProbabilities() {
        ArrayList<String> orderedLetters = new ArrayList<String>();
        orderedLetters.add("e");
        orderedLetters.add("t");
        orderedLetters.add("a");
        orderedLetters.add("o");
        orderedLetters.add("i");
        orderedLetters.add("n");
        orderedLetters.add("s");
        orderedLetters.add("h");
        orderedLetters.add("r");
        orderedLetters.add("d");
        orderedLetters.add("l");
        orderedLetters.add("c");
        orderedLetters.add("u");
        orderedLetters.add("m");
        orderedLetters.add("w");
        orderedLetters.add("f");
        orderedLetters.add("g");
        orderedLetters.add("y");
        orderedLetters.add("p");
        orderedLetters.add("b");
        orderedLetters.add("v");
        orderedLetters.add("k");
        orderedLetters.add("j");
        orderedLetters.add("x");
        orderedLetters.add("q");
        orderedLetters.add("z");

        return orderedLetters;
    }
}
