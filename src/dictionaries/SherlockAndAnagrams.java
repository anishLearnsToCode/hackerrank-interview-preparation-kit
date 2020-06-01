// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem

package dictionaries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class SherlockAndAnagrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String string = scanner.next();
            System.out.println(substringAnagrams(string));
        }
    }

    private static int substringAnagrams(String string) {
        Map<UnorderedTuple, Integer> substringQueries = getFrequencyMap(string);
        int count = 0;
        for (Map.Entry<UnorderedTuple, Integer> entry : substringQueries.entrySet()) {
            if (entry.getValue() > 1) {
                count += (entry.getValue() * (entry.getValue() - 1)) / 2;
            }
        }
        return count;
    }

    private static Map<UnorderedTuple, Integer> getFrequencyMap(String string) {
        Map<UnorderedTuple, Integer> result = new HashMap<>();
        for (int i = 0 ; i < string.length() ; i++) {
            for (int j = i + 1 ; j < string.length() + 1 ; j++) {
                UnorderedTuple tuple = new UnorderedTuple(string.substring(i, j));
                result.put(tuple, result.getOrDefault(tuple, 0) + 1);
            }
        }
        return result;
    }

    private static class UnorderedTuple {
        private final Map<Character, Integer> frequencyMap = new HashMap<>();

        UnorderedTuple(String string) {
            for (int index = 0 ; index < string.length() ; index++) {
                char character = string.charAt(index);
                this.frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnorderedTuple that = (UnorderedTuple) o;
            return frequencyMap.equals(that.frequencyMap);
        }

        @Override
        public int hashCode() {
            return Objects.hash(frequencyMap);
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "frequency=" + frequencyMap +
                    '}';
        }
    }
}
