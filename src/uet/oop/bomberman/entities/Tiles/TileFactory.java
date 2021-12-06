package uet.oop.bomberman.entities.Tiles;

import uet.oop.bomberman.graphics.Sprite;

public class TileFactory {
    private TileFactory() {

    }

    public static void getTile(int i, int j, char c) {
        switch (c){
            case '#':
                new Wall(i, j, Sprite.wall.getFxImage());
                break;
            case '*':
                new Brick(i, j, Sprite.brick.getFxImage());
                break;
            default:
                new Grass(i, j, Sprite.grass.getFxImage());
                break;
        }
    }
}
