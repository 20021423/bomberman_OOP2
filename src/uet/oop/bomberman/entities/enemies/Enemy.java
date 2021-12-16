package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.GameSound;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends AnimatedEntity {
    protected int _points;

    public Enemy(Coordinates tile, int points) {
        super(tile);
        this._points = points;
    }

    public void animate() {
        if (_animate > 1200) {
            _animate = 0;
        } else {
            _animate++;
        }
    }

    @Override
    public void update() {
        animate();
        if (!_alive) {
            BombermanGame.removeEnemy(this);
            afterDie();

            if (_animate == 60) {
                BombermanGame.addPoints(_points);
                BombermanGame.removeDead(this);
                GameSound.playMusic(GameSound.ENEMY_DIE);
            }
        }

    }

    protected void handleMove() {
        if (d.getX() != 0 || d.getY() != 0) {
            move(xa * Sprite.PLAYER_SPEED, ya * Sprite.PLAYER_SPEED);
            d.setX((int) (d.getX() - xa * Sprite.PLAYER_SPEED));
            d.setY((int) (d.getY() - ya * Sprite.PLAYER_SPEED));
            _moving = true;
        } else {
            _moving = false;
        }
    }

    @Override
    protected abstract void handleDirection();

    @Override
    protected void move(double xa, double ya) {
        super.move(xa, ya);
    }


    @Override
    protected boolean canMoveToDirection(int x, int y) {
        return super.canMoveToDirection(x, y);
    }

}
