package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.Item;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Iterator;
import java.util.List;

public class Bomb extends AnimatedEntity {
    private int timeBeforeExplode = 120;
    private int timeBeforeRemove = 15;
    private boolean isExploded = false;
    private boolean isRemoved = false;
    private boolean isBomberPass = false;
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
        if (e instanceof Character && ((Character) e).isBombPass()) {
            return true;
        }

        if (e instanceof Bomber) {
           // check
            Bomber b = (Bomber) e;
            if (b.getXTile() != this.xTile || b.getYTile() != this.yTile) {
                isBomberPass = true;
            }
            return !isBomberPass;
        }
        return false;
    }

    private void explode() {
        new Sound().playMusicEffect(Sound.BOMB_BANG);
        isExploded = true;
        int length = gameMap.bomber.getBombLength();
        explosions = new Explosion[5][length];
        // center
        explosions[4][0] = new Explosion(xTile, yTile,-1,false);
        List<Character> characters = gameMap.getCharactersAtTile(xTile, yTile);
        if (!characters.isEmpty()) {
            characters.forEach(character -> character.collide(explosions[4][0]));
        }

        // 4 direction
        int[] xDirection = {0, 1, 0, -1}; // hướng x y
        int[] yDirection = {-1, 0, 1, 0};

        for(int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < length; i++) {
                int tileX = xTile + (1 + i) * xDirection[direction];
                int tileY = yTile + (1 + i) * yDirection[direction];
                if (tileX < 0 || tileX >= gameMap.WIDTH || tileY < 0 || tileY >= gameMap.HEIGHT) {
                    break;
                }
                Entity tile = gameMap.getTileAt(tileX, tileY);
                if (tile instanceof Item) {
                    ((Item) tile).destroy();
                    break;
                }
                if (tile instanceof Wall) {
                    break;
                }
                if (tile instanceof Brick) {
                    ((Brick) tile).destroy();
                    break;
                }
                if (direction % 2 == 0) { // up down
                    explosions[direction][i] = new Explosion(tileX, tileY, direction, i == length - 1);
                } else { // right left
                    explosions[direction][i] = new Explosion(tileX, tileY, direction, i == length - 1);
                }
                characters = gameMap.getCharactersAtTile(tileX, tileY);
                if (!characters.isEmpty()) {
                    for (Character ch : characters) {
                        ch.collide(explosions[direction][i]);
                    }
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
        //gameMap.bombs.remove(this);
        isRemoved = true;
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

    public boolean getIsRemoved() {
        return isRemoved;
    }
}
