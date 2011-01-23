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
package BlockCryptography;

/**
 * 
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class DESCipher {

	private static int[][] S1 = {
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
			{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
			{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
			{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
	private static int[][] S2 = {
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
			{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
			{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
			{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
	private static int[][] S3 = {
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
			{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
			{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
			{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
	private static int[][] S4 = {
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
			{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
			{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
			{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
	private static int[][] S5 = {
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
			{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
			{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
			{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
	private static int[][] S6 = {
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
			{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
			{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
			{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
	private static int[][] S7 = {
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
			{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
			{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
			{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
	private static int[][] S8 = {
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
			{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
			{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
			{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

	public static byte[] intToByteArray(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

	public static String[][] bruteForce(String text) {
		String[][] posibilities = new String[1024][2];
		for (int x = 0; x < 1024; x++) {

			String key = Integer.toBinaryString(x);
			String complement = "";
			int len = key.length();
			for (int y = 0; y < (10 - len); y++) {
				complement = complement.concat("0");
			}
			key = complement.concat(key);
			posibilities[x][0] = key;
			posibilities[x][1] = encryptDecrypt(text, key, false);
		}
		return posibilities;

	}

	public static String encryptDecrypt(String text, String hexaKeyString,
			boolean mode) {
		// System.out.println("DES Cipher encrypt method running...");

		int keys[][] = keySchedulingAlgorithm(hexaKeyString);

		int[] secretBin = textSchedulingAlgorithm(text, keys, mode);

		String secretHex = "";
		for (int x = 0; x < 64; x++) {
			secretHex = secretHex.concat(String.valueOf(secretBin[x]));
		}
		String secret = "";
		for (int x = 0; x < 64; x = x + 4) {
			secret = secret.concat(Integer.toHexString(Integer.parseInt(
					secretHex.substring(x, x + 4), 2)));
		}

		return secret;

	}

	private static int[] textSchedulingAlgorithm(String text, int[][] keys,
			boolean mode) {

		int[] binText = cpcommonmethods.HexTools.fromHexStringToBinArray(text);

		binText = perIP(binText);

		int[][] leftText = new int[17][32];
		int[][] rightText = new int[17][32];

		for (int x = 0; x < 32; x++) {
			leftText[0][x] = binText[x];
			rightText[0][x] = binText[x + 32];
		}

		int[] rightExpanded = new int[48];

		// System.out.println("\n==================================== =ROUNDS= ===================================");
		for (int x = 1; x < 17; x++) {

			leftText[x] = rightText[x - 1];

			rightExpanded = perExp(rightText[x - 1]);

			int[] xorKeyAndRight = new int[48];
			if (mode) {
				xorKeyAndRight = XOR(keys[x], rightExpanded);
			} else {
				xorKeyAndRight = XOR(keys[17 - x], rightExpanded);
			}

			int[][] sBoxesValues = new int[8][48];

			int box = 1;

			for (int y = 0; y < 48; y = y + 6) {
				sBoxesValues[box - 1] = boxS(xorKeyAndRight[y],
						xorKeyAndRight[y + 1], xorKeyAndRight[y + 2],
						xorKeyAndRight[y + 3], xorKeyAndRight[y + 4],
						xorKeyAndRight[y + 5], box);
				box++;
			}

			int[] f = new int[32];
			for (int y = 0; y < 8; y++) {
				for (int z = 0; z < 4; z++) {
					f[(y * 4) + z] = sBoxesValues[y][z];
				}
			}

			int[] perP = perP(f);

			rightText[x] = XOR(perP, leftText[x - 1]);

		}

		// System.out.println("\n================================== =========== ==================================");
		int[] secret = new int[64];
		for (int x = 0; x < 32; x++) {
			secret[x] = rightText[16][x];
			secret[x + 32] = leftText[16][x];
		}

		int[] secretIPinv = perIPinv(secret);
		return secretIPinv;
	}

	private static int[][] keySchedulingAlgorithm(String keyString) {

		int[] keyBin = cpcommonmethods.HexTools
				.fromHexStringToBinArray(keyString);

		int[] keyPC1 = perCho1(keyBin);

		int[][] key56 = ciComputing(keyPC1);

		int[][] key48 = new int[17][48];
		for (int x = 1; x < 17; x++) {
			key48[x] = perCho2(key56[x]);
		}

		// System.out.println("========================= Keys =========================");
		/*
		 * for (int x = 0; x < 17; x++) { for (int y = 0; y < 48; y++) {
		 * 
		 * if (y % 6 == 0) { System.out.print(" "); }
		 * System.out.print(key48[x][y]); } System.out.println(""); }
		 * System.out.
		 * println("====================== =========== ======================");
		 */
		return key48;
	}

	private static int[][] ciComputing(int[] key) {
		int[][] C = new int[17][56];
		for (int y = 0; y < 56; y++) {
			C[0][y] = key[y];
		}

		for (int x = 1; x < 17; x++) {
			int[] keyRot = new int[56];
			for (int y = 0; y < 56; y++) {
				keyRot[y] = C[x - 1][y];
			}
			if (x == 1 || x == 2 || x == 9 || x == 16) {
				keyRot = leftshift1(keyRot);
			} else {
				keyRot = leftshift2(keyRot);
			}

			for (int y = 0; y < 56; y++) {
				C[x][y] = keyRot[y];
			}

		}

		return C;
	}

	private static int[] leftshift1(int[] key) {
		int temp = key[0];
		for (int x = 0; x < 28; x++) {
			key[x] = key[(x + 1)];
		}
		key[27] = temp;

		temp = key[28];
		for (int x = 28; x < 55; x++) {
			key[x] = key[(x + 1)];

		}
		key[55] = temp;
		return key;
	}

	private static int[] leftshift2(int[] key) {
		key = leftshift1(leftshift1(key));
		return key;
	}

	private static int[] perCho1(int[] key) {
		int[] perCho1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18,
				10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55,
				47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
				45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
		int[] per = new int[58];
		for (int x = 0; x < 56; x++) {
			per[x] = key[(perCho1[x] - 1)];
		}
		return per;
	}

	private static int[] perCho2(int[] key) {
		int[] perCho1 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19,
				12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30,
				40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
				32 };
		int[] per = new int[48];
		for (int x = 0; x < 48; x++) {
			per[x] = key[(perCho1[x] - 1)];
		}
		return per;
	}

	private static int[] perIP(int[] key) {
		int[] pIP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20,
				12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24,
				16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
				11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23,
				15, 7 };
		int[] per = new int[64];
		for (int x = 0; x < 64; x++) {
			per[x] = key[(pIP[x] - 1)];
		}
		return per;
	}

	private static int[] perExp(int[] key) {
		int[] perExp = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12,
				13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22,
				23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
		int[] per = new int[48];
		for (int x = 0; x < 48; x++) {
			per[x] = key[(perExp[x] - 1)];
		}
		return per;
	}

	private static int[] perP(int[] key) {
		int[] perP = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31,
				10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
		int[] per = new int[32];
		for (int x = 0; x < 32; x++) {
			per[x] = key[(perP[x] - 1)];
		}
		return per;
	}

	private static int[] perIPinv(int[] key) {
		int[] pIPinv = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23,
				63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21,
				61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19,
				59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
				57, 25 };
		int[] per = new int[64];
		for (int x = 0; x < 64; x++) {
			per[x] = key[(pIPinv[x] - 1)];
		}
		return per;
	}

	private static int[] XOR(int[] a, int[] b) {

		int[] xor = new int[a.length];
		for (int x = 0; x < a.length; x++) {
			xor[x] = a[x] ^ b[x];
		}
		return xor;
	}

	public static int[] boxS(int v1, int v2, int v3, int v4, int v5, int v6,
			int box) {

		int rowIndex = Integer.parseInt(
				String.valueOf(v1).concat(String.valueOf(v6)), 2);
		int colIndex = Integer.parseInt(
				String.valueOf(v2).concat(String.valueOf(v3))
						.concat(String.valueOf(v4).concat(String.valueOf(v5))),
				2);
		int boxValue = 0;
		switch (box) {
		case 1:
			boxValue = S1[rowIndex][colIndex];
			break;
		case 2:
			boxValue = S2[rowIndex][colIndex];
			break;
		case 3:
			boxValue = S3[rowIndex][colIndex];
			break;
		case 4:
			boxValue = S4[rowIndex][colIndex];
			break;
		case 5:
			boxValue = S5[rowIndex][colIndex];
			break;
		case 6:
			boxValue = S6[rowIndex][colIndex];
			break;
		case 7:
			boxValue = S7[rowIndex][colIndex];
			break;
		case 8:
			boxValue = S8[rowIndex][colIndex];
			break;
		}
		String binBoxValue = Integer.toBinaryString(boxValue);
		while (binBoxValue.length() < 4) {
			binBoxValue = "0".concat(binBoxValue);
		}
		int[] intBoxValue = new int[4];
		for (int x = 0; x < 4; x++) {
			intBoxValue[x] = Integer.parseInt(binBoxValue.substring(x, x + 1));
		}
		return intBoxValue;
	}
}
