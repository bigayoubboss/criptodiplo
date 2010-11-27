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
public class TrigramsOcurrence implements Comparator {

    private int firstLetter;
    private int secondLetter;
    private int thirdLetter;
    private int frequency;
    private ArrayList<String> commonTrigrams = null;

    public TrigramsOcurrence() {
    }

    public TrigramsOcurrence(int l1, int l2, int l3, int f) {
        firstLetter = l1;
        secondLetter = l2;
        thirdLetter = l3;
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
     * @return the thirdLetter
     */
    public int getThirdLetter() {
        return thirdLetter;
    }

    /**
     * @param thirdLetter the thirdLetter to set
     */
    public void setThirdLetter(int thirdLetter) {
        this.thirdLetter = thirdLetter;
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
        TrigramsOcurrence aux = (TrigramsOcurrence) o;
        return (this.firstLetter == aux.getFirstLetter() && this.secondLetter == aux.getSecondLetter() && this.thirdLetter == aux.getThirdLetter()) ? true : false;
    }

    /**
     * Compare its two arguments for order
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return a positive integer, zero, or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compare(Object o1, Object o2) {
        TrigramsOcurrence lo1 = (TrigramsOcurrence) o1;
        TrigramsOcurrence lo2 = (TrigramsOcurrence) o2;
        return lo2.getFrequency() - lo1.getFrequency();
    }

    /**
     * Returns greatest object of ocurrence of set of bigrams.
     *
     * @param ArrayList of ocurrences
     * @return Greatest ocurrence object
     */
    public static TrigramsOcurrence greatest(ArrayList<TrigramsOcurrence> frequencies) {
        TrigramsOcurrence greatest = frequencies.get(0);
        for (TrigramsOcurrence frecuency : frequencies) {
            if (greatest.getFrequency() < frecuency.getFrequency()) {
                greatest = frecuency;
            }
        }
        return greatest;
    }

    /**
     * Returns the ocurrence frequency of each encoded trigram in a module-26 text.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequenciesMod26
     */
    public static ArrayList<TrigramsOcurrence> frequenciesMod26(String cipherText) {
        ArrayList<TrigramsOcurrence> trigramsOcurrence = new ArrayList<TrigramsOcurrence>();
        for (int i = 0; i < cipherText.length() - 2; i++) {
            Character c1 = cipherText.charAt(i);
            Character c2 = cipherText.charAt(i + 1);
            Character c3 = cipherText.charAt(i + 2);
            TrigramsOcurrence to = new TrigramsOcurrence(Code.encodeMod26(c1.toString())[0], Code.encodeMod26(c2.toString())[0], Code.encodeMod26(c3.toString())[0], 1);
            if (trigramsOcurrence.contains(to)) {
                int index = trigramsOcurrence.indexOf(to);
                int newFreq = trigramsOcurrence.get(index).getFrequency() + 1;
                trigramsOcurrence.get(index).setFrequency(newFreq);
            } else {
                trigramsOcurrence.add(to);
            }
        }
        return trigramsOcurrence;
    }

    /**
     * Returns the ocurrence frequency of each encoded trigram in a module-189 text.
     *
     * @param  encodedText the encoded text to analyze
     * @return the integer array of frequencies
     */
    public static ArrayList<TrigramsOcurrence> frequenciesMod189(String cipherText) {
        ArrayList<TrigramsOcurrence> trigramsOcurrence = new ArrayList<TrigramsOcurrence>();
        for (int i = 0; i < cipherText.length() - 2; i++) {
            Character c1 = cipherText.charAt(i);
            Character c2 = cipherText.charAt(i + 1);
            Character c3 = cipherText.charAt(i + 2);
            TrigramsOcurrence to = new TrigramsOcurrence(Code.encodeMod189(c1.toString())[0], Code.encodeMod189(c2.toString())[0], Code.encodeMod189(c3.toString())[0], 1);
            if (trigramsOcurrence.contains(to)) {
                int index = trigramsOcurrence.indexOf(to);
                int newFreq = trigramsOcurrence.get(index).getFrequency() + 1;
                trigramsOcurrence.get(index).setFrequency(newFreq);
            } else {
                trigramsOcurrence.add(to);
            }
        }
        return trigramsOcurrence;
    }

    /**
     * Return the index of first  trigram that meets any of the
     * characteristics in the ordered trigramsOcurrence list
     *
     * @param frequenciesMod26 the trigramsOcurrence list
     * @param trigram  the trigram wanted
     * @return int index of trigram , -1 if don't find
     */
    public static int findIndex(ArrayList<TrigramsOcurrence> frequencies, TrigramsOcurrence trigram) {

        Iterator<TrigramsOcurrence> itTrigrams = frequencies.iterator();
        int index = 0;
        TrigramsOcurrence aux = null;
        while (itTrigrams.hasNext()) {
            aux = itTrigrams.next();


            if (trigram.getFirstLetter() == aux.getFirstLetter() && trigram.getSecondLetter() == aux.getSecondLetter() && trigram.getThirdLetter() == -1 && trigram.getFrequency() == -1) {
                return index;
            }

            if (trigram.getFirstLetter() == aux.getFirstLetter() && trigram.getSecondLetter() == -1 && trigram.getThirdLetter() == aux.getThirdLetter() && trigram.getFrequency() == -1) {
                return index;
            }

            if (trigram.getFirstLetter() == -1 && trigram.getSecondLetter() == aux.getSecondLetter() && trigram.getThirdLetter() == aux.getThirdLetter() && trigram.getFrequency() == -1) {
                return index;
            }


            index++;
        }
        return -1;


    }

    /**
     * @return the array string list decreasing of the 12 most common Trigrams
     * of english in uppercase
     */
    public ArrayList<String> getCommonTrigrams() {

        commonTrigrams.add("THE");
        commonTrigrams.add("ING");
        commonTrigrams.add("AND");
        commonTrigrams.add("HER");
        commonTrigrams.add("ERE");
        commonTrigrams.add("ENT");
        commonTrigrams.add("THA");
        commonTrigrams.add("NTH");
        commonTrigrams.add("WAS");
        commonTrigrams.add("ETH");
        commonTrigrams.add("FOR");
        commonTrigrams.add("DTH");
        return commonTrigrams;
    }
}
