package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class FindNearestNodeNaive {
    static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int nodes = SCANNER.nextInt();
        int edges = SCANNER.nextInt();
        int[][] edgeMap = new int[edges][2];

        for (int row = 0 ; row < edges ; row++) {
            edgeMap[row][0] = SCANNER.nextInt();
            edgeMap[row][1] = SCANNER.nextInt();
        }

        int[] colors = getColors(nodes);
        int desiredColor = SCANNER.nextInt();
        int result = nearestClone(colors, edgeMap, desiredColor);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int nearestClone(int[] colors, int[][] edges, int color) {
        Graph graph = new Graph(colors);
        for (int[] row : edges) {
            graph.addEdge(row[0], row[1]);
        }
        return graph.nearestCloneBfs(color);
    }

    private static int[] getColors(int size) {
        int[] array = new int[size];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = SCANNER.nextInt();
        }
        return array;
    }

    private static class Graph {
        Map<Integer, Vertex> vertices;
        int[] colors;

        private static class Vertex implements Iterable<Vertex> {
            int color;
            Set<Vertex> edges = new HashSet<>();

            Vertex(int color) {
                this.color = color;
            }

            private void addEdge(Vertex to) {
                edges.add(to);
            }

            @Override
            public Iterator<Vertex> iterator() {
                return edges.iterator();
            }
        }

        Graph(int[] colors) {
            this.colors = colors;
            vertices = new HashMap<>(colors.length);
            for (int index = 0 ; index < colors.length ; index++) {
                addVertex(index + 1, colors[index]);
            }
        }

        void addVertex(int data, int color) {
            vertices.put(data, new Vertex(color));
        }

        void addEdge(int from, int to) {
            Vertex vertex1 = vertices.get(from);
            Vertex vertex2 = vertices.get(to);
            vertex1.addEdge(vertex2);
            vertex2.addEdge(vertex1);
        }

        private int firstVertexWithColor(int color) {
            for (int index = 0 ; index < colors.length ; index++) {
                if (colors[index] == color) {
                    return index + 1;
                }
            }

            return -1;
        }

        private int nearestCloneBfs(int color) {
            int vertexNumber = firstVertexWithColor(color);
            return vertexNumber == -1 ? -1 : nearestCloneBfs(vertices.get(vertexNumber), color);
        }

        private int nearestCloneBfs(Vertex start, int color) {
            Set<Vertex> visited = new HashSet<>();
            Queue<Info> queue = new LinkedList<>();
            visited.add(start);

            for (Vertex connected : start) {
                queue.add(new Info(connected, 1));
            }

            return nearestCloneBfs(visited, queue, color);
        }

        private int nearestCloneBfs(Set<Vertex> visited, Queue<Info> queue, int color) {
            int result = Integer.MAX_VALUE;

            while (!queue.isEmpty()) {
                Info info = queue.poll();
                if (!visited.contains(info.vertex)) {
                    visited.add(info.vertex);
                    int distance = info.distance;

                    if (info.vertex.color == color) {
                        result = Math.min(result, info.distance);
                        distance = 0;
                    }

                    for (Vertex connected : info.vertex) {
                        queue.add(new Info(connected, distance + 1));
                    }
                }
            }

            return result;
        }

        private static class Info {
            Vertex vertex;
            int distance;

            Info(Vertex vertex, int distance) {
                this.vertex = vertex;
                this.distance = distance;
            }
        }
    }
}
