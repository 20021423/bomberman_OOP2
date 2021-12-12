package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Flame extends StaticEntity {
    protected int time = 30;  // thời gian để nổ ra 4 phía
    protected int frame = -1;
    protected List<FlameSegment> _flameSegments;
    protected int _radius;

    public Flame(Coordinates tile, int radius) {
        super(tile);
        _radius = radius;
        createFlameSegments();
    }

    public void update() {
        time--;
        if (time > 0) {
            _flameSegments.forEach(FlameSegment::update);
        } else {
             BombermanGame.removeFlame();
        }
    }

    public void createFlameSegments() {
        int x = tile.getX();
        int y = tile.getY();
        _flameSegments = new ArrayList<>();
        _flameSegments.add(new FlameSegment(new Coordinates(x, y), DIRECTION.CENTER, false));
        createSpark(DIRECTION.UP);
        createSpark(DIRECTION.RIGHT);
        createSpark(DIRECTION.DOWN);
        createSpark(DIRECTION.LEFT);
    }

    public void createSpark(DIRECTION direction) {
        int x = tile.getX();
        int y = tile.getY();
        int radius = _radius;
        int xt = x; // xt,yt là tọa độ từ x đến tọa độ đuôi của flamesegment
        int yt = y;
        // xem từ center đến radius, xử lý cụ thể khi gặp bomber, brick và enemy, wall
        for (int i = 1; i <= radius; i++) {
            switch (direction) {
                case UP:
                    xt = x;
                    yt = y - i;
                    break;
                case DOWN:
                    xt = x;
                    yt = y + i;
                    break;
                case LEFT:
                    xt = x - i;
                    yt = y;
                    break;
                case RIGHT:
                    xt = x + i
                    ;
                    yt = y;
                    break;
            }
            Entity entity = BombermanGame.getEntityAt(xt, yt);
            if (entity instanceof Wall) { // gặp tường thì cắt bớt độ dài của flame
                radius = i - 1;
            } else if (entity instanceof Brick) { // gặp brick thì set lại độ dài flame
                radius = i;
                // thêm flagment ở đuôi flame vào mảng flame
                _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, true));
                Brick brick = (Brick) entity;
                brick.remove();
            } else if (entity instanceof Bomb) { // flame gặp bomb thì cho nổ ngay lập tức
                radius = i - 1;
                ((Bomb) entity)._timeToExplode = 0;

            } else {
                if (i == radius) { // flamesegment đuôi có hình ảnh khác với thân
                    _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, true));
                } else {
                    _flameSegments.add(new FlameSegment(new Coordinates(xt, yt), direction, false));
                }
            }
        }
    }

    public List<FlameSegment> get_flameSegments() {
        return _flameSegments;
    }
}
