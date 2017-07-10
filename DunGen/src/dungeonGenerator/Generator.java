package dungeonGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jm on 7/7/17.
 */
public class Generator {

    private ArrayList<Room> rooms = new ArrayList<>();

    private int[][] dungeon;

    private final int ROOM = 1;
    private final int CORRIDOR = 2;
    private final int VOID = 0;

    private final int gridRow;
    private final int minRoomSize;
    private final int mapSize;

    /** CONSTRUCTOR **/
    public Generator(int mapSize, int gridCount, int minRoomSize) {
        this.gridRow = gridCount;
        this.mapSize = mapSize;
        this.minRoomSize = minRoomSize;

        dungeon = new int[mapSize + 4][mapSize + 4];
    }

    /** GENERATION **/

    public void generateDungeon() {

        initializeMap();
        createRooms();
        createCorridors();

        plotMap();
    }

    private void initializeMap() {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                dungeon[y][x] = VOID;
            }
        }
    }

    private void createRooms() {

        System.out.println("Generating rooms started...");

        // calculate for the size of the grids
        int gridSize = mapSize / gridRow;

        System.out.println("Grid Size = " + gridSize);

        // iterate through every grid and place a room
        for (int x = 0; x < mapSize; x += gridSize) {
            for (int y = 0; y < mapSize; y += gridSize) {
                int roomHere = new Random().nextInt(2);

                System.out.println("Creating new room (STEP 1)...");

                if (roomHere == 1) { // generate room

                    System.out.println("Creating new room (STEP 2)...");

                    int maxRoomSize = gridSize - 1;
                    // randomize room size based on the size of the grid
                    // the maximum size is the grid's size + 1 and the minimum is the grid's size / 2
                    int width = new Random().nextInt(maxRoomSize - minRoomSize) + minRoomSize;
                    int height = new Random().nextInt(maxRoomSize - minRoomSize) + minRoomSize;

                    System.out.println("Room added with size (" + width + ", " + height + ")");

                    // randomize x and y points
                    int xPos = x + (x==0? 1 : ( x==mapSize? -1 : new Random().nextInt((gridSize - width))));
                    int yPos = y + (y==0? 1 : ( y==mapSize? -1 : new Random().nextInt((gridSize - height))));

                    System.out.println("Room added at (" + x + ", " + y + ")");

                    rooms.add(new Room(xPos, yPos, width, height));

                }
            }
        }

        System.out.println("Generating rooms finished...");
    }

    private void createCorridors() {


        // collect all center points
        // trace all centers from the list
        for ( Room room : rooms) {

            int x1 = (int) room.getCenter().getLocation().getX();
            int y1 = (int) room.getCenter().getLocation().getY();

            System.out.println("x1 = " + x1 + ", y1 = " + y1);

            int x2;
            int y2;
            try {
                Room r2 = rooms.get(rooms.indexOf(room) + 1);
                x2 = (int) r2.getCenter().getLocation().getX();
                y2 = (int) r2.getCenter().getLocation().getY();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Aborting corridor creation...");
                break;
            }

            System.out.println("x2 = " + x2 + ", y2 = " + y2);

            // trace corridor first in the X axis
            if (x1 - x2 > 0) { // if not negative
                for (int i = x2; i <= x1; i++) {
                    if (dungeon[y1][i] == VOID)
                        dungeon[y1][i] = CORRIDOR;
                }

                // trace next corridors in the Y axis
                if (y1 - y2 > 0) { // if not negative
                    for (int i = y2; i <= y1; i++) {
                        if (dungeon[i][x2] == VOID)
                            dungeon[i][x2] = CORRIDOR;
                    }
                }
                // if negative
                else {
                    for (int i = y1; i <= y2; i++) {
                        if (dungeon[i][x2] == VOID)
                            dungeon[i][x2] = CORRIDOR;
                    }
                }
            }
            // if negative
            else {
                for (int i = x1; i <= x2; i++) {
                    if (dungeon[y1][i] == VOID)
                        dungeon[y1][i] = CORRIDOR;
                }

                // trace next corridors in the Y axis
                if (y1 - y2 > 0) { // if not negative
                    for (int i = y2; i <= y1; i++) {
                        if (dungeon[i][x2] == VOID)
                            dungeon[i][x2] = CORRIDOR;
                    }
                }
                // if negative
                else {
                    for (int i = y1; i <= y2; i++) {
                        if (dungeon[i][x2] == VOID)
                            dungeon[i][x2] = CORRIDOR;
                    }
                }

            }
        }
    }

    private void plotMap() {

        for (Room room : rooms) {

            System.out.println("Plotting room ("+room.getX()+","+room.getY()+")-h="
                    +room.getHeight()+", w="+room.getWidth()+"...");

            for (int x = room.getX(); x < room.getX() + room.getWidth(); x++) {
                for (int y = room.getY(); y < room.getY() + room.getHeight(); y++) {
                    dungeon[y][x] = ROOM;
                }
            }

        }

    }


    public int[][] getDungeon() {
        return dungeon;
    }

}
