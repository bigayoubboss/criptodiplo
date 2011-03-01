/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockCryptography;

import java.util.Random;

/**
 *
 * @author Christian
 */
public class TabletCipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890.:";

    public static String encrypt(String plainText, String key) {

        String cipherKey = keyCipher(key);
        String secret = "";

        int keyLength = cipherKey.length();

        if (plainText.length() % keyLength != 0) {
            plainText = completePlainText(plainText, keyLength);
        }

        int rounds = plainText.length() / keyLength;

        for (int i = 0; i < rounds; i++) {

            int blockStart = (keyLength * i);
            int blockEnd = (keyLength * i) + keyLength;
            String block = plainText.substring(blockStart, blockEnd);

            secret = secret.concat(plainCipher(block, cipherKey));
        }
        return secret;
    }

    public static String keyCipher(String key) {

        int keyLength = key.length();
        String newKey = "";

        key = key.toLowerCase();

        for (int i = 0; i < keyLength; i++) {

            getAlphabetPosition(key.charAt(i));

            int keyIndex = (getAlphabetPosition(key.charAt(i)) + i + 1);

            newKey = newKey.concat(String.valueOf(alphabet.charAt(keyIndex)));
        }
        return newKey;
    }

    public static String completePlainText(String plainText, int keyLength) {

        Random randomGenerator = new Random();

        while (plainText.length() % keyLength != 0) {
            char c = (char) (randomGenerator.nextInt(25) + 97);
            plainText = plainText.concat(String.valueOf(c)).toLowerCase();
        }
        return plainText;
    }

    public static String plainCipher(String block, String key) {


        String secret = "";

        for (int i = 0; i < block.length(); i++) {

            int keyIndex = getAlphabetPosition(key.charAt(i));
            int plainIndex = getAlphabetPosition(block.charAt(i));
            int secretIndex = (keyIndex + plainIndex) % alphabet.length();

            String cipherChar = String.valueOf(alphabet.charAt(secretIndex));
            secret = secret.concat(cipherChar);


        }
        return secret;
    }

    public static int getAlphabetPosition(char c) {
        return alphabet.indexOf(c);
    }

    public static String getCipherTable(String key, char separator) {

        String newKey = keyCipher(key);
        String table = "";

        for (int i = 0; i < alphabet.length(); i++) {
            table = table.concat(String.valueOf(alphabet.charAt(i)).concat(String.valueOf(separator)));
            for (int j = 0; j < newKey.length(); j++) {

                int charIndex = (getAlphabetPosition(key.charAt(j)) + i) % alphabet.length();
                table = table.concat(String.valueOf(alphabet.charAt(charIndex)));
                table = table.concat(String.valueOf(separator));
            }
            table = table.concat("\n");
        }
        return table;
    }
}
