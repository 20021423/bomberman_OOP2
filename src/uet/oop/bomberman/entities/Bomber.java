package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.GameSound;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.Bomb;
import uet.oop.bomberman.entities.staticEntities.Item;
import uet.oop.bomberman.entities.staticEntities.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.io.IOException;

public class Bomber extends AnimatedEntity {
    public static int bomberLife = 3;
    protected Keyboard key;
    public static int quitGameTime = 50;
    protected static int bombCount = 1;
    protected static int delayTime = 0;

    public Bomber(Coordinates tile, Keyboard key) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX() + 4);
        this.key = key;
    }

    @Override
    public void update() {
        animate();
        if (!alive) {
            afterDie();
            if (animate == 60) {
                BombermanGame.removeBomber();
                if (bomberLife > 0) {
                    BombermanGame.setBomber(new Bomber(new Coordinates(1, 1), BombermanGame.input));
                    alive = true;
                }
            }
            return;
        }
        putBomb();
        handleDirection();
        handleCollision();
        chooseSprite(Sprite.player_right,
                Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);
    }

    @Override
    protected void handleCollision() {
        Entity entity = BombermanGame.getEntityAt(tile.getX(), tile.getY());
        if (entity instanceof Enemy) {
            bomberLife--;
            die();
            GameSound.playMusic(GameSound.DEATH1);
        }
        if (entity instanceof Item) {
            ((Item) entity).getItem();
            GameSound.playMusic(GameSound.ITEM);
        }
        if (entity instanceof Portal) {
            try {
                if (Map.getTarget() == 0) {
                    Bomb.setFlameRadius(1);
                    bombCount = 1;
                    BombermanGame.getBomber().setSpeed(1);
                    BombermanGame.createMap(Map.getLevel() + 1);
                    GameSound.playMusic(GameSound.WIN);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    protected void putBomb() {
        if (key.space && delayTime < 0 && bombCount > 0
                && !(BombermanGame.getEntityAt(tile.getX(), tile.getY()) instanceof Bomb)) {
            BombermanGame.setBomb(new Bomb(new Coordinates(tile.getX(), tile.getY())));
            bombCount--;
            delayTime = 10;
            GameSound.playMusic(GameSound.BOOM);
        }
    }

    @Override
    public void animate() {
        if (animate > 120) animate = 0;
        else animate++;
        delayTime--;
        if (bomberLife <= 0) {
            quitGameTime--;
        }
    }


    @Override
    protected void handleDirection() {
        double xa = 0, ya = 0;
        if ((key.up && direct.getX() == 0 && canMoveToDirection(0, -1)) || direct.getY() < 0) {
            ya = -speed;
            if (direct.getY() >= 0) direct.setY(direct.getY() - Sprite.SCALED_SIZE);
        }
        if ((key.down && direct.getX() == 0 && canMoveToDirection(0, 1)) || direct.getY() > 0) {
            ya = speed;
            if (direct.getY() <= 0) direct.setY(direct.getY() + Sprite.SCALED_SIZE);
        }
        if ((key.left && direct.getY() == 0 && canMoveToDirection(-1, 0)) || direct.getX() < 0) {
            xa = -speed;
            if (direct.getX() >= 0) direct.setX(direct.getX() - Sprite.SCALED_SIZE);
        }
        if ((key.right && direct.getY() == 0 && canMoveToDirection(1, 0)) || direct.getX() > 0) {
            xa = +speed;
            if (direct.getX() <= 0) direct.setX(direct.getX() + Sprite.SCALED_SIZE);
        }

        if (direct.getX() != 0 || direct.getY() != 0) {
            move(xa * Sprite.PLAYER_SPEED, ya * Sprite.PLAYER_SPEED);
            direct.setX((int) (direct.getX() - xa * Sprite.PLAYER_SPEED));
            direct.setY((int) (direct.getY() - ya * Sprite.PLAYER_SPEED));
            moving = true;
        } else {
            if (key.up) direction = Entity.DIRECTION.UP;
            if (key.right) direction = Entity.DIRECTION.RIGHT;
            if (key.down) direction = Entity.DIRECTION.DOWN;
            if (key.left) direction = Entity.DIRECTION.LEFT;
            moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected void afterDie() {
        img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 20).getFxImage();
        if (quitGameTime <= 0) {
            BombermanGame.stage.hide();
        }
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    public void addBomb() {
        bombCount++;
    }

    public int getSpeed() {
        return (int) speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
