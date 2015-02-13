/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

/**
 * Timer class to keep track of how long 'something' has been running.
 * @author Rian Neufelt a00723442
 * @date Oct 14, 2012
 */
public class Timer {
    
    private static double tm;
    
    /**
     * Returns the elapsed time since the timer was 'started'.
     * @return time in milliseconds.
     */
    public static double getElapsed() {
        return (System.nanoTime()-tm)/(1000000000.0);
    }
    /**
     * starts the timer.
     */
    public static void start() {
        tm = System.nanoTime();
    }
}
