package drone;

import org.json.simple.JSONObject;

import java.util.Scanner;

class DroneInterface {
	//initializes necessary variables for this class
    private Scanner s;								// scanner used for input from user
    private DroneArena myArena;				// arena in which drones are shown
    private  ArenaStorage store;
    private Direction direction;
    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface() {
        s = new Scanner(System.in);			// set up scanner for user input
        myArena = new DroneArena(10, 20);	// create arena of size 20*6
        store = new ArenaStorage();

        char ch = ' ';
        do {
            System.out.print("Enter (A)dd drone, get (I)nformation, (D)isplay Drones, (M)ove drones 10 times, (B)ild new arena, (S)ave arena, (L)oad arena or e(X)it > ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'A' :
                case 'a' :
                    myArena.addDrone();	// add a new drone to arena
                    break;
                case 'I' :
                case 'i' :
                    System.out.print(myArena.toString());
                    break;
                case 'x' : 	
                case 'X':
                	System.exit(0);// when X detected program ends
                    break;
                case 'd':
                case 'D':
                    this.toDisplay();
                    break;
                case 'm':
                case 'M':
                    try {
                        xMoves(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'b':
                case 'B':
                    makeNewBuilding();
                    break;
                case 's':
                case 'S':
                    storeArena();
                    break;
                case 'l':
                case 'L':
                    loadArena();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);
            }
        } while (ch != 'X');						// test if end

        s.close();									// close scanner
    }

    void loadArena() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter the name for the arena: ");
        String params = sc.nextLine();
        JSONObject oj = store.readFromFile(params);
        myArena = store.JsonToObject(oj);
    }

    void storeArena(){
        try{
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            System.out.print("Enter the file name for the arena: ");
            String params = sc.nextLine();  // Read user input
            JSONObject jo = store.objectToJson(myArena);
            if(jo != null){
                store.writeToFile(jo, params.trim());
            }else{
                throw new Exception("Failed to store arena");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    void xMoves(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            myArena.moveAllDrones(myArena);
            toDisplay();
            Thread.sleep(200,1);
        }
    }

    void toDisplay() {
        ConsoleCanvas c = new ConsoleCanvas(myArena.getSizeX(), myArena.getSizeY());
        myArena.showDrones(c);
        System.out.println(c.toString());
    }

    public DroneArena makeNewBuilding(){
        int x,y;
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter x,y for new building size (x,y): ");
        String params = sc.nextLine();  // Read user input

        try{
            String[] p1 = params.split(",");
            x = Integer.parseInt(p1[0].trim());
            y = Integer.parseInt(p1[1].trim());
            if (x < 3 || y < 3) {
                throw new Exception("Needs to be 3,3 or larger");
            }
            this.myArena = new DroneArena(x,y);
            return myArena;
        } catch (Exception ex){
            System.out.println("value entered incorrectly, creating default size");
            this.myArena = new DroneArena();
            return myArena;
        }
    }

    public static void main(String[] args) {
        DroneInterface r = new DroneInterface();	// just call the interface
    }

}
