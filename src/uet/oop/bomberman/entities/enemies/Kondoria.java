package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

/**
 * nếu bị chặn thì di chuyển theo hướng ngược lại
 * biết sinh đẻ sau 1 khoảng thời gian, 1 con chỉ được sinh 1 lần
 */
public class Kondoria extends Enemy {
    private boolean generative;

    public Kondoria(Coordinates tile) {
        super(tile);
        img = Sprite.kondoria_left1.getFxImage();
        distanceY = speed;
        generative = true;
    }

    @Override
    protected void handleDirection() {
        if (direct.getX() == 0 && distanceY ==0) {
            if (distanceX <= 0) {
                if (canMoveToDirection(-1, 0)) {
                    distanceX = -speed;
                    direct.setX(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(1, 0)) {
                    distanceX = speed;
                    direct.setX(Sprite.SCALED_SIZE);
                } else {
                    distanceX = 0;
                }
            } else if (distanceX >= 0) {
                if (canMoveToDirection(1, 0)) {
                    distanceX = speed;
                    direct.setX(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(-1, 0)) {
                    distanceX = -speed;
                    direct.setX(-Sprite.SCALED_SIZE);
                } else {
                    distanceX = 0;
                }
            }
        }
        if (direct.getY() == 0 && distanceX ==0) {
            if (distanceY <= 0) {
                if (canMoveToDirection(0, -1)) {
                    distanceY = -speed;
                    direct.setY(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, 1)) {
                    distanceY = speed;
                    direct.setY(Sprite.SCALED_SIZE);
                } else {
                    distanceY = 0;
                }
            } else if (distanceY >= 0) {
                if (canMoveToDirection(0, 1)) {
                    distanceY = speed;
                    direct.setY(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, -1)) {
                    distanceY = -speed;
                    direct.setY(-Sprite.SCALED_SIZE);
                } else {
                    distanceY = 0;
                }
            }
        }

        handleMove();
    }

    @Override
    public void update() {
        super.update();
        if (!alive) {
            return;
        }
        proliferate();
        handleDirection();

        chooseSprite(Sprite.kondoria_right1,
                Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3,
                Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3,
                Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3,
                Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3);

    }

    private void proliferate() {
        if (generative && animate % 600 == 0) {
            BombermanGame.setEnemy(new Kondoria(tile));
            generative = false;
        }
    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
        img = Sprite.kondoria_dead.getFxImage();
    }
}