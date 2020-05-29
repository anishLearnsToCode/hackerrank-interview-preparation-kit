// https://www.hackerrank.com/challenges/matrix/problem

package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        int machines = scanner.nextInt();

        List<Edge> edges = new ArrayList<>(vertices - 1);
        for (int index = 0 ; index < vertices - 1 ; index++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int time = scanner.nextInt();
            edges.add(new Edge(from, to, time));
        }

        Set<Integer> machineVertices = new HashSet<>();
        for (int i = 0 ; i < machines ; i++) {
            machineVertices.add(scanner.nextInt());
        }

        System.out.println(minimumCost(edges, machineVertices));
    }

    private static long minimumCost(List<Edge> edges, Set<Integer> machines) {
        Set<Integer> usedVertices = new HashSet<>();
        Map<Integer, Integer> cityIndices = new HashMap<>();
        List<City> cities = new ArrayList<>();
        long cost = 0;
        edges.sort(Edge::compareTo);

        for (Edge edge : edges) {
            if (usedVertices.contains(edge.from)) {
                cost += addEdgeInCities(cityIndices, edge.from, edge.time, cities, machines, usedVertices, edge.to, edge);
            } else if (usedVertices.contains(edge.to)) {
                cost +=  addEdgeInCities(cityIndices, edge.to, edge.time, cities, machines, usedVertices, edge.from, edge);
            } else {
                City city = new City();
                city.hasMachine = machines.contains(edge.from) || machines.contains(edge.to);
                useEdgeAtCity(edge, cities.size(), usedVertices, cityIndices);
                cities.add(city);
            }
        }

        return cost;
    }

    private static void useEdgeAtCity(Edge edge, int cityIndex, Set<Integer> usedVertices, Map<Integer, Integer> cityIndices) {
        usedVertices.add(edge.from);
        usedVertices.add(edge.to);
        cityIndices.put(edge.from, cityIndex);
        cityIndices.put(edge.to, cityIndex);
    }

    private static long addEdgeInCities(Map<Integer, Integer> cityIndices, int vertex, int time, List<City> cities,
                                        Set<Integer> machines, Set<Integer> usedVertices, int otherVertex, Edge edge) {
        long cost = 0;
        int index = cityIndices.get(vertex);
        City city = cities.get(index);
        if ((machines.contains(otherVertex) || machines.contains(vertex)) && city.hasMachine) {
            cost = time;
        } else {
            updateData(edge, usedVertices, cityIndices, index, city, machines);
        }

        return cost;
    }

    private static void updateData(Edge edge, Set<Integer> usedVertices, Map<Integer, Integer> cityIndices,
                                   int index, City city, Set<Integer> machines) {
        usedVertices.add(edge.from);
        usedVertices.add(edge.to);
        cityIndices.put(edge.from, index);
        cityIndices.put(edge.to, index);
        city.hasMachine = city.hasMachine || machines.contains(edge.from) || machines.contains(edge.to);
    }

    private static class City {
        boolean hasMachine = false;
    }

    private static class Edge implements Comparable<Edge> {

        int time;
        int from;
        int to;

        Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(edge.time, this.time);
        }
    }
}
