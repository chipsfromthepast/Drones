package drone;

import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) {
        //
        DroneArena d = new DroneArena(10,10);
        ConsoleCanvas c = new ConsoleCanvas(d.getSizeX(), d.getSizeY());
        ArenaStorage store = new ArenaStorage();

        d.addDrone();

        System.out.println(d.toString());

        JSONObject jo = store.objectToJson(d);

        store.writeToFile(jo, "file");


        JSONObject oj = store.readFromFile("file");
        DroneArena d2 = store.JsonToObject(jo);
        //DroneArena d2 = store.JsonToObject(jo);
        System.out.println(d2.toString());
    }
}
