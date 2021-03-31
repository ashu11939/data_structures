package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    /**
     * Ignoring the 0th index in the adjency matrix
     */
    static class Node {
        int v;
        int e;
        int[][] adj;
        Node(int v, int e){
            this.v = v;
            this.e = e;
            this.adj = new int[v+1][v+1];
            for(int i = 1; i <= v; i++) {
                for(int j = 1; j <= v; j++) {
                    this.adj[i][j] = 0;
                }
            }
        }
    }

    static void DFS(Node graph, boolean[] visited, int gindex) {
        visited[gindex] = true;
        System.out.println("Node : " + gindex);
        for (int i = 1; i <= 5; i++) {
            System.out.println("index : " + i + " visited : " + visited[i]);
        }
        for(int i = 1; i <= graph.v; i++ ){
            if(!visited[i] && graph.adj[gindex][i] == 1) {
                DFS(graph, visited, i);
            }
        }
    }

    static void BFS(Node graph, boolean[] visited, int gindex){
        Queue<Integer> q = new LinkedList<>();
        q.add(gindex);
        while(!q.isEmpty()) {
            int temp = q.poll();
            visited[temp] = true;
            System.out.println("Node : " + temp);         
            for (int i = 1; i <= 5; i++) {
                System.out.println("index : " + i + " visited : " + visited[i]);
            }
            for(int i = 1; i < graph.v; i++){
                if(!visited[i] && graph.adj[temp][i] == 1) {
                    q.add(i);
                }
            }
        }
    }

    static void isCyclic(Node graph, boolean[] visited, int v, int parent) {
        
    }


    public static void main(String[] args0) {
        System.out.println("****** Welcome to Graphs ******");

        Node graph = new Node(5, 5);
        graph.adj[1][2] = 1;
        graph.adj[2][3] = 1;
        graph.adj[3][4] = 1;
        graph.adj[3][5] = 1;
        graph.adj[4][5] = 1;
        graph.adj[1][4] = 1;

        boolean[] visited = new boolean[5+1];
        for (int i = 0; i <= 5; i++) {
            visited[i] = false;
        }
        System.out.println("****** DFS Traversal ******");
        for(int i = 1; i <= graph.v; i++) {
            if(!visited[i]){
                DFS(graph, visited, i);
            }
        }

        for (int i = 0; i <= 5; i++) {
            visited[i] = false;
        }
        System.out.println("****** BFS Traversal ******");
        for(int i = 1; i <= graph.v; i++) {
            if(!visited[i]){
                BFS(graph, visited, i);
            }
        }
    }
}
