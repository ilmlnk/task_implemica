package org.example.maintask;

import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        BigInteger fact = Factorial.calculateFactorial(BigInteger.valueOf(100));
        int sumNum = Factorial.summarizeDigits(fact);
        System.out.println(fact);
        System.out.println(sumNum);
        System.out.println(Brackets.countBracketPairs("(())())"));
    }
}
