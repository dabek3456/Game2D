package dev.marcindabrowski.tilegame.entities.creatures;

import dev.marcindabrowski.tilegame.Handler;
import dev.marcindabrowski.tilegame.entities.Entity;
import dev.marcindabrowski.tilegame.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 32;
    public static final int DEFAULT_CREATURE_HEIGHT = 32;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {
        if (xMove > 0) { // moving right

            int tmp = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tmp, (int) (y + bounds.y / Tile.TILEHEIGHT)) &&
                    !collisionWithTile(tmp, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {

                x += xMove;
            }else{
                x = tmp * Tile.TILEWIDTH - bounds.x - bounds.height - 1;
            }


        } else if (xMove < 0) { // moving left

            int temp = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(temp, (int) (y + bounds.y / Tile.TILEHEIGHT)) &&
                    !collisionWithTile(temp, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {

                x += xMove;
            } else {
                x = temp * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove > 0) { // moving down

            int tmp = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x / Tile.TILEWIDTH), tmp) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, tmp)) {

                y += yMove;
            } else {
                y = tmp * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        } else if (yMove < 0) { // moving up

            int temp = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x / Tile.TILEWIDTH), temp) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, temp)) {

                y += yMove;
            } else {
                y = temp * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // getters and setters

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
