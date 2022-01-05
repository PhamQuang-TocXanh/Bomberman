package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Message.Message;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Character.CharacterFactory;
import uet.oop.bomberman.entities.Character.Enemy.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.Item;
import uet.oop.bomberman.entities.Items.ItemFactory;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.LevelLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Map {
    private static Map gameMap = new Map();
    private Map() {}

    public int WIDTH;
    public int HEIGHT;
    public static int level = 1;
    public static boolean goNextLevel;
    public static boolean nextLevel;
    public Entity[][] tiles;
    public ArrayList<Character> characters = new ArrayList<>();
    public ArrayList<Bomb> bombs = new ArrayList<>();
    //public LinkedList<Bomb> bombs = new LinkedList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Message> messages = new ArrayList<>();
    public static Bomber bomber;
    private LevelLoader levelLoader = new LevelLoader();
    public static Map getMap() {
        return gameMap;
    }

    private void resetMap() {
        goNextLevel = false;
        nextLevel = false;
        tiles = new Entity[HEIGHT][WIDTH];
        characters.clear(); if (bomber != null && level != 1) characters.add(bomber);
        bombs.clear();
        items.clear();
        messages.clear();
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
                CharacterFactory.getCharacter(i , j, c);
                TileFactory.getTile(i, j, c);
                ItemFactory.getItem(i, j, c);
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

        messages.forEach(Message::update);

        removeDead();
        if (characters.size() == 1) goNextLevel = true;

        for(Bomb b : bombs) {
            b.update();
        }
    }

    public void renderMap(GraphicsContext gc) {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                tiles[j][i].render(gc);
            }
        }

        bombs.forEach(b -> b.render(gc));
        characters.forEach(ch -> ch.render(gc));
        items.forEach(item -> item.render(gc));
        messages.forEach(message -> message.render(gc));
    }


    public Entity getTileAt(int x, int y) {
        for (Item item : items) {
            if (item.getxTile() == x && item.getyTile() == y) {
                return item;
            }
        }
        return tiles[y][x];
    }

    public List<Character> getCharactersAtTile(int x, int y) {
        ArrayList<Character> out = new ArrayList<>();
        for (Character character : characters) {
            if(character.getXTile() == x && character.getYTile() == y) {
                out.add(character);
            }
        }
        return out;
    }

    public Bomb getBombAt(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getXTile() == x && bomb.getYTile() == y) {
                return bomb;
            }
        }
        return null;
    }

    public Explosion getExplosionAt(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getExplosionAt(x, y) != null) {
                return bomb.getExplosionAt(x, y);
            }
        }
        return null;
    }

    public Entity getEntityAtTile(int x, int y) {
        Entity e;

        e = getExplosionAt(x, y);
        if (e != null) return e;

        e = getBombAt(x, y);
        if (e != null) return e;

        return getTileAt(x, y);
    }

    public void removeDead() {
        try {
            for(int i=0;i<characters.size();i++){
                if(characters.get(i) != null){
                    if (!(characters.get(i) instanceof Enemy)) continue;
                    Enemy e = (Enemy) characters.get(i);
                    if (e.timeAfter <= 0) {
                        messages.add(new Message("+" + e.getScore(), e, 100, false));
                        characters.remove(e);
                        BombermanGame.SCORE += e.getScore();
                        System.out.println(BombermanGame.SCORE);
                        i--;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*có thể cần đổi tên hàm*/

        bombs.removeIf(bomb -> bomb.getIsRemoved());

        items.removeIf(item -> item.getIsRemoved());

        messages.removeIf(message -> message.getIsRemoved());
    }
}
