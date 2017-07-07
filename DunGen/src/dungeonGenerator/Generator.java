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
        // createCorridors();

        plotMap();
    }

    private void initializeMap() {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                dungeon[y][x] = 0;
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
                int roomHere = new Random().nextInt(1 - 0 + 1) + 0;

                System.out.println("Creating new room (STEP 1)...");

                if (roomHere == 1) { // generate room

                    System.out.println("Creating new room (STEP 2)...");

                    int maxRoomSize = gridSize - 1;
                    int minRoomSize = gridSize / 2 + 1;
                    // randomize room size based on the size of the grid
                    // the maximum size is the grid's size + 1 and the minimum is the grid's size / 2
                    int width = new Random().nextInt(maxRoomSize - minRoomSize) + minRoomSize;
                    int height = new Random().nextInt(maxRoomSize - minRoomSize) + minRoomSize;

                    System.out.println("Room added with size (" + width + ", " + height + ")");

                    // randomize x and y points
                    int xPos = x + 1;
                    int yPos = y + 1;

                    System.out.println("Room added at (" + x + ", " + y + ")");

                    rooms.add(new Room(xPos, yPos, width, height));

                }
            }
        }

        System.out.println("Generating rooms finished...");
    }

    private void createCorridors() {

        LinkedList<Point> points = new LinkedList<>();

        // collect all center points
        for ( Room room : rooms) {
            points.add(room.getCenter());
        }

        // have an agent run to all the centers while carving corridors


    }

    private void plotMap() {

        for (Room room : rooms) {

            System.out.println("Plotting room...");

            for (int x = room.getX(); x < room.getX() + room.getWidth(); x++) {
                for (int y = room.getY(); y < room.getY() + room.getHeight(); y++) {
                    dungeon[y][x] = 1;
                }
            }

        }

    }


    public int[][] getDungeon() {
        return dungeon;
    }

}
