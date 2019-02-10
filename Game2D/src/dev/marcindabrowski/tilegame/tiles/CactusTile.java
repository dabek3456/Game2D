package dev.marcindabrowski.tilegame.tiles;

import dev.marcindabrowski.tilegame.gfx.Assets;

public class CactusTile extends Tile {


    public CactusTile( int id) {
        super(Assets.cactus, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
