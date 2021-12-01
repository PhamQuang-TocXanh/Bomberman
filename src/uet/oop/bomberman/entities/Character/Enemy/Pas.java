package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Pas extends Enemy {

    public Pas(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        score = 400;
        velocity = 4;
        sprite = Sprite.pas_right1;
        autoBot = new AutoBot1();
    }

    @Override
    public void render(GraphicsContext gc) {
        try {
            if (alive) {
                chooseSprite();
            } else {
                sprite = Sprite.pas_dead;
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
                    sprite = Sprite.movingSprite(Sprite.pas_right1, Sprite.pas_right2, Sprite.pas_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.pas_left1, Sprite.pas_left2, Sprite.pas_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.pas_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 30);
            }
        }
    }
}
