package Graphs;

import java.util.LinkedList;

public class GraphList {

    static class NodeList {
        int v;
        LinkedList<Integer>[] adj;
        NodeList(int v) {
            this.v = v;
            this.adj = new LinkedList[v+1];
            for(int i = 0; i <= v; i++) {
                if(this.adj[i] == null) {
                    this.adj[i] = new LinkedList<Integer>();
                } 
            }
        }
    }

    static void DFS(NodeList graph, boolean[] visited, int index) {
        visited[index] = true;
        System.out.println("Node : " + index);
        for (int i = 1; i <= 5; i++) {
            System.out.println("index : " + i + " visited : " + visited[i]);
        }
        for(int u = 1; u <= graph.v; u++) {
            if(!visited[u] && graph.adj[index].contains(u)) {
                DFS(graph, visited, u);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(" ********* Welcome to list graphs ******");
        NodeList graph = new NodeList(5);
        graph.adj[1].add(2);
        graph.adj[2].add(3);
        graph.adj[3].add(4);
        graph.adj[4].add(5);
        graph.adj[3].add(5);
        System.out.println("****** DFS Traversal ********");
        boolean[] visited = new boolean[5+1];
        for(int i = 0; i <= graph.v; i++) {
            visited[i] = false;
        }

        for(int i = 1; i <= graph.v; i++) {
            if(!visited[i]) {
                DFS(graph, visited, i);
            }
        }
    }

    
}
