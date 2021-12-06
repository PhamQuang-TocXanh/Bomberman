package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Character.Enemy.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.LevelLoader;

import java.util.ArrayList;

public class Map {
    private static Map gameMap = new Map();
    private Map() {}

    public int WIDTH;
    public int HEIGHT;
    public int level = 1;
    public Entity tiles[][];
    public ArrayList<Character> characters = new ArrayList<>();
    public static Bomber bomber;
    private LevelLoader levelLoader = new LevelLoader();
    public static Map getMap() {
        return gameMap;
    }

    private void resetMap() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters.clear();
    }

    public void createMap() {
        if(!levelLoader.loadLevel(level)) {
            System.out.println("Can't load map level");
        }
        WIDTH = levelLoader.getWIDTH();
        HEIGHT = levelLoader.getHEIGHT();
        resetMap();
        char[][] mapInput = levelLoader.getMap();
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                char c = mapInput[j][i];
                if (c == 'p') {
                    bomber = new Bomber(i, j, Sprite.player_right.getFxImage());
                    characters.add(bomber);
                } else if (c == '1') {
                    characters.add(new Balloon(i, j, Sprite.balloom_left1.getFxImage()));
                } else if (c == '2') {
                    characters.add(new Oneal(i, j, Sprite.oneal_right1.getFxImage()));
                } else if (c == '3') {
                    characters.add(new Doll(i, j, Sprite.doll_left1.getFxImage()));
                } else if (c == '4') {
                    characters.add(new Minvo(i, j, Sprite.minvo_right1.getFxImage()));
                } else if (c == '5') {
                    characters.add(new Kondoria(i, j, Sprite.kondoria_left1.getFxImage()));
                } else if (c == '6') {
                    characters.add(new Ovape(i, j, Sprite.ovape_right1.getFxImage()));
                } else if (c == '7') {
                    characters.add(new Pas(i, j, Sprite.pas_left1.getFxImage()));
                } else if (c == '8') {
                    characters.add(new PontanOrange(i, j, Sprite.pontan_orange_right1.getFxImage()));
                } else if (c == '9') {
                    characters.add(new PontanRed(i, j, Sprite.pontan_red_left1.getFxImage()));
                }
                TileFactory.getTile(i, j, c);
            }
        }
    }

    public void updateMap() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                tiles[j][i].update();
            }
        }

        characters.forEach(Character::update);
    }

    public void renderMap(GraphicsContext gc) {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                tiles[j][i].render(gc);
            }
        }

        characters.forEach(ch -> ch.render(gc));
    }

    public Entity getTileAt(int x, int y) {
        return tiles[y][x];
    }
}
