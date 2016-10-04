/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctci_1_3_permutation_test;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author klsandbox
 */
public class CTCI_1_3_Permutation_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String a = "ehllo";
        String b = "lheol";
        System.out.println("isPermutation:" + isPermutation(a, b));
        System.out.println("isPermutationFast:" + isPermutationFast(a, b));

        long startTime = System.nanoTime();
        for (int ctr = 0; ctr < 1000; ctr++) {
            isPermutation(a, b);
        }

        long estimatedTime = System.nanoTime() - startTime;

        long startTime2 = System.nanoTime();
        for (int ctr = 0; ctr < 1000; ctr++) {
            isPermutationFast(a, b);
        }

        long estimatedTime2 = System.nanoTime() - startTime2;

        System.out.println("slow:" + (estimatedTime / 1000) + ", fast:" + (estimatedTime2 / 1000));

    }

    public static boolean isPermutation(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        LinkedList<Character> list = new LinkedList<Character>();
        for (int ctr = 0; ctr < a.length(); ctr++) {
            list.add(a.charAt(ctr));
        }

        for (int ctr = 0; ctr < b.length(); ctr++) {
            ListIterator<Character> iterator = list.listIterator();
            char target = b.charAt(ctr);
//            System.out.println("target:" + target);
            while (iterator.hasNext()) {
                char current = iterator.next();
                if (current == target) {
//                    System.out.println("removing:" + target);
                    iterator.remove();
                    break;
                }
            }
        }

        //System.out.println("size:" + list.size());

        return list.isEmpty();
    }

    public static boolean isPermutationFast(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        char[] letters = b.toCharArray();
        int length = b.length();

        for (int ctr = 0; ctr < a.length(); ctr++) {
            char target = a.charAt(ctr);

            boolean found = false;
            for (int i = 0; i < length; i++) {
                if (letters[i] == target) {
                    letters[i] = letters[length - 1];
                    length -= 1;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }
}
