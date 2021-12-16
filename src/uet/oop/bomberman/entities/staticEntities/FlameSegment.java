package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.GameSound;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Khi bom nổ thì sẽ nổ ra 4 hướng, mỗi hướng lại chia thành 2 trạng thái hình ảnh,
 * hình ảnh ở phần đuôi sẽ khác so với phần thân
 * phần center sẽ là phần đầu khi tung ra 4 hướng
 */
public class FlameSegment extends StaticEntity implements Animated {
    protected boolean tail; // dùng để xác định phần đuôi của 1 flamesegment
    protected int animate = 0;

    public FlameSegment(Coordinates tile, Entity.DIRECTION direction, boolean last) {
        super(tile);
        tail = last;
        this.direction = direction;
    }

    @Override
    public void animate() {
        if (animate > 30) {
            animate = 0;
        } else {
            animate++;
        }
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        this.img = Sprite.movingSprite(sprite2, sprite3, sprite1, animate, 10).getFxImage();
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        collisionHandling();

    }

    protected void chooseSprite() {
        switch (direction) {
            case CENTER:
                loadAnimated(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
                break;
            case RIGHT:
                if (tail) {
                    loadAnimated(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2);
                } else {
                    loadAnimated(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2);
                }
                break;
            case UP:
                if (tail) {
                    loadAnimated(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case DOWN:
                if (tail) {
                    loadAnimated(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case LEFT:
                if (tail) {
                    loadAnimated(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2);
                } else {
                    loadAnimated(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2);
                }
                break;
            case NONE:
            default:
                break;
        }
    }

    /**
     * hàm xử lý va chạm của FlameSegment.
     */
    public void collisionHandling() {
        Entity entity = BombermanGame.getEntityAt(tile.getX(), tile.getY());
        if (entity instanceof AnimatedEntity) {
            if (!((AnimatedEntity) entity).isFlamePass()) {
                if (entity instanceof Bomber && ((Bomber) entity).isAlive()) {
                    Bomber.bomberLife--;
                    GameSound.playMusic(GameSound.DEATH1);
                }
                ((AnimatedEntity) entity).die();
            }
        }
        if (entity instanceof Brick) {
            ((Brick) entity).remove();
        }
    }
}
