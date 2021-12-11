package uet.oop.bomberman.input;



import javafx.scene.input.KeyEvent;


public class Keyboard {
    public boolean up, down, left, right, space, detonate;
    public Keyboard() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.space = false;
        this.detonate = false;
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
                right = true;
                break;
            case SPACE:
                space = true;
                break;
            case B:
                detonate = true;
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
                right = false;
                break;
            case SPACE:
                space = false;
                break;
            case B:
                detonate = false;
                break;
        }
    }
}
