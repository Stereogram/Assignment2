/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.util.Comparator;

/**
 * represents an edge.
 * obvious getters/setters should be obvious.
 * @author Rian Neufelt a00723442
 * @date 24/11/2012
 */
public class Cable {
    private int source;
    private int destination;
    private double weight;
    
    /**
     *
     * @param a
     * @param b
     */
    public Cable(int a, int b) {
        source = a;
        destination = b;
    }
    /**
     *
     * @param a
     * @param b
     * @param c
     */
    public Cable(int a, int b, double c) {
        this(a, b);
        weight = c;
    }
    
    /**
     *
     * @return
     */
    public int getSource() {
        return source;
    }
    /**
     *
     * @param a
     */
    public void setSource(int a) {
        source = a;
    }
    /**
     *
     * @return
     */
    public int getDestination() {
        return destination;
    }
    /**
     *
     * @param a
     */
    public void setDestination(int a) {
        destination = a;
    }
    /**
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }
    /**
     *
     * @param a
     */
    public void setWeight(double a) {
        weight = a;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return (source+1) + " " + (destination+1);
    }
    
    /**
     * For use in a priority queue sorts elements by weight
     */
    public static class WeightComparator implements Comparator<Cable> {
        /**
         *
         * @param a
         * @param b
         * @return sorts by weight; -1 if a < b; 1 if a > b; else 0.
         */
        @Override
        public int compare(Cable a, Cable b){    
           return a.getWeight() == b.getWeight() ? 0 : a.getWeight() < b.getWeight() ? -1 : 1;
        }
    }
    /**
     * For use in a priority queue when printing sorts elements by source then destination
     */
    public static class SourceComparator implements Comparator<Cable> {
        /**
         *
         * @param a
         * @param b
         * @return sorts by source, then destination
         */
        @Override
        public int compare(Cable a, Cable b){    
           return a.getSource() == b.getSource() ? a.getDestination() == b.getDestination()? 0 : a.getDestination() < b.getDestination()? -1: 1 : a.getSource() < b.getSource() ? -1 : 1;
        }
    }
}
