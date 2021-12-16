package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.Bomb;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public abstract class AnimatedEntity extends Entity implements Animated {
    protected boolean alive = true;
    protected boolean moving = false;
    protected boolean bombPass = false;
    protected boolean flamePass = false;
    protected boolean brickPass = false;
    protected int speed = 1;
    protected int distanceX = 0, distanceY = 0;

    protected int animate = 0;
    protected Coordinates direct = new Coordinates(0, 0);

    public AnimatedEntity(Coordinates tile) {
        super(tile);
    }

    public abstract void update();

    protected abstract void handleDirection();

    protected void move(double xa, double ya) {
        if (direct.getX() > 0) direction = DIRECTION.RIGHT;
        if (direct.getX() < 0) direction = DIRECTION.LEFT;
        if (direct.getY() > 0) direction = DIRECTION.DOWN;
        if (direct.getY() < 0) direction = DIRECTION.UP;

        pixel.setY((int) (pixel.getY() + ya));

        pixel.setX((int) (pixel.getX() + xa));

        tile = pixel.convertPixelToTile();
    }

    public void die() {
        if (!alive) return;
        this.alive = false;
        animate = 0;
        if (this instanceof Enemy) {
            BombermanGame.setDead((Enemy) this);
            Map.setTarget(Map.getTarget() - 1);
        }
    }

    protected abstract void afterDie();

    protected boolean canMoveToDirection(int x, int y) {
        Entity entity = BombermanGame.getEntityAt(tile.getX() + x, tile.getY() + y);
        return !(entity instanceof Wall) && (brickPass || !(entity instanceof Brick)) && (bombPass || !(entity instanceof Bomb));
    }


    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = sprite1.getFxImage();
        if (moving) {
            img = Sprite.movingSprite(sprite2, sprite3, animate, 20).getFxImage();
        }
    }

    protected void chooseSprite(Sprite default1,
                                Sprite left1, Sprite left2, Sprite left3,
                                Sprite right1, Sprite right2, Sprite right3,
                                Sprite up1, Sprite up2, Sprite up3,
                                Sprite down1, Sprite down2, Sprite down3) {
        switch (direction) {
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

    public boolean isFlamePass() {
        return flamePass;
    }


    public Coordinates getDirect() {
        return direct;
    }

    protected abstract void handleCollision() throws IOException;

    public boolean isAlive() {
        return alive;
    }

}
