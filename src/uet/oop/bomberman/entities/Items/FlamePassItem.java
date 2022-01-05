package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

public class FlamePassItem extends Item{
    public FlamePassItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void effect() {
        gameMap.bomber.canPassFlame();
    }
}
