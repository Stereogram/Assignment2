/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Implements makeSet, find and union methods for a 'disjointsubset'
 * @author Rian Neufelt a00723442
 * @date 30/11/2012
 */
public class DisjointSet {
    
    private List<HashSet<Integer>> sets;
    
    /**
     * Initializes sets to n
     * @param n
     */
    public DisjointSet(int n) {
        sets = new ArrayList<>(n);
    }
    /**
     * Initializes sets to 0
     */
    public DisjointSet() {
        this(0);
    }
    /**
     * Adds v as a set
     * @param v
     */
    public void makeSet(int v) {
        HashSet<Integer> hs = new HashSet<>(1);//creates a new subset
        hs.add(v);//adds v to it
        sets.add(hs);//adds the subset to the list
    }
    /**
     * finds v
     * @param v element(vertex) to look for
     * @return the set if found, null otherwise
     */
    public HashSet<Integer> find(int v) {
        for(HashSet<Integer> h: sets) {//for each subset...
            if(h.contains(v)) {//searches each subset in the list of sets
                return h;
            }
        }
        return null;
    }
    /**
     * Merges two sets into one and removes the one that got merged.
     * @param x 
     * @param y
     */
    public void union(HashSet<Integer> x, HashSet<Integer> y) {
        if(x != y) {
            x.addAll(y);//add all of y to x
            sets.remove(y);//then remove y
        }
    }
    
    /**
     * debug, prints out sets
     */
    public void print() {
        for(HashSet<Integer> h: sets) {
            for(Integer i: h) {
                System.out.print(i + "");
            }
            System.out.print("|");
        }
        System.out.println("");
    }
    
    /**
     * the number of elements in the set
     * @return
     */
    public int length() {
        return sets.size();
    }
}
