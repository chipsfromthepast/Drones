package Drones;

import javafx.scene.paint.Color;

/**
 *class to create the player/paddle object
 */
public class Player extends Drone {

    /**Set player drone/paddle size ir at ix,iy position in the arena and determine colour of object
     * @param ix
     * @param iy
     * @param ir
     */
    public Player(double ix, double iy, double ir) {
        super(ix, iy, ir);
        colour = "BLACK";    
    }
 
    /**
     * draw a rectangle into the canvas at point x,y
     * @param mc
     */
    public void drawDrone(ObjectCanvas mc) {
        mc.drawRect(x, y, radian, colour);
    }

    /**
     *  to check drone/ball but has nothing to do
     */
    @Override
    protected void checkDrone(Arena a) {
        // nothing to do
    }

    /**
     *  to adjust drone/ball but has nothing to do
     */
    @Override
    protected void adjustDrone() {
        // nothing to do
    }
    /**
     *  return string description as player
     */
    protected String getStrType() {
        return "Player";
    }
}
