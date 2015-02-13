/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Distance object generates/holds the distances between every lab.
 * @author Rian Neufelt, a00723442
 * @date Oct 14, 2012
 */
public class Distance {
    
    private double[][] d;
    private int n;
    private List<Lab> list;
    private DecimalFormat df;

    /**
     * initializes variables.
     * @param a The current test file's labs.
     */
    public Distance(List<Lab> a) {
        df = new DecimalFormat("0.00");
        n = a.size();
        d = new double[n][n];
        list = a;
        generate();
    }
    
    /**
     * Fills the distances array.
     */
    private void generate() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                d[i][j] = calc( list.get(i).getX(), list.get(j).getX(), list.get(i).getY(), list.get(j).getY()  );
            }
        }
    }
    
    /**
     *
     * @return the distances array.
     */
    public double[][] getDistance() {
        return d;
    }
    
    /**
     * for debug, prints the distances array.
     */
    public void print() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(df.format(d[i][j]) + " ");
            }
            System.out.println("");
        }
    }
    /*
     * distance calculation.
     */
    private double calc(int x1, int x2, int y1, int y2 ) {
        return Math.sqrt( sqr(x2-x1) + sqr(y2-y1) );
    }
    //simple sqr function; returns n^2.
    private double sqr(double n) {
        return n * n;
    }
}
