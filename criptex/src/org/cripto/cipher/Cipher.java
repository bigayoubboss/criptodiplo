package org.cripto.cipher;

/**
 *
 * @author Christian Rodiguez B.
 */
public interface Cipher {

    String encode(Object plainText, Object key, Object[] params);

    String decode(String cipherText, Object key);
}
