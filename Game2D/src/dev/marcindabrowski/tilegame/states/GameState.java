package dev.marcindabrowski.tilegame.states;

import dev.marcindabrowski.tilegame.Handler;
import dev.marcindabrowski.tilegame.entities.creatures.Player;
import dev.marcindabrowski.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"C:\\Users\\Marcin\\JavaProjects\\" +
                "untitled\\Game2D\\res\\worlds\\world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 32, 32);

    }

    @Override
    public void tick() {
        player.tick();
        world.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
        player.render(graphics);
    }
}
