package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

public class BombPassItem extends Item {
    public BombPassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void effect() {
        gameMap.bomber.canPassBomb();
    }
}
