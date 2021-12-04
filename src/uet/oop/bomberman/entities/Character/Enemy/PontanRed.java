package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class PontanRed extends Enemy {

    public PontanRed(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 8000;
        velocity = 4;
        sprite = Sprite.pontan_red_left1;
        autoBot = new AutoBot1();
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
                    sprite = Sprite.movingSprite(Sprite.pontan_red_right3, Sprite.pontan_red_right2, Sprite.pontan_red_right1, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.pontan_red_left3, Sprite.pontan_red_left2, Sprite.pontan_red_left1, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.pontan_red_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
