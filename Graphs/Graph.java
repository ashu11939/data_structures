package Graphs;

public class Graph {

    static class Node {
        int v;
        int e;
        int[][] adj;
        Node(int v, int e){
            this.v = v;
            this.e = e;
            this.adj = new int[v][v];
            for(int i = 0; i < v; i++) {
                for(int j = 0; j < v; j++) {
                    this.adj[i][j] = 0;
                }
            }
        }
    }
    
}
