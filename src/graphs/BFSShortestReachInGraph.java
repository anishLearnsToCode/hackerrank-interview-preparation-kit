package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFSShortestReachInGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();

        while (queries-- > 0) {
            int vertices = scanner.nextInt();
            int edges = scanner.nextInt();
            Graph graph = new Graph(vertices);
            while (edges-- > 0) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                graph.addEdge(from, to);
            }
            int start = scanner.nextInt();
            System.out.println(spaceSeparated(graph.shortestReach(start)));
        }
    }

    static String spaceSeparated(int[] array) {
        StringBuilder accumulator = new StringBuilder();
        for (int number : array) {
            accumulator.append(number).append(" ");
        }
        return accumulator.toString();
    }

    private static class Graph {
        Map<Integer, Vertex> vertices;

        private static class Vertex {
            int number;
            Set<Vertex> edges = new HashSet<>();

            Vertex(int number) {
                this.number = number;
            }

            void addEdge(Vertex vertex) {
                edges.add(vertex);
            }
        }

        private static class Info {
            Vertex vertex;
            int distance;

            Info(Vertex vertex, int distance) {
                this.vertex = vertex;
                this.distance = distance;
            }
        }

        Graph(int capacity) {
            vertices = new HashMap<>(capacity);
            for (int number = 1 ; number <= capacity ; number++) {
                addVertex(number);
            }
        }

        int[] shortestReach(int start) {
            int[] result = new int[vertices.size() - 1];
            Vertex startVertex = vertices.get(start);
            Queue<Info> queue = new LinkedList<>();
            queue.add(new Info(startVertex, 0));
            Set<Vertex> visited = new HashSet<>();

            while (!queue.isEmpty()) {
                Info info = queue.poll();
                Vertex vertex = info.vertex;

                if (visited.contains(vertex)) {
                    continue;
                }

                visited.add(vertex);

                if (vertex.number != start) {
                    result[vertex.number - 1 - (vertex.number > start ? 1 : 0)] = info.distance;
                }

                for (Vertex connected : vertex.edges) {
                    queue.add(new Info(connected, info.distance + 6));
                }
            }

            return addUnconnectedVertices(result);
        }

        int[] addUnconnectedVertices(int[] result) {
            for (int index = 0 ; index < result.length ; index++) {
                if (result[index] == 0) {
                    result[index] = -1;
                }
            }
            return result;
        }

        void addVertex(int data) {
            vertices.put(data, new Vertex(data));
        }

        void addEdge(int from, int to) {
            Vertex vertex1 = vertices.get(from);
            Vertex vertex2 = vertices.get(to);
            vertex1.addEdge(vertex2);
            vertex2.addEdge(vertex1);
        }
    }
}
