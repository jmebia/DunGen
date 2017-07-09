import dungeonGenerator.Generator;

/**
 * Created by jm on 7/7/17.
 */
public class Main {

    public static void main(String[] args) {

        Generator generator = new Generator(60,6, 4);
        generator.generateDungeon();

        int[][] dungeon = generator.getDungeon();

        for (int x = 0; x < dungeon.length; x++) {
            for (int y = 0; y < dungeon.length; y++) {
                System.out.print((dungeon[y][x] == 1? "0 " : (dungeon[y][x] == 2? "# " : "  ")));
            }
            System.out.println();
        }

    }

}
