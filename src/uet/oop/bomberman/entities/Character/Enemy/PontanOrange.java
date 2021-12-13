package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class PontanOrange extends Enemy {

    public PontanOrange(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 5000;
        velocity = 4;
        sprite = Sprite.pontan_orange_left1;
        autoBot = new AutoBot1();
        bombPass = true;
    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.pontan_orange_right1, Sprite.pontan_orange_right2, Sprite.pontan_orange_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.pontan_orange_left1, Sprite.pontan_orange_left2, Sprite.pontan_orange_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.pontan_orange_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
