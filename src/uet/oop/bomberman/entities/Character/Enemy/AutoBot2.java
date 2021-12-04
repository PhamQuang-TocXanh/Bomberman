package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class AutoBot2 implements AutoBot {
    private final Bomber bomber = BombermanGame.bomber;
    private Random random;
    protected long changeDirectionTime;

    public AutoBot2() {
        random = new Random();
        changeDirectionTime = System.currentTimeMillis();
    }

    @Override
    public int calculateDirection(int curDirection, Enemy myEnemy) {
        if (bomber == null) return random.nextInt(4);
        if (distance(myEnemy) < Sprite.SCALED_SIZE * 4) {
            int vertical = random.nextInt(2);
            if (vertical == 1) {
                int v = rowDirection(myEnemy);
                return v != -1 ? v : colDirection(myEnemy);
            } else {
                int h = colDirection(myEnemy);
                return h != -1 ? h : rowDirection(myEnemy);
            }
        }
        if (!myEnemy.can_move || System.currentTimeMillis() - changeDirectionTime >= 3000) {
            changeDirectionTime = System.currentTimeMillis();
            return random.nextInt(4);
        }
        return curDirection;
    }

    protected int colDirection(Enemy myEnemy) {
        if (bomber.getX() < myEnemy.getX()) return 3;
        if (bomber.getX() / Sprite.SCALED_SIZE > myEnemy.getX() / Sprite.SCALED_SIZE) return 1;
        return - 1;
    }

    protected int rowDirection(Enemy myEnemy) {
        if (bomber.getY() < myEnemy.getY()) return 0;
        if (bomber.getY() / Sprite.SCALED_SIZE > myEnemy.getY() / Sprite.SCALED_SIZE) return 2;
        return - 1;
    }

    private double distance(Enemy myEnemy) {
        return Math.sqrt(Math.pow(myEnemy.getX() - bomber.getX(), 2) + Math.pow(myEnemy.getY() - bomber.getY(), 2));
    }
}
