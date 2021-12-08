package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public interface Animated {

    void animate();

    void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3);

    void update();
}
