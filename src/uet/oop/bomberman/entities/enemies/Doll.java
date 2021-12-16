package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

/**
 * gặp có thể đi xuyên qua Brick, gặp Wall thì đi lên hoặc xuống.
 * Chỉ đi một phương, gặp wall mới đổi phương khác (hướng dọc hoặc ngang)
 */
public class Doll extends Enemy {
    private static Random random = new Random();
    int direc = 0;

    public Doll(Coordinates tile) {
        super(tile);
        img = Sprite.doll_left1.getFxImage();
        distanceX = speed;
    }

    @Override
    protected void handleDirection() {
        if (direct.getX() == 0) {
            if (Math.abs(distanceX) == speed) {
                int xt = distanceX / Math.abs(distanceX);
                if (canMoveToDirection(xt, 0)) {
                    direct.setX(xt * Sprite.SCALED_SIZE);
                } else {
                    distanceX = 0;
                    do {
                        direc = random.nextInt(3);
                        direc--;
                    } while (direc == 0);
                    if (canMoveToDirection(0, direc)) {
                        distanceY = direc * speed;
                    } else {
                        distanceY = -direc * speed;
                    }
                }
            }
        }
        if (direct.getY() == 0) {
            if (Math.abs(distanceY) == speed) {
                int yt = distanceY / Math.abs(distanceY);
                if (canMoveToDirection(0, yt)) {
                    direct.setY(yt * Sprite.SCALED_SIZE);
                } else {
                    distanceY = 0;
                    do {
                        direc = random.nextInt(3);
                        direc--;
                    } while (direc == 0);
                    if (canMoveToDirection(direc, 0)) {
                        distanceX = direc * speed;
                    } else {
                        distanceX = -direc * speed;
                    }
                }
            }
        }

        handleMove();
    }

    @Override
    public void animate() {
        if (animate > 6000) {
            animate = 0;
        } else {
            animate++;
        }
    }

    @Override
    public void update() {
        super.update();
        if (!alive) {
            return;
        }
        handleDirection();

        chooseSprite(Sprite.doll_left1,
                Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3,
                Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3,
                Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left1,
                Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right1);

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        Entity entity = BombermanGame.getEntityAt(tile.getX() + x, tile.getY() + y);
        return !(entity instanceof Wall);
    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void afterDie() {
        img = Sprite.doll_dead.getFxImage();
    }
}
