// https://www.hackerrank.com/challenges/counting-valleys/problem?h_l=interview

package warmup;

import java.util.Scanner;

public class CountingValleys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        String string = scanner.next();
        System.out.println(numberOfValleys(string));
    }

    private static int numberOfValleys(String string) {
        int valleys = 0, level = 0;
        boolean check = true;

        for (int index = 0 ; index < string.length() ; index++) {
            level += string.charAt(index) == 'U' ? 1 : -1 ;
            if (check && level < 0) {
                valleys++;
                check = false;
            } else if (level >= 0) {
                check = true;
            }
        }

        return valleys;
    }
}
