package dev.marcindabrowski.tilegame.entities.creatures;

import dev.marcindabrowski.tilegame.Handler;
import dev.marcindabrowski.tilegame.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 8;
        bounds.y = 16;
        bounds.width = 16;
        bounds.height = 16;
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) {
            yMove = -speed;
        }
        if(handler.getKeyManager().down) {
            yMove = speed;
        }
        if(handler.getKeyManager().left) {
            xMove = -speed;
        }
        if(handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        graphics.setColor(Color.blue);
        graphics.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                          (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                          bounds.width, bounds.height);

    }
}
