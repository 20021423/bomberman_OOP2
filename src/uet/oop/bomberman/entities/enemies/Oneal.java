package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.staticEntities.Bomb;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

/**
 * gặp tường thì di chuyển hướng ngược lại và biết đuổi theo bomber.
 */
public class Oneal extends Enemy {

    public Oneal(Coordinates tile) {
        super(tile);
        img = Sprite.oneal_left1.getFxImage();
        distanceX = -speed;
    }

    @Override
    protected void handleDirection() {
        if (direct.getY() == 0 && direct.getX() == 0) {
            findBomber();
        }
        if (direct.getX() == 0 && distanceY == 0) { // đi ngang
            if (distanceX < 0) { // đi ngang trái bị chặn sẽ đi ngược lại sang ngang phải
                if (canMoveToDirection(-1, 0)) {
                    direct.setX(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(1, 0)) {
                    distanceX = speed;
                    direct.setX(Sprite.SCALED_SIZE);
                }
            } else if (distanceX > 0) { // đi ngang phải bị chặn sẽ đi được lại sang ngang trái
                if (canMoveToDirection(1, 0)) {
                    direct.setX(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(-1, 0)) {
                    distanceX = -speed;
                    direct.setX(-Sprite.SCALED_SIZE);
                }
            }
        }
        if (direct.getY() == 0 && distanceX == 0) { // đi dọc
            if (distanceY < 0) {
                if (canMoveToDirection(0, -1)) {
                    direct.setY(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, 1)) {
                    distanceY = speed;
                    direct.setY(Sprite.SCALED_SIZE);
                }
            } else if (distanceY > 0) {
                if (canMoveToDirection(0, 1)) {
                    direct.setY(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, -1)) {
                    distanceY = -speed;
                    direct.setY(-Sprite.SCALED_SIZE);
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
        handleDirection();

        chooseSprite(Sprite.oneal_left1,
                Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3);


    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    /**
     * trường hợp di chuyển trên cùng 1 cột hoặc 1 hàng thì sẽ cập nhật cho quái tốc độ tương ứng
     * và kiểm tra từ quái đến bomber có chướng ngại không, nếu có thì sẽ dừng đuổi.
     */
    private void findBomber() {
        if (BombermanGame.getBomber() == null) {
            return;
        }
        int xtemp = 0, ytemp = 0;
        int low, high;
        speed = 1;
        if (tile.getX() == BombermanGame.getBomber().getTile().getX()) {
            xtemp = 0;
            if (tile.getY() < BombermanGame.getBomber().getTile().getY()) {
                ytemp = speed;
                low = tile.getY();
                high = BombermanGame.getBomber().getTile().getY();
            } else if (tile.getY() > BombermanGame.getBomber().getTile().getY()) {
                ytemp = -speed;
                low = BombermanGame.getBomber().getTile().getY();
                high = tile.getY();
            } else {
                return;
            }
            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(tile.getX(), i) instanceof Wall
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Brick
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Bomb) {
                    return;
                }
            }
            distanceX = xtemp;
            distanceY = ytemp;
            return;
        } else if (tile.getY() == BombermanGame.getBomber().getTile().getY()) {
            ytemp = 0;
            if (tile.getX() < BombermanGame.getBomber().getTile().getX()) {
                xtemp = speed;
                low = tile.getX();
                high = BombermanGame.getBomber().getTile().getX();
            } else if (tile.getX() > BombermanGame.getBomber().getTile().getX()) {
                xtemp = -speed;
                low = BombermanGame.getBomber().getTile().getX();
                high = tile.getX();
            } else {
                distanceY = ytemp;
                distanceX = xtemp;
                return;
            }

            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(i, tile.getY()) instanceof Wall
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Brick
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Bomb) {
                    return;
                }
            }
            distanceX = xtemp;
            distanceY = ytemp;
        }
    }

    @Override
    protected void afterDie() {
        img = Sprite.oneal_dead.getFxImage();
    }
}
