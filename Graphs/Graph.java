package Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    static boolean isCyclic(Node graph) {        
        boolean[] visited = new boolean[graph.v+1];
        for(int i = 1; i <= graph.v; i++) {
            visited[i] = false;
        }
        for(int i = 1; i <= graph.v; i++) {
            if(!visited[i])
                if(isCyclicUtil(graph, visited, i, -1))
                    return true;
        }
        return false;
    }

    static boolean isCyclicUtil(Node g, boolean[] vis, int u, int parent) {
        vis[u] = true;
        for(int i = 1; i <= g.v; i++) {
            if(g.adj[u][i] == 1) {
                if(!vis[i]){
                    if(isCyclicUtil(g, vis, i, u)) {
                        return true;
                    }
                }
                // If an adjacent is visited 
                // and not parent of current
                // vertex, then there is a cycle.
                else if(i != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    //Cycle in a Directed graph
    static boolean isDirectedGraphCyclic(Node graph) {

        COLOR[] color = new COLOR[graph.v+1];
        for(int i = 1; i <= graph.v; i++){
            color[i] = COLOR.WHITE;
        }

        for(int i = 1; i <= graph.v; i++) {
            if(color[i] == COLOR.WHITE){
                if(isDirectedGraphCyclicUtil(graph, i, color)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isDirectedGraphCyclicUtil(Node g, int u, COLOR[] color){
        color[u] = COLOR.GRAY;
        for(int i = 1; i <= g.v; i++){
            if(g.adj[u][i] == 1) {

                if(color[i] == COLOR.GRAY){
                    return true;
                }

                if(color[i] == COLOR.WHITE && isDirectedGraphCyclicUtil(g, i, color)){
                    return true;
                }
            }
        }
        color[u] = COLOR.BLACK;
        return false;
    }

    static int countIslands(int[][] M) {
        int r = M.length;
        int c = M[0].length;
        boolean[][] vis = new boolean[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                vis[i][j] = false;
            }
        }
        int count = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(!vis[i][j] && M[i][j] == 1) {
                    DFSIslands(M, vis, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    static void DFSIslands(int[][] M, boolean[][] v, int i, int j) {
        int r = M.length;
        int c = M[0].length;
        if(i < r && i >= 0 && j >= 0 && j < c && !v[i][j] && M[i][j] == 1) {
            v[i][j] = true;
            //i+1,j
            DFSIslands(M, v, i+1, j);
            //i-1,j
            DFSIslands(M, v, i-1, j);
            //i,j+1
            DFSIslands(M, v, i, j+1);
            //i,j-1
            DFSIslands(M, v, i, j-1);
            //i-1,j-1
            DFSIslands(M, v, i-1, j-1);
            //i-1,j+1
            DFSIslands(M, v, i-1, j+1);
            //i+1,j+1
            DFSIslands(M, v, i+1, j+1);
            //i+1,j-1
            DFSIslands(M, v, i+1, j-1);
        }
    }

    static void topologicalSort(Node g) {

        boolean[] vis = new boolean[g.v+1];
        Stack<Integer> s = new Stack<Integer>();

        for(int i = 1; i <= g.v; i++) {
            if(!vis[i]) {
                topologicalSortUtil(g, vis, s, i);
            }
        }

        while(!s.empty()){
            System.out.println(s.pop());
        }
    }

    static void topologicalSortUtil(Node g, boolean[] vis, Stack<Integer> s, int u) {
        vis[u] = true;
        for(int i = 1; i <= g.v; i++) {
            if(!vis[i] && g.adj[u][i] == 1){
                topologicalSortUtil(g, vis, s, i);
            }
        }
        s.push(u);
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

        int[][] islands = new int[4][4];
        islands[0][1] = 1;
        islands[1][1] = 1;
        islands[2][1] = 1;
        islands[3][0] = 1;
        islands[0][3] = 1;
        islands[1][3] = 1;
        islands[3][3] = 1;

        System.out.println("****** Count Islands ******" + countIslands(islands));

        Node ncgraph = new Node(5, 5);
        ncgraph.adj[1][2] = 1;
        ncgraph.adj[2][3] = 1;
        ncgraph.adj[3][4] = 1;
        ncgraph.adj[4][5] = 1;
        
        System.out.println("****** isCyclic ******" + isCyclic(graph));
        System.out.println("****** isCyclic ******" + isCyclic(ncgraph));

        Node dcgraph = new Node(6, 6);
        dcgraph.adj[1][2] = 1;
        dcgraph.adj[2][3] = 1;
        dcgraph.adj[1][3] = 1;
        dcgraph.adj[1][4] = 1;
        dcgraph.adj[4][5] = 1;
        dcgraph.adj[5][6] = 1;
        dcgraph.adj[6][4] = 1;
               
        System.out.println("****** isDirectedCyclic ******" + isDirectedGraphCyclic(dcgraph));
        System.out.println("****** isDirectedCyclic ******" + isDirectedGraphCyclic(ncgraph));

        Node topoGraph = new Node(7, 7);
        topoGraph.adj[1][3] = 1;
        topoGraph.adj[2][3] = 1;
        topoGraph.adj[3][4] = 1;
        topoGraph.adj[2][5] = 1;
        topoGraph.adj[4][6] = 1;
        topoGraph.adj[5][6] = 1;
        topoGraph.adj[6][7] = 1;
        System.out.println("****** topologicalSort ******");
        topologicalSort(topoGraph);
    }
}

enum COLOR {
    WHITE, GRAY, BLACK
}