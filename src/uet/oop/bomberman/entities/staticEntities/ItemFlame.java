package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class ItemFlame extends Item{

    public ItemFlame(Coordinates tile) {
        super(tile);
        this.img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void getItem() {
        Bomb.setFlameRadius(Bomb.getFlameRadius() + 1);
        BombermanGame.obtainItem(this);
    }
}
