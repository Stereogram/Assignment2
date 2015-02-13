/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rian Neufelt a00723442
 * @date 24/11/2012
 */
public class Network {
    
    private static Reader r;
    private static ArrayList<List<Lab>> labs;
    private static Kruskal k;
    private static ArrayList<AdjGraph> adj;
    
    /**
     * processes test cases.
     * @param input stream to read from
     * @param output stream to print to
     */
    public static void processTestCases(InputStream input, OutputStream output) {
        //output = System.out;
        Timer.start();//starts the timer which is checked in both algorithms
        int datacenter;
        boolean read;
        AdjGraph temp;//completed solution graph
        r = new Reader(input);//reads input from stream into...
        read = r.read();
        labs = r.getLabs();//gets labs from input
        adj = r.getGraphs();//gets graphs from input
        for(int i = 0; i < labs.size(); i++) {//for each test case...
            try {
                output.write( ("Test " + (i+1) + "\n").getBytes() );//eg. Test 01
                k = new Kruskal(adj.get(i), new Distance(labs.get(i)), output);
                if(!read || (temp = k.generate()) == null || (datacenter = new Dijkstra(temp).getLab()) == 0 ) {//Kruskal fills the completed solution graph then Dijkstra gets the data center from it.
                    output.write( (" No solution found.\n").getBytes() );//if either timer 'triggers' print no solution message.
                    break;
                }
                k.print();//print new cables that are needed to be addded.
                output.write( (" Data-centre: " + datacenter + "\n\n").getBytes() );//prints the data-center lab
            } catch ( Exception | OutOfMemoryError e) {
                try {
                    output.write( (" No solution found.\n").getBytes() );
                } catch (IOException ex1) {
                    //...
                }
            }
        }
    }
    /**
     * Program entry point
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        FileInputStream f = null;
        try {
            f = new FileInputStream("a.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
        processTestCases(f, System.out);
    }
    
}
