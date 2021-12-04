package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 100;
        velocity = 2;
        sprite = Sprite.balloom_left1;
        autoBot = new AutoBot1();
    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 40);
            }
        }
    }
}
