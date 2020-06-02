// https://www.hackerrank.com/challenges/special-palindrome-again/problem

package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpecialStringAgain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        String string = scanner.next();
        System.out.println(specialStrings(string));
    }

    static long specialStrings(String string) {
        long count = 0;
        for (int index = 0 ; index < string.length() ; index++) {
            Distribution distribution = new Distribution();
            for (int j = index ; j < string.length() ; j++) {
                distribution.add(string.charAt(j));
                if ((j - index + 1) % 2 == 0) {
                   count += distribution.frequencies.size() == 1 ? 1 : 0;
                } else {
                    count += distribution.frequencies.size() <= 2
                        && distribution.histogramCounter() < 2
                        && string.charAt(index + (j - index + 1) / 2) == distribution.charWithSmallestFrequency()
                    ? 1 : 0 ;
                }

                if (distribution.frequencies.size() > 2 || distribution.histogramCounter() > 1) {
                    break;
                }
            }
        }
        return count;
    }

    private static class Distribution {
        private final Map<Character, Integer> frequencies = new HashMap<>();

        void add(char character) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        char charWithSmallestFrequency() {
            int minFrequency = frequencies.values().stream().min(Integer::compareTo).get();
            for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                if (entry.getValue() == minFrequency) {
                    return entry.getKey();
                }
            }
            return '*';
        }

        int histogramCounter() {
            int count = 0;
            for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                if (entry.getValue() > 1) {
                    count++;
                }
            }
            return count;
        }
    }
}
