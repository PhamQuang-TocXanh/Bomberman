package uet.oop.bomberman.entities.Character;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private int life;
    protected Keyboard input;
    private List<Bomb> _bombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        velocity = 2;
        sprite = Sprite.player_right;
        input = new Keyboard();
        bounds = new Rectangle2D(0, 4, 24, Sprite.SCALED_SIZE - 6);
        life = 1;
    }

    @Override
    public void update() {
        if (!alive) {
            afterKill();
            return;
        }
        // time between put bomb

        animate();

        calculateMove();

        // dectect place bomb
    }

    @Override
    public void render(GraphicsContext gc) {
        if (alive) {
            chooseSprite();
        } else {
            sprite = Sprite.player_dead1;
        }
        img = sprite.getFxImage();

        super.render(gc);
    }

    @Override
    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.right) xa++;
        if (input.left) xa--;

        if (xa != 0 || ya != 0) {
            moving = true;
            move(xa * velocity, ya * velocity);
        } else {
            moving = false;
        }
    }


    protected void move(int xa, int ya) {
        if (ya < 0) direction = 0; // up
        if (ya > 0) direction = 2; // down
        if (xa > 0) direction = 1; // right
        if (xa < 0) direction = 3; // left
       // super.move(xa, ya);
        if(canMove(xa, 0)) {
            x += xa;
        }

        if(canMove(0, ya)) {
            y += ya;
        }
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }
/*
    @Override
    protected boolean canMove(int xMove, int yMove) {
        int xTile;
        int yTile;
        if (xMove > 0) {//move right
            xTile = (int) (x + xMove + bounds.getWidth())/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    System.out.println(yTile + "-" + xTile);
                    return false;
                }
            }
        } else if (xMove < 0) { //move left
            xTile = (int) (x + xMove)/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    System.out.println(yTile + "-" + xTile);
                    return false;
                }
            }
        }

        if (yMove < 0) { // move up
            yTile = (int) (y + bounds.getMinY() + yMove)/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                xTile = (int) (x + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    System.out.println(yTile + "-" + xTile);
                    return false;
                }
            }
        } else if (yMove > 0) { //move down
            yTile = (int) (y + bounds.getMinY() + yMove + bounds.getHeight())/Sprite.SCALED_SIZE;
            for (int i = 0; i < 2; i++) {
                xTile = (int) (x + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                Entity e = gameMap.getTileAt(xTile, yTile);
                if (!e.collide(this)) {
                    System.out.println(yTile + "-" + xTile);
                    return false;
                }
            }
        }
        return true;
    }*/
    public Keyboard getKeyboard() {
        return input;
    }

    public List<Bomb> get_bombs() {
        return _bombs;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void set_bombs(List<Bomb> _bombs) {
        this._bombs = _bombs;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
                sprite = Sprite.player_up;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
        }
    }
}