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
package org.cripto.systems.block;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class AESCipher {

    // S-Box substitution table
    static int[] S_BOX = new int[]{
        0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
        0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
        0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0,
        0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
        0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc,
        0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
        0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a,
        0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
        0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0,
        0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
        0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b,
        0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
        0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85,
        0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
        0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5,
        0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
        0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17,
        0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
        0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88,
        0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
        0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c,
        0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
        0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9,
        0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
        0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6,
        0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
        0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e,
        0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
        0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94,
        0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
        0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68,
        0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16};
    //the inverse of  S-Box substitution table
    static int[] invS_BOX = new int[]{
        0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb, 0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb, 0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e, 0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25, 0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92, 0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84, 0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06, 0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b, 0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73, 0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e, 0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b, 0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4, 0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f, 0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef, 0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61, 0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
    };
    static final int I00 = 0;
    static final int I01 = 1;
    static final int I02 = 2;
    static final int I03 = 3;
    static final int I10 = 4;
    static final int I11 = 5;
    static final int I12 = 6;
    static final int I13 = 7;
    static final int I20 = 8;
    static final int I21 = 9;
    static final int I22 = 10;
    static final int I23 = 11;
    static final int I30 = 12;
    static final int I31 = 13;
    static final int I32 = 14;
    static final int I33 = 15;

    /**
     * Transforms a String into an array of his ASCII equivalent
     *
     * @param text String cypherText to transform
     * @return An array of integers which represent the ASCII of the String
     */
    private static int[] toASCIIArray(String text) {
        int[] dbyte = new int[16];

        // check length of data
        if (text.length() > 16) {
            // System.out.println(text + " is too long, using the first 16 ASCII characters");
        }

        // does ASCII data have 16 characters?
        if (text.length() >= 16) {
            // 16 or more characters
            for (int i = 0; i < 16; i++) {
                dbyte[i] = text.codePointAt(i);
            }
        } else {
            // less than 16 characters - fill with NULLs
            for (int i = 0; i < text.length(); i++) {
                dbyte[i] = text.codePointAt(i);
            }
            for (int i = text.length(); i < 16; i++) {
                dbyte[i] = 0;
            }
        }

        return dbyte;
    }

    /**
     * Transforms a String into an array of his ASCII equivalent
     *
     * @param text String cypherText to transform
     * @return An array of integers which represent the ASCII of the String
     */
    private static int[] toHexaArray(String hexaText) {
        int[] dbyte = new int[16];

        // check length of data
        if (hexaText.length() > 32) {
            // System.out.println(hexaText + " is too long, using the first 32 hexa characters");
        }

        // less than 32 characters - fill with NULLs
        while (hexaText.length() < 32) {
            hexaText = hexaText.concat("0");
        }
        int j = 0;
        for (int i = 0; i < 32; i += 2) {
            dbyte[j] = Integer.valueOf(hexaText.substring(i, i + 2), 16);
            j++;
        }

        return dbyte;
    }

    /**
     * AES key expansion consists of several primitive operations:
     * <p>1. Rotate - takes a 4-byte word and rotates everything one byte to the left, e.g. rotate([1,2,3,4]) → [2, 3, 4, 1]</p>
     * <p>2. SubBytes - each byte of a word is substituted with the value in the S-Box whose index is the value of the original byte</p>
     * <p>3. Rcon - the first byte of a word is XORed with the round constant. Each value of the Rcon table is a member of the Rinjdael finite field.</p>

     * @param key_byte is the 128 bits key represented as an array
     * @return the array of words
     */
    private static int[] keyExpansion(int[] key_byte) {
        int[] temp = new int[4];
        int i, j;
        int[] w = new int[176];

        // copy initial key information
        for (i = 0; i < 16; i++) {
            w[i] = key_byte[i];
        }

        // generate rest of key schedule using 32-bit words
        i = 4;
        while (i < 44) {		// blocksize * ( rounds + 1 )
            // copy word W[i-1] to temp
            for (j = 0; j < 4; j++) {
                temp[j] = w[(i - 1) * 4 + j];
            }

            if (i % 4 == 0) {
                // temp = SubWord(RotWord(temp)) ^ Rcon[i/4];
                temp = rotWord(temp);
                temp = subWord(temp);
                temp[0] ^= rcon(i >>> 2);
            }

            // word = word ^ temp
            int r;
            for (j = 0; j < 4; j++) {
                r = w[(i - 4) * 4 + j];
                w[i * 4 + j] = r ^ temp[j];
            }

            i++;
        }

        return w;
    }

    /**
     * Rotates the bytes of a given word
     * @param word_array word to rotate
     * @return rotated word
     */
    private static int[] rotWord(int[] word_array) {
        return new int[]{word_array[1], word_array[2], word_array[3], word_array[0]};
    }

    /**
     * Aplies the S-box substitution to the key expansion
     * @param word_array is the received subkey
     * @return returns the updates subkey
     */
    private static int[] subWord(int[] word_array) {
        int coso, num;
        int[] returned = new int[16];
        for (int i = 0; i < 4; i++) {
            num = word_array[i];
            coso = S_BOX[num];
            returned[i] = coso;
        }

        return returned;
    }

    /**
     * AES GF(2**8) multiplication by doing a shif and add approach
     * @param a first number to multiply
     * @param b second number to multiply
     * @return multiplication result
     */
    private static int aes_mul(int a, int b) {
        int res = 0;

        while (a > 0) {
            if ((a & 1) != 0) {
                res = res ^ b;		// "add" to the result
            }

            a >>>= 1;			// shift a to get next higher-order bit
            b <<= 1;			// shift multiplier also
        }

        // now reduce it modulo x**8 + x**4 + x**3 + x + 1
        int hbit = 0x10000;		// bit to test if we need to take action
        int modulus = 0x11b00;          // modulus - XOR by this to change value
        while (hbit >= 0x100) {
            if ((res & hbit) != 0) { // if the high-order bit is set
                res ^= modulus;         // XOR with the modulus
            }
            // prepare for the next loop

            hbit >>= 1;
            modulus >>= 1;
        }
        return res;
    }

    /**
     * calculate the first item Rcon[i] = { x^(i-1), 0, 0, 0 }
     * @param exp represents the exponential
     * @return the Rcon value
     */
    private static int rcon(int exp) {
        int val = 2;
        int result = 1;

        // remember to calculate x^(exp-1)
        exp--;

        // process the exponent using normal shift and multiply
        while (exp > 0) {
            if ((exp & 1) != 0) {
                result = aes_mul(result, val);
            }
            // square the value
            val = aes_mul(val, val);
            // move to the next bit
            exp >>= 1;
        }

        return result;
    }

    /**
     * Helps using an array like a matrix assuming that the matrix is a 4 x 4
     * dimension matrix
     *
     * @param x is the row
     * @param y is the column
     * @return the position in a matrix equivalent
     */
    private static int I(int x, int y) {
        return (x * 4) + y;
    }

    /**
     * Returns a Transposed Array
     * @param message_byte array to transpose
     * @return transposed array
     */
    private static int[] transpose(int[] message_byte) {
        int row, col;
        int[] state = new int[16];

        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                state[I(row, col)] = message_byte[I(col, row)];
            }
        }

        return state;
    }

    /**
     * AES operation. Does an X-OR between the Sub-Keys and the STATE matrix
     * @param STATE is the actual state matrix
     * @param w is the key matrix
     * @param base is the begininng position in which we will save the information
     *in the state matrix
     * @return updated STATE matrix
     */
    private static int[] addRoundKey(int[] state, int[] w, int base) {
        int col;

        for (col = 0; col < 4; col++) {
            state[I(0, col)] ^= w[base + col * 4];
            state[I(1, col)] ^= w[base + col * 4 + 1];
            state[I(2, col)] ^= w[base + col * 4 + 2];
            state[I(3, col)] ^= w[base + col * 4 + 3];
        }

        return state;
    }

    /**
     * Does a substitution using the S-BOX
     * @param state array representing the STATE matrix
     * @return updated STATE matrix as an array
     */
    private static int[] subBytes(int[] state) {
        for (int i = 0; i < 16; i++) {
            state[i] = S_BOX[state[i]];
        }

        return state;
    }

    /**
     * Does a invverse substitution using the invS-BOX
     * @param state array representing the STATE matrix
     * @return updated STATE matrix as an array
     */
    private static int[] invSubBytes(int[] state) {
        for (int i = 0; i < 16; i++) {
            state[i] = invS_BOX[state[i]];
        }

        return state;
    }

    /**
     * Shifts each rows as the AES standard says
     * @param state array representing the STATE matrix
     * @return updated STATE matrix as an array
     */
    private static int[] shiftRows(int[] state) {
        int t0, t1, t2, t3;

        // top row (row 0) isn't shifted

        // next row (row 1) rotated left 1 place
        t0 = state[I10];
        t1 = state[I11];
        t2 = state[I12];
        t3 = state[I13];
        state[I10] = t1;
        state[I11] = t2;
        state[I12] = t3;
        state[I13] = t0;

        // next row (row 2) rotated left 2 places
        t0 = state[I20];
        t1 = state[I21];
        t2 = state[I22];
        t3 = state[I23];
        state[I20] = t2;
        state[I21] = t3;
        state[I22] = t0;
        state[I23] = t1;

        // bottom row (row 3) rotated left 3 places
        t0 = state[I30];
        t1 = state[I31];
        t2 = state[I32];
        t3 = state[I33];
        state[I30] = t3;
        state[I31] = t0;
        state[I32] = t1;
        state[I33] = t2;

        return state;
    }

    /**
     * the inverse operation Shift for each row as the AES standard says
     * @param state array representing the STATE matrix
     * @return updated STATE matrix as an array
     */
    private static int[] invShiftRows(int[] state) {
        int t0, t1, t2, t3;

        // top row (row 0) isn't shifted

        // next row (row 1) rotated left 1 place
        t0 = state[I10];
        t1 = state[I11];
        t2 = state[I12];
        t3 = state[I13];
        state[I10] = t3;
        state[I11] = t0;
        state[I12] = t1;
        state[I13] = t2;

        // next row (row 2) rotated left 2 places
        t0 = state[I20];
        t1 = state[I21];
        t2 = state[I22];
        t3 = state[I23];
        state[I20] = t2;
        state[I21] = t3;
        state[I22] = t0;
        state[I23] = t1;

        // bottom row (row 3) rotated left 3 places
        t0 = state[I30];
        t1 = state[I31];
        t2 = state[I32];
        t3 = state[I33];
        state[I30] = t1;
        state[I31] = t2;
        state[I32] = t3;
        state[I33] = t0;

        return state;
    }

    /**
     * AES MIXCOLUMNS step.<br />
     * The MixColumns function takes four bytes as input and outputs four bytes, where each input byte affects all four output bytes.
     *
     * @param state is the STATE matrix represented as an array
     * @return updated STATE matrix
     */
    private static int[] mixColumns(int[] state) {
        int col;
        int c0, c1, c2, c3;

        for (col = 0; col < 4; col++) {
            c0 = state[I(0, col)];
            c1 = state[I(1, col)];
            c2 = state[I(2, col)];
            c3 = state[I(3, col)];

            // do mixing, and put back into array
            state[I(0, col)] = aes_mul(2, c0) ^ aes_mul(3, c1) ^ c2 ^ c3;
            state[I(1, col)] = c0 ^ aes_mul(2, c1) ^ aes_mul(3, c2) ^ c3;
            state[I(2, col)] = c0 ^ c1 ^ aes_mul(2, c2) ^ aes_mul(3, c3);
            state[I(3, col)] = aes_mul(3, c0) ^ c1 ^ c2 ^ aes_mul(2, c3);
        }

        return state;
    }

    /**
     * AES inverse MIXCOLUMNS step
     *
     * with the matrix
     *      14 11 13  9
     *      9  14 11 13
     *      13  9 14 11
     *      11 13  9 14
     *
     * @param state is the STATE matrix represented as an array
     * @return updated STATE matrix
     */
    private static int[] invMixColumns(int[] state) {
        int col;
        int c0, c1, c2, c3;

        for (col = 0; col < 4; col++) {
            c0 = state[I(0, col)];
            c1 = state[I(1, col)];
            c2 = state[I(2, col)];
            c3 = state[I(3, col)];

            // do mixing, and put back into array
            state[I(0, col)] = aes_mul(14, c0) ^ aes_mul(11, c1) ^ aes_mul(13, c2) ^ aes_mul(9, c3);
            state[I(1, col)] = aes_mul(9, c0) ^ aes_mul(14, c1) ^ aes_mul(11, c2) ^ aes_mul(13, c3);
            state[I(2, col)] = aes_mul(13, c0) ^ aes_mul(9, c1) ^ aes_mul(14, c2) ^ aes_mul(11, c3);
            state[I(3, col)] = aes_mul(11, c0) ^ aes_mul(13, c1) ^ aes_mul(9, c2) ^ aes_mul(14, c3);
        }

        return state;
    }

    /**
     * Returns an encrypted CypherText using the Rijndael Block Cipher
     * using a key of 128 bits
     *
     *@param cypherText the String cypherText we want to cipher
     *@param key_byte the Array key used to encrypt is an array of 16 hexadecimal numbers
     *@return the resulting cipherText
     */
    public static String encrypt(String plainText, int[] key_byte) {
        // We represent the String as an array of ASCII text
        int[] message_byte = toASCIIArray(plainText);

        // We expand the Key
        int[] w = keyExpansion(key_byte);

        // Initialize STATE matrix using transpose operation
        int[] state = transpose(message_byte);

        // first call to ADD-ROUND KEY
        state = addRoundKey(state, w, 0);

        // Next 9 rounds
        for (int Nr = 1; Nr < 10; Nr++) {
            state = subBytes(state);
            state = shiftRows(state);
            state = mixColumns(state);
            // note here the spec uses 32-bit words, we are using bytes, so an extra *4
            state = addRoundKey(state, w, Nr * 4 * 4);
        }

        // last Operations
        subBytes(state);
        shiftRows(state);
        addRoundKey(state, w, 10 * 4 * 4);

        // from hexadecimal array to String
        String y = "";
        state = transpose(state);
        for (int i = 0; i < state.length; i++) {
            String actualState = Integer.toHexString(state[i]);
            y += actualState.length() == 2 ? actualState : "0".concat(actualState);
        }

        return y;
    }

    /**
     * Returns an decrypted PlainText using the Rijndael Block Cipher
     * using a key of 128 bits
     *
     *@param CypherText the String cypherText we want to cipher
     *@param key_byte the Array key used to encrypt is an array of 16 hexadecimal numbers
     *@return the resulting PlainText
     */
    public static String decrypt(String cypherText, int[] key_byte) {

        // We represent the String as an array of ASCII text
        int[] message_byte = toHexaArray(cypherText);

        // EVALUABLE POINT (use it if you want to see any matrix, do respective changes)
        //for (int i = 0; i < message_byte.length; i++) {
        //    System.out.print(Integer.toHexString(message_byte[i]) + " ");
        //    if ((i + 1) % 4 == 0) {
        //        System.out.println("");
        //    }
        //}

        // We expand the Key
        int[] w = keyExpansion(key_byte);
        w = invKey(w);

        // Initialize STATE matrix using transpose operation
        int[] state = transpose(message_byte);

        // Cero round
        state = addRoundKey(state, w, 0);
        state = invSubBytes(state);
        state = invShiftRows(state);

        // Next 9 rounds
        for (int Nr = 1; Nr < 10; Nr++) {
            // note here the spec uses 32-bit words, we are using bytes, so an extra *4
            state = addRoundKey(state, w, Nr * 4 * 4);
            state = invMixColumns(state);
            state = invSubBytes(state);
            state = invShiftRows(state);
        }

        //Final round
        addRoundKey(state, w, 10 * 4 * 4);

        state = transpose(state);

        // from hexadecimal array to String
        String y = "";
        for (int i = 0; i < state.length; i++) {
            //    System.out.print(state[i]);
            y += Character.toString((char) state[i]);
        }

        return y;
    }

    private static int[] invKey(int[] w) {
        int[] v = new int[176];
        int[] aux = new int[16];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 16; j++) {
                aux[j] = w[j + (16 * i)];
            }

            for (int j = 0; j < 16; j++) {
                v[j + (16 * (10 - i))] = aux[j];
            }
        }
        w = v;
        return w;
    }

    public static String longEncrypt(String plainText, String key) {
        // System.out.println("aesCipher running...");
        String result = "";
        int[] arrayKey = stringKeytoArray(key);
        int x = 0;
        int y = 0;

        double blocksNumber = plainText.length() / 16;
        while (x < blocksNumber) {
            result = result.concat(AESCipher.encrypt(plainText.substring(y, y + 16), arrayKey));
            x += 1;
            y = x * 16;
        }
        if (plainText.length() % 16 != 0) {
            result = result.concat(AESCipher.encrypt(plainText.substring(y), arrayKey));
        }

        return result;
    }

    public static String longDecrypt(String cypherText, String key) {
        // System.out.println("aesCipher running...");
        String result = "";
        int[] arrayKey = stringKeytoArray(key);
        int x = 0;
        int y = 0;

        double blocksNumber = cypherText.length() / 32;
        while (x < blocksNumber) {
            result = result.concat(AESCipher.decrypt(cypherText.substring(y, y + 32), arrayKey));
            x += 1;
            y = x * 32;
        }
        if (cypherText.length() % 32 != 0) {
            result = result.concat(AESCipher.decrypt(cypherText.substring(y), arrayKey));
        }

        return result;
    }

    private static int[] stringKeytoArray(String key) {
        int[] dbyte = new int[16];

        // check length of data
        if (key.length() > 32) {
            // System.out.println(key + " is too long, using the first 32 hexa characters");
        }

        // less than 32 characters - fill with NULLs
        while (key.length() < 32) {
            key = key.concat("0");
        }
        int j = 0;
        for (int i = 0; i < 32; i += 2) {
            dbyte[j] = Integer.valueOf(key.substring(i, i + 2), 16);
            j++;
        }

        return dbyte;
    }
}
