package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Items.Bomb;
import uet.oop.bomberman.input.KeyBoard;

import java.util.List;

public class Bomber extends AnimatedEntity {
    private List<Bomb> _bombs;
    protected KeyBoard _input;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
}
