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
public class BigramsOcurrence implements Comparator {

    private int firstLetter;
    private int secondLetter;
    private int frequency;
    private ArrayList<String> commonBigrams = null;

    public BigramsOcurrence() {
    }

    public BigramsOcurrence(int l1, int l2, int f) {
        firstLetter = l1;
        secondLetter = l2;
        frequency = f;
    }

    /**
     * @return the firstLetter
     */
    public int getFirstLetter() {
        return firstLetter;
    }

    /**
     * @param firstLetter the firstLetter to set
     */
    public void setFirstLetter(int letter) {
        this.firstLetter = letter;
    }

    /**
     * @return the secondLetter
     */
    public int getSecondLetter() {
        return secondLetter;
    }

    /**
     * @param secondLetter the secondLetter to set
     */
    public void setSecondLetter(int secondLetter) {
        this.secondLetter = secondLetter;
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
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        BigramsOcurrence aux = (BigramsOcurrence) o;
        return (this.firstLetter == aux.getFirstLetter() && this.secondLetter == aux.getSecondLetter()) ? true : false;
    }

    /**
     * Compare its two arguments for order
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return a positive integer, zero, or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compare(Object o1, Object o2) {
        BigramsOcurrence lo1 = (BigramsOcurrence) o1;
        BigramsOcurrence lo2 = (BigramsOcurrence) o2;
        return lo2.getFrequency() - lo1.getFrequency();
    }

    /**
     * Return the index of first  bigram that meets the
     * characteristics in the ordered BigramsOcurrence list
     *
     * @param frequenciesMod26 the BigramsOcurrence list
     * @param bigram  the bigram wanted
     * @return int index of bigram , -1 if don't find
     */
    public static int findIndex(ArrayList<BigramsOcurrence> frequencies, BigramsOcurrence bigram) {

        Iterator<BigramsOcurrence> itBigrams = frequencies.iterator();
        int index = 0;
        BigramsOcurrence aux = null;
        while (itBigrams.hasNext()) {
            aux = itBigrams.next();

            if (bigram.getFirstLetter() == aux.getFirstLetter() && bigram.getSecondLetter() == aux.getSecondLetter() && bigram.getFrequency() == -1) {
                return index;
            }


            if (bigram.getFirstLetter() == aux.getFirstLetter() && bigram.getSecondLetter() == -1 && bigram.getFrequency() == -1) {
                return index;
            }

            if (bigram.getFirstLetter() == -1 && bigram.getSecondLetter() == aux.getSecondLetter() && bigram.getFrequency() == -1) {
                return index;
            }

            index++;
        }
        return -1;


    }

    /**
     * Returns greatest object of ocurrence of set of bigrams.
     *
     * @param ArrayList of ocurrences
     * @return Greatest ocurrence object
     */
    public static BigramsOcurrence greatest(ArrayList<BigramsOcurrence> frequencies) {
        BigramsOcurrence greatest = frequencies.get(0);
        for (BigramsOcurrence frecuency : frequencies) {
            if (greatest.getFrequency() < frecuency.getFrequency()) {
                greatest = frecuency;
            }
        }
        return greatest;
    }

    /**
     * Returns the ocurrence frequency of each encoded bigram in a module-26 text.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequencies
     */
    public static ArrayList<BigramsOcurrence> frequenciesMod26(String cipherText) {
        ArrayList<BigramsOcurrence> bigramsOcurrence = new ArrayList<BigramsOcurrence>();
        for (int i = 0; i < cipherText.length() - 1; i++) {
            Character c1 = cipherText.charAt(i);
            Character c2 = cipherText.charAt(i + 1);
            BigramsOcurrence bo = new BigramsOcurrence(Code.encodeMod26(c1.toString())[0], Code.encodeMod26(c2.toString())[0], 1);
            if (bigramsOcurrence.contains(bo)) {
                int index = bigramsOcurrence.indexOf(bo);
                int newFreq = bigramsOcurrence.get(index).getFrequency() + 1;
                bigramsOcurrence.get(index).setFrequency(newFreq);
            } else {
                bigramsOcurrence.add(bo);
            }
        }
        return bigramsOcurrence;
    }

    /**
     * Returns the ocurrence frequency of each encoded bigram in a module-189 text.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequencies
     */
    public static ArrayList<BigramsOcurrence> frequenciesMod189(String cipherText) {
        ArrayList<BigramsOcurrence> bigramsOcurrence = new ArrayList<BigramsOcurrence>();
        for (int i = 0; i < cipherText.length() - 1; i++) {
            Character c1 = cipherText.charAt(i);
            Character c2 = cipherText.charAt(i + 1);
            BigramsOcurrence bo = new BigramsOcurrence(Code.encodeMod189(c1.toString())[0], Code.encodeMod189(c2.toString())[0], 1);
            if (bigramsOcurrence.contains(bo)) {
                int index = bigramsOcurrence.indexOf(bo);
                int newFreq = bigramsOcurrence.get(index).getFrequency() + 1;
                bigramsOcurrence.get(index).setFrequency(newFreq);
            } else {
                bigramsOcurrence.add(bo);
            }
        }
        return bigramsOcurrence;
    }

    /**
     * @return the array string list decreasing of the 30 most common Digrams
     * of english in uppercase
     */
    public ArrayList<String> getCommonBigrams() {

        commonBigrams.add("TH");
        commonBigrams.add("HE");
        commonBigrams.add("IN");
        commonBigrams.add("ER");
        commonBigrams.add("AN");
        commonBigrams.add("RE");
        commonBigrams.add("ED");
        commonBigrams.add("ON");
        commonBigrams.add("ES");
        commonBigrams.add("ST");
        commonBigrams.add("EN");
        commonBigrams.add("AT");
        commonBigrams.add("TO");
        commonBigrams.add("NT");
        commonBigrams.add("HA");
        commonBigrams.add("ND");
        commonBigrams.add("OU");
        commonBigrams.add("EA");
        commonBigrams.add("NG");
        commonBigrams.add("AS");
        commonBigrams.add("OR");
        commonBigrams.add("TI");
        commonBigrams.add("IS");
        commonBigrams.add("ET");
        commonBigrams.add("IT");
        commonBigrams.add("AR");
        commonBigrams.add("TE");
        commonBigrams.add("SE");
        commonBigrams.add("HI");
        commonBigrams.add("OF");
        return commonBigrams;
    }
}
