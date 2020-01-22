package drone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArenaStorage {

    // takes a drone area object, and converts it a Json object
    JSONObject objectToJson(DroneArena d){
        JSONObject obj = new JSONObject();
        obj.put("areaX", d.getSizeX());
        obj.put("areaY", d.getSizeY());

        JSONArray arr = new JSONArray();
        //loops through each drone stored in drones array
        for (int i = 0; i < d.getDronesCount(); i++) {
            JSONObject droneObj = new JSONObject();
            try{
                Drone tmpDrone = d.getDrones().get(i);
                droneObj.put("id", tmpDrone.getID());
                droneObj.put("xPos", (int)tmpDrone.getPositionX());
                droneObj.put("yPos", tmpDrone.getPositionY());
                droneObj.put("direction", tmpDrone.getDirection().toString());

                //adds drone object to drones array
                arr.add(i, droneObj);
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
            //adds all drones into json object
            obj.put("Drones",arr);
        }
        return obj;
    }

    // takes json file and converts it to DroneArea with all drones
    DroneArena JsonToObject(JSONObject jo){
        DroneArena d1;
        try{
            Long x = (Long) jo.get("areaX");
            Long y = (Long) jo.get("areaY");

            //Create new drone arena with x,y from json
            d1 = new DroneArena(x.intValue(),y.intValue());

            JSONArray drones = (JSONArray) jo.get("Drones");
            //loops through each drone in json array
            for (int i = 0; i <drones.size() ; i++) {
                JSONObject droneObj = (JSONObject) drones.get(i);
                Long xPos = (Long) droneObj.get("xPos");
                Long yPos = (Long) droneObj.get("yPos");
                String dir = (String) droneObj.get("direction");
                Direction d = Direction.fromString(dir);

                //adds drone to arena
                d1.addDrone(xPos.intValue(), yPos.intValue(), d);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        System.out.println("Loaded");
        return d1;
    }

    //writes json file to specified file in working directory
    void writeToFile(JSONObject js, String fileName){
        try{
            fileName+=".txt";
            FileWriter f = new FileWriter(fileName);
            f.write(js.toJSONString());
            f.flush();
            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads json object from specified file
    JSONObject readFromFile(String file){
        JSONParser parser = new JSONParser();
        try{
            Object ob1 = parser.parse(new FileReader(file+".txt"));
            JSONObject oj = (JSONObject) ob1;
            return oj;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
