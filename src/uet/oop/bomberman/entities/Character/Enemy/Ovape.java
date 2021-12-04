package uet.oop.bomberman.entities.Character.Enemy;

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
        autoBot = new AutoBot2();
    }

    @Override
    public boolean canMove(int xa, int ya) {
        Entity e = this.collision(xa, ya);
        can_move = e != null && !(e instanceof Wall);
        return can_move;
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
                sprite = Sprite.movingSprite(Sprite.ovape_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
