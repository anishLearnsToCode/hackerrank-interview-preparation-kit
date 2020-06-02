package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaximumSubArraySum {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int length = scanner.nextInt();
            int m = scanner.nextInt();
            long[] array = getArray(length);
            System.out.println(maximumSumSubArray(array, m));
        }
    }

    private static long[] getArray(int length) {
        long[] array = new long[length];
        for (int index = 0 ; index < length ; index++) {
            array[index] = scanner.nextLong();
        }
        return array;
    }

    private static long maximumSumSubArray(long[] array, long m) {
        List<Element> prefixSumArray = getPrefixSumArray(array, m);
        prefixSumArray.sort(Element::compareTo);
        long minimumDifference = minimumDifference(prefixSumArray, m);
        return m - minimumDifference;
    }

    private static List<Element> getPrefixSumArray(long[] array, long m) {
        long[] prefixSum = new long[array.length];
        prefixSum[0] = array[0];
        for (int index = 1 ; index < array.length ; index++) {
            prefixSum[index] = (prefixSum[index - 1] + array[index] ) % m ;
        }

        List<Element> elements = new ArrayList<>(array.length);
        for (int index = 0 ; index < prefixSum.length ; index++) {
            elements.add(new Element(prefixSum[index], index));
        }

        return elements;
    }

    private static long minimumDifference(List<Element> elements, long start) {
        long result = start;
        for (int index = 0 ; index < elements.size() - 1 ; index++) {
            Element current = elements.get(index);
            Element next = elements.get(index + 1);
            if (current.index > next.index) {
                result = Math.min(result, Math.abs(current.data - next.data));
            }
        }
        return result;
    }

    private static class Element implements Comparable<Element> {
        long data;
        int index;

        Element(long data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public int compareTo(Element element) {
            return Long.compare(this.data, element.data);
        }
    }
}
