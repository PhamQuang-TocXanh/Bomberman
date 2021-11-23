package uet.oop.bomberman.entities.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Items.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.List;

public class Bomber extends Character {
    private int velocity;
    private boolean dead;
    protected int dx; // keyboard
    protected int dy;
    protected Keyboard input;
    private List<Bomb> _bombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        velocity = 3;
        sprite = Sprite.player_right;
        input = new Keyboard();
    }

    @Override
    public void update() {
        if (alive == false) {
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
        if (alive == true) {
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

    @Override
    protected void move(int xa, int ya) {
        if (ya < 0) direction = 0; // up
        if (ya > 0) direction = 2; // down
        if (xa > 0) direction = 1; // right
        if (xa < 0) direction = 3; // left

        if (canMove(xa, 0)) {
            x += xa;
        }
        if (canMove(0, ya)) {
            y += ya;
        }
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected boolean canMove(int x, int y) {
        return true;
    }

    public void move(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DOWN: case S:
                dy = velocity;
                break;
            case UP: case W:
                dy = -velocity;
                break;
            case LEFT: case A:
                dx = -velocity;
                break;
            case RIGHT: case D:
                dx = velocity;
                break;
        }
    }

    public void endMove(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DOWN: case S:
            case UP: case W:
                dy = 0;
                break;
            case LEFT: case A:
            case RIGHT: case D:
                dx = 0;
                break;
        }
    }

    public int getVelocity() {
        return velocity;
    }

    public Keyboard getKeyboard() {
        return input;
    }

    public boolean isDead() {
        return dead;
    }

    public List<Bomb> get_bombs() {
        return _bombs;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void set_bombs(List<Bomb> _bombs) {
        this._bombs = _bombs;
    }

    private void chooseSprite() {
        switch(direction) {
            case 0:
                sprite = Sprite.player_up;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
