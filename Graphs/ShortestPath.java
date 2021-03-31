package Graphs;

import java.util.LinkedList;
import java.util.Queue;

import Graphs.Graph.Node;

public class ShortestPath {

    public static void main(String[] args) {
        System.out.println("Shortest path from a source");
        Node g = new Node(5, 6);
        g.adj[1][2] = 1;
        g.adj[1][4] = 1;
        g.adj[2][3] = 1;
        g.adj[2][5] = 1;
        g.adj[3][4] = 1;
        g.adj[4][5] = 1;
        shortestPathUnweighted(g, 1);

        Node g2 = new Node(5, 6);
        g2.adj[1][2] = 2;
        g2.adj[1][4] = 3;
        g2.adj[2][3] = 7;
        g2.adj[2][5] = 1;
        g2.adj[4][3] = 4;
        g2.adj[4][5] = 5;
        System.out.println("Shortest path weighted from a source");
        shortestPathWeighted(g2, 1);
    }
    

    public static void shortestPathUnweighted(Node g, int s) {

        int[] path = new int[g.v+1];
        int[] distance = new int[g.v+1];
        for(int i = 0; i <= g.v; i++) {
            distance[i] = -1;
        }
        distance[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int u = 1; u <= g.v; u++) {
                if (g.adj[temp][u] == 1) {
                    if(distance[u] == -1) {
                        distance[u] = distance[temp] + 1;
                        path[u] = temp;
                        q.add(u);
                    }
                }
            }
        }

        for(int i = 1; i <= g.v; i++) {
            System.out.print("index : " + i + " distance from " + s + " = " + distance[i] + "\n");
            System.out.print("index : " + i + " path from " + path[i] + "\n");
        }
    }

    public static void shortestPathWeighted(Node g, int s) {

        int[] distance = new int[g.v + 1];
        int[] path = new int[g.v + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        for(int i = 0; i <= g.v; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = 0;
        }
        distance[s] = 0;
        while(!q.isEmpty()) {

            int temp = q.poll();
            for(int u = 1; u <= g.v; u++){
                if(g.adj[temp][u] != 0) {
                    int d = distance[temp] + g.adj[temp][u];
                    if(d < distance[u]) {
                        distance[u] = d;
                        path[u] = temp;
                        if(!q.contains(u)) {
                            q.add(u);
                        }
                    }
                }
            }
        }

        for(int i = 1; i <= g.v; i++) {
            System.out.print("index : " + i + " distance from " + s + " = " + distance[i] + "\n");
            System.out.print("index : " + i + " path from " + path[i] + "\n");
        }
    }
}
