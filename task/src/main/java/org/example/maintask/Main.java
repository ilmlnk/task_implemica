package org.example.maintask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int destination;                                                             // variable which points at city which will be the next
    public static class City {
        private int id;                                                                         // number of city
        private long cost;                                                                      // city cost

        public City(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCost() {
            return cost;
        }

        public void setCost(long cost) {
            this.cost = cost;
        }
    }

    private static class IOStreamController {
        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public IOStreamController() {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        protected String next() {                                                               // find another value with buffer and tokenizer
            while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    System.err.println("It is not possible to find next city!");
                }
            }
            return stringTokenizer.nextToken();
        }

        protected int nextInt() {                                                               // return value in integer
            return Integer.parseInt(next());
        }

        protected long nextLong() {                                                             // return value in long
            return Long.parseLong(next());
        }
    }

    static void dijkstra(int source, ArrayList<City>[] adj, int[] cost) {                       // using dijkstra's algorithm to find the cheapest route
        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> (int) (c1.cost - c2.cost));    // creating queue in order to compare costs
        pq.add(new City(source, 0));
        cost[source] = 0;                                                                       // cost to the source will be 0
        while(!pq.isEmpty()) {
            City parent = pq.poll();
            if (parent.id == destination) break;                                                // if next city is not found, then break the cycle
            for (City neighbour : adj[parent.getId()]) {
                long newCost = neighbour.getCost() + parent.getCost();                          // adding two costs in order to receive new cost
                if (newCost < cost[neighbour.getId()]) {
                    pq.add(new City(neighbour.getId(), newCost));
                    cost[neighbour.getId()] = (int) newCost;
                }
            }
        }
    }

    public static void main(String[] args) {
        IOStreamController io = new IOStreamController();
        int s = io.nextInt();                                                                   // number of tests
        assert s <= 10;                                                                         // assertion of valid values for s (tests)

        for (int i = 0; i < s; i++) {
            Map<String, Long> cityMap = new HashMap<>();                                        // creating HashMap (key: city name, value: cost)
            int n = io.nextInt();                                                               // number of cities
            assert n <= 1000;                                                                   // assertion of valid values for n (cities)
            ArrayList<City>[] adj = new ArrayList[n+1];                                         // adjacency list
            for (int j = 1; j <= n; j++) adj[j] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                String cityName = io.next();
                cityMap.put(cityName, (long) j+1);
                int edges = io.nextInt();                                                       // edges (amount of routes between cities)
                for (int k = 0; k < edges; k++) {
                    int id = io.nextInt();
                    long cost = io.nextLong();
                    adj[j+1].add(new City(id, cost));
                }
            }
            int r = io.nextInt();                                                               // number of paths to find
            assert r <= 100;                                                                    // assertion of valid number of paths to find

            for (int p = 0; p < r; p++) {
                String source = io.next();                                                      // name of the source node
                String des = io.next();                                                         // name of destination
                long sourceIndex = cityMap.get(source);
                long destinationIndex = cityMap.get(des);
                destination = (int) destinationIndex;
                int[] costs = new int[n+1];                                                     // cost array
                Arrays.fill(costs, Integer.MAX_VALUE);
                dijkstra((int) sourceIndex, adj, costs);                                        // using Dijkstra's algorithm
                System.out.println(destinationIndex);
            }
        }
    }
}