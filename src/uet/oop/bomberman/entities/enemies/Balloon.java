package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

    public Balloon(Coordinates tile) {
        super(tile, 100);
        img = Sprite.balloom_left1.getFxImage();
        xa = -speed;
    }

    @Override
    protected void handleDirection() {
        if (d.getX() == 0) {
            if (xa == -speed) {
                if (canMoveToDirection(-1, 0)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, -1)) {
                        ya = -speed;
                    } else {
                        ya = speed;
                    }
                }
            } else if (xa == speed) {
                if (canMoveToDirection(1, 0)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    if (canMoveToDirection(0, 1)) {
                        ya = speed;
                    } else {
                        ya = -speed;
                    }
                }
            }
        }
        if (d.getY() == 0) {
            if (ya == -speed) {
                if (canMoveToDirection(0, -1)) {
                    d.setY(-Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(1, 0)) {
                        xa = speed;
                    } else {
                        xa = -speed;
                    }
                }
            } else if (ya == speed) {
                if (canMoveToDirection(0, 1)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    if (canMoveToDirection(-1, 0)) {
                        xa = -speed;
                    } else {
                        xa = speed;
                    }
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

        handleDirection();

        chooseSprite(Sprite.balloom_right1,
                Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,
                Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3);

    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
        img = Sprite.balloom_dead.getFxImage();
    }
}
