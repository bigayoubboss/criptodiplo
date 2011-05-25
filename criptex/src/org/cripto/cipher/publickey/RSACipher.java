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
package org.cripto.cipher.publickey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class RSACipher {

    /**
     * e(plainText) = (plainText^e) mod n
     *
     * @param sn string that represents the big integer n and is part of the public key.
     * @param se string that represents the big integer e and is part of the public key.
     * @param plainText the ASCII text to be encrypted
     * @return The digit-cipher text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String encrypt(String sn, String se, String plainText) throws Exception {
        BigInteger n = new BigInteger(sn);
        if (n.compareTo(new BigInteger("255")) == -1 || n.compareTo(new BigInteger("255")) == 0) {
            throw new Exception("n debe ser mayor que 255.");
        }
        BigInteger e = new BigInteger(se);
        validateEandN(e, n);

        String result = generalEncrypt(n, e, plainText);
        return result;
    }

    /**
     * n = p * q;
     * totient(n) = (p - 1) * (q - 1)
     * 0 < e < totient(n)
     * gcd(e, totient(n)) = 1
     *
     * @param sp string that represents the big integer p and is coprime to q; p is part of the private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the private key.
     * @param se string that represents the big integer e such that 0 < e < totient(n) and is coprime to totient(n)
     * @param plainText the ASCII text to be encrypted
     * @return The digit-cipher text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String encrypt(String sp, String sq, String se, String plainText) throws Exception {
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        if (p.compareTo(q) == 0) {
            throw new Exception("p y q deben ser diferentes.");
        }
        
        BigInteger n = calculateN(p, q);
        if (n.compareTo(new BigInteger("255")) == -1 || n.compareTo(new BigInteger("255")) == 0) {
            throw new Exception("n debe ser mayor que 255.");
        }
        BigInteger tn = totientN(p, q);
        BigInteger e = new BigInteger(se);
        validateE(e, tn);

        String result = generalEncrypt(n, e, plainText);
        return result;
    }

    /**
     * Method used only by Bellare-Rogaway cipher, be careful when using it
     * e(plainText) = (plainText^b) mod n
     *
     * @param sn string that represents the big integer n and is part of the public key.
     * @param sb string that represents the big integer b and is part of the public key.
     * @param plainText the module-26 text to be encrypted
     * @return The cipher text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String encryptBR(BigInteger n, BigInteger b, String plainText) throws Exception {
        if (n.toString().length() < 3) {
            throw new Exception("n debe ser de 3 o más dígitos.");
        }
        validateEandN(b, n);

        //Define the length of each block in accordance with n
        int blockLength = n.toString().length() - 2; // Each bock contains 2 digits less than n
        if (blockLength == 0) {
            blockLength = 1;
        }

        //Calculate the number of blocks to encryptBR
        int blockSize = (int) Math.ceil((double) plainText.length() / (double) blockLength);

        int firstIndex;
        int lastIndex;

        String cipherText = "";

        for (int i = 0; i < blockSize; i++) {

            firstIndex = i * blockLength;
            lastIndex = ((i + 1) * blockLength > plainText.length()) ? plainText.length() : (i + 1) * blockLength;

            //Define new block
            String plainBlock = plainText.substring(firstIndex, lastIndex);

            if (i == blockSize - 1) { // if it is the last one
                int count0 = 0;
                // Establish the number of 0s at block beginning
                while (plainBlock.charAt(0) == '0') {
                    count0++;
                    plainBlock = plainBlock.substring(1, plainBlock.length());
                }
                // Add the number of 0s at block ending
                plainBlock = plainBlock.concat(String.valueOf(count0));
            }

            BigInteger numberPlainBlock = new BigInteger(plainBlock);

            //Encrypt the block
            String cipherBlock = completeBlock(numberPlainBlock.modPow(b, n).toString(), n.toString().length());

            //Add block to entire ciphertext
            cipherText = cipherText.concat(cipherBlock);
        }
        return cipherText;
    }

    private static String generalEncrypt(BigInteger n, BigInteger e, String plainText) {
        //Define the length of each block in accordance with n
        int blockLength = (int) (n.bitLength() / 8);
        if (blockLength % 8 == 0) {
            blockLength--;
        }
//        System.out.println("Block length: " + blockLength);

        //Complete plainText with spaces to obtain k*blockLength characters
        while (plainText.length() % blockLength != 0) {
            plainText = plainText.concat(" ");
        }
//        System.out.println("plainText length: " + plainText.length());

        //Calculate the number of blocks to encrypt
        int blockSize = plainText.length() / blockLength;

        String cipherText = "";

        for (int i = 0; i < blockSize; i++) {
            //Define new block
            String plainBlock = plainText.substring(i * blockLength, (i + 1) * blockLength);
//            System.out.print("b" + i + ": " + plainBlock);

            //Encode the block
            String binPlainBlock = "";
            for (int j = 0; j < blockLength; j++) {
                String binChar = Integer.toBinaryString((int) plainBlock.charAt(j));
                binChar = completeBlock(binChar, 8);
                binPlainBlock = binPlainBlock.concat(binChar);
            }
//            System.out.print(" binblock: " + binPlainBlock);
            BigInteger decPlainBlock = new BigInteger(binPlainBlock, 2);
//            System.out.println(" decBlock: " + decPlainBlock);

            //Encrypt the block
            String cipherBlock = completeBlock(decPlainBlock.modPow(e, n).toString(), n.toString().length());
//            System.out.println(" cipherBlock: " + cipherBlock);

            //Add block to entire ciphertext
            cipherText = cipherText.concat(cipherBlock);
        }
//        System.out.println("cipherText: " + cipherText);
        return cipherText;
    }

    /**
     * d(cipherText) = (plainText^d) mod n
     *
     * @param sd string that represents the big integer d and is the multiplicative inverse of e module totient(n)
     * @param sn string that represents the big integer n and is part of the public key.
     * @param cipherText the digit-based text to be decrypted
     * @return The ASCII-plain text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String decrypt(String sd, String sn, String cipherText) throws Exception {
        BigInteger d = new BigInteger(sd);
        BigInteger n = new BigInteger(sn);
        if (n.compareTo(new BigInteger("255")) == -1 || n.compareTo(new BigInteger("255")) == 0) {
            throw new Exception("n debe ser mayor que 255.");
        }
        validateEandN(d, n);

        String result = generalDecrypt(n, d, cipherText);
        return result;
    }

    /**
     * n = p * q;
     * totient(n) = (p - 1) * (q - 1)
     * ed = 1 % totient(n)
     * 
     * @param sp string that represents the big integer p and is coprime to q; p is part of the private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the private key.
     * @param sd string that represents the big integer d such ed = 1 % totient(n).
     * @param cipherText the digit-based text to be decrypted
     * @return The ASCII-plain text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String decrypt(String sp, String sq, String sd, String cipherText) throws Exception {
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        if (p.compareTo(q) == 0) {
            throw new Exception("p y q deben ser diferentes.");
        }

        BigInteger n = calculateN(p, q);
        if (n.compareTo(new BigInteger("255")) == -1 || n.compareTo(new BigInteger("255")) == 0) {
            throw new Exception("n debe ser mayor que 255.");
        }

        BigInteger tn = totientN(p, q);
        BigInteger d = new BigInteger(sd);
        BigInteger e = d.modInverse(tn);
        validateE(e, tn);

        String result = generalDecrypt(n, d, cipherText);
        return result;
    }

    /**
     * Method used by Bellare-Rogaway cipher, be careful when using it
     * d(cipherText) = (plainText^a) mod n
     *
     * @param sa string that represents the big integer a and is the multiplicative inverse of b module totient(n)
     * @param sn string that represents the big integer n and is part of the public key.
     * @param cipherText the digit-based text to be decrypted
     * @return The plain text
     * @throws java.lang.Exception if some parameter does not fulfill the requirements
     */
    public static String decryptBR(BigInteger n, BigInteger a, String cipherText) throws Exception {
        if (n.toString().length() < 3) {
            throw new Exception("n debe ser de 3 o más dígitos.");
        }

        //Define the length of each block in accordance with n
        int blockLength = n.toString().length();
        if (blockLength == 0) {
            blockLength = 1;
        }

        //Define the number of blocks to decrypt
        int blockSize = (int) Math.ceil((double) cipherText.length() / (double) blockLength);

        String plainText = "";

        int firstIndex;
        int lastIndex;

        for (int i = 0; i < blockSize; i++) {
            firstIndex = i * blockLength;
            lastIndex = ((i + 1) * blockLength > cipherText.length()) ? cipherText.length() : (i + 1) * blockLength;

            //Define new block
            String numberBlockText = cipherText.substring(firstIndex, lastIndex);

            BigInteger numberBlock = new BigInteger(numberBlockText);

            //Decrypt the block
            BigInteger plainBlock = numberBlock.modPow(a, n);

            String plainBlockText = "";
            if (i != blockSize - 1) { //if block is not the last one, complete it with 0s
                plainBlockText = completeBlock(plainBlock.toString(), blockLength - 2);
            } else { //if block is the last one
                plainBlockText = plainBlock.toString();
                // look for the number of 0s
                int count0 = Integer.valueOf(plainBlockText.substring(plainBlockText.length() - 1, plainBlockText.length()));
                // Take it off that number
                plainBlockText = plainBlockText.substring(0, plainBlockText.length() - 1);
                // add the necessary number of 0s
                while (count0 > 0) {
                    plainBlockText = "0".concat(plainBlockText);
                    count0--;
                }
            }

            plainText = plainText.concat(plainBlockText);
        }
        return plainText;
    }

    private static String generalDecrypt(BigInteger n, BigInteger d, String cipherText) throws NumberFormatException {
        //Define the length of each block in accordance with n
        int blockLength = n.toString().length();

        //Define the number of blocks to decrypt
        int blockSize = (int) Math.ceil((double) cipherText.length() / (double) blockLength);

        String plainText = "";

        int firstIndex;
        int lastIndex;

        for (int i = 0; i < blockSize; i++) {
            firstIndex = i * blockLength;
            lastIndex = ((i + 1) * blockLength > cipherText.length()) ? cipherText.length() : (i + 1) * blockLength;

            //Define new block
            String numberBlockText = cipherText.substring(firstIndex, lastIndex);
//            System.out.print("b" + i + ": " + numberBlockText);

            BigInteger numberBlock = new BigInteger(numberBlockText);

            //Decrypt the block
            BigInteger decPlainBlock = numberBlock.modPow(d, n);
//            System.out.print(" plainBlock: " + decPlainBlock);

            String binPlainBlock = decPlainBlock.toString(2);
//            System.out.println(" binPlainBlock: " + binPlainBlock);

            //Decode the decrypted block
            String plainBlock = "";
            for (int j = binPlainBlock.length(); j > 0; j -= 8) {
                int fi = (j - 8 < 0) ? 0 : j - 8;
//                System.out.println("bin: " + binPlainBlock.substring(fi, j));
                int intChar = Integer.valueOf(binPlainBlock.substring(fi, j), 2);
                char c = (char) intChar;
                plainBlock = Character.toString(c).concat(plainBlock);
            }
            plainText = plainText.concat(plainBlock);
        }
//        System.out.println("plainText: " +  plainText);
        return plainText;
    }

    /**
     * p, q primes
     * n = p * q;
     * totient(n) = (p - 1) * (q - 1)
     * ed = 1 % totient(n)
     *
     * @return A string array composed by p, q, n, e and d
     */
    public static String[] generateKeys() {
        Random rnd = new SecureRandom();
        int size = rnd.nextInt(96) + 4;

        BigInteger n = new BigInteger("255");
        BigInteger p = BigInteger.probablePrime(size, new SecureRandom());
        BigInteger q = BigInteger.probablePrime(size, new SecureRandom());

        while (n.compareTo(new BigInteger("256")) == -1 || p.compareTo(q) == 0){
            q = q.nextProbablePrime();
            //Calculate n = pq
            n = p.multiply(q);
        }

        //Calculate totient(n)
        BigInteger tn = totientN(p, q);

        //New big integer e
        BigInteger e = new BigInteger(size, new SecureRandom());
        //Find e such that 0 < e < totient(n) and gcd(b,totient(n))=1
        while (!(areCoprimes(e, tn) && (e.compareTo(new BigInteger("0")) == 1) && (e.compareTo(tn) == -1))) {
            e = new BigInteger(size, new Random());
        }

        //Calculate d = e^-1 % totient(n)
        BigInteger d = e.modInverse(tn);

        String[] result = {p.toString(), q.toString(), n.toString(), e.toString(), d.toString()};
        return result;
    }

    /**
     * n = p * q;
     *
     * @param sp string that represents the big integer p and is coprime to q; p is part of the private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the private key.
     * @return a string representation of n.
     * @throws java.lang.Exception if p y q are not primes.
     */
    public static String calculateN(String sp, String sq) throws Exception {
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        BigInteger n = calculateN(p, q);
        return n.toString();
    }

    public static BigInteger calculateN(BigInteger p, BigInteger q) throws Exception {
        if (!p.isProbablePrime(1)) {
            throw (new Exception("p debe ser primo"));
        }
        if (!q.isProbablePrime(1)) {
            throw (new Exception("p debe ser primo"));
        }
        BigInteger n = p.multiply(q);
        return n;
    }

    /**
     * totient(n) = (p - 1) * (q - 1)
     * x*x^-1 = 1 % totient(n)
     *
     * @param sx string that represents the big integer x.
     * @param sp string that represents the big integer p and is coprime to q; p is part of the private key.
     * @param sq string that represents the big integer q and is coprime to p; q is part of the private key.
     * @return a string representation of x^-1.
     * @throws java.lang.Exception if p y q are not primes.
     */
    public static String calculateInverse(String sx, String sp, String sq) throws Exception {
        BigInteger x = new BigInteger(sx);
        BigInteger p = new BigInteger(sp);
        BigInteger q = new BigInteger(sq);
        calculateN(p, q);
        BigInteger tn = totientN(p, q);
        if (tn.gcd(x).compareTo(BigInteger.ONE) != 0){
            throw new Exception("e y d deben ser primos relativos con totient(n).");
        }
        return x.modInverse(tn).toString();
    }

    private static String completeBlock(String block, int blockLength) {
        while (block.length() != blockLength) {
            block = "0".concat(block);
        }
        return block;
    }

    public static BigInteger totientN(BigInteger p, BigInteger q) {
        BigInteger pminus1 = p.subtract(new BigInteger("1"));
        BigInteger qminus1 = q.subtract(new BigInteger("1"));
        return pminus1.multiply(qminus1);
    }

    private static boolean areCoprimes(BigInteger bi1, BigInteger bi2) {
        return (bi1.gcd(bi2).equals(new BigInteger("1"))) ? true : false;
    }

    public static void validateE(BigInteger b, BigInteger tn) throws Exception {
        if (!(b.compareTo(new BigInteger("0")) == 1 && b.compareTo(tn) == -1)) {
            throw new Exception("e debe estar en el rango (0, totient(n))");
        }
        if (!areCoprimes(b, tn)) {
            throw new Exception("e y totient(n) deben ser primos relativos.");
        }
    }

    private static void validateEandN(BigInteger e, BigInteger n) throws Exception {
        if (!(e.compareTo(new BigInteger("0")) == 1 && e.compareTo(n) == -1)) {
            throw new Exception("e y d deben estar en el rango (0, n)");
        }
    }
}