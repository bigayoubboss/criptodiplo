/*
 * Universidad Nacional de Colombia - Sede Bogotá
 * 
 *      David Montaño - damontanofe@unal.edu.co
 *      Laura Moreno - lvmorenoc@unal.edu.co
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
*/

package cpcommonmethods;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class MathUtilFunctions {

    public static int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }

    public static int greatestCommonDivisor(int a, int... b) {
        int result = GCD(a, b[0]);
        for (int i = 1; i < b.length; i++) {
            result = GCD(result, b[i]);
        }
        return result;
    }

    public static int multiplicativeInverse(int a, int m) {
        int[] euc = extendedEuclidean(a, m);
        int result;
        if (euc[1] < 0) {
            result = euc[1] + m;
        } else {
            result = euc[1];
        }
        return result;
    }

    public static int[] extendedEuclidean(int a, int b) {
        int[] result = {Math.round((float) a), 1, 0};
        if (b != 0) {
            int[] aux = extendedEuclidean(b, a % b);
            result[0] = aux[0];
            result[1] = aux[2];
            result[2] = Math.round((float) (aux[1] - (a / b) * aux[2]));
        }
        return result;
    }

    public static BigInteger[] extendedEuclidean(BigInteger a, BigInteger b) {
        //System.out.println("b is: " + b);
        BigInteger[] result = {a, BigInteger.ONE, BigInteger.ZERO};
        if (b.compareTo(BigInteger.ZERO) != 0) {
            BigInteger[] aux = extendedEuclidean(b, a.mod(b));
            result[0] = aux[0];
            result[1] = aux[2];
            result[2] = aux[1].subtract((a.divide(b)).multiply(aux[2]));
        }
        return result;
    }

    //Finds simultaneous solutions to a linear system of congruences
    //involving only one variable and multiple moduli.
    public static BigInteger[] solveCRT(BigInteger[] residue, BigInteger[] modulus) {
        // ONE
        BigInteger ONE = new BigInteger("1");
        //See if the number of moduli and residues match
        if (residue.length!=modulus.length) throw new IllegalArgumentException("Residues and moduli are in different amounts.");
        //See if the moduli are pairwise relatively prime
        for (int i=0; i<modulus.length-1; i++) {
            for (int j=i+1; j<modulus.length; j++) {
                if (!(modulus[i].gcd(modulus[j]).equals(ONE)))throw new IllegalArgumentException("Moduli are not pairwise relatively prime.");
            }
        }
        //Form the product of the individual moduli
        BigInteger M=new BigInteger("1");
        for (int i=0; i<modulus.length; i++)
            M=M.multiply(modulus[i]);
        //Form the solution as in the Chinese Remainder Theorem
        BigInteger solution=new BigInteger("0");
        for (int i=0;i<modulus.length; i++) {
        BigInteger Mi=M.divide(modulus[i]);
        solution=solution.add(residue[i].multiply(Mi).multiply
        (Mi.modInverse(modulus[i])));
        }
        solution=lnr(solution,M);

        //Answer must be returned as a two dimensional array.
        BigInteger[] result=new BigInteger[2];
        result[0]=solution;
        result[1]=M;
        return result;
    }

    //Computes the least nonnegative residue of b mod m, where m>0.
    public static BigInteger lnr(BigInteger b, BigInteger m) {
        BigInteger ZERO = new BigInteger("0");
        if (m.compareTo(ZERO)<=0) throw new IllegalArgumentException("Modulus must be positive.");
        BigInteger answer=b.mod(m);
        return (answer.compareTo(ZERO)<0)?answer.add(m):answer;
    }


}
