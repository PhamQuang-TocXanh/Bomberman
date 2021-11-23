package uet.oop.bomberman.entities.Character;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;

public abstract class Character extends AnimatedEntity {
    protected int direction = -1;
    protected boolean alive = true;
    protected boolean moving = true;
    public int timeAfter = 80;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public abstract void update();

    protected abstract void calculateMove();

    protected abstract void move(int xa, int ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(int x, int y);

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }
/*
    protected double getXMessage() {
        return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
    }

    protected double getYMessage() {
        return (_y* Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
    }
*/
}
