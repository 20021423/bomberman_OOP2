package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.staticEntities.Bomb;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{

    public Oneal(Coordinates tile) {
        super(tile, 300);
        img = Sprite.oneal_left1.getFxImage();
        xa = -speed;
    }

    @Override
    protected void handleDirection() {
        if (d.getY() == 0 && d.getX() == 0) {
            findBomber();
        }
        if (d.getX() == 0) { // đi ngang
            if (xa < 0) { // đi ngang trái bị chặn sẽ đi ngược lại sang ngang phải
                if (canMoveToDirection(-1, 0)) {
                    d.setX(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(1, 0)) {
                    xa = speed;
                    d.setX(Sprite.SCALED_SIZE);
                }
            } else if (xa > 0) { // đi ngang phải bị chặn sẽ đi được lại sang ngang trái
                if (canMoveToDirection(1, 0)) {
                    d.setX(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(-1, 0)) {
                    xa = -speed;
                    d.setX(-Sprite.SCALED_SIZE);
                }
            }
        }
        if (d.getY() == 0 ) { // đi dọc
            if (ya < 0) {
                if (canMoveToDirection(0, -1)) {
                    d.setY(-Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, 1)) {
                    ya = speed;
                    d.setY(Sprite.SCALED_SIZE);
                }
            } else if (ya > 0) {
                if (canMoveToDirection(0, 1)) {
                    d.setY(Sprite.SCALED_SIZE);
                } else if (canMoveToDirection(0, -1)) {
                    ya = -speed;
                    d.setY(-Sprite.SCALED_SIZE);
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
     *  trường hợp di chuyển trên cùng 1 cột hoặc 1 hàng thì sẽ cập nhật cho quái tốc độ tương ứng
     *    và kiểm tra từ quái đến bomber có chướng ngại không, nếu có thì sẽ dừng đuổi.
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
                xa = xtemp;
                ya = ytemp;
                return;
            }
            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(tile.getX(), i) instanceof Wall
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Brick
                        || BombermanGame.getEntityAt(tile.getX(), i) instanceof Bomb) {
                    return;
                }
            }
            xa = xtemp;
            ya = ytemp;
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
                ya = ytemp;
                xa = xtemp;
                return;
            }

            for (int i = low + 1; i < high; i++) {
                if (BombermanGame.getEntityAt(i, tile.getY()) instanceof Wall
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Brick
                        || BombermanGame.getEntityAt(i, tile.getY()) instanceof Bomb) {
                    return;
                }
            }
            xa = xtemp;
            ya = ytemp;
        }
        //speed = 1;
    }

    @Override
    protected void afterDie() {
        img = Sprite.oneal_dead.getFxImage();
    }
}
