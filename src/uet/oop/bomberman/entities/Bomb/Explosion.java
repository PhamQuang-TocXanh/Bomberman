package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.*;

public class Explosion extends AnimatedEntity {

    private int xTile, yTile;
    private int direction;
    private boolean isLast;
    public Explosion(int xUnit, int yUnit, int direction, boolean isLast) {
        super(xUnit, yUnit, null);
        xTile = xUnit;
        yTile = yUnit;
        this.direction = direction;
        this.isLast = isLast;
    }

    @Override
    public void update() {
        animate();
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();

        img = sprite.getFxImage();

        super.render(gc);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Character) {
            ((Character) e).kill();
        }
        return true;
    }

    private void chooseSprite() {
        switch (direction) {
            case -1:
                sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate, 30);
                break;
            case 0:
                if(isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2, _animate, 30);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, _animate, 30);
                }
                break;
            case 1:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2, _animate, 30);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, _animate, 30);
                }
                break;
            case 2:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2, _animate, 30);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, _animate, 30);
                }
                break;
            case 3:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2, _animate, 30);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, _animate, 30);
                }
                break;
            default:
                     break;
        }
    }

    public int getXTile() {
        return xTile;
    }

    public int getYTile() {
        return yTile;
    }
}
