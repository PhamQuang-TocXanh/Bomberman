package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Wall;

public abstract class Enemy extends Character {
    public int score;
    protected AutoBot autoBot;
    public boolean can_move = true;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        direction = 1;
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
        moving = false;
        alive = false;
        _animate = 0;
    }

    @Override
    protected void afterKill() {
        if (timeAfter > 0) timeAfter--;
        System.out.println(timeAfter);
    }

    public int getScore() {
        return score;
    }

    @Override
    protected abstract void chooseSprite();
}
