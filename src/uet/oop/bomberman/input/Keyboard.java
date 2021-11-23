package uet.oop.bomberman.input;


import javafx.scene.input.KeyEvent;

public class Keyboard {
    public boolean up, down, left, right;
    public Keyboard() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }

    public void update() {

    }

    public void keyPressed(KeyEvent e) {

        switch (e.getCode()) {
            case W: case UP:
                up = true;
                break;
            case S: case DOWN:
                down = true;
                break;
            case A: case LEFT:
                left = true;
                break;
            case D: case RIGHT:
                right= true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case W: case UP:
                up = false;
                break;
            case S: case DOWN:
                down = false;
                break;
            case A: case LEFT:
                left = false;
                break;
            case D: case RIGHT:
                right= false;
                break;
        }
    }
}
