package uet.oop.bomberman.level;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Objects;

public class NotificationBoard {
    public static Text LEVEL = new Text();
    public static Text SCORE = new Text();
    public static Text LIFE = new Text();

    private static final ClassLoader c = ClassLoader.getSystemClassLoader();
    private static final Font font = Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 20);

    public static void updateScoreBoard() {
        SCORE.setText("SCORE: " + BombermanGame.SCORE);
        LEVEL.setText("LEVEL: " + Map.level);
        LIFE.setText("LIFE: " + BombermanGame.bomber.getLife());
    }

    public static HBox scoreBoard() {
        LEVEL.setFont(font); LEVEL.setFill(Color.GREY);
        SCORE.setFont(font); SCORE.setFill(Color.GREEN);
        LIFE.setFont(font); LIFE.setFill(Color.RED);
        HBox hBox = new HBox(LEVEL, SCORE, LIFE);
        hBox.setPrefHeight(50);
        hBox.setSpacing(150);
        hBox.setAlignment(Pos.CENTER);
        BackgroundImage backgroundimage = null;
        try {
            Image image = new Image(Objects.requireNonNull(c.getResource("p.png")).toString());
             backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (Exception e) {
            System.out.println("Load image error: " + e.getMessage());
        }
        if (backgroundimage != null) hBox.setBackground(new Background(backgroundimage));
        else hBox.setBackground(new Background(new BackgroundFill(Color.rgb(186, 187, 188),new CornerRadii(10),null)));
        return hBox;
    }

    public static Scene pauseBoard() {
        Text t1 = new Text("PAUSE");
        t1.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 50));
        t1.setFill(Color.WHITESMOKE);
        Button button = new Button("Press P to continue!!!");
        button.setPrefHeight(50); button.setPrefWidth(400);
        button.setFont(font);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1),null,null)));
        button.setStyle("-fx-text-fill: #ffffff");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BombermanGame.pause++;
            }
        });
        VBox root = new VBox(t1, button);
        root.setAlignment(Pos.CENTER);  root.setSpacing(60);
        root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1),null,null)));
        try {
            Image image = new Image(Objects.requireNonNull(c.getResource("star.jpg")).toString());
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundimage));
        } catch (Exception e) {
            System.out.println("Load pause image error: " + e.getMessage());
        }
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                BombermanGame.pause++;
            }
        });
        return scene;
    }
}
