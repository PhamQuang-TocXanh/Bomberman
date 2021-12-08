package uet.oop.bomberman.entities.Character;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Character.Enemy.AutoBot;
import uet.oop.bomberman.entities.Character.Enemy.AutoBot1;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Random;

public abstract class Character extends AnimatedEntity {
    protected int direction = -1; //0: up, 1: right, 2: down, 3: left
    protected boolean alive = true;
    protected boolean moving = true;
    public int timeAfter = 40;
    protected int velocity;
    protected Rectangle2D bounds;
    protected boolean wallPass = false;
    protected boolean bombPass = false;
    protected boolean flamePass = false;
    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public abstract void update();
    @Override
    public boolean collide(Entity e) {
        return true;
    }

    protected abstract void calculateMove();


    protected boolean canMove(int xMove, int yMove) {
        int xTile;
        int yTile;
        if (xMove > 0) {//move right
            xTile = (int) (x + xMove + bounds.getMinX() + bounds.getWidth())/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    return false;
                }
            }
        } else if (xMove < 0) { //move left
            xTile = (int) (x + bounds.getMinX() + xMove)/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    return false;
                }
            }
        }

        if (yMove < 0) { // move up
            yTile = (int) (y + bounds.getMinY() + yMove)/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                xTile = (int) (x + bounds.getMinX() + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    return false;
                }
            }
        } else if (yMove > 0) { //move down
            yTile = (int) (y + yMove + bounds.getMinY() + bounds.getHeight())/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                xTile = (int) (x + bounds.getMinX() + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Entity collision(int xa, int ya) {
        List<Entity> l = BombermanGame.stillObjects;
        int autoY = autoCorrectPosition(ya);
        int autoX = autoCorrectPosition(xa);
        Entity e = null;

        if (direction == 0) {
            int yy = (ya) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getY() == yy && entity.getX() == autoX) {
                    e = entity;
                }
            }
        } else if (direction == 1){
            int xx = (x + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getX() == xx && entity.getY() == autoY) {
                    e = entity;
                }
            }
        } else if (direction == 2) {
            int yy = (y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getY() == yy && entity.getX() == autoX) {
                    e = entity;
                }
            }
        } else if (direction == 3) {
            int xx = (xa) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getX() == xx && entity.getY() == autoY) {
                    e = entity;
                }
            }
        }
        return e;
    }

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract void chooseSprite();

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isWallPass() {
        return wallPass;
    }

    public boolean isBombPass() {
        return bombPass;
    }

    public boolean isFlamePass() {
        return flamePass;
    }

    public int autoCorrectPosition(int a) {
        int temp = a;
        for (int i = a - Sprite.SCALED_SIZE / 4; i <= a + Sprite.SCALED_SIZE /4; i++) {
            if (i == a / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE
                    || i == a / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE) {
                temp = i; break;
            }
        }

        return temp;
    }
}
