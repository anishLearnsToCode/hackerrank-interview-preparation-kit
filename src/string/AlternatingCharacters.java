// https://www.hackerrank.com/challenges/alternating-characters/problem

package string;

import java.util.Scanner;

public class AlternatingCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String string = scanner.next();
            System.out.println(minimumDeletions(string));
        }
    }

    private static int minimumDeletions(String string) {
        int deletions = 0;
        char current = '-';
        for (int index = 0 ; index < string.length() ; index++) {
            if (string.charAt(index) == current) {
                deletions++;
            } else {
                current = string.charAt(index);
            }
        }
        return deletions;
    }
}
