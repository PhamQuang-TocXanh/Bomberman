package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

public class DetonatorItem extends Item {

    public DetonatorItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void effect() {
        gameMap.bomber.setCanDetonate();
    }
}
