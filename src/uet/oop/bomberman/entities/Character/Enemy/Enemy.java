package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Enemy extends Character {
    protected AutoBot autoBot;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        autoBot = new AutoBot1();
    }

    @Override
    protected void calculateMove() {
        direction = autoBot.calculateDirection(x, y, direction);
        int xa = 0, ya = 0;
        if (direction == 0) ya--;
        if (direction == 2) ya++;
        if (direction == 1) xa++;
        if (direction == 3) xa--;
        move(xa * velocity, ya * velocity);
    }

    @Override
    protected boolean canMove(int xa, int ya) {
        Entity e = this.collision(xa, ya);
        return e != null && !(e instanceof Brick) && !(e instanceof Wall) && !(e instanceof Bomb)
                && !(e instanceof BombItem) && !(e instanceof FlameItem) && !(e instanceof SpeedItem);
    }

    @Override
    public Entity collision(int xa, int ya) {
        List<Entity> l = BombermanGame.mapList;
        int autoY = autoCorrectPosition(ya);
        int autoX = autoCorrectPosition(xa);
        Entity e = null;

        if (direction == 0) {
            int yy = (ya) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getY() == yy && entity.getX() == autoX) {
                    e = entity; break;
                }
            }
        } else if (direction == 1){
            int xx = (x + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getX() == xx && entity.getY() == autoY) {
                    e = entity; break;
                }
            }
        } else if (direction == 2) {
            int yy = (y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getY() == yy && entity.getX() == autoX) {
                    e = entity; break;
                }
            }
        } else if (direction == 3) {
            int xx = (xa) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
            for (Entity entity : l) {
                if (entity.getX() == xx && entity.getY() == autoY) {
                    e = entity; break;
                }
            }
        }
        return e;
    }


    @Override
    public void update() {

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected void chooseSprite() {

    }
}
