package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.NotificationBoard;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static int WIDTH = 20;
    public static int HEIGHT = 15;
    public static int SCORE = 0;
    public static int chooseScene = -1;// >=0 le thi dung man hinh

    public static Stage stage;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    private Scene scene;

    private Bomber bomber;
    public static Map gameMap = Map.getMap();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage mainStage) {
        stage = mainStage;
        gameMap.createMap();
        WIDTH= gameMap.WIDTH;
        HEIGHT = gameMap.HEIGHT;
        bomber = Map.bomber;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        VBox root = new VBox();
        root.getChildren().add(NotificationBoard.scoreBoard());
        root.getChildren().add(canvas);
        // Tao scene
        scene = new Scene(root);// main scene
        Scene pauseScene = NotificationBoard.pauseScene();

        // Them scene vao stage
        stage.setResizable(false);
        stage.setScene(NotificationBoard.startScene());
        stage.show();
        stage.setTitle("Bomberman 25");
//        stage.setOnCloseRequest(e -> {
//            e.consume();
//            logout(stage);
//        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                if (chooseScene >= 0) {
                    if (chooseScene % 2 == 0) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        gameMap.updateMap();
                        gameMap.renderMap(gc);
                        NotificationBoard.updateScoreBoard();
                        stage.setScene(scene);
                        Sound.playBackgroundMusic();
                    } else {
                        Sound.stopBackgroundMusic();
                        stage.setScene(pauseScene);
                    }
                }
            }
        };
        timer.start();

        scene.setOnKeyReleased(keyEvent -> bomber.getKeyboard().keyReleased(keyEvent));
        scene.setOnKeyPressed(keyEvent -> {
            bomber.getKeyboard().keyPressed(keyEvent);
            if (keyEvent.getCode() == KeyCode.P) {
                new Sound().playMusicEffect(Sound.CLICKY);
                chooseScene++;
            }
        });
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
        if (bomber.timeAfter <= 0 && bomber.getLife() <= 0) {
            chooseScene = -1;
            Map.level = 1;
            stage.setScene(NotificationBoard.win_loseScene(false));
            gameMap.createMap();
            WIDTH = gameMap.WIDTH;
            HEIGHT = gameMap.HEIGHT;
            bomber = Map.bomber;
            bomber.getKeyboard().reset();
            Map.nextLevel = false;
            setGameScene();
            return;
        }
        if (Map.nextLevel) {
            chooseScene = -1;
            Map.level++;
            if (Map.level > 5) {
                Map.level = 1;
                stage.setScene(NotificationBoard.win_loseScene(true));
            } else {
                bomber.setLife(bomber.getLife() + 1);
                stage.setScene(NotificationBoard.levelScene());
            }
            gameMap.createMap();
            WIDTH = gameMap.WIDTH;
            HEIGHT = gameMap.HEIGHT;
            bomber = Map.bomber;
            bomber.getKeyboard().reset();
            Map.nextLevel = false;
            setGameScene();
        }
    }

    private void setGameScene() {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        VBox root = new VBox();
        root.getChildren().add(NotificationBoard.scoreBoard());
        root.getChildren().add(canvas);
        scene = new Scene(root);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Bomberman 25");
        scene.setOnKeyReleased(keyEvent -> bomber.getKeyboard().keyReleased(keyEvent));
        scene.setOnKeyPressed(keyEvent -> {
            bomber.getKeyboard().keyPressed(keyEvent);
            if (keyEvent.getCode() == KeyCode.P) {
                new Sound().playMusicEffect(Sound.CLICKY);
                chooseScene++;
            }
        });
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

    public Bomber getBomber() {
        return bomber;
    }
}
