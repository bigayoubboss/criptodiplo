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

import org.cripto.utils.MathUtilFunctions;
import java.math.BigInteger;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class RabinCipher {

    /**
     * Returns an alphabetic ciphertext using Rabin Cipher.
     *
     * @param  plainText the text to encrypt
     * @param  p first prime number
     * @param  q second prime number
     * @return the cipher text string
     */
    public static String encrypt(String plainText, String p, String q) throws Exception {
        System.out.println("RabinCipher running...");

        // Verificar p q
        if(!p.matches("\\d+") || !q.matches("\\d+")){
            throw new Exception("p or q are not numbers");
        }

        // BigInteger
        BigInteger big_p = new BigInteger(p);
        BigInteger big_q = new BigInteger(q);

        // Encoding
        int[] encoded_plain = new int[plainText.length()];
        int i = 0;
        for(int actual:plainText.toCharArray()){
            encoded_plain[i] = actual;
            i++;
        }
        int[] encoded_cypherText = new int[encoded_plain.length];

        // n
        BigInteger n = big_p.multiply(big_q);

        // Encryption Algorithm
        String secret = "";
        for(i = 0;i<encoded_plain.length;i++){
            BigInteger number = new BigInteger(""+encoded_plain[i]+"");
            number = (number.multiply(number)).mod(n);
            encoded_cypherText[i] = Integer.parseInt(number.toString());
            if(Integer.toHexString(encoded_cypherText[i]).length()<2){
                secret += "0"+Integer.toHexString(encoded_cypherText[i]);
            }else{
                secret += Integer.toHexString(encoded_cypherText[i]);
            }
            
        }
        return secret;
    }

    /**
     * Returns an array of possible plain texts by decrypting the Rabin Cipher
     *
     * @param  cipherText the text to decrypt
     * @param  p first prime number
     * @param  q second prime number
     * @return array of possible decrypted texts
     */
    public static String[] cryptoAnalysis(String cipherText, String p, String q) throws Exception {

        // Verificar p q
        if(!p.matches("\\d+") || !q.matches("\\d+")){
            throw new Exception("p or q are not numbers");
        }

        // BigDecimal
        BigInteger big_p = new BigInteger(p);
        BigInteger big_q = new BigInteger(q);

        // n = p*q
        BigInteger n = big_p.multiply(big_q);

        // where the result is going to be stored
        String[] results = new String[cipherText.length()/2];

        // encodes CipherText
        int[] encoded_cipherText =  new int[cipherText.length()/2];
        int k = 0;
        for(int j=0;j<encoded_cipherText.length;j++){
            encoded_cipherText[j] = Integer.parseInt(cipherText.substring(k, k+2), 16);
            k = k+2;
            System.out.println("encoded en es "+encoded_cipherText[j]);
        }
        System.out.println("encoded length: " + encoded_cipherText.length);
        System.out.println("cipher length: " + cipherText.length());

        // calculate yp & yq
        BigInteger[] yp = MathUtilFunctions.extendedEuclidean(big_p, big_q);
        BigInteger[] yq = MathUtilFunctions.extendedEuclidean(big_q, big_p);

        // exponents for p & q
        BigInteger p_exp = (big_p.add(BigInteger.ONE)).divide(new BigInteger("4"));
        BigInteger q_exp = (big_q.add(BigInteger.ONE)).divide(new BigInteger("4"));

        System.out.println("p: " + p);
        System.out.println("q: " + q);

        System.out.println("p_exp: " + p_exp);
        System.out.println("q_exp: " + q_exp);

        int r[][] = new int[encoded_cipherText.length][4];
        System.out.println("Encoded Cypher text length: " + encoded_cipherText.length);

        for(int l = 0; l < encoded_cipherText.length; l++){

            System.out.println("enc: " + encoded_cipherText[l]);

            String s = Integer.toString(encoded_cipherText[l]);
            BigInteger big_s = new BigInteger(s);

            BigInteger mp = big_s.modPow(p_exp, big_p);
            BigInteger mq = big_s.modPow(q_exp, big_q);

            System.out.println("Mp: " + mp);
            System.out.println("Mq: " + mq);

            BigInteger a = (yp[1].multiply(big_p)).multiply(mq);
            BigInteger b = (yq[1].multiply(big_q)).multiply(mp);

            r[l][0] = ((a.add(b)).mod(n)).intValue();

            b = b.negate();

            r[l][1] = ((a.add(b)).mod(n)).intValue();

            b = b.negate();
            a = a.negate();

            r[l][2] = ((a.add(b)).mod(n)).intValue();
            
            b = b.negate();

            r[l][3] = ((a.add(b)).mod(n)).intValue();

            /*for(int i = 0; i < 4; i++){
                //if(r[l][i]<0) r[l][i] += n;
                if(r[l][i]>189) r[l][i] %= 189;
                System.out.println("r["+l+"]["+i+"] = " + r[l][i]);
            }*/

            String uy = "";
            for(int i:r[l]){

                    uy += (char)i+",";
            }
            results[l] = uy;

        }

        return results;
    }

}
