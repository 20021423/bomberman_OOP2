package uet.oop.bomberman.entities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public abstract class AnimatedEntity extends Entity implements Animated {
    protected boolean _alive = true;
    protected boolean _moving = false;
    protected boolean _bombpass = false;
    protected boolean _flamepass = false;
    protected boolean _brickpass = false;
    protected int speed = 1;
    protected int xa = 0, ya = 0;

    protected int _animate = 0;
    protected Coordinates d = new Coordinates(0, 0);

    public AnimatedEntity(Coordinates tile) {
        super(tile);
    }

    //@Override
    public abstract void update();

    protected abstract void handleDirection();

    protected void move(double xa, double ya) {
        if (d.getX() > 0) _direction = DIRECTION.RIGHT;
        if (d.getX() < 0) _direction = DIRECTION.LEFT;
        if (d.getY() > 0) _direction = DIRECTION.DOWN;
        if (d.getY() < 0) _direction = DIRECTION.UP;

        pixel.setY((int) (pixel.getY() + ya));

        pixel.setX((int) (pixel.getX() + xa));

        tile = pixel.convertPixelToTile();
    }

    public void die() {
        if (!_alive) return;
        this._alive = false;
        _animate = 0;
//        if (this instanceof Enemy) {
//            BombermanGame.setDead((Enemy) this);
//        }
    }

    protected abstract void afterDie();

    protected boolean canMoveToDirection(int x, int y) {
//        Entity entity = BombermanGame.getEntityAt(tile.getX() + x, tile.getY() + y);
//        return !(entity instanceof Wall) && (_brickpass || !(entity instanceof Brick)) && (_bombpass || !(entity instanceof Bomb));
        return false;
    }


    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = sprite1.getFxImage();
        if (_moving) {
            img = Sprite.movingSprite(sprite2, sprite3, _animate, 20).getFxImage();
        }
    }

    protected void chooseSprite(Sprite default1,
                                Sprite left1, Sprite left2, Sprite left3,
                                Sprite right1, Sprite right2, Sprite right3,
                                Sprite up1, Sprite up2, Sprite up3,
                                Sprite down1, Sprite down2, Sprite down3) {
        switch (_direction) {
            case UP:
                loadAnimated(up1, up2, up3);
                break;
            case RIGHT:
                loadAnimated(right1, right2, right3);
                break;
            case DOWN:
                loadAnimated(down1, down2, down3);
                break;
            case LEFT:
                loadAnimated(left1, left2, left3);
                break;
            default:
                img = default1.getFxImage();
                break;
        }
    }

    public boolean is_flamepass() {
        return _flamepass;
    }

    public void set_bombpass(boolean _bombpass) {
        this._bombpass = _bombpass;
    }

    public void set_flamepass(boolean _flamepass) {
        this._flamepass = _flamepass;
    }

    public void set_brickpass(boolean _brickpass) {
        this._brickpass = _brickpass;
    }

    public Coordinates getD() {
        return d;
    }

    public void setD(Coordinates d) {
        this.d = d;
    }

    protected abstract void handleCollision();

    public boolean is_alive() {
        return _alive;
    }

}
