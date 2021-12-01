package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Character.Enemy.*;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Portal;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.LevelLoader;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static int WIDTH = 20;
    public static int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    private LevelLoader levelLoader;
    public static Bomber bomber;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        try {
            levelLoader = new LevelLoader();
            if (levelLoader.loadLevel(4)) {
                WIDTH = levelLoader.getWIDTH();
                HEIGHT = levelLoader.getHEIGHT();
            } else {
                if (levelLoader.loadLevel(1)) {// level mac dinh neu khong load dc map
                    WIDTH = levelLoader.getWIDTH();
                    HEIGHT = levelLoader.getHEIGHT();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bomberman 25");
//        stage.setOnCloseRequest(e -> {
//            e.consume();
//            logout(stage);
//        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomber.getKeyboard().keyPressed(keyEvent);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomber.getKeyboard().keyReleased(keyEvent);
            }
        });
    }

    public void createMap() {
        char[][] map;

    {
        assert false;
        map = levelLoader.getMap();
    }
    if (map == null) {
        map = new char[HEIGHT][WIDTH];
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                Entity object;
                if (i == 0 || j == 0 || i == WIDTH - 1 || j == HEIGHT - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomber);
        return;
    }
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                Entity object;
                if (map[j][i] == 'p') {
                    bomber = new Bomber(i, j, Sprite.player_right.getFxImage());
                    entities.add(bomber);
                } else if (map[j][i] == '1') {
                    entities.add(new Balloon(i, j, Sprite.balloom_left1.getFxImage()));
                } else if (map[j][i] == '2') {
                    entities.add(new Oneal(i, j, Sprite.oneal_right1.getFxImage()));
                } else if (map[j][i] == '3') {
                    entities.add(new Doll(i, j, Sprite.doll_left1.getFxImage()));
                } else if (map[j][i] == '4') {
                    entities.add(new Minvo(i, j, Sprite.minvo_right1.getFxImage()));
                } else if (map[j][i] == '5') {
                    entities.add(new Kondoria(i, j, Sprite.kondoria_left1.getFxImage()));
                } else if (map[j][i] == '6') {
                    entities.add(new Ovape(i, j, Sprite.ovape_right1.getFxImage()));
                } else if (map[j][i] == '7') {
                    entities.add(new Pas(i, j, Sprite.pas_left1.getFxImage()));
                } else if (map[j][i] == '8') {
                    entities.add(new PontanOrange(i, j, Sprite.pontan_orange_right1.getFxImage()));
                } else if (map[j][i] == '9') {
                    entities.add(new PontanRed(i, j, Sprite.pontan_red_left1.getFxImage()));
                }

                if (map[j][i] == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else if (map[j][i] == '*') {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                }
                else if (map[j][i] == 'x') {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    stillObjects.add(new Portal(i, j, Sprite.portal.getFxImage()));
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                }
                else if (map[j][i] == 'b') {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    stillObjects.add(new BombItem(i, j, Sprite.powerup_bombs.getFxImage()));
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                }
                else if (map[j][i] == 'f') {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    stillObjects.add(new BombItem(i, j, Sprite.powerup_flames.getFxImage()));
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                }
                else if (map[j][i] == 's') {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    stillObjects.add(new SpeedItem(i, j, Sprite.powerup_speed.getFxImage()));
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Closing!!!");
        alert.setHeaderText("Are you sure want to quit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public LevelLoader getLevelLoader() {
        return levelLoader;
    }

    public Bomber getBomber() {
        return bomber;
    }
}
