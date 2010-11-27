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
package cpcommonmethods;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class Code {

    /**
     * This method encode the text to number format in module 26
     */
    public static int[] encodeMod26(String text) {
        //System.out.println("encoding... ");

        char[] textArray = new char[text.length()];
        int[] numberArray = new int[text.length()];

        text.getChars(0, text.length(), textArray, 0);
        for (int i = 0; i < textArray.length; i++) {
            numberArray[i] = Character.getNumericValue(textArray[i]) - 10;
        }

        return numberArray;
    }

    /**
     * This method decode the number to text format in module 26
     */
    public static String decodeMod26(int[] numberArray) {
        //System.out.println("decoding...");
        char[] textArray = new char[numberArray.length];
        String text;

        for (int i = 0; i < numberArray.length; i++) {
            textArray[i] = (char) (numberArray[i] + 97);
        }

        text = String.copyValueOf(textArray);

        return text;
    }

    /**
     * @return the 189 letters defined for alphabet
     */
    public static char[] char189() {
        final char[] char189 = {' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')',
            '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '¡', '¢', '£', '¤', '¥',
            '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ',
            '¶', '·', '¸', '¹', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä',
            'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó',
            'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â',
            'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ',
            'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};
        return char189;
    }

    /**
     * This method encode the text to number format in module 189
     */
    public static int[] encodeMod189(String text) {
        //System.out.println("encoding... ");

        int[] numberArray = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            int aux = (int) text.charAt(i);
            if (aux >= 32 && aux <= 126) {
                numberArray[i] = aux - 32;
            } else if (aux >= 161 && aux <= 172) {
                numberArray[i] = aux - 66;
            } else if (aux >= 174 && aux <= 256) {
                numberArray[i] = aux - 67;
            }
        }

        return numberArray;
    }

    /**
     * This method decode the number to text format in module 189
     */
    public static String decodeMod189(int[] numberArray) {
        //System.out.println("decoding...");
        char[] textArray = new char[numberArray.length];
        String text;

        for (int i = 0; i < numberArray.length; i++) {
            textArray[i] = char189()[numberArray[i]];
        }

        text = String.copyValueOf(textArray);

        return text;
    }

    /**
     * This method remove all non-defined characters on char189()
     */
    public static String removeCharactersOutOfMod189(String text) {
        Character actual = ' ';
        for (int character = 0; character <= 31; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 127; character <= 160; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        actual = (char) 173;
        text = text.replace(actual.toString(), "");
        return text;
    }

    /**
     * this method remove all especial characteres
     */
    public static String removeCharactersOutOfMod26(String text) {
        Character actual;
        for (int character = 0; character <= 64; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 91; character <= 96; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 123; character <= 255; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        return text;
    }

    public static String removeCharactersOutOfMod16(String text) {
        Character actual;
        for (int character = 0; character < 48; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
         for (int character = 58; character <= 64; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 81; character <= 96; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 113; character <= 255; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        return text;
    }

    public static String removeCharactersOutOfDigits(String text) {
        Character actual;
        for (int character = 0; character <= 47; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 58; character <= 255; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        return text;
    }

    public static String removeCharactersOutOfHexa(String text) {
        Character actual;
        for (int character = 0; character < 48; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
         for (int character = 58; character <= 64; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 71; character <= 96; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        for (int character = 103; character <= 255; character++) {
            actual = (char) character;
            text = text.replace(actual.toString(), "");
        }
        return text;
    }
}