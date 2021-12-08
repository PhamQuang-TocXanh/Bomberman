package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.Map;

public class AutoBot3 implements AutoBot {
    private Map map = Map.getMap();
    private Enemy thisE = null;

    @Override
    public int calculateDirection(int curDirection, Enemy myEnemy) {
        thisE = myEnemy;
        return 0;
    }

    private boolean validMove() {

        return true;
    }
}
