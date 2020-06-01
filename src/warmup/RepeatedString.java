// https://www.hackerrank.com/challenges/repeated-string/problem

package warmup;

import java.util.Scanner;

public class RepeatedString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        long length = scanner.nextLong();
        System.out.println(occurrencesOfA(string, length));
    }

    private static long occurrencesOfA(String string, long length) {
        long repetitions = length / string.length() ;
        int remainder = (int) (length % string.length());
        int occurrencesInString = occurrencesOfA(string);
        int occurrencesInRemainder = occurrencesOfA(string.substring(0, remainder));
        return occurrencesInRemainder + repetitions * occurrencesInString ;
    }

    private  static int occurrencesOfA(String string) {
        int count = 0;
        for (int index = 0 ; index < string.length() ; index++) {
            if (string.charAt(index) == 'a') {
                count++;
            }
        }
        return count;
    }
}
