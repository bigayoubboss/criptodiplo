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

        String keyString = "secret";
        String keyStringExpected = "tgfvjz";

        assertEquals(keyStringExpected, TabletCipher.keyCipher(keyString));

    }

    @Test
    public void testCompletePlainText() {

        String plainText = "plaintext";
        int lengthToComplete = 12;

        assertEquals(12, TabletCipher.completePlainText(plainText, lengthToComplete).length());
    }

    @Test
    public void testCipherText() {

        String plainText = "plaintextab:";
        String keyString = "secret";

        String secret = "9rf4wgx4yvky";
        assertEquals(secret, TabletCipher.encrypt(plainText, keyString));
    }

    @Test
    public void testGetCipherTable() {

        String keyString = "tgfvjz";
        System.out.println(TabletCipher.getCipherTable(keyString, ';'));
    }
}
