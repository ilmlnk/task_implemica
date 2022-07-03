package org.example.maintask;

import java.math.BigInteger;


/*
*
* @author Illia Melnyk
* */
public class Factorial {
    private static final BigInteger ten = new BigInteger("10");                 // initialized divider in method summarizeDigits
    public static BigInteger calculateFactorial(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) < 1) return BigInteger.ONE;               // based condition
        return num.multiply(calculateFactorial(num.subtract(BigInteger.ONE)));      // return calculated factorial using recursive call
    }

    public static int summarizeDigits(BigInteger num) {
        BigInteger sum = new BigInteger("0");                                   // create sum and initializing it
        while (num.compareTo(BigInteger.ZERO) == 1) {                               // while num doesn't equal 1 divide by % and //
            sum = sum.add(num.remainder(ten));
            num = num.divide(ten);
        }
        return sum.intValue();
    }

    public static void main(String[] args) {
        BigInteger fact = Factorial.calculateFactorial(BigInteger.valueOf(100));
        int sumNum = Factorial.summarizeDigits(fact);
        System.out.println(fact);
        System.out.println(sumNum);
    }

}
