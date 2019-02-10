package dev.marcindabrowski.tilegame.worlds;

import dev.marcindabrowski.tilegame.Handler;
import dev.marcindabrowski.tilegame.tiles.Tile;
import dev.marcindabrowski.tilegame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height; // int tiles
    private int xSpawn, ySpawn;
    private int[][] tiles;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {

        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                getTile(x, y).render(graphics,
                        (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {

        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) {
            return Tile.dirtTile;
        }
        return t;
    }

    private void loadWorld(String path) {

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        xSpawn = Utils.parseInt(tokens[2]);
        ySpawn = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Utils.parseInt(tokens[(i + j * width) + 4]);
            }
        }

        /*  width = 5;
        height = 5;
        tiles = new int[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                tiles[x][y] = 0;
            }
        }*/

    }

    public int getWidth() {
        return width;
    }

    public  int getHeight() {
        return height;
    }
}
