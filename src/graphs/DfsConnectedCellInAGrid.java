// https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem

package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class DfsConnectedCellInAGrid {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] grid = getGrid(rows, columns);
        System.out.println(largestRegionSize(grid, rows, columns));
    }

    private static int[][] getGrid(int rows, int columns) {
        int[][] array = new int[rows][columns];
        for (int row = 0 ; row < rows ; row++) {
            for (int column = 0 ; column < columns ; column++) {
                array[row][column] = scanner.nextInt();
            }
        }
        return array;
    }

    private static int largestRegionSize(int[][] grid, int rows, int columns) {
        if (rows == 0 || columns == 0) return 0;
        if (rows == 1 && columns == 1) return grid[0][0];
        if (rows == 1) {
            return kadane(grid[0]);
        }
        if (columns == 1) {
            return kadane(grid);
        }

        Graph graph = Graph.from(grid);
        List<Set<Graph.Vertex>> disjointSets = graph.disjointSets();
        int maxSize = 0;
        for (Set<Graph.Vertex> disjointSet : disjointSets) {
            maxSize = Math.max(maxSize, disjointSet.size());
        }
        return maxSize;
    }

    private static int kadane(int[] array) {
        int count = 0, result = 0;
        for (int number : array) {
            count = number == 1 ? count + 1 : 0;
            result = Math.max(result, count);
        }
        return result;
    }

    private static int kadane(int[][] grid) {
        int result = 0, count = 0;
        for (int[] array : grid) {
            count = array[0] == 1 ? count + 1 : 0;
            result = Math.max(result, count);
        }
        return result;
    }

    private static class Graph {
        Map<Position, Vertex> vertices;

        static Graph from(int[][] grid) {
            int rows = grid.length;
            int columns = grid[0].length;
            Graph graph = new Graph(rows * columns);
            graph.instantiateFrom(grid, rows, columns);
            return graph;
        }

        Graph(int capacity) {
            this.vertices = new HashMap<>(capacity);
        }

        void instantiateFrom(int[][] grid, int rows, int columns) {
            for (int row = 0 ; row < rows - 1 ; row++) {
                for (int column = 0 ; column < columns - 1 ; column++) {
                    Set<Vertex> pool = new HashSet<>();
                    for (int i = row ; i < row + 2 ; i++) {
                        for (int j = column ; j < column + 2 ; j++) {
                            if (grid[i][j] == 1) {
                                Position position = new Position(i, j);
                                pool.add(vertices.getOrDefault(position, new Vertex(position)));
                            }
                        }
                    }
                    connectPool(pool);
                    addPool(pool);
                }
            }
        }

        void addPool(Set<Vertex> pool) {
            for (Vertex vertex : pool) {
                this.vertices.put(vertex.position, vertex);
            }
        }

        void connectPool(Set<Vertex> pool) {
            for (Vertex vertex : pool) {
                for (Vertex other : pool) {
                    if (other == vertex) {
                        continue;
                    }
                    addEdge(vertex, other);
                }
            }
        }

        void addEdge(Vertex from, Vertex to) {
            from.addEdge(to);
        }

        List<Set<Vertex>> disjointSets() {
            Set<Vertex> visited = new HashSet<>(vertices.size());
            List<Set<Vertex>> result = new ArrayList<>();

            for (Vertex vertex : vertices.values()) {
                if (visited.contains(vertex)) {
                    continue;
                }

                Set<Vertex> disjointSet = new HashSet<>();
                Queue<Vertex> queue = new LinkedList<>();
                queue.add(vertex);
                while (!queue.isEmpty()) {
                    Vertex top = queue.poll();
                    disjointSet.add(top);
                    visited.add(top);

                    for (Vertex connected : top.edges) {
                        if (!visited.contains(connected)) {
                            queue.add(connected);
                        }
                    }
                }

                result.add(disjointSet);
            }

            return result;
        }

        private static class Position {
            int row;
            int column;

            Position(int row, int column) {
                this.row = row;
                this.column = column;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Position position = (Position) o;
                return row == position.row &&
                        column == position.column;
            }

            @Override
            public int hashCode() {
                return Objects.hash(row, column);
            }
        }

        private static class Vertex {
            Position position;
            Set<Vertex> edges = new HashSet<>();

            Vertex(Position position) {
                this.position = position;
            }

            void addEdge(Vertex to) {
                this.edges.add(to);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Vertex vertex = (Vertex) o;
                return position.equals(vertex.position);
            }

            @Override
            public int hashCode() {
                return Objects.hash(position);
            }
        }
    }
}
