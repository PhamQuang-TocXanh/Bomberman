package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Iterator;

public class Bomb extends AnimatedEntity {
    private int timeBeforeExplode = 120;
    private int timeBeforeRemove = 20;
    private boolean isExploded = false;
    private int xTile;
    private int yTile;
    private Explosion[][] explosions;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        xTile = xUnit;
        yTile = yUnit;
    }

    @Override
    public void update() {
        animate();
        if (timeBeforeExplode > 0) --timeBeforeExplode;
        else if(!isExploded){
            explode();
            System.out.println("exploded");
        } else {
            afterExploded();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isExploded) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 40);

            img = sprite.getFxImage();

            super.render(gc);
        } else {
            renderExplosion(gc);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
           // check
            return true;
        }
        return false;
    }

    private void explode() {
        isExploded = true;
        int length = gameMap.bomber.getBombLength();
        explosions = new Explosion[5][length];
        explosions[4][0] = new Explosion(xTile, yTile,-1,false); // center
        int[] xDirection = {0, 1, 0, -1}; // hướng x y
        int[] yDirection = {-1, 0, 1, 0};

        for(int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < length; i++) {
                int tileX = xTile + (1 + i) * xDirection[direction];
                int tileY = yTile + (1 + i) * yDirection[direction];
                if (tileX < 0 || tileX >= gameMap.WIDTH || tileY < 0 || tileY >= gameMap.HEIGHT) {
                    break;
                }
                Entity e = gameMap.getTileAt(tileX, tileY);
                if (e instanceof Wall) {
                    break;
                }
                if (e instanceof Brick) {
                    ((Brick) e).destroy();
                    break;
                }
                if (direction % 2 == 0) { // up down
                    explosions[direction][i] = new Explosion(tileX, tileY, direction, i == length - 1);
                } else { // right left
                    explosions[direction][i] = new Explosion(tileX, tileY, direction, i == length - 1);
                }
                Character character = gameMap.getCharacterAtTile(tileX, tileY);
                if (character != null) {
                    character.collide(explosions[direction][i]);
                }
                Bomb bomb = gameMap.getBombAt(tileX, tileY);
                if (bomb != null) {
                    bomb.setTimeBeforeExplode(1);
                }
            }
        }
    }

    private void renderExplosion(GraphicsContext gc) {
        explosions[4][0].update();
        int length = gameMap.bomber.getBombLength();
        for(int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < length; i++) {
                if (explosions[direction][i] != null) {
                    explosions[direction][i].update();
                    explosions[direction][i].render(gc);
                }
            }
        }
        explosions[4][0].render(gc);
    }

    public Explosion getExplosionAt(int x, int y) {
        if(!isExploded) return null;
        int length = gameMap.bomber.getBombLength();
        for(int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < length; i++) {
                Explosion e = explosions[direction][i];
                if (e != null && e.getXTile() == x && e.getYTile() == y) {
                    return e;
                }
            }
        }
        return null;
    }

    private void afterExploded() {
        if (timeBeforeRemove > 0) --timeBeforeRemove;
        else {
            //remove
            System.out.println("remove");
            remove();
        }
    }

    private void remove() {
        gameMap.bombs.remove(this);
        gameMap.bomber.decreaseUsedBombs();
    }

    private void chooseSprite() {
        if(!isExploded) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate,40);
        } else {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate,40);
        }
    }

    public int getXTile() {
        return xTile;
    }

    public int getYTile() {
        return yTile;
    }

    public void setTimeBeforeExplode(int timeBeforeExplode) {
        this.timeBeforeExplode = timeBeforeExplode;
    }
}
