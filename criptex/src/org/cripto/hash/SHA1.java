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
package org.cripto.hash;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class SHA1 {

    public static String hash(String plainText) {
        String y = SHA1pad(plainText);
        long H0 = 0x67452301L & 0xFFFFFFFFL;
        long H1 = 0xEFCDAB89L & 0xFFFFFFFFL;
        long H2 = 0x98BADCFEL & 0xFFFFFFFFL;
        long H3 = 0x10325476L & 0xFFFFFFFFL;
        long H4 = 0xC3D2E1F0L & 0xFFFFFFFFL;

        float n = y.length() / 512;
        for (int i = 0; i < n; i++) {
            String Mi = y.substring(i * 512, (i + 1) * 512);
            String[] Wt = new String[80];

            for (int t = 0; t < 16; t++) {
                Wt[t] = Mi.substring(t * 32, (t + 1) * 32);
            }

            for (int t = 16; t < 80; t++) {
                long Wt3 = Long.parseLong(Wt[t - 3], 2) & 0xFFFFFFFFL;
                long Wt8 = Long.parseLong(Wt[t - 8], 2) & 0xFFFFFFFFL;
                long Wt14 = Long.parseLong(Wt[t - 14], 2) & 0xFFFFFFFFL;
                long Wt16 = Long.parseLong(Wt[t - 16], 2) & 0xFFFFFFFFL;
                long xor = (Wt3 ^ Wt8 ^ Wt14 ^ Wt16) & 0xFFFFFFFFL;
                long rot = rotl1(xor);

                Wt[t] = long2binary32(rot);
            }

            long A = H0 & 0xFFFFFFFFL;
            long B = H1 & 0xFFFFFFFFL;
            long C = H2 & 0xFFFFFFFFL;
            long D = H3 & 0xFFFFFFFFL;
            long E = H4 & 0xFFFFFFFFL;

            for (int t = 0; t < 80; t++) {
                long temp = (rotl5(A) + ft(B, C, D, t) + E + (Long.parseLong(Wt[t], 2) & 0xFFFFFFFFL) + kt(t)) % (long) Math.pow(2, 32);
                E = D & 0xFFFFFFFFL;
                D = C & 0xFFFFFFFFL;
                C = rotl30(B) & 0xFFFFFFFFL;
                B = A & 0xFFFFFFFFL;
                A = temp & 0xFFFFFFFFL;
            }
            H0 = (H0 + A) & 0xFFFFFFFFL;
            H1 = (H1 + B) & 0xFFFFFFFFL;
            H2 = (H2 + C) & 0xFFFFFFFFL;
            H3 = (H3 + D) & 0xFFFFFFFFL;
            H4 = (H4 + E) & 0xFFFFFFFFL;
        }

        String result = long2hexa8(H0).concat(long2hexa8(H1)).concat(long2hexa8(H2)).concat(long2hexa8(H3)).concat(long2hexa8(H4));
        return result;
    }

    /**
     * Extends the binary representation of x by at most one extra 512-bit block
     *
     * @param text the text to extend
     * @return the padding scheme of the text (a binary string of lenght module 512)
     */
    public static String SHA1pad(String text) {
        String y = "";

        // x is the plaintext's binary representation
        String x = string2binary(text);

        if (x.lastIndexOf(x) > (Math.pow(2, 64) - 1)) {
            x = x.substring(0, (int) Math.pow(2, 64));
        }

        // l is the binary representation of |x|, where |l|  = 64
        String l = Integer.toBinaryString(x.length());
        while (l.length() < 64) {
            l = "0".concat(l);
        }

        // y is x concatenated 1 concatenated 0^d concatenated l
        y = x.concat("1");
        while ((y.length() + 64) % 512 != 0) {
            y = y.concat("0");
        }
        y = y.concat(l);

        return y;
    }

    /**
     * Retuns an eight-length string representation of the long argument as an unsigned integer in base 16
     *
     * @param number the long to be converted
     * @return an 8 length hexa string
     */
    public static String long2hexa8(long number) {
        String hexa = Long.toHexString(number);
        while (hexa.length() < 8) {
            hexa = "0".concat(hexa);
        }
        return hexa;
    }

    /**
     * Retuns a binary representation of the String argument using ASCII
     *
     * @param text the String to be converted
     * @return binary string
     */
    public static String string2binary(String text) {
        char[] charArray = text.toCharArray();
        String completeBinary = "";
        for (int i = 0; i < charArray.length; i++) {
            String bin = Integer.toBinaryString((int) charArray[i]);
            while (bin.length() < 8) {
                bin = "0".concat(bin);
            }
            completeBinary = completeBinary.concat(bin);
        }

        return completeBinary;
    }

    /**
     * Retuns a 32-length string representation of the long argument as an unsigned integer in base 2
     *
     * @param number the long to be converted
     * @return a 32 length binary string
     */
    public static String long2binary32(long number) {
        String completeBinary = Long.toBinaryString(number);
        while (completeBinary.length() < 32) {
            completeBinary = "0".concat(completeBinary);
        }
        return completeBinary;
    }

    /**
     * Retuns a 64-length string representation of the long argument as an unsigned integer in base 2
     *
     * @param number the long to be converted
     * @return a 64 length binary string
     */
    public static String long2binary64(long number) {
        String completeBinary = Long.toBinaryString(number);
        while (completeBinary.length() < 64) {
            completeBinary = "0".concat(completeBinary);
        }
        return completeBinary;
    }

    public static long ft(long B, long C, long D, int t) {
        long result = 0;
        if (t >= 0 && t <= 19) {
            result = (B & C) | ((~B) & D);
        }
        if (t >= 20 && t <= 39) {
            result = B ^ C ^ D;
        }
        if (t >= 40 && t <= 59) {
            result = (B & C) | (B & D) | (C & D);
        }
        if (t >= 60 && t <= 79) {
            result = B ^ C ^ D;
        }
        result = result & 0xFFFFFFFFL;
        return result;
    }

    public static long kt(int t) {
        long result = 0;
        if (t >= 0 && t <= 19) {
            result = 0x5A827999;
        }
        if (t >= 20 && t <= 39) {
            result = 0x6ED9EBA1;
        }
        if (t >= 40 && t <= 59) {
            result = 0x8F1BBCDC;
        }
        if (t >= 60 && t <= 79) {
            result = 0xCA62C1D6;
        }
        result = result & 0xFFFFFFFFL;
        return result;
    }

    /**
     * Returns the value obtained by rotating binary representation of the specified long value as if it was 32 length left by 1 bit
     *
     * @param number the Long to be rotated
     * @return the left-rotated long by 1 bit
     */
    public static Long rotl1(Long number) {
        number = Long.rotateLeft(number, 1);
        String bin = long2binary64(number);
        char[] binArray = bin.toCharArray();
        binArray[63] = bin.charAt(31);
        bin = String.valueOf(binArray);
        number = Long.valueOf(bin, 2) & 0xFFFFFFFFL;
        return number;
    }

    /**
     * Returns the value obtained by rotating binary representation of the specified long value as if it was 32 length left by 5 bit
     *
     * @param number the Long to be rotated
     * @return the left-rotated long by 5 bits
     */
    public static Long rotl5(Long number) {
        number = Long.rotateLeft(number, 5);
        String bin = long2binary64(number);
        char[] binArray = bin.toCharArray();
        binArray[59] = bin.charAt(27);
        binArray[60] = bin.charAt(28);
        binArray[61] = bin.charAt(29);
        binArray[62] = bin.charAt(30);
        binArray[63] = bin.charAt(31);
        bin = String.valueOf(binArray);
        number = Long.valueOf(bin, 2) & 0xFFFFFFFFL;
        return number;
    }

    /**
     * Returns the value obtained by rotating binary representation of the specified long value as if it was 32 length left by 30 bit
     *
     * @param number the Long to be rotated
     * @return the left-rotated long by 30 bits
     */
    public static Long rotl30(Long number) {
        number = Long.rotateRight(number, 2);
        String bin = long2binary64(number);
        char[] binArray = bin.toCharArray();
        binArray[32] = bin.charAt(0);
        binArray[0] = '0';
        binArray[33] = bin.charAt(1);
        binArray[1] = '0';
        bin = String.valueOf(binArray);
        number = Long.valueOf(bin, 2) & 0xFFFFFFFFL;
        return number;
    }
}
