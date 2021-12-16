package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

/**
 * con quái Balloon di chuyển ngang, dọc trên cỏ
 * Khi đi ngang phía trái trên cỏ nếu gặp tường nó sẽ đi dọc lên, nếu đi dọc lên bị chặn thì đi dọc xuống
 * Khi đi ngang phải trên cỏ nếu bị chặn thì nó sẽ đi xuống trước, nếu dọc xuống bị chặn thì nó đi lên trên
 * Khi đi dọc hướng lên trên cỏ nếu bị chặn thì nó sẽ đi ngang phải trước, nếu bị chặn tiếp thì đi ngang trái
 * Khi đi dọc hướng xuống trên cỏ nếu bị chặn thì nó sẽ đi ngang trái trước, nếu bị chặn tiếp thì đi ngang phải
 */
public class Balloon extends Enemy {

    public Balloon(Coordinates tile) {
        super(tile);
        img = Sprite.balloom_left1.getFxImage();
        distanceX = -speed;
    }

    @Override
    protected void handleDirection() { // Coordinate d là tọa độ tile để xác định hướng
        if (direct.getX() == 0) {
            if (distanceX == -speed) {
                if (canMoveToDirection(-1, 0)) { // đi trên cỏ về phía bên trái và cập nhật hoành độ mới
                    direct.setX(-Sprite.SCALED_SIZE);
                } else { // trường hợp va vào thực thể khác thì sẽ đi dọc
                    distanceX = 0;
                    if (canMoveToDirection(0, -1)) { // trường hợp đi lên trên
                        distanceY = -speed;
                    } else { // đi xuống dưới
                        distanceY = speed;
                    }
                }
            } else if (distanceX == speed) {  //
                if (canMoveToDirection(1, 0)) { // đi sang phải
                    direct.setX(Sprite.SCALED_SIZE);
                } else {  //
                    distanceX = 0;
                    if (canMoveToDirection(0, 1)) { // va vào thực thể khác thì đi xuống dưới
                        distanceY = speed;
                    } else { // đi lên trên
                        distanceY = -speed;
                    }
                }
            }
        }
        if (direct.getY() == 0) { // trường hợp đi dọc trên cỏ
            if (distanceY == -speed) { // đi lên trên
                if (canMoveToDirection(0, -1)) { // đi lên trên được trên cỏ
                    direct.setY(-Sprite.SCALED_SIZE);
                } else {
                    distanceY = 0;
                    if (canMoveToDirection(1, 0)) {
                        distanceX = speed;
                    } else {
                        distanceX = -speed;
                    }
                }
            } else if (distanceY == speed) { // đi xuống dưới
                if (canMoveToDirection(0, 1)) {
                    direct.setY(Sprite.SCALED_SIZE);
                } else {
                    distanceY = 0;
                    if (canMoveToDirection(-1, 0)) {
                        distanceX = -speed;
                    } else {
                        distanceX = speed;
                    }
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

        chooseSprite(Sprite.balloom_right1,
                Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,
                Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3);

    }

    @Override
    protected void afterDie() {
        img = Sprite.balloom_dead.getFxImage();
    }

    @Override
    protected void handleCollision() {
    }
}
