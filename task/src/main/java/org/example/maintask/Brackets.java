package org.example.maintask;

import java.util.*;

/*
*
* @author Illia Melnyk
* */

public class Brackets {
    public static int countBracketPairs(String str) {
        Deque<Character> deque = new LinkedList<>();                                // create Deque in order to input brackets
        int count = 0;                                                              // create counter to find bracket pairs
        for (char c: str.toCharArray()) {                                           // use for-each to transform String into char array and find brackets
            if (c == '(') {
                deque.addFirst(c);                                                  // add in Deque bracket if a bracket is found
            } else {
                if (!deque.isEmpty() && (deque.peekFirst() == '(' && c == ')')) {   // if deque is not empty and pair for another bracket is found
                    deque.removeFirst();
                    count++;                                                        // increase counter if pair is found
                } else {
                    return count;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Brackets.countBracketPairs("(())())"));
    }
}
