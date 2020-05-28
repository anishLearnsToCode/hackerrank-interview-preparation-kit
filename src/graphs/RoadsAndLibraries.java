package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class RoadsAndLibraries {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int cities = scanner.nextInt();
            int roads = scanner.nextInt();
            int libraryCost = scanner.nextInt();
            int roadCost = scanner.nextInt();
            System.out.println(minimumConstructionCost(cities, roads, libraryCost, roadCost));
        }
    }

    private static long minimumConstructionCost(int cities, int roads, int libraryCost, int roadCost) {
        Graph graph = new Graph(cities);
        for (int i = 0 ; i < roads ; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            graph.addEdge(from, to);
        }
        return graph.minimumConstructionCost(libraryCost, roadCost);
    }

    private static class Graph implements Iterable<Graph.Vertex>  {
        private final Map<Integer, Vertex> vertices;
        private int numberOfEdges = 0;

        Graph(int size) {
            vertices = new HashMap<>(size);
            for (int number = 1 ; number <= size ; number++) {
                this.addVertex(number);
            }
        }

        @Override
        public Iterator<Vertex> iterator() {
            return vertices.values().iterator();
        }

        static class Vertex implements Iterable<Vertex> {
            Integer data;
            Set<Vertex> edges = new HashSet<>();

            Vertex(int data) {
                this.data = data;
            }

            private void addEdge(Vertex other) {
                this.edges.add(other);
            }

            private boolean edgeTo(Vertex to) {
                return edges.contains(to);
            }

            @Override
            public Iterator<Vertex> iterator() {
                return edges.iterator();
            }
        }

        private void addVertex(Integer data) {
            vertices.put(data, new Vertex(data));
        }

        private void addEdge(int from, int to) {
            if (vertices.get(from).edgeTo(vertices.get(to))) {
                return;
            }

            vertices.get(from).addEdge(vertices.get(to));
            vertices.get(to).addEdge(vertices.get(from));
            numberOfEdges++;
        }

        private long minimumConstructionCost(int libraryCost, int roadCost) {
            List<Set<Vertex>> subGraphs = disjointSets();
            long cost = 0;
            for (Set<Vertex> set : subGraphs) {
                cost += libraryCost > roadCost
                        ? roadCost * (set.size() - 1) + libraryCost
                        : libraryCost * set.size();
            }
            return cost;
        }

        private List<Set<Vertex>> disjointSets() {
            List<Set<Vertex>> sets = new ArrayList<>();
            Set<Vertex> visited = new HashSet<>();

            for (Vertex vertex : this) {
                if (visited.contains(vertex)) {
                    continue;
                }

                visited.add(vertex);

                Set<Vertex> disjointSet = new HashSet<>();
                Queue<Vertex> queue = new LinkedList<>();
                queue.add(vertex);

                while (!queue.isEmpty()) {
                    Vertex node = queue.poll();
                    disjointSet.add(node);
                    for (Vertex connected : node) {
                        if (!visited.contains(connected)) {
                            queue.add(connected);
                            visited.add(connected);
                        }
                    }
                }

                sets.add(disjointSet);
            }

            return sets;
        }
    }
}
