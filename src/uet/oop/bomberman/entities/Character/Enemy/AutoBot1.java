package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class AutoBot1 implements AutoBot{
    private Random random;
    protected long changeDirectionTime;

    public AutoBot1() {
        random = new Random();
        changeDirectionTime = System.currentTimeMillis();
    }

    public int calculateDirection(int x, int y, int curDirection) {
        if (System.currentTimeMillis() - changeDirectionTime >= 1000
                && x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            curDirection = random.nextInt(4);
            changeDirectionTime = System.currentTimeMillis();
        }
        return curDirection;
    }

    @Override
    public int calculateDirection(int x, int y, int curDirection, Character character) {
        if (System.currentTimeMillis() - changeDirectionTime >= 1000
                && x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            curDirection = random.nextInt(4);
            changeDirectionTime = System.currentTimeMillis();
        }
        return curDirection;
    }
}
