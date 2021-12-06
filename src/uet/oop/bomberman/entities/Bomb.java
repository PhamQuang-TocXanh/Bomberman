package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends Entity {
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
