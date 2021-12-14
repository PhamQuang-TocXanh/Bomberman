package uet.oop.bomberman.entities.Character;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Message.Message;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Bomber extends Character {
    private int life;
    protected Keyboard input;
    private List<Bomb> bombs;
    private int maxBombs = 1;
    private int usedBombs = 0;
    private int bombLength = 1;
    private int timeBeforeNextBomb = 0;
    private int timeBeforeNextDetonate = 0;
    private boolean canDetonate = false;
    private boolean invincible = false;
    private int invincibleTime = 400;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        velocity = 2;
        sprite = Sprite.player_right;
        input = new Keyboard();
        bounds = new Rectangle2D(0, 4, 24, Sprite.SCALED_SIZE - 6);
        life = 1;
        bombs = gameMap.bombs;
        timeAfter = 50;
    }

    @Override
    public void update() {
        animate();
        if (!alive) {
            afterKill();
        } else {
            if (invincible) {
                invincibleTimeCheck();
            }
            calculateMove();

            checkCollideEnemy();

            placeBomb();

            detonateBomb();

        }
    }

    @Override
    public void render(GraphicsContext gc) {

        chooseSprite();

        img = sprite.getFxImage();

        super.render(gc);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Explosion && !flamePass) {
            kill();
        }

        return true;
    }

    @Override
    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.right) xa++;
        if (input.left) xa--;

        if (xa != 0 || ya != 0) {
            moving = true;
            move(xa * velocity, ya * velocity);
        } else {
            moving = false;
        }
    }


    protected void move(int xa, int ya) {
        if (ya < 0) direction = 0; // up
        if (ya > 0) direction = 2; // down
        if (xa > 0) direction = 1; // right
        if (xa < 0) direction = 3; // left
       // super.move(xa, ya);
        if(canMove(xa, 0)) {
            x += xa;
        }

        if(canMove(0, ya)) {
            y += ya;
        }
    }

    @Override
    public void kill() {

        if (!alive || invincible) return;

        alive = false;
        _animate = 0;
        life--;
        new Sound().playMusicEffect(Sound.BOMBER_DIE);
        gameMap.messages.add(new Message("-1 LIFE", this, 100, false));
    }

    @Override
    protected void afterKill() {
        if (timeAfter > 0) --timeAfter;
        else {
            alive = true;
            timeAfter = 50;
            x = 32;
            y = 32;
        }
    }

    public void checkCollideEnemy() {
        for (Character character : gameMap.characters) {
            if (character == this) continue;
            //if (character.getXTile() == this.getXTile() && character.getYTile() == this.getYTile()) {
            if (this.getBounds().intersects(character.getBounds())) {
                this.kill();
                return;
            }
        }
    }

    public  void placeBomb() {
        if(input.space && usedBombs < maxBombs && timeBeforeNextBomb < 0) {
            if (!(gameMap.getTileAt(this.getXTile(), this.getYTile()) instanceof Grass)) return;
            if (gameMap.getBombAt(this.getXTile(), this.getYTile()) != null) return;
            timeBeforeNextBomb = 20;
            usedBombs++;
            Bomb b = new Bomb(this.getXTile(), this.getYTile(), Sprite.bomb.getFxImage());
            gameMap.bombs.add(b);
            new Sound().playMusicEffect(Sound.NEW_BOMB);
        }
        else timeBeforeNextBomb--;
    }

    public void detonateBomb() {
        if (canDetonate && input.detonate && timeBeforeNextDetonate < 0 && !bombs.isEmpty()) {
            // có thể kích nổ nhiều lần k?
            timeBeforeNextDetonate = 30;
            bombs.get(bombs.size() - 1).setTimeBeforeExplode(1);
        } else timeBeforeNextDetonate--;
    }

    public void invincibleTimeCheck() {
        if (invincibleTime > 0) {
            invincibleTime --;
        } else {
            invincible = false;
            invincibleTime = 400;
        }
    }

    /* Power up */
    public void increaseMaxBombs() {
        ++maxBombs;
        gameMap.messages.add(new Message("+1 Bomb", this, 100, false));
    }

    public void increaseBombLength() {
        ++bombLength;
        gameMap.messages.add(new Message("+ Bomb Length", this, 100, false));
    }

    public void increaseSpeed() {
        ++velocity;
        gameMap.messages.add(new Message("+ Speed", this, 100, false));
    }

    public void canPassWall() {
        wallPass = true;
        gameMap.messages.add(new Message("Wall Pass", this, 100, false));
    }

    public void canPassBomb() {
        bombPass = true;
        gameMap.messages.add(new Message("Bomb Pass", this, 100, false));
    }

    public void canPassFlame() {
        flamePass = true;
        gameMap.messages.add(new Message("Flame Pass", this, 100, false));
    }

    public void setCanDetonate() {
        canDetonate = true;
        gameMap.messages.add(new Message("Now you can press B to detonate bomb", this, 100, false));
    }

    public void setInvincible() {
        invincible = true;
        gameMap.messages.add(new Message("INVINCIBLE", this, invincibleTime, true));
    }

    public void setUsedBombs(int usedBombs) {
        this.usedBombs = usedBombs;
    }

    public void setTimeBeforeNextBomb(int timeBeforeNextBomb) {
        this.timeBeforeNextBomb = timeBeforeNextBomb;
    }

    public void setTimeBeforeNextDetonate(int timeBeforeNextDetonate) {
        this.timeBeforeNextDetonate = timeBeforeNextDetonate;
    }

    public void setCanDetonate(boolean canDetonate) {
        this.canDetonate = canDetonate;
    }

    /*
        @Override
        protected boolean canMove(int xMove, int yMove) {
            int xTile;
            int yTile;
            if (xMove > 0) {//move right
                xTile = (int) (x + xMove + bounds.getWidth())/Sprite.SCALED_SIZE;
                for (int i = 0; i < 2; i++) {
                    yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                    Entity e = gameMap.getTileAt(xTile, yTile);
                    if (!e.collide(this)) {
                        System.out.println(yTile + "-" + xTile);
                        return false;
                    }
                }
            } else if (xMove < 0) { //move left
                xTile = (int) (x + xMove)/Sprite.SCALED_SIZE;
                for (int i = 0; i < 2; i++) {
                    yTile = (int) (y + bounds.getMinY() + bounds.getHeight() * i)/Sprite.SCALED_SIZE;
                    Entity e = gameMap.getTileAt(xTile, yTile);
                    if (!e.collide(this)) {
                        System.out.println(yTile + "-" + xTile);
                        return false;
                    }
                }
            }

            if (yMove < 0) { // move up
                yTile = (int) (y + bounds.getMinY() + yMove)/Sprite.SCALED_SIZE;
                for (int i = 0; i < 2; i++) {
                    xTile = (int) (x + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                    Entity e = gameMap.getTileAt(xTile, yTile);
                    if (!e.collide(this)) {
                        System.out.println(yTile + "-" + xTile);
                        return false;
                    }
                }
            } else if (yMove > 0) { //move down
                yTile = (int) (y + bounds.getMinY() + yMove + bounds.getHeight())/Sprite.SCALED_SIZE;
                for (int i = 0; i < 2; i++) {
                    xTile = (int) (x + bounds.getWidth() * i)/Sprite.SCALED_SIZE;
                    Entity e = gameMap.getTileAt(xTile, yTile);
                    if (!e.collide(this)) {
                        System.out.println(yTile + "-" + xTile);
                        return false;
                    }
                }
            }
            return true;
        }*/
    public Keyboard getKeyboard() {
        return input;
    }

    public List<Bomb> get_bombs() {
        return bombs;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void set_bombs(List<Bomb> _bombs) {
        this.bombs = _bombs;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    @Override
    protected void chooseSprite() {
        if (!alive) {
            sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 50);
            return;
        }

        switch(direction) {
            case 0:
                sprite = Sprite.player_up;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
        }
    }

    public int getMaxBombs() {
        return maxBombs;
    }

    public void setMaxBombs(int maxBombs) {
        this.maxBombs = maxBombs;
    }

    public int getUsedBombs() {
        return usedBombs;
    }

    public void decreaseUsedBombs() {
        if (this.usedBombs > 0) {
            this.usedBombs--;
        }
    }

    public int getBombLength() {
        return bombLength;
    }

    public void setBombLength(int bombLength) {
        this.bombLength = bombLength;
    }
}