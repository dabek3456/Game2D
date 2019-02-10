package dev.marcindabrowski.tilegame;

public class Launcher {

    public static void main(String[] args) {

        Game game = new Game("Title", 320, 320);
        game.start();
    }
}
