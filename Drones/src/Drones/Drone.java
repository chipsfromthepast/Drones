package Drones;

import java.io.Serializable;

import javafx.scene.paint.Color;

public abstract class Drone implements Serializable {
	protected int droneID;							// unique identifier for item
    protected double x, y, radian;						// coordinates and size of the drones
    protected String colour;								// used to set colour
    static int counter = 0;						// used to give each ball a unique identifier

    Drone() {
        this(100, 100, 10);
    }
    /**
     * construct a drone/ball of radian ir at ix,iy of this colour
     * @param ix
     * @param iy
     * @param ir
     */
    Drone(double ix, double iy, double ir) {
        x = ix;
        y = iy;
        radian = ir;
        droneID = counter++;			// identifier and increment class static
        colour = "BLACK";
    }
    /**
     * return x coordinate of the ball/drone
     * @return
     */
    public double getX() { return x; }
    /**
     * return y coordinate of the ball/drone
     * @return
     */
    public double getY() { return y; }
    /**
     * return radius of the ball/drone
     * @return
     */
    public double getRad() { return radian; }
    /**
     * set the ball at position nx,ny
     * @param nx
     * @param ny
     */
    public void setXY(double nx, double ny) {
        x = nx;
        y = ny;
    }
    /**
     * return the ID of the ball/drone
     * @return
     */
    public int getID() {return droneID; }
    /**
     * paint a ball/drone onto the arena
     * @param mc
     */
    public void drawDrone(ObjectCanvas mc) {
        mc.drawCircle(x, y, radian, colour);
    }
    protected String getStrType() {
        return "Ball";
    }
    /**
     * return string describing the drone/ball
     */
    public String toString() {
        return getStrType()+" at "+Math.round(x)+", "+Math.round(y);
    }
    /**
     * abstract method for adjusting a ball/drone
     */
    protected abstract void adjustDrone();
    /**
     * abstract method for checking for a ball/drone in arena a
     * @param a
     */
    protected abstract void checkDrone(Arena a);
  
    /**
     * is an object at ox,oy size or colliding with this ball/drone
     * @param ox
     * @param oy
     * @param or
     * @return true if a hit takes place
     */
    public boolean hit(double ox, double oy, double or) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+radian)*(or+radian);
    }		// hit is made if dist between ball and ox,oy < ist rad + or

    /** has the ball/drone hit another ball/drone
     * @param oDrone - the other ball
     * @return true if a hit is made
     */
    public boolean hitting (Drone oDrone) {
        return hit(oDrone.getX(), oDrone.getY(), oDrone.getRad());
    }
}
