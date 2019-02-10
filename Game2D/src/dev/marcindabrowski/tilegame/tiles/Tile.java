package dev.marcindabrowski.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // static stuff here

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile cactusdTile = new CactusTile(3);

    // class

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;

    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return false;
    }

    public void tick() {

    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
