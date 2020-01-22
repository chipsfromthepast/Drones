package drone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DroneArena {
    //stores the rows and columns (size) of arena
    private int sizeX, sizeY;

    //Creates array list to store drones in
    private List<Drone> drones = new ArrayList<Drone>();

    public List<Drone> getDrones() {
        return drones;
    }

    int getSizeX() {
        return sizeX;
    }

    int getSizeY() {
        return sizeY;
    }

    int getDronesCount() {
        return drones.size();
    }

    int maxPossibleDrones() {
        return (getSizeX()-2) * (getSizeY()-2);
    }

    boolean canAddDrone() {
        if(getDronesCount() < maxPossibleDrones()) {
            return true;
        }else{
            return false;
        }
    }

    //Default constructor
    public DroneArena(){
       new DroneArena(10,20);
    }

    //Constructor override with specified size
    public DroneArena(int x, int y){
        this.sizeX = x;
        this.sizeY = y;
    }

    //Creates new drone in center of arena
    public void addDroneMiddle(){
        Drone d1 = new Drone(this.sizeX/2, this.sizeY/2, Direction.getRandom());
        drones.add(d1);
    }

    //Creates new drone in random position
    //Default
    void addDrone(){
        Random r1 = new Random();
        int xPosition = r1.nextInt(this.sizeX)+1;
        int yPosition = r1.nextInt(this.sizeY)+1;

        addDrone(xPosition, yPosition ,Direction.getRandom());
    }

    //addDrone override, specify position and direction
    void addDrone(int x, int y, Direction d){
        //Checks to see if drone already exists here and if in arena
        if(getDroneAt(x, y) == null && x < this.sizeX-1 && y < this.sizeY-1){
            //creates new drone in arena
            drones.add(new Drone(x, y, d));
        }else{
            try{
                //tests to see if there is space for new drone
                if(canAddDrone()){
                    //if space try to add random drone
                    addDrone();
                }else{
                    throw new Exception("Arena is full!");
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    //toString override, prints information of arena and all drones in it
    public String toString(){
        String s;
        s = "The drone area is "+this.sizeX+","+this.sizeY;
        //loop through each stored drone
        for (int i = 0; i <drones.size() ; i++) {
            s+= "\n\t"+drones.get(i).toString();
        }
        s+="\n";

        return s;
    }

    //look for drone at specific point and return the drone object if exists
    private Drone getDroneAt(int x, int y){
        for (Drone n:drones) {
            if(n.isHere(x,y)){
                return n;
            }
        }
        return null;
    }

    //Takes canvas as an argument and displays each of the drones
    void showDrones(ConsoleCanvas c){
        for (Drone d:drones) {
            d.displayDrone(c);
        }
    }

    //checks to see if drone can be placed
    boolean canMoveHere(int x, int y){
        if( x < this.sizeX-1 && y < this.sizeY-1
               && x != 0 && y != 0
               && getDroneAt(x,y) ==  null){
            //if is in bounds of arena and no drone is there
           return true;
        }else{
           return false;
        }
    }

    //try to move each of the drones
    void moveAllDrones(DroneArena area){
        for (Drone d:drones) {
           d.tryToMove(this);
        }
    }

    //move all drones n times
    public void moveAllDrones(DroneArena area, int times){
        for (Drone d:drones) {
            for (int i = 0; i < times; i++) {
                try{
                    d.tryToMove(this);
                }catch (Exception es){
                    es.printStackTrace();
                }
            }
        }
    }
}
