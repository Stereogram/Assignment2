/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File reader object, opens files and parses data into appropriate data structures.
 * @author Rian Neufelt a00723442
 * @date 24/11/2012
 */
public class Reader {
    
    private ArrayList<List<Lab>> labs;
    private ArrayList<Cable> cables;
    private Scanner in;
    private ArrayList<AdjGraph> g;
    
    /**
     * Initializes data structures for reading.
     * @param is stream to read input from
     */
    public Reader(InputStream is)
    {
        g = new ArrayList<>();
        in = new Scanner(is);
    }
    /**
     *debug prints recorded info.
     */
    public void print() {
        int j;
        for(List<Lab> l: labs) {
            j = 0;
            for(Lab a: l ) {
                j++;
                System.out.println("lab: " + j + " " + a.getX() + ", " + a.getY());
            }
            System.out.println("");
        }
    }
    /**
     * returns the labs
     * @return each lab in each test case
     */
    public ArrayList<List<Lab>> getLabs() {
        return labs;
    }
    /**
     * reads input and places data into appropriate data structures.
     */
    public boolean read() {
        try {
            int i,j,k,lab = 0,c = 0, min, max;
            int test = in.nextInt();
            labs = new ArrayList<>();
            for(i = 0; i < test; i++) {
                if( in.hasNextInt() ) {
                    lab = in.nextInt();
                }
                if( in.hasNextInt() ) {
                    c = in.nextInt();
                }
                labs.add( new ArrayList<Lab>(lab) );
                cables = new ArrayList<>(c);
                for(j = 0; j < lab; j++) {//reads the next 'lab' labs into 'labs'
                    labs.get(i).add(new Lab(in.nextInt(),in.nextInt()));
                }
                for(k = 0; k < c; k++) {//reads the next 'c' cables into 'cables'
                    cables.add(new Cable(in.nextInt(), in.nextInt()));
                }
                g.add(new AdjGraph(lab));//creates a new graph
                for(Cable a: cables) {
                    min = a.getSource() < a.getDestination()? a.getSource() : a.getDestination();// to ensure cables are added with a lower source then destination
                    max = a.getSource() < a.getDestination()? a.getDestination() : a.getSource();// eg. 1 2 instead of 2 1
                    g.get(i).addEdge(min-1, max-1);//adds pre placed cables to the graph
                }
            }
            in.close();
            return true;
        } catch (OutOfMemoryError oome) {
            return false;
        }
    }
    
    /**
     *
     * @return each graph in each test case
     */
    public ArrayList<AdjGraph> getGraphs() {
        return g;
    }
}
