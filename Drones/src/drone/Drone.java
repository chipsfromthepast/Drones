package drone;

import javax.swing.*;
import java.util.Random;

public class Drone {
	//initializes necessary variables for this class
    private int positionX;
    private int positionY;
    private int ID;
    private static int UID;
    private Direction direction;
//simple function that return the corresponding variable
    public int getPositionX() {
        return positionX;
    }
  //simple function that sets the corresponding variable
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
  //simple function that sets the corresponding variable
    public int getPositionY() {
        return positionY;
    }
  //simple function that sets the corresponding variable
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
  //simple function that return the corresponding variable
    public int getID() {
        return ID;
    }
  //simple function that return the corresponding variable
    public void setID(int ID) {
        this.ID = ID;
    }
  //simple function that return the corresponding variable
    public static int getUID() {
        return UID;
    }
  //simple function that sets the corresponding variable
    public static void setUID(int UID) {
        Drone.UID = UID;
    }
  //simple function that return the corresponding variable
    public Direction getDirection() {
        return direction;
    }
  //simple function that sets the corresponding variable
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //constructs the drone object and the parameters within it
    public Drone(int x, int y, Direction d){
        this.positionX = x;
        this.positionY = y;
        this.direction = d;
        this.UID++;
        this.ID = this.UID;
    }

    public Drone(){
    }

    //checks if there is a drone true or false(boolean)
    boolean isHere(int x, int y){
        return this.positionX == x && this.positionY == y;
    }

    //toString override to display drones location
    public String toString(){
        return "Drone " + this.ID +
                " is at " + this.positionX +","+this.positionY+
                " and is looking " +this.direction;
    }

    //shows location on drone on ConsoleCanvas
    void displayDrone(ConsoleCanvas c){
        c.showIt(this.positionX, this.positionY, "d");
    }

    //try to move drone 1 position in current direction
    boolean tryToMove(DroneArena area){
        int nx = this.positionX;
        int ny = this.positionY;

        //pushes drone into the next position
        if(this.direction == Direction.North){
            ny++;
        }else if(this.direction == Direction.East){
            nx++;
        }else if(this.direction == Direction.South){
            ny--;
        }else if(this.direction == Direction.West){
            nx--;
        }

        //looks to see if drone can be moved here
        if(area.canMoveHere(nx, ny)){
            //moves drone to new position
            this.positionX = nx;
            this.positionY = ny;
            return true;
        }else{
            //change direction if one way is not available
            this.direction = direction.next(this.direction);
            return false;
        }
    }
}
