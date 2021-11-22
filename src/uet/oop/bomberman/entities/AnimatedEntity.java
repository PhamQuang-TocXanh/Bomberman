package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    protected int dx;
    protected int dy;

    protected int direction = -1;  //0: up, 1: right, 2: down, 3: left
    protected boolean alive = true;
    protected boolean moving;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected boolean canMove() {

        return true;
    }
}
