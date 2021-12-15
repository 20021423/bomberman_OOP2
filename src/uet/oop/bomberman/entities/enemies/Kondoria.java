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
        super(tile, 100);
        img = Sprite.kondoria_left1.getFxImage();
        ya = speed;
        generative = true;
    }

    @Override
    protected void handleDirection() {
        if (d.getX() == 0 && ya==0) {
            if (xa <= 0) {
                if (canMoveToDirection(-1, 0)) {
                    xa = -speed;
                    d.setX(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(1, 0)) {
                    xa = speed;
                    d.setX(Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                }
            } else if (xa >= 0) {
                if (canMoveToDirection(1, 0)) {
                    xa = speed;
                    d.setX(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(-1, 0)) {
                    xa = -speed;
                    d.setX(-Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                }
            }
        }
        if (d.getY() == 0 && xa==0) {
            if (ya <= 0) {
                if (canMoveToDirection(0, -1)) {
                    ya = -speed;
                    d.setY(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, 1)) {
                    ya = speed;
                    d.setY(Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                }
            } else if (ya >= 0) {
                if (canMoveToDirection(0, 1)) {
                    ya = speed;
                    d.setY(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, -1)) {
                    ya = -speed;
                    d.setY(-Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                }
            }
        }

        handleMove();
    }

    @Override
    public void update() {
        super.update();
        if (!_alive) {
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
        if (generative && _animate % 600 == 0) {
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