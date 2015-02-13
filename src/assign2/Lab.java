/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

/**
 * Simple Location class to store lab x,y coordinates.
 * @author Rian Neufelt a00723442
 * @date 24/11/2012
 */
public class Lab {
    
    private int x;
    private int y;
    
    Lab(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Sets x.
     * @param x
     */
    public void setX(int x) {
        this.x  = x;
    }
    /**
     * Sets y.
     * @param y
     */
    public void setY(int y) {
        this.y  = y;
    }
    /**
     * Gets x.
     * @return
     */
    public int getX() {
        return x;
    }
    /**
     * Sets y.
     * @return
     */
    public int getY() {
        return y;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return (String.valueOf(x) + "," + String.valueOf(y));
    }
}
