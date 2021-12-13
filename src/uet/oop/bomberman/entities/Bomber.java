package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedEntity {
    public static int bomber_life = 3;
    protected Keyboard _input;
    public static int time_exit_game = 60;
    protected static int bomb = 1;
    protected static int distance = 0;

    public Bomber(Coordinates tile, Keyboard _input) {
        super(tile);
        this.img = Sprite.player_right.getFxImage();
        this.pixel.setX(this.pixel.getX() + 4);
        this._input = _input;
    }

    @Override
    public void update() {
        animate();
        if (!_alive) {
            afterDie();
            if (_animate == 60) {
                BombermanGame.removeBomber();

                if (bomber_life > 0) {
                    BombermanGame.setBomber(new Bomber(new Coordinates(1, 1), BombermanGame.input));
                    _alive = true;
                }
            }
            return;
        }

        putBomb();
        handleDirection();
        handleCollision();

        chooseSprite(Sprite.player_right,
                Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);
    }

    @Override
    protected void handleCollision() {
        Entity entity = BombermanGame.getEntityAt(tile.getX(), tile.getY());
        if (entity instanceof Enemy) {
            bomber_life--;
            die();
        }
    }


    protected void putBomb() {
        if (_input.space && distance < 0 && bomb > 0
                && !(BombermanGame.getEntityAt(tile.getX(), tile.getY()) instanceof Bomb)) {
            BombermanGame.setBomb(new Bomb(new Coordinates(tile.getX(), tile.getY())));
            bomb--;
            distance = 10;
        }
    }

    @Override
    public void animate() {
        if (_animate > 120) _animate = 0;
        else _animate++;
        distance--;
        if (bomber_life <= 0) {
            time_exit_game--;
        }
    }


    @Override
    protected void handleDirection() {
        double xa = 0, ya = 0;
        if ((_input.up && d.getX() == 0 && canMoveToDirection(0, -1)) || d.getY() < 0) {
            ya = -speed;
            if (d.getY() >= 0) d.setY(d.getY() - Sprite.SCALED_SIZE);
        }
        if ((_input.down && d.getX() == 0 && canMoveToDirection(0, 1)) || d.getY() > 0) {
            ya = speed;
            if (d.getY() <= 0) d.setY(d.getY() + Sprite.SCALED_SIZE);
        }
        if ((_input.left && d.getY() == 0 && canMoveToDirection(-1, 0)) || d.getX() < 0) {
            xa = -speed;
            if (d.getX() >= 0) d.setX(d.getX() - Sprite.SCALED_SIZE);
        }
        if ((_input.right && d.getY() == 0 && canMoveToDirection(1, 0)) || d.getX() > 0) {
            xa = +speed;
            if (d.getX() <= 0) d.setX(d.getX() + Sprite.SCALED_SIZE);
        }

        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYER_SPEED, ya * Sprite.PLAYER_SPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYER_SPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYER_SPEED));
            _moving = true;
        } else {
            if (_input.up) _direction = Entity.DIRECTION.UP;
            if (_input.right) _direction = Entity.DIRECTION.RIGHT;
            if (_input.down) _direction = Entity.DIRECTION.DOWN;
            if (_input.left) _direction = Entity.DIRECTION.LEFT;
            _moving = false;
        }

    }

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }

    @Override
    protected void afterDie() {


        if (time_exit_game <= 0) {
            BombermanGame.stage.hide();

        }
    }

    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

    public void addBomb() {
        bomb++;
    }

    public int getSpeed() {
        return (int) speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
