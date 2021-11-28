package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        velocity = 2;
        sprite = Sprite.doll_left1;
        autoBot = new AutoBot1();
    }

    @Override
    public void update() {
        if (!alive) {
            afterKill();
            return;
        }

        animate();

        calculateMove();
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
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected void chooseSprite() {
        if (moving) {
            switch (direction) {
                case 0: case 1:
                    sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 30);
                    break;
                case 2: case 3:
                    sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 30);
                    break;
            }
        } else {
            if (!alive) {
                sprite = Sprite.movingSprite(Sprite.doll_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 30);
            }
        }
    }
}
