package org.cripto.cipher;

/**
 *
 * @author Christian Rodiguez B.
 */
public interface Cipher {

    String encode(Object objectPlainText, Object objectKey, Object[] params);

    String decode(String cipherText, Object objectKey);
}
