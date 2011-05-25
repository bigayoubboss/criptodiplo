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

import org.cripto.hash.SHA1;
import java.util.Random;

/**
 * 
 * @author lvmorenoc,carodriguezb
 */
public class D1Cipher {

	private static final String alphabet = "abcdefghijklmnñopqrstuvwxyz1234567890.:";

	public static String encrypt(String plainText, String key) {

		String cipherKey = keyCipher(key);
		String secret = "";

		int keyLength = cipherKey.length();

		if (plainText.length() % keyLength != 0) {
			plainText = completePlainText(plainText, keyLength);
		}

		int rounds = plainText.length() / keyLength;

		for (int i = 0; i < rounds; i++) {

			int blockStart = (keyLength * i);
			int blockEnd = (keyLength * i) + keyLength;
			String block = plainText.substring(blockStart, blockEnd);

			secret = secret.concat(plainCipher(block, cipherKey));
		}
		return secret;
	}

	public static String decrypt(String cipherText, String key) {

		String cipherKey = keyCipher(key);
		String plain = "";

		int keyLength = cipherKey.length();

		if (cipherText.length() % keyLength != 0) {
			cipherText = completePlainText(cipherText, keyLength);
		}

		int rounds = cipherText.length() / keyLength;

		for (int i = 0; i < rounds; i++) {

			int blockStart = (keyLength * i);
			int blockEnd = (keyLength * i) + keyLength;
			String block = cipherText.substring(blockStart, blockEnd);

			plain = plain.concat(secretUncipher(block, cipherKey));
		}
		return plain;
	}

	public static String keyCipher(String key) {

		int keyLength = key.length();
		String newKey = "";

		key = key.toLowerCase();

		for (int i = 0; i < keyLength; i++) {

			getAlphabetPosition(key.charAt(i));

			int keyIndex = (getAlphabetPosition(key.charAt(i)) + i + 1)
					% alphabet.length();

			newKey = newKey.concat(String.valueOf(alphabet.charAt(keyIndex)));
		}
		return newKey;
	}

	public static String keyUncipher(String key) {

		int keyLength = key.length();
		String newKey = "";

		key = key.toLowerCase();

		for (int i = 0; i < keyLength; i++) {

			getAlphabetPosition(key.charAt(i));

			int keyIndex = (getAlphabetPosition(key.charAt(i)) - i - 1);

			if (keyIndex < 0) {
				keyIndex += alphabet.length();
			}

			newKey = newKey.concat(String.valueOf(alphabet.charAt(keyIndex)));
		}
		return newKey;
	}

	public static String completePlainText(String plainText, int keyLength) {

		Random randomGenerator = new Random();

		while (plainText.length() % keyLength != 0) {
			char c = (char) (randomGenerator.nextInt(25) + 97);
			plainText = plainText.concat(String.valueOf(c)).toLowerCase();
		}
		return plainText;
	}

	public static String plainCipher(String block, String key) {

		String secret = "";

		for (int i = 0; i < block.length(); i++) {

			int keyIndex = getAlphabetPosition(key.charAt(i));
			int plainIndex = getAlphabetPosition(block.charAt(i));
			int secretIndex = (keyIndex + plainIndex) % alphabet.length();

			String cipherChar = String.valueOf(alphabet.charAt(secretIndex));
			secret = secret.concat(cipherChar);

		}
		return secret;
	}

	public static String secretUncipher(String block, String key) {

		String secret = "";

		for (int i = 0; i < block.length(); i++) {

			int keyIndex = getAlphabetPosition(key.charAt(i));
			int secretIndex = getAlphabetPosition(block.charAt(i));
			int plainIndex = secretIndex - keyIndex;

			if (plainIndex < 0) {
				plainIndex += alphabet.length();
			}

			String cipherChar = String.valueOf(alphabet.charAt(plainIndex));
			secret = secret.concat(cipherChar);

		}
		return secret;
	}

	public static int getAlphabetPosition(char c) {
		return alphabet.indexOf(c);
	}

	public static String getCipherTable(String key, char separator) {

		String newKey = keyCipher(key);
		String table = "";

		for (int i = 0; i < alphabet.length(); i++) {
			table = table.concat(String.valueOf(alphabet.charAt(i)).concat(
					String.valueOf(separator)));
			for (int j = 0; j < newKey.length(); j++) {

				int charIndex = (getAlphabetPosition(key.charAt(j)) + i)
						% alphabet.length();
				table = table
						.concat(String.valueOf(alphabet.charAt(charIndex)));
				table = table.concat(String.valueOf(separator));
			}
			table = table.concat("\n");
		}
		return table;
	}

	public static String getHiddingSequence(String secret) {

		String sha = SHA1.hash(secret);

		int isComplete = 0;
		String sequence = "";

		do {

			int current = Integer.parseInt(
					String.valueOf(sha.charAt(isComplete)), 16);
			if (current < 9 && current > 0) {
				sequence = sequence.concat(String.valueOf(sha
						.charAt(isComplete)));
			}

			if (sequence.length() == 8) {
				isComplete = -1;
			} else {
				isComplete++;
			}

		} while (isComplete != -1);

		return String.valueOf(sequence);
	}
}
