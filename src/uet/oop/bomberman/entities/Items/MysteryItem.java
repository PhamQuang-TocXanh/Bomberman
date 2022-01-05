package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

import java.util.Random;

public class MysteryItem extends Item {

    public MysteryItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void effect() {
        Random random = new Random();
        int randValue = random.nextInt(10);
        if (randValue < 4) { // 40%
            gameMap.bomber.setInvincible();
        } else if (randValue < 7) { // 30%
            gameMap.bomber.increaseLife();
        }
    }
}
