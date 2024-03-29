/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cripto.utils;

/**
 *
 * @author Administrador
 */
public class HexTools {

    public static int[] fromHexStringToBinArray(String hexString) {
        String binString = "";
        for (int x = 0; x < hexString.length(); x++) {
            int hexInteget = Integer.parseInt(hexString.substring(x, x + 1), 16);
            String hexBinary = Integer.toBinaryString(hexInteget);
            while (hexBinary.length() < 4) {
                hexBinary = "0".concat(hexBinary);
            }
            binString = binString.concat(hexBinary);

        }

        int[] key = new int[hexString.length() * 4];
        for (int x = 0; x < hexString.length() * 4; x++) {
            key[x] = Integer.parseInt(binString.substring(x, x + 1));
        }
        return key;
    }

    public static String fromHexStringToASCIIString(String hexString) {
        String ASCIIString = "";
        for (int x = 0; x < hexString.length(); x = x + 2) {
            int hexInteget = Integer.parseInt(hexString.substring(x, x + 2), 16);
            ASCIIString = ASCIIString.concat(String.valueOf((char) hexInteget));

        }


        return ASCIIString;
    }

    public static int[] fromBinStringToBinArray(String binString) {

        int[] key = new int[binString.length()];
        for (int x = 0; x < binString.length(); x++) {
            key[x] = Integer.parseInt(binString.substring(x, x + 1));
        }
        return key;
    }

    public static String fromASCIIStringToHexString(String asciiString) {
        String hexString = "";
        char[] charString = asciiString.toCharArray();
        for (int x = 0; x < asciiString.length(); x++) {
            hexString = hexString.concat(Integer.toHexString((int) charString[x]));
        }
        return hexString;
    }

    public static int[] fromASCIIStringToIntArray(String text) {
        int[] dbyte = new int[16];

        // check length of data
        if (text.length() > 16) {
            // System.out.println(text + " is too long, using the first 16 ASCII characters");
        }

        // does ASCII data have 16 characters?
        if (text.length() >= 16) {
            // 16 or more characters
            for (int i = 0; i < 16; i++) {
                dbyte[i] = text.codePointAt(i);
            }
        } else {
            // less than 16 characters - fill with NULLs
            for (int i = 0; i < text.length(); i++) {
                dbyte[i] = text.codePointAt(i);
            }
            for (int i = text.length(); i < 16; i++) {
                dbyte[i] = 0;
            }
        }

        return dbyte;
    }

    public static int[] fromHexaStringToHexaArray(String key) {

        int[] dbyte = new int[16];

        int j = 0;
        for (int i = 0; i < 32; i += 2) {
            dbyte[j] = Integer.valueOf(key.substring(i, i + 2), 16);
            j++;
        }
        return dbyte;
    }
}
