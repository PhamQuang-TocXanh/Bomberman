package uet.oop.bomberman.level;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.io.File;
import java.io.FileInputStream;
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
        LIFE.setText("LIFE: " + Map.bomber.getLife());
    }

    public static HBox scoreBoard() {
        LEVEL.setFont(font); LEVEL.setFill(Color.GREY);
        SCORE.setFont(font); SCORE.setFill(Color.GREEN);
        LIFE.setFont(font); LIFE.setFill(Color.RED);
        HBox hBox = new HBox(LEVEL, SCORE, LIFE);
        hBox.setPrefHeight(50);
        hBox.setSpacing(150);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(186, 187, 188),new CornerRadii(10),null)));
        return hBox;
    }

    public static Scene pauseScene() {
        Text t1 = new Text("PAUSE");
        t1.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 50));
        t1.setFill(Color.WHITESMOKE);
        Button button = new Button("Press P to continue!!!");
        button.setPrefHeight(50); button.setPrefWidth(400);
        button.setFont(font);
        button.setStyle("-fx-text-fill: #ffffff;" +
                        " -fx-background-color: rgb(10, 2, 1)");
        button.setOnAction(actionEvent -> {
            new Sound().playMusicEffect(Sound.CLICKY);
            BombermanGame.chooseScene++;
        });
        VBox root = new VBox(t1, button);
        root.setAlignment(Pos.CENTER);  root.setSpacing(60);
        root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1),null,null)));
        root.setStyle("-fx-background-image: url(star.jpg);");
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                new Sound().playMusicEffect(Sound.CLICKY);
                BombermanGame.chooseScene++;
            }
        });
        return scene;
    }

    public static Scene startScene() {
        Button play = new Button("PLAY");
        play.setPrefHeight(50); play.setPrefWidth(300);
        play.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(), 40));
        play.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.WIDTH) / 2 - 150);
        play.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50) / 2);
        play.setStyle("-fx-text-fill: #ffffff;" +
                        " -fx-background-radius: 50;" +
                        "-fx-background-color: rgb(96,186,251)");
        play.setOnAction(actionEvent -> {
            new Sound().playMusicEffect(Sound.CLICKY);
            BombermanGame.chooseScene = 0;
        });
        play.setOnMouseEntered(mouseEvent -> play.setStyle("-fx-text-fill: #ffffff;" +
                        " -fx-background-radius: 50;" +
                        "-fx-background-color: rgb(18,128,255)"));
        play.setOnMouseExited(mouseEvent -> play.setStyle("-fx-text-fill: #ffffff;" +
                        " -fx-background-radius: 50;" +
                        "-fx-background-color: rgb(96,186,251)"));

        Button help = new Button("HELP");
        help.setPrefHeight(50); help.setPrefWidth(300);
        help.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(), 40));
        help.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.WIDTH) / 2 - 150);
        help.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50) / 2 + 100);
        help.setStyle("-fx-text-fill: #ffffff;" +
                " -fx-background-radius: 50;" +
                "-fx-background-color: rgb(96,186,251)");
        help.setOnAction(actionEvent -> {
            Stage s = new Stage();
            AnchorPane a = new AnchorPane();
            a.setStyle("-fx-background-image: url('helpMenu.png');" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-position: top left;" +
                    "-fx-background-size: 100% 100%");
            Scene scene = new Scene(a);
            s.setScene(scene);
            s.setWidth(700); s.setHeight(500);
            s.setResizable(false);
            s.initModality(Modality.APPLICATION_MODAL);
            s.showAndWait();
        });
        help.setOnMouseEntered(mouseEvent -> help.setStyle("-fx-text-fill: #ffffff;" +
                " -fx-background-radius: 50;" +
                "-fx-background-color: rgb(18,128,255)"));
        help.setOnMouseExited(mouseEvent -> help.setStyle("-fx-text-fill: #ffffff;" +
                " -fx-background-radius: 50;" +
                "-fx-background-color: rgb(96,186,251)"));


        AnchorPane root = new AnchorPane(play, help);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1),null,null)));
        root.setStyle("-fx-background-image: url('img.png');" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-position: top left;" +
                        "-fx-background-size: 100% 100%");
        root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                new Sound().playMusicEffect(Sound.CLICKY);
                BombermanGame.chooseScene = 0;
            }
        });
        return scene;
    }

    public static Scene win_loseScene(boolean win) {
        Sound.stopBackgroundMusic();
        if (win) new Sound().playMusicEffect(Sound.WIN);
        else new Sound().playMusicEffect(Sound.GAME_OVER);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(4.0f);
        ds.setColor(Color.rgb(72,71,70));
        Text text;
        if (win) text = new Text("YOU WIN!!!");
        else text = new Text(" YOU LOSE ");
        text.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(), 70));
        text.setFill(Color.rgb(237,250,246)); text.setEffect(ds);
        Text score = new Text("Your score: " + BombermanGame.SCORE);
        score.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(), 33));
        score.setFill(Color.rgb(237,250,246)); score.setEffect(ds);

        Button playAgain = new Button("Play again?");
        playAgain.setPrefHeight(50); playAgain.setPrefWidth(350);
        playAgain.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(), 38));
        playAgain.setStyle("-fx-text-fill: #ffffff;" +
                            " -fx-background-radius: 50;" +
                            " -fx-background-color: rgb(96,186,251)");
        playAgain.setOnAction(actionEvent -> {
            new Sound().playMusicEffect(Sound.CLICKY);
            BombermanGame.chooseScene = 0;
            BombermanGame.SCORE = 0;
        });
        playAgain.setOnMouseEntered(mouseEvent -> playAgain.setStyle("-fx-text-fill: #ffffff;" +
                " -fx-background-radius: 50;" +
                "-fx-background-color: rgb(18,128,255)"));
        playAgain.setOnMouseExited(mouseEvent -> playAgain.setStyle("-fx-text-fill: #ffffff;" +
                " -fx-background-radius: 50;" +
                "-fx-background-color: rgb(96,186,251)"));
        Label empty = new Label(""); empty.setPrefHeight(100);
        VBox root = new VBox(empty ,text, score, playAgain);
        root.setAlignment(Pos.CENTER);  root.setSpacing(40);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(6, 2, 1),null,null)));
        if (win) root.setStyle("-fx-background-image: url('img.png');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: top left;" +
                "-fx-background-size: 100% 100%");
        root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                new Sound().playMusicEffect(Sound.CLICKY);
                BombermanGame.chooseScene = 0;
            }
        });
        return scene;
    }

    public static Scene levelScene() {
        Sound.stopBackgroundMusic();
        Text text = new Text("Level " + Map.level); text.setWrappingWidth(200);
        text.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 50));
        text.setFill(Color.WHITESMOKE);
        text.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.WIDTH) / 2 - text.getWrappingWidth() / 2); text.setLayoutY(150);/////

        AnchorPane root = new AnchorPane(text);
        root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT + 50);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 5),null,null)));
        root.setStyle("-fx-background-image: url(star.jpg);" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-position: top left;" +
                        "-fx-background-size: 100% 100%");
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.P || keyEvent.getCode() == KeyCode.SPACE ||
                    keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.S) {
                new Sound().playMusicEffect(Sound.CLICKY);
                BombermanGame.chooseScene = 0;
            }
        });
        return scene;
    }
}
