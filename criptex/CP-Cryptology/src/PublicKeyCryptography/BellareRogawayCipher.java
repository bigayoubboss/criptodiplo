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
package PublicKeyCryptography;

import HashFunctions.SHA1;
import cpcommonmethods.Code;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class BellareRogawayCipher {

    private static int k0; // k0 is a integer fixed by protocol
    private static int k1; // k1 is a integer fixed by protocol
    private static int blockLength; // blockLength is the plaintext-blocks length

    /**
     * Bellare-rogaway encryption given by
     * e(x) = f(concat{xor{x0k1, g(r)}, xor{r, h(xor{x0k1, g(r)})})
     * where
     * f is a RSA function
     * g, h are hashes
     *
     * @param plainText the module-26 text to be encrypted
     * @param sp string that represents the big integer p and is coprime to q; p is part of the RSA private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the RSA private key.
     * @param sb string that represents the big integer b and is part of RSA public key.
     * @return the digit cipher text
     * @throws java.lang.Exception
     */
    public static String encrypt(String plainText, String sp, String sq, String sb) throws Exception {
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        BigInteger n = RSACipher.calculateN(p, q);
        BigInteger tn = RSACipher.totientN(p, q);
        BigInteger b = new BigInteger(sb);
        RSACipher.validateE(b, tn);
        return encrypt(plainText, n.toString(), sb);
    }

    /**
     * Bellare-rogaway encryption given by
     * e(x) = f(concat{xor{x0k1, g(r)}, xor{r, h(xor{x0k1, g(r)})})
     * where
     * f is a RSA function
     * g, h are hashes
     *
     * @param plainText the module-26 text to be encrypted
     * @param sn string that represents the big integer n and is part of RSA public key.
     * @param sb string that represents the big integer b and is part of RSA public key.
     * @return the digit cipher text
     * @throws java.lang.Exception
     */
    public static String encrypt(String plainText, String sn, String sb) throws Exception {
        BigInteger n = new BigInteger(sn);
        String binaryPlainText = text2binary(plainText);

        setLengths(n.bitLength());
        //while (binaryPlainText.length() % blockLength != 0) {
        //binaryPlainText = binaryPlainText.concat("1");
        //}

        //Define the number of blocks
        int blockSize = (int) Math.ceil((double) binaryPlainText.length() / (double) blockLength);

        String cipherText = "";

        for (int i = 0; i < blockSize; i++) {
            int firstIndex = i * blockLength;
            int lastIndex = ((i + 1) * blockLength > binaryPlainText.length()) ? binaryPlainText.length() : (i + 1) * blockLength;

            // m is a blockLength-bit block of binary plaintext
            String m = binaryPlainText.substring(firstIndex, lastIndex);

            //m0k1 is m concatenated with k1 0s
            String m0k1 = m;
            while (m0k1.length() < blockLength + k1) {
                m0k1 = m0k1.concat("0");
            }

            // r is a random k0-bit string
            String r = "";
            Random rnd = new Random();
            for (int j = 0; j < k0; j++) {
                r = r.concat(String.valueOf(rnd.nextInt(2)));
            }

            String gr = g(r);

            String x = xor(m0k1, gr);

            String hx = h(x);

            String y = xor(r, hx);

            cipherText = cipherText.concat(x).concat(y);
        }
        BigInteger b = new BigInteger(sb);
        //Encrypt the full cipherText qith RSA
        String cipherTextRSA = RSACipher.encryptBR(n, b, new BigInteger(cipherText, 2).toString());
        return cipherTextRSA;
    }

    /**
     * Inverse process of Bellare-Rogaway encrypt
     *
     * @param plainText the module-26 text to be encrypted
     * @param sp string that represents the big integer p and is coprime to q; p is part of the RSA private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the RSA private key.
     * @param sa string that represents the big integer a and is the multiplicative inverse of b module totient(n)
     * @return the plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(String cipherText, String sp, String sq, String sa) throws Exception {
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        BigInteger n = RSACipher.calculateN(p, q);

        BigInteger tn = RSACipher.totientN(p, q);
        BigInteger a = new BigInteger(sa);
        BigInteger b = a.modInverse(tn);
        RSACipher.validateE(b, tn);
        return decrypt(cipherText, n.toString(), sa);
    }
    /**
     * Inverse process of Bellare-Rogaway encrypt
     *
     * @param plainText the module-26 text to be encrypted
     * @param sn string that represents the big integer n and is part of RSA public key.
     * @param sa string that represents the big integer a and is the multiplicative inverse of b module totient(n)
     * @return the plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(String cipherText, String sn, String sa) throws Exception {
        BigInteger n = new BigInteger(sn);
        BigInteger a = new BigInteger(sa);
        
        //Decrypt the full cipherText with RSA
        String plainTextRSA = RSACipher.decryptBR(n, a, cipherText);

        String binaryPlainTextRSA = new BigInteger(plainTextRSA).toString(2);

        //Define the complete block length
        int completeBlock = blockLength + k0 + k1;
        while (binaryPlainTextRSA.length() % completeBlock != 0) {
            binaryPlainTextRSA = "0".concat(binaryPlainTextRSA);
        }

        setLengths(n.bitLength());

        //Calculate the number of blocks
        int blockSize = (int) Math.ceil((double) binaryPlainTextRSA.length() / (double) completeBlock);

        String result = "";
        for (int i = 0; i < blockSize; i++) {
            int firstIndex = i * completeBlock;
            int lastIndex = ((i + 1) * completeBlock > binaryPlainTextRSA.length()) ? binaryPlainTextRSA.length() : (i + 1) * completeBlock;

            String blockText = binaryPlainTextRSA.substring(firstIndex, lastIndex);

            // Find x and y
            String x = blockText.substring(0, blockLength + k1);
            String y = blockText.substring(blockLength + k1, blockText.length());

            // Make reverse process to find m
            String hx = h(x);

            String r = xor(hx, y);

            String gr = g(r);

            String m0k1 = xor(x, gr);

            String m = m0k1.substring(0, blockLength);

            result = result.concat(m);
        }
        String plainText = binary2text(result);
        return plainText;
    }

    /**
     * k0 = 160
     * blockLength + k1 = 160
     */
    private static void setLengths(int n) {
        k0 = 160;
        if (n > 140) {
            blockLength = 140;
            k1 = 20;
        } else {
            blockLength = n;
            k1 = 160 - n;
        }
    }

    private static String text2binary(String plainText) {
        int[] encodedPlainText = Code.encodeMod26(plainText);
        String binary = "";
        for (int i = 0; i < encodedPlainText.length; i++) {
            binary = binary.concat(completeBinaryLetter(Integer.toBinaryString(encodedPlainText[i] + 1)));
        }
        return binary;
    }

    private static String binary2text(String binaryString) {
        int possibleLetters = (int) Math.ceil(binaryString.length() / 5);
        String text = "";
        for (int i = 0; i < possibleLetters; i++) {
            String binaryLetter = binaryString.substring(i * 5, (i + 1) * 5);
            int[] decimalLetter = {Integer.valueOf(binaryLetter, 2) - 1};
            if (decimalLetter[0] < 0 || decimalLetter[0] > 25) {
                // break;
            } else {
                text = text.concat(Code.decodeMod26(decimalLetter));
            }
        }
        return text;
    }

    /**
     * Expands the k0 bits of r to blocklength + k1 using SHA1
     */
    private static String g(String r) {
        String gr = SHA1.hash(r);
        BigInteger grint = new BigInteger(gr, 16);
        gr = grint.toString(2);
        gr = completeBinaryHash(gr);
        return gr;
    }

    /**
     * Reduces the blockLength + k1 bits to k0 bits using SHA1
     */
    private static String h(String x) {
        String hx = SHA1.hash(x);
        BigInteger hxint = new BigInteger(hx, 16);
        hx = hxint.toString(2);
        hx = completeBinaryHash(hx);
        return hx;
    }

    private static String xor(String x, String y) throws Exception {
        String result = "";
        if (x.length() != y.length()) {
            throw new Exception("Longitud diferente en las cadenas");
        }
        for (int i = 0; i < x.length(); i++) {
            if (!((x.charAt(i) != '0' || x.charAt(i) != '1') && (y.charAt(i) != '0' || y.charAt(i) != '1'))) {
                throw new NumberFormatException("x o y no son binarias");
            }
            if (x.charAt(i) == y.charAt(i)) {
                result = result.concat("0");
            } else {
                result = result.concat("1");
            }
        }
        return result;
    }

    private static String completeBinaryHash(String hash) {
        while (hash.length() < 160) {
            hash = "0".concat(hash);
        }
        return hash;
    }

    private static String completeBinaryLetter(String binaryString) {
        while (binaryString.length() < 5) {
            binaryString = "0".concat(binaryString);
        }
        return binaryString;
    }
}
