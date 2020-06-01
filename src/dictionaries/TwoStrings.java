// https://www.hackerrank.com/challenges/two-strings/problem

package dictionaries;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TwoStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String first = scanner.next();
            String second = scanner.next();
            System.out.println(shareCommonSubString(first, second) ? "YES" : "NO");
        }
    }

    private static boolean shareCommonSubString(String first, String second) {
        Map<Character, Integer> characterFrequencies = getCharacterFrequencies(first);
        for (int index = 0 ; index < second.length() ; index++) {
            char character = second.charAt(index);
            if (characterFrequencies.containsKey(character)) {
                return true;
            }
        }
        return false;
    }

    private static Map<Character, Integer> getCharacterFrequencies(String string) {
        Map<Character, Integer> result = new HashMap<>();
        for (int index = 0 ; index < string.length() ; index++) {
            char character = string.charAt(index);
            result.put(character, result.getOrDefault(character, 0) + 1);
        }
        return result;
    }
}
