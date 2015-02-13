/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Represents a graph as a adjacency matrix.
 * @author Rian Neufelt a00723442
 * @date 2/12/2012
 */
public class AdjGraph {
    
    /**
     * directed/undirected
     */
    public boolean directed;
    private int[][] graph;
    private int length;

    /**
     * the constructor allocates space for a graph with V vertices 
     * (ie: it creates (minimally) a VxV matrix).
     * @param V size of graph.
     */
    public AdjGraph(int V) {
        length = 0;//how many edges have been added.
        graph = new int[V][V];
        directed = true;
    }

    /**
     * converts directed to undirected
     */
    public void convert() {
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 1) {
                    graph[j][i] = 1;
                }
            }
        }
    }
    
    /**
     * 
     * @param v vertex
     * @return int array neighbors of v
     */
    public int[] neighbors(int v) {
        List<Integer> t = new ArrayList<>();
        for(int i = 0; i < graph.length; i++) {
            if(graph[v][i] == 1) {
                t.add(i);
            }
        }
        return toIntArray(t);
    }
    //convert collection to int[].
    private int[] toIntArray(Collection<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list) {
            ret[i++] = e.intValue();
        }
        return ret;
    }
    
    /**
     * debug for printing out collections
     * @param c
     */
    public void print(Collection<Integer> c) {
        for(Integer s: c) {
            System.out.print((s+1) + " ");
        }
        System.out.println("");
    }

    
    /**
     * Note: if you want to have a more general implementation you should probably
     * define Vertex and Edge objects, and make your Graph a VxV matrix of Edge objects 
     * (as opposed to a VxV matrix of ints 
     * the addEdge method allows you to define an edge from vertex u to vertex v
     * where the vertices are identified by integers 0..V-1
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        if(!directed) {
            graph[v][u] = 1;
            length++;
        }
        graph[u][v] = 1;
        length++;
    }
    
    
    /**
     * alternatively you might do: void addEdge(Vertex u, Vertex v);
     * (If you do, the Vertex object will need to know its row/column in the adjacency matrix.)
     * the toString method returns the adjacency matrix as a string with one line of
     * output for each vertex, for example:
     * 0 1 1
     * 1 0 0
     * 1 0 0
     * your method should print the graph exactly as shown, ie, with spaces and 
     * new lines after each row
     * @return
     */
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < graph.length; i++) {
            s += "\n";
            for(int j = 0; j < graph[i].length; j++) {
                s += ( Integer.toString(graph[i][j]) + " ");
            }
            
        }
        s += "\n";
        return s;
    }
    
    /**
     * the degree method returns the degree of the specified vertex, for example:
     *     int d = myGraph.degree(0);  // assume myGraph is the first graph shown above
     *     System.out.println("degree[0]="+ d); // prints "degree[0]=3"
     * or you could have int degree(Vertex v); if you create a Vertex class
     * @param v
     * @return
     */
    public int degree(int v) {
        int n = 0;
        for(int i = 0; i < graph[v].length; i++) {
            if(graph[v][i] == 1) {
                n++;
            }
        }
        return n;
    }
    /**
     *
     * @param v
     * @return
     */
    public int inDegree(int v) {
        int n = 0;
        for(int i = 0; i < graph.length; i++) {
            if(graph[i][v] == 1) {
                n++;
            }
        }
        return n;
    }
    /**
     *
     * @param v
     * @return
     */
    public int outDegree(int v) {
        return degree(v);
    }
     
    /**
     * example:
     *     myGraph.directed = true;  // make myGraph a directed graph
     *     boolean d = myGraph.isDirected();
     *     System.out.println("directed=" + d); // prints "directed=true"
     * @return
     */
    public boolean isDirected() {
        return directed;
    }
    
    /**
     *
     * @return
     */
    public int[][] getGraph() {
        return graph;
    }
    
    /**
     * number of edges this graph has
     * @return
     */
    public int length() {
        return length;
    }
}
