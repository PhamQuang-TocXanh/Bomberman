package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Character.Balloon;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Doll;
import uet.oop.bomberman.entities.Character.Oneal;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
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
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private LevelLoader levelLoader;
    private Bomber bomber;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        levelLoader = new LevelLoader();
        levelLoader.loadLevel(3);
        WIDTH = levelLoader.getWIDTH();
        HEIGHT = levelLoader.getHEIGHT();
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
                bomber.move(keyEvent);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomber.endMove(keyEvent);
            }
        });
    }

    public void createMap() {
        char[][] map;

    {
        assert false;
        map = levelLoader.getMap();
    }
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
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
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
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
}
