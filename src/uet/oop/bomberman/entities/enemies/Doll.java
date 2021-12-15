package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

/**
 * gặp có thể đi xuyên qua Brick, gặp Wall thì đi lên hoặc xuống.
 */
public class Doll extends Enemy {
    private static Random random = new Random();
    int direc = 0;

    public Doll(Coordinates tile) {
        super(tile, 200);
        img = Sprite.doll_left1.getFxImage();
        xa = speed;
    }

    @Override
    protected void handleDirection() {
        if (d.getX() == 0) {
            if (Math.abs(xa) == speed) {
                int xt = xa / Math.abs(xa);
                if (canMoveToDirection(xt, 0)) {
                    d.setX(xt * Sprite.SCALED_SIZE);
                } else {
                    xa = 0;
                    do {
                        direc = random.nextInt(3);
                        direc--;
                    } while (direc == 0);
                    if (canMoveToDirection(0, direc)) {
                        ya = direc * speed;
                    } else {
                        ya = -direc * speed;
                    }
                }
            }
        }
        if (d.getY() == 0) {
            if (Math.abs(ya) == speed) {
                int yt = ya / Math.abs(ya);
                if (canMoveToDirection(0, yt)) {
                    d.setY(yt * Sprite.SCALED_SIZE);
                } else {
                    ya = 0;
                    do {
                        direc = random.nextInt(3);
                        direc--;
                    } while (direc == 0);
                    if (canMoveToDirection(direc, 0)) {
                        xa = direc * speed;
                    } else {
                        xa = -direc * speed;
                    }
                }
            }
        }

        handleMove();
    }

    @Override
    public void animate() {
        if (_animate > 6000) {
            _animate = 0;
        } else {
            _animate++;
        }
    }

    @Override
    public void update() {
        super.update();
        if (!_alive) {
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
