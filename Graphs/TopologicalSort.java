package Graphs;

import java.util.LinkedList;
import java.util.Queue;

import Graphs.Graph.Node;

public class TopologicalSort {
    /**
     * Topological sort has an ordering of vertices in a directed acyclic graph
     * in which each node comes before all node to which it has an out going edges
     * @param args
     */
    
    public static void main(String[] args) {
        System.out.println("Topological Sorting");

        Node g = new Node(8, 9);
        g.adj[1][4] = 1;
        g.adj[1][5] = 1;
        g.adj[2][4] = 1; 
        g.adj[3][5] = 1;
        g.adj[3][8] = 1;
        g.adj[4][6] = 1;
        g.adj[4][7] = 1;
        g.adj[4][8] = 1;
        g.adj[5][7] = 1;
        
        topologicalSort(g);
        

    }
    
    public static void indegree(Node g, int[] arr) {
        System.out.println("indegree");
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        for(int u = 1; u <= g.v; u++) {
            for(int v = 1; v<= g.v; v++) {
                if(g.adj[u][v] == 1) {
                    arr[v]++;
                }
            }
        }
    }

    public static void topologicalSort(Node g){

        int[] indegree = new int[g.v + 1];
        indegree(g, indegree);

        

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= g.v; i++){
            System.out.println("vertex : " +  i +  " indegree : " +  indegree[i]);
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;
        while(!q.isEmpty()) {

            int temp = q.poll();
            System.out.print(temp + " ");
            count++;

            for(int j = 1; j <= g.v; j++) {
                
                //If path exists from temp to any node then reduce the indegree of that vertex
                if(g.adj[temp][j] == 1) {
                    indegree[j]--;
                    if(indegree[j] == 0) {
                        q.add(j);
                    }
                }
            }
        }

        if(count != g.v) {
            System.out.println("Graph has a cycle not a directed acyclic graph");
        }
    }
}
