package org.cripto.cipher;

/**
 *
 * @author Christian Rodiguez B.
 */
public interface Cipher {

    String encode(Object oPlainText, Object oKey, Object[] params);

    String decode(String cipherText, Object oKey);
    
    Object cryptoAnalysis(String cipherText);
}
