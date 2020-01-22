package Drones;

import javafx.scene.paint.Color;

/**
 * class to create drone/ball objects
 */
public class Balls extends Drone {

    double Angle, Speed;			// angle and speed of travel
    
    /** Create balls, size ir ay ix,iy, moving at angle ia and speed is and determine colour
     * @param ix
     * @param iy
     * @param ir
     * @param ia
     * @param is
     */
    public Balls(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        Angle = ia;
        Speed = is;
        colour = "BLACK";
    }
    
    /**
     * draw a circle onto the canvas at point x,y
     * @param mc
     */
    public void drawDrone(ObjectCanvas mc) {
        mc.drawCircle(x, y, radian, colour);
    }

    /**
     * change angle of the ball/drone if hitting wall or another ball
     * @param a   ballArena
     */
    @Override
    protected void checkDrone(Arena a) {
        Angle = a.CheckDroneAngle(x, y, radian, Angle, droneID);
    }

    /**
     * adjust the ball/drone depending on its speed and angle
     */
    @Override
    protected void adjustDrone() {
        double radAngle = Angle*Math.PI/180;		// angle in radians
        x += Speed * Math.cos(radAngle);		// new X,Y coordinates
        y += Speed * Math.sin(radAngle);		
    }
    /**
     * return string defining the drone
     */
    protected String getStrType() {
        return "Drone";
    }

}
