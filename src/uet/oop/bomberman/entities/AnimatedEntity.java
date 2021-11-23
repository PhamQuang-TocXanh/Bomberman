package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    /*
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
    */

    protected int _animate = 0;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
    }
}
