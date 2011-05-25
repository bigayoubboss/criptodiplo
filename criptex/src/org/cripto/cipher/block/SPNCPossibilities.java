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

package org.cripto.cipher.block;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class SPNCPossibilities {

    private String key = "";
    private String plainText = "";

    public SPNCPossibilities(String key, String plaintext) {
        this.key = key;
        this.plainText = plaintext;
    }

    public SPNCPossibilities() {
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the plainText
     */
    public String getPlainText() {
        return plainText;
    }
}
