package uet.oop.bomberman.entities.Character;

import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Character.Enemy.*;
import uet.oop.bomberman.graphics.Sprite;

public class CharacterFactory {
    private static Map gameMap = Map.getMap();
    private CharacterFactory() {
    }

    public static void getCharacter(int i, int j, char c) {
        switch (c) {
            case 'p':
                if (Map.bomber == null) {
                    Map.bomber = new Bomber(i, j, Sprite.player_right.getFxImage());
                    gameMap.characters.add(Map.bomber);
                }
                break;
            case '1':
                gameMap.characters.add(new Balloon(i, j, Sprite.balloom_left1.getFxImage()));
                break;
            case '2':
                gameMap.characters.add(new Oneal(i, j, Sprite.oneal_right1.getFxImage()));
                break;
            case '3':
                gameMap.characters.add(new Doll(i, j, Sprite.doll_left1.getFxImage()));
                break;
            case '4':
                gameMap.characters.add(new Minvo(i, j, Sprite.minvo_right1.getFxImage()));
                break;
            case '5':
                gameMap.characters.add(new Kondoria(i, j, Sprite.kondoria_left1.getFxImage()));
                break;
            case '6':
                gameMap.characters.add(new Ovape(i, j, Sprite.ovape_right1.getFxImage()));
                break;
            case '7':
                gameMap.characters.add(new Pas(i, j, Sprite.pas_left1.getFxImage()));
                break;
            case '8':
                gameMap.characters.add(new PontanOrange(i, j, Sprite.pontan_orange_right1.getFxImage()));
                break;
            case '9':
                gameMap.characters.add(new PontanRed(i, j, Sprite.pontan_red_left1.getFxImage()));
                break;
            default:
                break;
        }
    }
}
