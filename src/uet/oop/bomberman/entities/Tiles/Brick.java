package uet.oop.bomberman.entities.Tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends AnimatedEntity {

    private boolean isDestroyed = false;
    private int timeBeforeRemove = 30;
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        gameMap.tiles[yUnit][xUnit] = this;
    }

    @Override
    public void update() {
        if(isDestroyed) {
            animate();
            if (timeBeforeRemove > 0) {
                timeBeforeRemove--;
            } else {
                remove();
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDestroyed) {
            sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 30);

            img = sprite.getFxImage();
        }
        super.render(gc);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Character && ((Character)e).isWallPass()) {
            return true;
        }
        /*
        if (e instanceof Character) {
            ((Character) e).kill();
        }
        */
        return false;
    }

    public void destroy() {
        isDestroyed = true;
    }

    public void remove() {
        int xTile = x / Sprite.SCALED_SIZE;
        int yTile = y / Sprite.SCALED_SIZE;
        gameMap.tiles[yTile][xTile] = new Grass(xTile, yTile, Sprite.grass.getFxImage());
    }
}
