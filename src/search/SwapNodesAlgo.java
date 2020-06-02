// https://www.hackerrank.com/challenges/swap-nodes-algo/problem

package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SwapNodesAlgo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        Node root = new Node(1, 1);
        createTree(root);
        performQueries(root);
    }

    private static void performQueries(Node root) {
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int swapDepth = scanner.nextInt();
            swapSubTrees(root, swapDepth);
            printInOrder(root);
            System.out.println();
        }
    }

    private static void swapSubTrees(Node root, int swapDepth) {
        if (root == null) {
            return;
        }

        if (root.level % swapDepth == 0) {
            Node extra = root.left;
            root.left = root.right;
            root.right = extra;
        }

        swapSubTrees(root.left, swapDepth);
        swapSubTrees(root.right, swapDepth);
    }

    private static void printInOrder(Node root) {
        if (root == null) {
            return;
        }

        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }

    private static void createTree(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            if (left != -1) {
                top.addLeft(left);
                queue.add(top.left);
            }
            if( right != -1) {
                top.addRight(right);
                queue.add(top.right);
            }
        }
    }

    private static class Node {
        int data;
        int level;
        Node left;
        Node right;

        Node(int data, int level) {
            this.data = data;
            this.level = level;
        }

        void addLeft(int data) {
            this.left = new Node(data, level + 1);
        }

        void addRight(int data) {
            this.right = new Node(data, level + 1);
        }
    }
}
