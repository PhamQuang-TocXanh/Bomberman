package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class AutoBot1 implements AutoBot{
    private final Random random;
    private long changeDirectionTime;

    public AutoBot1() {
        random = new Random();
        changeDirectionTime = System.currentTimeMillis();
    }

    @Override
    public int calculateDirection(int curDirection, Enemy myEnemy) {
        if (!myEnemy.can_move) {
            return random.nextInt(4);
        }

        if (System.currentTimeMillis() - changeDirectionTime >= 1000
                && myEnemy.autoCorrectPosition(myEnemy.getY()) % Sprite.SCALED_SIZE == 0
                && myEnemy.autoCorrectPosition(myEnemy.getY()) % Sprite.SCALED_SIZE == 0) {
            curDirection = random.nextInt(4);
            changeDirectionTime = System.currentTimeMillis();
        }
        return curDirection;
    }
}
