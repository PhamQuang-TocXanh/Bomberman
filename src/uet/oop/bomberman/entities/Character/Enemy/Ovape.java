package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Ovape extends Enemy {

    public Ovape(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 2000;
        velocity = 2;
        sprite = Sprite.ovape_left1;
        autoBot = new AutoBot1();
    }

    @Override
    public void render(GraphicsContext gc) {
        try {
            if (alive) {
                chooseSprite();
            } else {
                sprite = Sprite.ovape_dead;
            }
            img = sprite.getFxImage();

            super.render(gc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean canMove(int xa, int ya) {
        Entity e = this.collision(xa, ya);
        return e != null && !(e instanceof Wall);
    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.ovape_right1, Sprite.ovape_right2, Sprite.ovape_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.ovape_left1, Sprite.ovape_left2, Sprite.ovape_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.ovape_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 30);
            }
        }
    }
}
