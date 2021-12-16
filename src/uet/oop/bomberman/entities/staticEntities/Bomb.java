package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.GameSound;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends StaticEntity implements Animated {
    protected double timeToExplode = 120;
    protected int animate = 0;
    protected static int flameRadius = 1;

    public Bomb(Coordinates tile) {
        super(tile);
    }

    @Override
    public void animate() {
        if (animate > 90) {
            animate = 0;
        } else {
            animate++;
        }
        timeToExplode--;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1, sprite2, sprite3, animate, 30).getFxImage();
    }

    @Override
    public void update() {
        animate();
        if (timeToExplode < 0) {
            GameSound.playMusic(GameSound.BANG);
            bombExplode();
            return;
        }
        loadAnimated(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2);
    }

    protected void bombExplode() {
        BombermanGame.setFlame(new Flame(tile, flameRadius));
        BombermanGame.removeBomb();
    }

    public static int getFlameRadius() {
        return flameRadius;
    }

    public static void setFlameRadius(int flameRadius) {
        Bomb.flameRadius = flameRadius;
    }
}
