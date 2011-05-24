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

package org.cripto.systems.image;

import org.cripto.utils.jama.Matrix;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class HillMRIVCipher {

    /**
     * Returns an image using Hill Cipher.
     *
     * @param  plainImage the text to encrypt
     * @param  key the text to encrypt using Vigenere Cipher
     * @return the cipher text string
     */
    public static int[] encryptHill(int[] plainImage, Matrix key) {
        ////System.out.println("hillCipher running...");

        // m es la dimension de la imagen cuadrada
        int m = key.getColumnDimension();
        // plainImageDivisionQuantity nos da la cantidad de divisiones en las cuales dividiremos la imagen
        int plainImageDivisionQuantity = (int) Math.ceil((double) plainImage.length / m);

        // se divide la imagen en bloques de tamaño m
        Matrix[] encodedPlainImageMatrixes = divideImage(plainImage, plainImageDivisionQuantity, m);

        // e(x) = xK
        // se calculan las matrices resultado usando la función times() de la clase Matrix
        // multiplicamos las matrices creadas en el ciclo anterior y la matriz key
        Matrix[] Results = new Matrix[plainImageDivisionQuantity];
        for (int i = 0; i < plainImageDivisionQuantity; i++) {
            Results[i] = encodedPlainImageMatrixes[i].times(key);
        }

        // en secretNumberArray se guarda el arreglo de enteros que vamos a retornar es decir la
        // imagen cifrado
        int[] secretNumberArray = joinImage(Results, m, plainImage.length);
        // return secret
        return secretNumberArray;
    }

    /**
     * Returns an image using Hill Cipher.
     *
     * @param  plainImage the text to encrypt
     * @param  key the text to encrypt using Vigenere Cipher
     * @return the cipher text string
     */
    public static int[] encryptHillMRIV(int[] plainImage, double[] V, Matrix key) {
        ////System.out.println("hillCipher running...");

        // m es la dimension de la imagen cuadrada
        int m = key.getColumnDimension();
        // plainImageDivisionQuantity nos da la cantidad de divisiones en las cuales dividiremos la imagen
        int plainImageDivisionQuantity = (int) Math.ceil((double) plainImage.length / m);

        // se divide la imagen en bloques de tamaño m
        Matrix[] encodedPlainImageMatrixes = divideImage(plainImage, plainImageDivisionQuantity, m);

        // e(x) = xK
        // se calculan las matrices resultado usando la función times() de la clase Matrix
        // multiplicamos las matrices creadas en el ciclo anterior y la matriz key
        Matrix[] Results = new Matrix[plainImageDivisionQuantity];
        Results[0] = encodedPlainImageMatrixes[0].times(key);
        for (int i = 1; i < plainImageDivisionQuantity; i++) {
            key = keyAdjustment(V, key, (i - 1) % key.getRowDimension());

            ////System.out.println("encode: " + Results.length);
            Results[i] = encodedPlainImageMatrixes[i].times(key);
        }

        // en secretNumberArray se guarda el arreglo de enteros que vamos a retornar es decir la
        // imagen cifrado
        int[] secretNumberArray = joinImage(Results, m, plainImage.length);

        // return secret
        return secretNumberArray;
    }

    public static Matrix keyAdjustment(double[] V, Matrix key, int i) {
        //System.out.print("\n === KEY ===");
        for (int x = 0; x < key.getRowDimension(); x++) {
           // System.out.print("\n");
            for (int y = 0; y < key.getColumnDimension(); y++) {
                if (x == y) {
                   // System.out.print((int) key.get(x, y) + " , ");
                }
            }
        }
        Matrix vector = new Matrix(V, V.length);
        Matrix kSubICol = key.times(vector);

        Matrix kSubI = new Matrix(1, kSubICol.getRowDimension());

        for (int x = 0; x < kSubICol.getRowDimension(); x++) {
            kSubI.set(0, x, kSubICol.get(x, 0));
        }

        double[][] matrix = key.getArray();
        double[][] rowSubI = kSubI.getArray();

        matrix[i] = rowSubI[0];

        matrix = org.cripto.utils.ArraysUtils.matrixModule(matrix, 256);

        return key;
    }

    public static Matrix[] divideImage(int[] plainImage, int plainImageDivisionQuantity, int m) {

        // actualPosition guarda la posición que se lleva mientras recorremos la imagen
        int actualPosition = 0;
        // el algoritmo va creando las matrices (filas) en las cuales se divide la imagen
        Matrix[] encodedPlainImageMatrixes = new Matrix[plainImageDivisionQuantity];
        for (int i = 0; i < plainImageDivisionQuantity; i++) {
            encodedPlainImageMatrixes[i] = new Matrix(1, m);
            for (int y = 0; y < m; y++) {
                if (actualPosition < plainImage.length) {
                    encodedPlainImageMatrixes[i].set(0, y, plainImage[actualPosition]);
                } else {
                    encodedPlainImageMatrixes[i].set(0, y, 0);
                }
                actualPosition++;
            }
        }
        return encodedPlainImageMatrixes;
    }

    public static int[] joinImage(Matrix[] Results, int m, int length) {
        int actualPosition = 0;
        int[] secretNumberArray = new int[length];
        for (int i = 0; i < length; i++) {
            secretNumberArray[i] = ((int) Results[actualPosition].get(0, i % m)) % 256;
            if (i > 0 && i % m == (m - 1)) {
                actualPosition++;
            }
        }
        return secretNumberArray;
    }

    /**
     * Prints an array of Matrixes.
     *
     * @param  array Array of Matrixes to print
     */
    public static void printMatrixes(Matrix[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i].print(2, 2);
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
