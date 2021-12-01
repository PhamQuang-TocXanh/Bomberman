package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 800;
        velocity = 4;
        sprite = Sprite.minvo_right1;
        autoBot = new AutoBot1();
    }

    @Override
    public void render(GraphicsContext gc) {
        try {
            if (alive) {
                chooseSprite();
            } else {
                sprite = Sprite.doll_dead;
            }
            img = sprite.getFxImage();

            super.render(gc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.minvo_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 30);
            }
        }
    }
}
