package Drones;

/**
 * class to create the moving blocking drone
 */
public class Enemy extends Drone {
    private int score;
    private int direction = 1; // Determines left / right direction for target (-1 means left, 1 means right)
    private double speed = 3;
    /**
     * determines direction and speed of the enemy drone
     */


    /**
     * sets up the score and colour of the created enemy drone
     * @param ix
     * @param iy
     * @param ir
     */
    public Enemy(double ix, double iy, double ir) {
        super(ix, iy, ir);
        score = 0;
        colour = "MAROON";
    }
    /**
     * return string defining the object as an enemy
     */
    protected String getStrType() {
        return "enemy";
    }

    /**
     * check ball/drone in the arena
     * @param a BallArena
     */
    @Override
    protected void checkDrone(Arena a) {
        if (a.checkHit(this)) {
        	score++;			// if been hit, then increase score
        }
        if (a.inBorder(this) == false) {
        	direction = -direction;
        }
    }
    /**
     * draw Ball and display score
     */
    public void drawDrone(ObjectCanvas mc) {
        super.drawDrone(mc);
        mc.showInt(x, y, score);
    }

    /**
     * adjust the drone to a new direction
     */
    @Override
    protected void adjustDrone() {
        x += speed * direction;				
    }
  
}
