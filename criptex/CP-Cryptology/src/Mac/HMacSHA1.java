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

import HashFunctions.SHA1;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class HMacSHA1 {

    /**
     * Returns an alphabetic ciphertext using HMAC-Sha1.
     *
     * @param  plainText the text to be authenticated.
     * @param  fkey Hexa string of 128 digits (512 bits).
     * @return the hmac string
     */
    public static String hash(String plainText, String key) throws Exception{
        if (key.length() != 128){
            throw new Exception("La clave no cumple con los 128 carácteres hexadecimales");
        }
        String ipad = "";
        String opad = "";

        for (int i = 0; i < 64; i++) {
            ipad = ipad.concat("36");
            opad = opad.concat("5c");
        }

        String[] xors = padsXORkey(ipad, opad, key);

        String sha1ipadXORkey = SHA1.hash(xors[0]);
        String all = xors[1].concat(sha1ipadXORkey).concat(plainText);

        return SHA1.hash(all);
    }

    /**
     * Retuns the binary representation of an hexa string
     *
     * @param hexa the hexa string to be converted
     * @return binary string
     */
    public static String hexa2binary(String hexa) throws NumberFormatException{
        String completeBinary = "";
        for (int i = 0; i < hexa.length(); i++) {
            String bin = Integer.toBinaryString(Integer.parseInt(hexa.substring(i, i + 1), 16));
            while (bin.length() < 4) {
                bin = "0".concat(bin);
            }
            completeBinary = completeBinary.concat(bin);
        }
        return completeBinary;
    }

    /**
     * Retuns the hexa representation of a binary string
     *
     * @param binary the binary string to be converted
     * @return hexa string
     */
    public static String binary2hexa(String binary) throws NumberFormatException{
        String completeBinary = "";
        for (int i = 0; i < binary.length(); i += 4) {
            String bin = Integer.toHexString(Integer.parseInt(binary.substring(i, i + 4), 2));
            completeBinary = completeBinary.concat(bin);
        }
        return completeBinary;
    }

    /**
     * Returns an array of two hexa strings:
     *         1. ipad^key
     *         2. opad^key
     * @param ipad 128 length hexa string
     * @param opad 128 length hexa string
     * @param key 128 length hexa string
     * @return the result of making xor between the pads ang the key
     */
    public static String[] padsXORkey(String ipad, String opad, String key) throws NumberFormatException{
        String result[] = {"", ""};
        ipad = hexa2binary(ipad);
        opad = hexa2binary(opad);
        key = hexa2binary(key);

        for (int i = 0; i < key.length(); i++) {
            if (ipad.charAt(i) == key.charAt(i)) {
                result[0] = result[0].concat("0");
            } else {
                result[0] = result[0].concat("1");
            }
            if (opad.charAt(i) == key.charAt(i)) {
                result[1] = result[1].concat("0");
            } else {
                result[1] = result[1].concat("1");
            }
        }

        result[0] = binary2hexa(result[0]);
        result[1] = binary2hexa(result[1]);
        return result;
    }
}
