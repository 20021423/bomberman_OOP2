package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates() {
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinates convertPixelToTile() {
        return new Coordinates((x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE,
                (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
    }

    public Coordinates convertTileToPixel() {
        return new Coordinates(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
}
