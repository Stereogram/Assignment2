/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

/**
 * Dijkstra's shortest path implementation.
 * @author Rian Neufelt a00723442
 * @date 2/12/2012
 */
public class Dijkstra {
    
    private int[][] g;
    private AdjGraph a;
    private final static int infinity = Integer.MAX_VALUE;
    private int lab;
    
    /**
     *
     * @param a Completed unweighted, ~directed~ graph
     */
    public Dijkstra(AdjGraph a) {
        this.a = a;
        g = a.getGraph();
        lab = smallest();
    }
    /*
     * generates a 'best distance found' array to each vertex from 's'
     */
    private int[] generate(int s) {
        int u, v;
        int[] n;
        int[] bdf = new int[g.length];
        boolean[] visited = new boolean[g.length];
        for(int i = 0; i < g.length; i++) {
            bdf[i] = infinity;//all distances are 'unknown'
        }
        bdf[s] = 0;//except known start location
        for(int i = 0; i < bdf.length; i++) {//for each vertex...
            v = next(bdf, visited);// gets the next candidate vertex(replaces PQ)
            visited[v] = true;
            n = a.neighbors(v);
            for(int j = 0; j < n.length; j++) {//for each neighbor...
                u = n[j];
                if (bdf[v] + 1 < bdf[u]) {//+1 for unweighted
                    bdf[u] = bdf[v] + 1;
                }
                if( Timer.getElapsed() >= 19.5) {
                    return null;
                }
            }
        }
        return bdf;
    }
    //returns the index of the next vertex which hasn't been visited already
    private static int next (int[] bdf, boolean[] v) {
        int x = infinity;
        int y = -1;
        for(int i = 0; i < bdf.length; i++) {//for each unvisited vertex
           if(!v[i] && bdf[i] <= x) {//with a 'known' lowest distance
               y = i;//set return to such index
               x = bdf[i];
           }
        }
        return y;
    }
    /*
     * finds the starting point with the smallest, 'longest length' to all nodes.
     */
    private int smallest() {
        int t, min = infinity, r = 0;
        int[] n;
        a.convert();//converts from directed graph to undirect to make algorithm work.
        for(int i = 0; i < g.length; i++) {
            if( (n = generate(i)) == null) {
                return -1;//if over time limit.
            }
            t = largest(n) ;
            if(t < min) {
                min = t;
                r = i;
            }
        }
        return r;
    }
    /*
     * finds the longest length
     */
    private int largest(int [] a) {
        int max = 0;
        for(int i = a.length-1; i >= 0 ; i--) {//to keep 'first' largest.
            if(a[i] > max) {
                max = a[i]; 
            }
        }
        return max;
    }
    /**
     *
     * @return the lab that should be the data-center
     */
    public int getLab() {
        return (lab+1);
    }
}
