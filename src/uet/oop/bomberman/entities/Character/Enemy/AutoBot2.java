package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class AutoBot2 implements AutoBot {
    private Bomber bomber = Map.bomber;
    private final Random random;
    protected long changeDirectionTime;
    private Enemy thisE = null;

    public AutoBot2() {
        random = new Random();
        changeDirectionTime = System.currentTimeMillis();
    }

    @Override
    public int calculateDirection(int curDirection, Enemy myEnemy) {
        bomber = Map.bomber;
        thisE = myEnemy;
        if (bomber == null) return random.nextInt(4);
        if (distance() < Sprite.SCALED_SIZE * 5 && bomber.isAlive()) {
            int direction = random.nextInt(2);
            if (direction == 1) {
                int v = colDirection();
                return v != -1 ? v : rowDirection();
            } else {
                int h = rowDirection();
                return h != -1 ? h : colDirection();
            }
        }
        else if (!myEnemy.can_move || System.currentTimeMillis() - changeDirectionTime >= 3000) {
            changeDirectionTime = System.currentTimeMillis();
            return random.nextInt(4);
        }
        return curDirection;
    }

    protected int rowDirection() {
        int pX = bomber.getX() / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        for (int i = bomber.getX() - Sprite.SCALED_SIZE / 4; i <= bomber.getX() + Sprite.SCALED_SIZE / 4; i++) {
            if (i % Sprite.SCALED_SIZE==0) pX = i;
        }

        if (pX < thisE.getX()) return 3;
        if (pX > thisE.getX()) return 1;
        return - 1;
    }

    protected int colDirection() {
        int pY = bomber.getY() / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        for (int i = bomber.getY() - Sprite.SCALED_SIZE / 4; i <= bomber.getY() + Sprite.SCALED_SIZE / 4; i++) {
            if (i % Sprite.SCALED_SIZE==0) pY = i;
        }
        if (pY < thisE.getY()) return 0;
        if (pY > thisE.getY()) return 2;
        return - 1;
    }

    private double distance() {
        return Math.sqrt(Math.pow(thisE.getX() - bomber.getX(), 2) + Math.pow(thisE.getY() - bomber.getY(), 2));
    }

}
