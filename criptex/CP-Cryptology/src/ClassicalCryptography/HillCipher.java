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
import jama.Matrix;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class HillCipher {

    /**
     * Returns an alphabetic ciphertext using Hill Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  key the text to encrypt using Vigenere Cipher
     * @return the cipher text string
     */
    public static String encrypt(String plainText, Matrix key){
        System.out.println("hillCipher running...");

        // Algorithm
        // encodeMod26 plainText
        int[] encodedPlainText = Code.encodeMod26(plainText);

        // creates plain text number Matrixes
        // m es la dimensión de la matriz cuadrada, también pudimos usar row
        int m = key.getColumnDimension();
        // plainTextDivisionQuantity nos da la cantidad de divisiones en las cuales dividiremos el
        // plainText
        int plainTextDivisionQuantity = (int) Math.ceil((double)plainText.length()/m);
        // actualPosition guarda la posición que se lleva mientras recorremos el plainText
        int actualPosition = 0;
        // el algoritmo va creando las matrices (filas) en las cuales se divide el plainText
        Matrix[] encodedPlainTextMatrixes = new Matrix[plainTextDivisionQuantity];
        for(int i = 0; i < plainTextDivisionQuantity; i++){
            encodedPlainTextMatrixes[i] = new Matrix(1,m);
            for(int y = 0; y < m; y++){
                if(actualPosition < encodedPlainText.length){
                    encodedPlainTextMatrixes[i].set(0, y, encodedPlainText[actualPosition]);
                }else{
                    encodedPlainTextMatrixes[i].set(0, y, 0);
                }
                actualPosition++;
            }
        }
        System.out.println("Print coded name");
        printMatrixes(encodedPlainTextMatrixes);

        // e(x) = xK
        // se calculan las matrices resultado usando la función times() de la clase Matrix
        // multiplicamos las matrices creadas en el ciclo anterior y la matriz key
        Matrix[] Results = new Matrix[plainTextDivisionQuantity];
        for(int i = 0; i < plainTextDivisionQuantity; i++){
            Results[i] = encodedPlainTextMatrixes[i].times(key);
        }
        System.out.println("Print coded results");
        printMatrixes(Results);

        // export Results a to number array
        // en secretNumberArray se guarda el arreglo de enteros que vamos a retornar es decir el
        // texto cifrado
        int[] secretNumberArray = new int[plainText.length()];
        // actualPosition guarda la posición que se lleva mientras recorremos el arreglo de
        // Results
        // dependiendo de m(dimensión de matriz key) actualizamos la actual position o no
        actualPosition = 0;
        for(int i = 0; i < plainText.length(); i++){
            secretNumberArray[i] = ((int) Results[actualPosition].get(0, i%m)) % 26;
            if(i > 0 && i%m == (m-1)) actualPosition++;
        }
        
        // decodeMod26 obtained encoded Text
        String secret = Code.decodeMod26(secretNumberArray);
        secret = secret.toUpperCase();
        // return secret
        return secret;
    }

    /**
     * Prints an array of Matrixes.
     *
     * @param  array Array of Matrixes to print
     */
    public static void printMatrixes(Matrix[] array){
        for(int i = 0; i < array.length; i++){
            array[i].print(2,2);
        }
    }

    /**
     * Returns an array of possible plain texts by decrypting Hill Cipher
     *
     * @param  cipherText the text to decrypt
     * @return array of possible decrypted texts
     */
    public static String[] cryptoAnalysis(String cipherText) {
        String[] resultados = null;
        return resultados;
    }

}
