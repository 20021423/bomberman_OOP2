package uet.oop.bomberman.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    public boolean up, down, left, right, space, pause;

    public Keyboard() {
        up = false;
        down = false;
        right = false;
        left = false;
        space = false;
        pause = false;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
            space = true;
        }
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            up = true;
        }
        if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            down = true;
        }
        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            left = true;
        }
        if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            right = true;
        }
    }

    public void keyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
            space = false;
        }
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            up = false;
        }
        if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            down = false;
        }
        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            left = false;
        }
        if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            right = false;
        }
    }
}