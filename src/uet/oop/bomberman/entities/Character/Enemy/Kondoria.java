package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 1000;
        velocity = 1;
        sprite = Sprite.kondoria_left2;
        autoBot = new AutoBot1();
        wallPass = true;
    }

//    @Override
//    public boolean canMove(int xa, int ya) {
//        Entity e = this.collision(xa, ya);
//        can_move = e != null && !(e instanceof Wall);
//        return can_move;
//    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 50);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 50);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
