import java.util.Objects;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
public class NewYearChaos {
    public static void main(String[] args) {
        int[] array = StdIn.getIntArray();
        Solution.minimumBribes(array);
    }
}

class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] array) {
//        int minimumBribes = minimumBribesOrNull(array);
//        if (minimumBribes == -1) {
//            System.out.print("Too chaotic");
//        } else {
//            System.out.print(minimumBribes);
//        }

        calc(array);
    }

    private static void calc(int[] q) {
        int ans = 0;
        for (int i = q.length - 1; i >= 0; i--) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, q[i] - 2); j < i; j++)
                if (q[j] > q[i]) ans++;
        }
        System.out.println(ans);
    }

    private static int minimumBribesOrNull(int[] array) {
        int answer = 0;
        BinarySearchTree tree = new BinarySearchTree();
        for (int index = array.length - 1 ; index >= 0 ; index--) {
            int bribe = tree.add(array[index]);
            if (bribe > 2) {
                return -1;
            }
            answer += bribe;
        }
        return answer;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
            if (tItr != t - 1) {
                System.out.println();
            }
        }

        scanner.close();
    }

    private static class BinarySearchTree {
        int root;
        int size;
        BinarySearchTree left;
        BinarySearchTree right;

        BinarySearchTree() {
            this.size = 0;
        }

        BinarySearchTree(int root) {
            this.root = root;
            size = 1;
        }

        int add(int element) {
            if (isEmpty()) {
                root = element;
                size++;
                return 0;
            } else {
                size++;
                if (element > root) {
                    if (right == null) {
                        this.right = new BinarySearchTree(element);
                        return this.size - 1;
                    } else {
                        return 1 + size(this.left) + right.add(element);
                    }
                } else {
                    if (this.left == null) {
                        this.left = new BinarySearchTree(element);
                        return 0;
                    } else {
                        return this.left.add(element);
                    }
                }
            }
        }

        int size(BinarySearchTree tree) {
            if (Objects.isNull(tree)) {
                return 0;
            }
            return tree.size;
        }

        boolean isEmpty() {
            return size == 0 ;
        }
    }

    private static class MaxHeap {
        private final int[] array;
        private final int length;
        private int size = 0;

        MaxHeap(int length) {
            this.length = length;
            this.array = new int[length];
        }

        boolean isEmpty() {
            return size == 0 ;
        }

        int add(int element) {
            if (isEmpty()) {
                array[size++] = element;
                return 0;
            } else {
                array[size++] = element;
                return bubbleUp(size - 1);
            }
        }

        int bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return size - 1;
            }

            if (array[index] > array[parentIndex]) {
                swap(index, parentIndex);
                return bubbleUp(parentIndex);
            } else {
                return size - index - 1;
            }
        }

        private void swap(int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }
    }
}
