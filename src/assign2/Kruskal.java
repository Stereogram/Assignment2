/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import assign2.Cable.SourceComparator;
import assign2.Cable.WeightComparator;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Java implementation of Kruskal's MST algorithm
 * @author Rian Neufelt a00723442
 * @date 30/11/2012
 */

public class Kruskal {
    
    private PriorityQueue<Cable> pq;
    private DisjointSet djs;
    private AdjGraph g;
    private double[][] d;
    private int n;
    private PriorityQueue<Cable> s;
    private OutputStream o;
    
    /**
     * Initializes queues, sets, graphs
     * @param gr directed graph already filled with pre placed cables
     * @param di distance matrix
     * @param output output stream for printing
     */
    public Kruskal(AdjGraph gr, Distance di, OutputStream output) {
        o = output;
        d = di.getDistance();
        n = d.length;//#of vertices
        s = new PriorityQueue<>(n, new SourceComparator());//printing queue
        g = gr;
        djs = new DisjointSet();
        initializeSets();
        pq = new PriorityQueue<>(djs.length(), new WeightComparator());//algorithm queue
    }
    
    /**
     * generates a minimal spanning tree.
     * initializations already done in constructor.
     * @return completed MST
     */
    public AdjGraph generate() {
        Cable c;
        HashSet<Integer> u, v;
        if( !initializeQueue()) {
            return null;
        }
        while(g.length() < n-1) {//MST has #of vertices-1 edges
            c = pq.poll();
            u = djs.find(c.getSource());
            v = djs.find(c.getDestination());
            if(u != v) {//if they're not in the same subset (creates no cycles)
                s.add(c);//add to print queue
                g.addEdge(c.getSource(), c.getDestination());//add to solution graph
                djs.union(u, v);//add to solution set
            }
            if(Timer.getElapsed() >= 19.5) {
                return null;
            }
        }
        return g;//completed graph
    }
    /*
     * initializes queue with all possible edges(cables) with distance from the matrix.
     */
    private boolean initializeQueue() {
        try{
            for(int i = 0; i < d.length; i++) {
                for(int j = i+1; j < d[i].length; j++) {//i+1 so we don't add cables twice. eg. 1 2 and 2 1
                    pq.add(new Cable(i,j,d[i][j]));
                }
            }
        }
        catch(OutOfMemoryError oome) {
            return false;
        }
        return true;
    }
    /*
     * initializes sets with already placed cable.
     */
    private void initializeSets() {
        int[][] t = g.getGraph();
        HashSet<Integer> u, v;
        for(int i = 0; i < t.length; i++) {//default Kruskal initialization
            djs.makeSet(i);//make a set for each vertex...
        }
        for(int i = 0; i < t.length; i++) {
            for(int j = 0; j < t[i].length; j++) {
                if(t[i][j] == 1) {//if already placed cable...
                    u = djs.find(i);
                    v = djs.find(j);
                    djs.union(u, v);//...add to solution set
                }
            }
        }
    }
    
    /**
     * prints formated cables that need to be added.
     * uses a priority queue that prioritizes source then destination.
     */
    public void print() {
        try {
            if(s.isEmpty()) {
                o.write(" No new data cables required.\n".getBytes());
            }
            while(!s.isEmpty()) {
                o.write( (" " + s.poll() + "\n").getBytes() );
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
