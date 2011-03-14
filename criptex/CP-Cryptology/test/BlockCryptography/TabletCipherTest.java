/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockCryptography;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christian
 */
public class TabletCipherTest {

    public TabletCipherTest() {
    }

    @Test
    public void testKeyCypher() {

        String keyString = "secret.a";
        String keyStringExpected = "tgfvjzfi";

        assertEquals(keyStringExpected, D13Cipher.keyCipher(keyString));

    }

    @Test
    public void testKeyUncypher() {

        String keyString = "tgfvjzfi";
        String keyStringExpected = "secret.a";

        assertEquals(keyStringExpected, D13Cipher.keyUncipher(keyString));

    }

    @Test
    public void testCompletePlainText() {

        String plainText = "plaintext";
        int lengthToComplete = 12;

        assertEquals(12, D13Cipher.completePlainText(plainText, lengthToComplete).length());
    }

    @Test
    public void testCipherText() {

        String plainText = "plaintext1:ñ236.";
        String keyString = "secret.a";

        String secret = "0qf4vhj6b7e0.p.g";
        assertEquals(secret, D13Cipher.encrypt(plainText, keyString));
    }

    @Test
    public void testGetCipherTable() {

        String keyString = "tgfvjz";
        System.out.println(D13Cipher.getCipherTable(keyString, ';'));
    }

     @Test
    public void testUncipherText() {

        String plainText = "0qf4vhj6b7e0.p.g";
        String keyString = "secret.a";

        String secret = "plaintext1:ñ236.";
        assertEquals(secret, D13Cipher.decrypt(plainText, keyString));
    }
}
