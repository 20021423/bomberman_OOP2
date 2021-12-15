package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Khi bom nổ thì sẽ nổ ra 4 hướng, mỗi hướng lại chia thành 2 trạng thái hình ảnh,
 * hình ảnh ở phần đuôi sẽ khác so với phần thân
 * phần center sẽ là phần đầu khi tung ra 4 hướng
 */
public class FlameSegment extends StaticEntity implements Animated {
    protected boolean _last; // dùng để xác định phần đuôi của 1 flamesegment
    protected int _animate = 0;

    public FlameSegment(Coordinates tile, Entity.DIRECTION direction, boolean last) {
        super(tile);
        _last = last;
        this._direction = direction;
    }

    @Override
    public void animate() {
        if (_animate > 30) {
            _animate = 0;
        } else {
            _animate++;
        }
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        this.img = Sprite.movingSprite(sprite2, sprite3, sprite1, _animate, 10).getFxImage();
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        collisionHandling();

    }

    protected void chooseSprite() {
        switch (_direction) {
            case CENTER:
                loadAnimated(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
                break;
            case RIGHT:
                if (_last) {
                    loadAnimated(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2);
                } else {
                    loadAnimated(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2);
                }
                break;
            case UP:
                if (_last) {
                    loadAnimated(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case DOWN:
                if (_last) {
                    loadAnimated(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2);
                } else {
                    loadAnimated(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2);
                }
                break;
            case LEFT:
                if (_last) {
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
            if (!((AnimatedEntity) entity).is_flamepass()) {
                if (entity instanceof Bomber && ((Bomber) entity).is_alive()) {
                    Bomber.bomber_life--;
                    //GameSound.playMusic(GameSound.BOMBER_DIE);
                }
                ((AnimatedEntity) entity).die();
            }
        }
        if (entity instanceof Brick) {
            ((Brick) entity).remove();
        }
    }
}
