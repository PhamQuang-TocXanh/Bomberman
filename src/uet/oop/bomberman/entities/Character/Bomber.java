package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Items.Bomb;

import java.util.List;

public class Bomber extends AnimatedEntity {
    private int velocity;
    private boolean dead;

    private List<Bomb> _bombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        velocity = 3;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
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
}
