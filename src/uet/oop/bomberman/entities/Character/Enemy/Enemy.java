package uet.oop.bomberman.entities.Character.Enemy;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Message.Message;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public abstract class Enemy extends Character {
    protected int score;
    protected AutoBot autoBot;
    public boolean can_move = true;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        direction = 1;
        bounds = new Rectangle2D(2, 2, Sprite.SCALED_SIZE - 4, Sprite.SCALED_SIZE - 4);
    }

    @Override
    protected void calculateMove() {
        direction = autoBot.calculateDirection(direction, this);
        int xa = 0, ya = 0;
        if (direction == 0) ya--;
        if (direction == 2) ya++;
        if (direction == 1) xa++;
        if (direction == 3) xa--;
        move(xa * velocity, ya * velocity);
    }

    protected void move(int xa, int ya) {
        can_move = false;
        if (direction == 0 || direction == 2) {
            if (canMove(0, ya)) {
                can_move = true;
                x = autoCorrectPosition(x);
                y += ya;
            }
        } else if (direction == 1 || direction == 3) {
            if(canMove(xa, 0)) {
                can_move = true;
                y = autoCorrectPosition(y);
                x += xa;
            }
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Explosion) {
            this.kill();
            return false;
        }
        return true;
    }

    /*
        @Override
        public boolean canMove(int xa, int ya) {
            Entity e = this.collision(xa, ya);
            if (e instanceof Wall) {
                this.kill();
                return false;
            }
            can_move = e != null && !(e instanceof Brick) && !(e instanceof Wall) && !(e instanceof Bomb)
                    && !(e instanceof BombItem) && !(e instanceof FlameItem) && !(e instanceof SpeedItem);
            return can_move;
        }
    */
    @Override
    public void render(GraphicsContext gc) {
        try {
            chooseSprite();
            img = sprite.getFxImage();
            super.render(gc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        animate();
        if (!alive) {
            afterKill();
            return;
        }
        calculateMove();
    }

    @Override
    public void kill() {
        if(!alive) return;
        new Sound().playMusicEffect(Sound.ENEMY_DIE);
        moving = false;
        alive = false;
        _animate = 0;
    }

    @Override
    protected void afterKill() {
        if (timeAfter > 0) timeAfter--;
    }

    public int getScore() {
        return score;
    }

    @Override
    protected abstract void chooseSprite();
}
