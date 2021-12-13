package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends StaticEntity implements Animated {
    protected double _timeToExplode = 120;
    protected int _animate = 0;
    protected static int damage = 1;

    public Bomb(Coordinates tile) {
        super(tile);
    }

    @Override
    public void animate() {
        if (_animate > 90) {
            _animate = 0;
        } else {
            _animate++;
        }
        _timeToExplode--;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1, sprite2, sprite3, _animate, 30).getFxImage();
    }

    @Override
    public void update() {
        animate();
        if (_timeToExplode < 0) {
            //GameSound.playMusic(GameSound.BONG_BANG);
            bombExplode();
            return;
        }
        loadAnimated(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2);
    }

    protected void bombExplode() {
        BombermanGame.setFlame(new Flame(tile, damage));
        BombermanGame.removeBomb();
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        Bomb.damage = damage;
    }
}
