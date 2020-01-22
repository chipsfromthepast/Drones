package drone;

import java.util.Random;

public enum Direction {
    North,
    South,
    East,
    West;

    //selects a random direction from Direction values
    public static Direction getRandom(){
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    //moves clockwise from east to west
    
    
    
    
    
    
    public Direction next(Direction direction) {
        if(direction == Direction.North){
            direction = Direction.East;
        }else  if(direction == Direction.East){
            direction = Direction.South;
        }else if(direction == Direction.South){
            direction = Direction.West;
        }else {
            direction = Direction.North;
        }

        return direction;
    }


    //converts string to Direction
    public static Direction fromString(String s){
        s = s.trim().toLowerCase();
        if(s.equals("north")){
            return Direction.North;
        } else if(s.equals("east")){
            return Direction.East;
        } else if(s.equals("south")){
            return Direction.South;
        } else if(s.equals("west")) {
            return Direction.West;
        }else{
            return null;
        }
    }
}
