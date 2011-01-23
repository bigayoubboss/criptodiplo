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

package ClassicalCryptography;

import cpcommonmethods.Code;
import cpcommonmethods.LettersOcurrence;
import java.util.ArrayList;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class VigenereCipher {

    private String text;
    private String key;

    public VigenereCipher() {
    }

    public VigenereCipher(String t, String k) {
        this.text = t;
        this.key = k;
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
        return key;
    }

    /**
     * Returns an alphabetic ciphertext using Vigenere Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  keyWord the text to encrypt using Vigenere Cipher
     * @return the cipher text string
     */
    public static String encrypt(String plainText, String keyWord) {
        // System.out.println("vigenereCipher running...");

        // Algorithm
        // encodeMod26 plainText
        int[] encodedPlainText = Code.encodeMod26(plainText);
        // encodeMod26 keyWord
        int[] encodedKeyWord = Code.encodeMod26(keyWord);
        // add plainText and keyWord numbers
        for (int i = 0; i < plainText.length(); i++) {
            encodedPlainText[i] += (encodedKeyWord[i % keyWord.length()]);
            encodedPlainText[i] %= 26;
        }
        // decodeMod26 obtained encoded Text
        String secret = Code.decodeMod26(encodedPlainText);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * Returns an array of possible plain texts by decrypting Vigenere Cipher
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    public static ArrayList<VigenereCipher> cryptoAnalysis(String cipherText) {
        // El conjunto de Strings posibles y de las respectivas llaves
        ArrayList<VigenereCipher> possiblePlainTexts = new ArrayList<VigenereCipher>();

        // encodes cipherText
        int[] encoded_cipher_text = Code.encodeMod26(cipherText);

        // key word lenght and quantity of sub texts
        int m = friedManTest(cipherText);

        // sub texts max length
        int sub_text_max_length = (int) Math.ceil( (double) cipherText.length() / m);

        // number of subtexts with max length
        int num_max_length = (cipherText.length() % m);

        // creates many sub cipher texts from cipher text
        int counter = 0;
        int[][] encoded_cipher_sub_texts_max = new int[num_max_length][sub_text_max_length];
        for(int i = 0; i < num_max_length; i++){
            counter = i;
            for(int j = 0; j < sub_text_max_length; j++){
                encoded_cipher_sub_texts_max[i][j] = encoded_cipher_text[counter];
                counter += m;
            }
        }

        int contador = 0;
        int[][] encoded_cipher_sub_texts_min = new int[m-num_max_length][sub_text_max_length-1];
        for(int i = num_max_length; i < m; i++){
            contador = i;
            for(int j = 0; j < sub_text_max_length-1; j++){
                encoded_cipher_sub_texts_min[i-num_max_length][j] = encoded_cipher_text[contador];
                contador += m;
            }
        }

        // PREUVE: están bien los subs
        /*for(int i = 0; i < encoded_cipher_sub_texts_max.length; i++){
            System.out.println(Code.decodeMod26(encoded_cipher_sub_texts_max[i]));
        }
        for(int i = 0; i < encoded_cipher_sub_texts_min.length; i++){
            System.out.println(Code.decodeMod26(encoded_cipher_sub_texts_min[i]));
        }*/

        // Calculates letters frequencies from each bigger sub cipher texts
        int [][] frecuencias_max = new int[encoded_cipher_sub_texts_max.length][26];
        ArrayList<LettersOcurrence> letter_frecuencies_sub = new ArrayList<LettersOcurrence>();
        counter = 0;
        for(int i = 0; i < encoded_cipher_sub_texts_max.length; i++){
            letter_frecuencies_sub = LettersOcurrence.frequencies(encoded_cipher_sub_texts_max[i]);
            for(int j = 0; j < 26; j++){
                frecuencias_max[i][j] = letter_frecuencies_sub.get(j).getFrequency();
            }
        }
        // array keeps information of the little sub cipher text frequencies
        int [][] frecuencias_min = new int[encoded_cipher_sub_texts_min.length][26];
        counter = 0;
        for(int i = 0; i < encoded_cipher_sub_texts_min.length; i++){
            letter_frecuencies_sub = LettersOcurrence.frequencies(encoded_cipher_sub_texts_min[i]);
            for(int j = 0; j < 26; j++){
                frecuencias_min[i][j] = letter_frecuencies_sub.get(j).getFrequency();
            }
        }

        // PREUVE: están bien las frecuencias
        /*System.out.println(cipherText);
        System.out.println("Max");
        for(int i = 0; i < frecuencias_max.length; i++){
            for(int j = 0; j < 26; j++){
                System.out.print(frecuencias_max[i][j] + " ");
            }
            System.out.println("");
        }
       System.out.println("Min");
        for(int i = 0; i < frecuencias_min.length; i++){
            for(int j = 0; j < 26; j++){
                System.out.print(frecuencias_min[i][j] + " ");
            }
            System.out.println("");
        }
        */
        // Se calcula Mg ... frecuencias de cada letra en cada sub texto
        float[][] Mg = new float[m][26];
        for(int i = 0; i < frecuencias_max.length; i++){
            Mg[i] = mGCalculator(frecuencias_max[i], sub_text_max_length);
        }
        counter = 0;
        for(int i = frecuencias_max.length; i < frecuencias_max.length + frecuencias_min.length; i++){
            //System.out.println("counter: "+counter);
            Mg[i] = mGCalculator(frecuencias_min[counter], sub_text_max_length - 1);
            counter++;
        }

        // PREUVE: shows Mg
        for(int i = 0; i < frecuencias_max.length; i++){
            //System.out.println("Mg for Max in " + i);
            for(int j = 0; j < Mg[0].length; j++){
                //System.out.println(Mg[i][j]);
            }
            //System.out.println("");
        }
        for(int i = frecuencias_max.length; i < frecuencias_max.length + frecuencias_min.length; i++){
            //System.out.println("Mg for Min in " + i);
            for(int j = 0; j < Mg[0].length; j++){
                //System.out.println(Mg[i][j]);
            }
            //System.out.println("");
        }

        // Busco en Mg de cada sub texto las letras con una frecuencia f entre 0.055 < f < 0.075
        // Retorno 3 posibles letras en cada fila
        // posibles_letras_key[fila/sub_texto][letra]
        //Hashtable a = new Hashtable();
        int[] encoded_key = new int[m];
        for(int i = 0; i < Mg.length; i++){
            //System.out.println("IIIIIIIIIIIIIII:" + i);
            for(int j = 0; j < Mg[0].length; j++){
                //System.out.println("0.055 < " +Mg[i][j] + " < 0.075 ?");
                if(Mg[i][j] > 0.055 && Mg[i][j] < 0.075){
                    //System.out.println("IN");
                    //a.put(i, j);
                    encoded_key[i] = j;
                }
            }
            //System.out.println("");
        }

        // preuve:
        /*for(int i = 0; i < encoded_key.length; i++){
            System.out.println(encoded_key[i]);
        }

        // ya tenemos la(s) key ahora a preparar los possiblePlainTexts
        System.out.println("encoded_cipher_text.length "+ encoded_cipher_text.length);
        System.out.println("encoded_key.length "+ encoded_key.length);
        */

        // plainText + keyWord numbers = cipher
        // cipher - keyWord numbers = plainText
        for (int i = 0; i < encoded_cipher_text.length; i++) {
            encoded_cipher_text[i] = (encoded_cipher_text[i]-(encoded_key[i % encoded_key.length])>0)?encoded_cipher_text[i]-(encoded_key[i % encoded_key.length]):encoded_cipher_text[i]-(encoded_key[i % encoded_key.length])+26;
            encoded_cipher_text[i] %= 26;
            //System.out.println("el i "+encoded_cipher_text[i]);
        }
        // decodeMod26 obtained encoded Text
        String secret = Code.decodeMod26(encoded_cipher_text);
        //System.out.println(secret);
        String key = Code.decodeMod26(encoded_key);
        //System.out.println(key);
        
        VigenereCipher resultado = new VigenereCipher(secret, key);
        possiblePlainTexts.add(resultado);

        return possiblePlainTexts;
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
        for(int g = 0; g < 26; g++){
            sum = 0;
            for(int i = 0; i < 25; i++){
                sum += LettersOcurrence.englishLettersProbabilities()[i] * f[(i+g)%26];
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
    private static int friedManTest(String cipherText){
        System.out.println("Longitud cipher es: " + cipherText.length());
        // length of key word
        int m = 1;

        int[] encoded_cipher_text = Code.encodeMod26(cipherText);

        // coincidence index
        float[][] indice_coincidencias = new float[8][8];

        //length of sub texts
        int longueur = cipherText.length();
        String sub;

        // frequencies
        ArrayList<LettersOcurrence> frecuencies = new ArrayList<LettersOcurrence>();
        frecuencies = LettersOcurrence.frequencies(encoded_cipher_text);

        float sum = 0f;
        for(int j = 1; j < 9; j++){
            longueur = cipherText.length()/j;
            for(int x = 1; x <= j; x++){
                sub = nextSub(x, j, cipherText);
                //System.out.println("SUB: "+ j + x + " " + sub);
                sum = 0;
                encoded_cipher_text = Code.encodeMod26(sub);
                frecuencies = LettersOcurrence.frequencies(encoded_cipher_text);
                for(int i = 0; i < 26; i++){
                    sum += frecuencies.get(i).getFrequency() * (frecuencies.get(i).getFrequency() - 1);
                }
                sum /= longueur * (longueur - 1);
                indice_coincidencias[j-1][x-1] = sum;
            }
        }

        int[] counter = new int[8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j <= i; j++){
                //System.out.println("EL indice coincidencia "+ i + " " + j + " es " + indice_coincidencias[i][j]);
                if(indice_coincidencias[i][j]>0.055 && indice_coincidencias[i][j]<0.075) counter[i]++;
            }
        }

        int max = 0;
        int index = -1;
        for(int i = 0; i < 8; i++){
            //System.out.println("Counter i: " + i + " is " + counter[i]);
            if(counter[i]>max){
                max = counter[i];
                index = i;
            }
        }

        m = index+1;

        // System.out.println("FIN INDICE COINCIDENCIA: " + m);

        return m;
    }

    /**
     * Returns next sub text
     *
     * @param  actual division of sub_text
     * @param  actual sub_text
     * @param  cipher text String
     * @return String sub text
     */
    private static String nextSub(int actual, int sub_text, String cipherText){
        String returned = new String();

        // div = 2 y actual = 1
        for(int i = (actual-1); i < cipherText.length(); i+=sub_text){
            returned += cipherText.charAt(i);
        }
        
        return returned;
    }
}
