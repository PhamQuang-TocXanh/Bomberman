package uet.oop.bomberman.entities.Items;

import uet.oop.bomberman.graphics.Sprite;

public class ItemFactory {
    private ItemFactory() {
    }

    public static void getItem(int i, int j, char c) {
        switch (c) {
            case 'b':
                new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                break;
            case 'f':
                new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                break;
            case 's':
                new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                break;
            case 'x':
                new Portal(i, j, Sprite.portal.getFxImage());
                break;
            case 'j':
                new WallPassItem(i, j, Sprite.powerup_wallpass.getFxImage());
                break;
            case 'k':
                new BombPassItem(i, j, Sprite.powerup_bombpass.getFxImage());
                break;
            case 'l':
                new FlamePassItem(i, j, Sprite.powerup_flamepass.getFxImage());
                break;
            case 'd':
                new DetonatorItem(i, j, Sprite.powerup_detonator.getFxImage());
                break;
        }
    }
}
