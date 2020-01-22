package Drones;

import javafx.scene.paint.Color;
/**
 * class to create static objects for the ball to bounce off
 */
public class Obstacle extends Drone {

    /**
     * draws the obstacle of those attributes in the specified colour
     * @param ix
     * @param iy
     * @param ir
     */
    public Obstacle(double ix, double iy, double ir) {
        super(ix, iy, ir);
        colour = "BLACK";
    }
    
    /**
     * draw a rectangle onto the arena canvas
     * @param mc
     */
    public void drawDrone(ObjectCanvas mc) {
        mc.drawRect(x, y, radian, colour);
    }
    /** 
     * gets the string of the object displayed as obstacle
     */
    
    protected String getStrType() {
        return "Obstacle";
    }

    /** 
     * checks obstacle ball/drone but has no function
     */
    protected void checkDrone(Arena a) {
        // nothing to do
    }

    /** 
     * adjusts obstacle ball/drone has no function
     */
    protected void adjustDrone() {
        // nothing to do
    }
}
