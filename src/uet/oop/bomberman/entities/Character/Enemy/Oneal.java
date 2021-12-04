package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 200;
        velocity = 3;
        sprite = Sprite.oneal_right1;
        autoBot = new AutoBot2();
    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
