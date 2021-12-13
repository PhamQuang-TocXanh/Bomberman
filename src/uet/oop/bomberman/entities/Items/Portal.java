package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Item {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void effect() {
        if (Map.goNextLevel) {
            Map.nextLevel = true;
        }
    }

    @Override
    protected void remove() {
    }
}
