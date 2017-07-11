package dungeonGenerator;

import java.awt.*;

/**
 * Created by jm on 7/7/17.
 */

public class Room {

    // external element
    private final int id;

    // elements of this room
    private final int x; // position x
    private final int y; // position y
    private final int width;
    private final int height;
    private final Point center = new Point();

    public Room(int x, int y, int width, int height, int id) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        center.setLocation(x + width / 2,y + height / 2);

        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }

}
