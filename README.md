![Alt text](http://i.imgur.com/BbhTLjT.jpg "DunGen header")

A simple 2D, grid-based dungeon generator that is being used in our game project/thesis: https://github.com/jmebia/fireflies-game.

## How to use
1) Add the DunGen package to your project's source then import the generator class.
2) To generate a dungeon, just create a generator object, fill in the required parameters, and then call the object's ```generateDungeon()``` function: 
```java
Generator generator = new Generator(map_size, grid_count, minumum_room_size);
generator.generateDungeon();
```
3) To use the generated dungeon, call the generator's ```getDungeon()``` function. This will return a 2-dimensional integer array which contains the <em>int</em> representation of the 2D dungeon structure.:
```java
int[][] dungeon = generator.getDungeon(); 
```

<em>This project is still a <strong>Work In Progress</strong> (WIP)</em>
