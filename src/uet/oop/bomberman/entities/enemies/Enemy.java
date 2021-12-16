package uet.oop.bomberman.entities.enemies;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.GameSound;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends AnimatedEntity {

    public Enemy(Coordinates tile) {
        super(tile);
    }

    public void animate() {
        if (animate > 1200) {
            animate = 0;
        } else {
            animate++;
        }
    }

    @Override
    public void update() {
        animate();
        if (!alive) {
            BombermanGame.removeEnemy(this);
            afterDie();

            if (animate == 60) {
                BombermanGame.removeDead(this);
                GameSound.playMusic(GameSound.DEATH2);
            }
        }

    }

    protected void handleMove() {
        if (direct.getX() != 0 || direct.getY() != 0) {
            move(distanceX * Sprite.PLAYER_SPEED, distanceY * Sprite.PLAYER_SPEED);
            direct.setX((int) (direct.getX() - distanceX * Sprite.PLAYER_SPEED));
            direct.setY((int) (direct.getY() - distanceY * Sprite.PLAYER_SPEED));
            moving = true;
        } else {
            moving = false;
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
