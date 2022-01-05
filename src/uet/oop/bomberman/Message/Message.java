package uet.oop.bomberman.Message;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.entities.Character.Character;

import java.util.Objects;

public class Message {
    private String text;
    private int duration;
    private int x;
    private int y;
    private boolean isRemoved = false;
    private boolean isMoving = false;
    private Character character = null;
    private static final ClassLoader c = ClassLoader.getSystemClassLoader();
    private static final Font font = Font.loadFont(Objects.requireNonNull(c.getResource("fonts/JosefinSans-SemiBold.ttf")).toString(), 14);

    public Message(String text, Character character, int duration, boolean isMoving) {
        this.text = text;
        this.character = character;
        this.x = character.getMessagePosX();
        this.y = character.getMessagePosY();
        this.duration = duration;
        this.isMoving = isMoving;
    }

    public void render(GraphicsContext gc) {
        gc.save();
        gc.setFont(font);
        chooseColor(gc);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(text, x, y);
        gc.restore();
    }

    public void update() {
        if (duration > 0) {
            duration --;
            if (isMoving) {
                x = character.getMessagePosX();
                y = character.getMessagePosY();
            }
        } else {
            isRemoved = true;
        }
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    private void chooseColor(GraphicsContext gc) {
        if (!isMoving) {
            gc.setFill(Color.WHITE);
            return;
        }

        int calc = duration % 40;
        int diff = 40 / 3;

        if(calc < diff) {
            gc.setFill(Color.RED);
            return;
        }

        if(calc < diff * 2) {
            gc.setFill(Color.BLUE);
            return;
        }

        gc.setFill(Color.GREEN);

    }
}
