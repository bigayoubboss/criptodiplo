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

package Mac;

import BlockCryptography.AESCipher;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class CBCMac {

     /**
     * Returns an alphabetic ciphertext using CBC-MAC.
     *
     * @param  plainText the text to encrypt.
     * @param  key String of Integers
     * @return the cipher text String
     */
    public static String encrypt(String plainText, int[] key){

       /* // t is the length in bits of each sub x (8 bits = 255 in decimal)
        //int t = 8;

        // n is the quantity of characters in the plainText
        int n = plainText.length();
        System.out.println("n is: " + n);
        
        // recovers an ASCII representation of the plainText
        int[] ascii_plain_text = toASCIIArray(plainText);

        // Initial vector of lenght t, in this case t = 8 bits
        int[] y = new int[n+1];
        System.out.println("y[0] is " + y[0]);

        // there has to be an ASCII character here
        int x = 0;

        for(int i = 1; i < n; i++){
            // next sub x
            x = ascii_plain_text[i-1];

            // y(i-1) xor x(i)
            int xor = y[i-1] ^ x;
            System.out.println("y(i-1): "+ y[i-1]);
            // we need to transform this int into a String
            String xor_string = Character.toString((char) xor);

            // we recover a new String
            xor_string = AESCipher.encrypt(xor_string, key);

            // we update y
            y[i-1] = 0;
            int num = 0;
            for(int j = 0; j < xor_string.length(); j++){
                num = xor_string.codePointAt(j)<<8*(xor_string.length()-j);
                y[i-1] = xor_string.codePointAt(j);
            }
            y[i] = y[i-1];
        }

        // we have in y the result of the encription
        // we need to transform the succession of y's into a String representation
        String result = "";
        for(int i = 1; i < y.length; i++){
            System.out.println("y in " + i + " is " + y[i]);
            System.out.println((char)y[i]);
            result += Character.toString((char)y[i]);
        }
        System.out.println("Result: "+ result);
        System.out.println("End of Result");
        return result;*/
        return null;

    }

     /**
     * Transforms a String into an array of his ASCII equivalent
     *
     * @param text String cypherText to transform
     * @return An array of integers which represent the ASCII of the String
     */
    private static int[] toASCIIArray(String text) {
        int[] dbyte = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            dbyte[i] = text.codePointAt(i);
        }
        
        return dbyte;
    }

}
