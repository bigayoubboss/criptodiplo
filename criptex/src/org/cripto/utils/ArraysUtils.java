/*
 * Universidad Nacional de Colombia - Sede Bogotá *
 *
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
*/

package org.cripto.utils;

/**
 *
 * @author carodriguezb
 */
public class ArraysUtils {

    public static int[][] array1DTo2D(int[] array1D) {
        int m = (int) Math.sqrt(array1D.length);
        int[][] array2D = new int[m][m];
        for (int x = 0; x < array1D.length; x++) {
            int fila = (x / m);
            int columna = x % m;
            array2D[fila][columna] = array1D[x];
        }
        return array2D;
    }

    public static int[] array2DTo1D(int[][] array2D) {
        int[] array1D = new int[array2D[0].length * array2D.length];
        for (int x = 0; x < array2D.length; x++) {
            for (int y = 0; y < array2D[0].length; y++) {
                array1D[(x * array2D.length) + y] = array2D[x][y];
            }
        }
        return array1D;
    }

    public static double[][] matrixModule(double[][] matrix, int module) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[x][y] = matrix[x][y] % 256;
            }
        }
        return matrix;
    }
}
