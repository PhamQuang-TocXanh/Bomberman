package uet.oop.bomberman.entities.Items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public abstract class Item extends Entity {

    private boolean isHidden = true;
    private boolean isRemoved = false;
    private int xTile, yTile;
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        xTile = xUnit;
        yTile = yUnit;
        gameMap.tiles[yTile][xTile] = new Brick(xTile, yTile, Sprite.brick.getFxImage());
        gameMap.items.add(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isHidden) {
            super.render(gc);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (isHidden) {
            return gameMap.tiles[yTile][xTile].collide(e);
        }

        if (e instanceof Bomber) {
            if (!isRemoved) {
                new Sound().playMusicEffect(Sound.ITEM);
                effect();
                remove();
            }
            return true;
        }
        return false;
    }

    public void destroy() {
        if (isHidden) {
            isHidden = false;
            appear();
        } else {
            remove();
        }
    }
    public abstract void effect();

    private void appear() {
        gameMap.tiles[yTile][xTile] = new Grass(xTile, yTile, Sprite.grass.getFxImage());
    }

    protected void remove() {
        isRemoved = true;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public int getxTile() {
        return xTile;
    }

    public int getyTile() {
        return yTile;
    }

}
