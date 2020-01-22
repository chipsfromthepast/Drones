package Drones;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to create the Arena to contain the objects
 */
public class Arena implements Serializable {
    double xSize, ySize;						// size of arena
    private ArrayList<Drone> allDrones;			// array list of all balls in arena
    /**
     * construct an arena of the specified size
     */
    Arena() {
        this(500, 400);			// default size
    }
    /**
     * construct arena of size xS by yS and the position of the starting objects
     * @param xS
     * @param yS
     */
    Arena(double xS, double yS){
        xSize = xS;
        ySize = yS;
        allDrones = new ArrayList<Drone>();					// list of all drones/balls initially empty
        allDrones.add(new Balls(xS/2, yS*0.8, 10, 45, 6));	// adds small black ball
        allDrones.add(new Enemy(xS/2, yS/5, 35));			// adds enemy drone
        allDrones.add(new Player(xS/2, yS-20, 20));		// adds player drone
        allDrones.add(new Obstacle(xS/20, 1*yS/5, 25)); // adds blocking objects/walls
        allDrones.add(new Obstacle(xS/8, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS/4, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS/3, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS, 1*yS/5, 25));
        allDrones.add(new Obstacle(2*xS/3, 1*yS/5, 25));
        allDrones.add(new Obstacle(2*xS/8, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS-100, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS-70, 1*yS/5, 25));
        allDrones.add(new Obstacle(xS-40, 1*yS/5, 25));
    }
    /**
     * return arena size in the x direction
     * @return
     */
    public double getX() {
        return xSize;
    }
    /**
     * return arena size in the y direction
     * @return
     */
    public double getY() {
        return ySize;
    }
    /**
     * draw the arena on the canvas
     * @param mc
     */
    public void drawArena(ObjectCanvas mc) {
        for (Drone b : allDrones) b.drawDrone(mc);		// draw all the balls/drones
    }
    /**
     * check all balls/drones, change angle of moving balls if needed
     */
    public void checkDrones() {
        for (Drone b : allDrones) b.checkDrone(this);	// check all the balls/drones
    }
    /**
     * adjust for the different moving drones/balls
     */
    public void adjustDrones() {
        for (Drone b : allDrones) b.adjustDrone();
    }
    /**
     * set the coordinates of the player drone
     * @param x
     * @param y
     */
    public void setPaddle(double x, double y) {
        for (Drone b : allDrones)
            if (b instanceof Player) b.setXY(x, y);
    }
    /**
     * return list of strings defining each ball/drone
     * @return
     */
    public ArrayList<String> describeAll() {
        ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
        for (Drone b : allDrones) ans.add(b.toString());			// add string defining each ball
        return ans;												// return string list
    }
    /**
     * checks the angle of the drone and its hit conditions depending on a wall or other drone/ball
     * @param x				ball x position
     * @param y				y
     * @param rad			radius
     * @param ang			current angle
     * @param notID			dont check id of the drone/ball
     * @return				new angle
     */
    public double CheckDroneAngle(double x, double y, double rad, double ang, int notID) {
        double ans = ang;
        if (x < rad || x > xSize - rad) ans = 180 - ans;
        // if ball hit (tried to go through) left or right walls, set mirror angle, being 180-angle
        if (y < rad || y > ySize - rad) ans = - ans;
        // if try to go off top or bottom, set mirror angle

        for (Drone b : allDrones)
            if (b.getID() != notID && b.hit(x, y, rad)) ans = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;
        // check all balls except one with given id
        // if hitting, return angle between the other ball and this one.

        return ans;		// return the angle
    }

    /**
     * check if the enemy drone has been hit by another ball
     * @param the enemy drone
     * @return 	true if hit
     */
    public boolean checkHit(Drone target) {
        boolean ans = false;
        for (Drone b : allDrones)
            if (b instanceof Balls && b.hitting(target)) ans = true;
        // try all balls
        return ans;
    }
    
    public boolean inBorder(Drone target) {
    	return !(target.getX() - target.getRad() < 0 || target.getY() - target.getRad() < 0 || target.getX() + target.getRad() > xSize || target.getY() + target.getRad() > ySize);
    }
    /**
     * adds a new ball/drone at the specified coordinates
     * 
     */
    public void addDrone() {
        allDrones.add(new Balls(xSize/2, ySize*0.8, 10, 60, 5));
    }
}
