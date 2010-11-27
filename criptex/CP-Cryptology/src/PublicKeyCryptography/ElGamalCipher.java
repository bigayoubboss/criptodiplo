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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class ElGamalCipher {

    static BigInteger prime = null;
    static BigInteger generator = null;
    static BigInteger random_key = null;
    static BigInteger k = null;

    /**
    * Creates KeyPair, the key for encryption and the key for decryption
    * (in development)
    */
    public static void keyGeneration() {
        // Generates Prime number
        Random rnd = new SecureRandom();
        int size = rnd.nextInt(96) + 4;
        prime = BigInteger.probablePrime(size, new SecureRandom());

        // Generates a Generator
        generator = BigInteger.ONE;

        // Generates a Random_Key
        random_key = BigInteger.ONE;
    }

    /**
     * Creates KeyPair, the key for encryption and the key for decryption
     * @param p Prime number
     * @param gen generator
     * @param random so that GCD(random, prime-1)==1
     * @throws Exception if the Strings have letters or simbols different to digits.
     */
    public static void keyGeneration(String p, String gen, String random) throws Exception {

        //Strings are digit sequence?
        if(!isDigitSequence(p) || !isDigitSequence(gen) || !isDigitSequence(random)){
            throw new Exception("Waiting for a sequence of digits, not letters...");
        }

        // Generates Prime number
        prime = new BigInteger(p);

        // is p prime? probability of 1-(0.5)^20=0.999999
        if(!prime.isProbablePrime(20)){
            throw new Exception("The Prime number is probably not prime...");
        }

        // is p > 255
        BigInteger twohundredfiftyfive = new BigInteger("255");
        if(prime.compareTo(twohundredfiftyfive)!=1){
            throw new Exception("Prime number must be bigger than 255");
        }

        // Generates a Generator
        generator = new BigInteger(gen);

        // gen is a Generator of p?
        if(generator==BigInteger.ONE || generator==prime){
            throw new Exception("The given Generator is not a TRUE Generator");
        }

        // Generates a Random_Key
        random_key = new BigInteger(random);

        // GCD(random;prime-1)==1 ?
        BigInteger GCD = random_key.gcd(prime.subtract(BigInteger.ONE));
        if(!GCD.equals(BigInteger.ONE)){
            throw new Exception("The random number must be coprime to prime-1. GCD(rand; prime-1) = " + GCD);
        }

        // Generates k
        k = generator.modPow(random_key, prime);
    }

     /**
     * Deletes KeyPair
     */
    public static void deleteKey(){
        // deletes Prime number
        prime = null;

        // deletes a Generator
        generator = null;

        // deletes a Random_Key
        random_key = null;

        // deletes k
        k = null;
    }

    /**
     * e(plainText, public_key) = (w, x)
     * w =  a ^ k mod p
     * x =  plaintext * beta^k mod p
     *
     * @param plainText string that represents the text to encrypt.
     * @return Tuple w(to clean the encrypted message) and x(where the hidden message is)
     * @throws Exception if Key hasnt been created
     */
    public static String[][] encrypt(String plainText) throws Exception {

        // Key was creatded?
        if(!isKeyReady()) throw new Exception("Key isnt ready.");

        // Prepares return String array
        String[][] retval = new String[plainText.length()][2];

        // plaintext to ascii
        int i = 0;
        for(int actual : plainText.toCharArray()){
            BigInteger big_actual = BigInteger.valueOf(actual);

            // Creates a SecureRandom
            Random rnd = new SecureRandom();
            int size = rnd.nextInt(96) + 4;
            BigInteger random_encryption = new BigInteger(size, new SecureRandom());
            System.out.println("Random for Encryption: " + random_encryption);

            // Encryption - return tuple (w,x)
            // x_prime just simplifies calculus
            BigInteger w = generator.modPow(random_encryption, prime);
            BigInteger x_prime = k.modPow(random_encryption, prime);
            BigInteger x = (big_actual.multiply(x_prime)).mod(prime);

            retval[i][0] = w.toString();
            retval[i][1] = x.toString();
            i++;
        }
        
        return retval;
    }

    /**
     * d(w, x, private_key) = plaintext
     *
     * @param w to clean the encrypted message
     * @param x where the hidden message is
     * @return The plain text
     * @throws Exception if Key hasnt been created
     */
    private static String decrypt(String w, String x) throws Exception {

        // Key was creatded?
        if(!isKeyReady()) throw new Exception("Key isnt ready.");

        //String retval = "";

        BigInteger W = new BigInteger(w);
        BigInteger X = new BigInteger(x);

        BigInteger p_prime = (prime.subtract(BigInteger.ONE)).subtract(random_key);
        //System.out.println("p_prime: " + p_prime);
        BigInteger m_prime = W.modPow(p_prime, prime);
        //System.out.println("m_prime: " + m_prime);
        BigInteger m = (m_prime.multiply(X)).mod(prime);
        
        return m.toString();
    }

     /**
     * All it does is to call decrypt for each cipher pair
     *
     * @param cipherpairs collection of ciphred pairs
     * @return The plain text
     * @throws Exception if Key hasnt been created
     */
    public static String general_decrypt(String[][] cipherpairs) throws Exception {

        String plaintext = "";
        for(int i = 0; i < cipherpairs.length; i++){
            int letter = Integer.valueOf(decrypt(cipherpairs[i][0], cipherpairs[i][1]));
            plaintext += (char)letter;
        }
        return plaintext;
    }

    private static boolean isDigitSequence(String p) {
        if(p.matches("^\\d+$")){
            return true;
        }else{
            return false;
        }
    }

    private static boolean isKeyReady() {
        if(prime==null || generator==null || random_key==null || k==null){
            return false;
        }else{
            return true;
        }
    }

    public static String key(){
        return "Prime: " + prime + "\nGenerator: " + generator + "\nRandom: " + random_key + "\nBeta: " + k;
    }
}
