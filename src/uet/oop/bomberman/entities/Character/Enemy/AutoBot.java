package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.entities.Character.Character;

public interface AutoBot {
    int calculateDirection(int x, int y, int curDirection, Character character);
}
