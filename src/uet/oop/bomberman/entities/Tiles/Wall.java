package uet.oop.bomberman.entities.Tiles;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        gameMap.tiles[y][x] = this;
    }

    @Override
    public void update() {

    }
    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
