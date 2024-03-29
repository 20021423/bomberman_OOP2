package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.enemies.Balloon;
import uet.oop.bomberman.entities.enemies.Doll;
import uet.oop.bomberman.entities.enemies.Kondoria;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.entities.staticEntities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private static int level;
    private static int rows;
    private static int cols;
    private static int target;

    public static void loadMap(int level) throws IOException {
        FileReader fr = new FileReader(new File("res/levels/Level" + level + ".txt"));
        BufferedReader br = new BufferedReader(fr);
        String[] line = br.readLine().split("\\s+");
        Map.level = Integer.parseInt(line[0]);
        rows = Integer.parseInt(line[1]);
        cols = Integer.parseInt(line[2]);
        for (int i = 0; i < rows; i++) {
            String map = br.readLine();
            for (int j = 0; j < cols; j++) {
                BombermanGame.setGrass(new Grass(new Coordinates(j, i)));
                switch (map.charAt(j)) {
                    case '#':
                        BombermanGame.setWall(new Wall(new Coordinates(j, i)));
                        break;
                    case '*':
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
                    case 'x':
                        BombermanGame.setPortal(new Portal(new Coordinates(j, i)));
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
                    case 'p':
                        BombermanGame.setBomber(new Bomber(new Coordinates(j, i), BombermanGame.input));
                        break;
                    case 'f':
                        BombermanGame.setItem(new ItemFlame(new Coordinates(j, i)));
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
                    case 'w':
                        BombermanGame.setItem(new ItemFlame(new Coordinates(j, i)));
                        break;
                    case 's':
                        BombermanGame.setItem(new ItemSpeed(new Coordinates(j, i)));
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
                    case 'b':
                        BombermanGame.setItem(new ItemBomb(new Coordinates(j, i)));
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
                    case '1':
                        BombermanGame.setEnemy(new Balloon(new Coordinates(j, i)));
                        target++;
                        break;
                    case '2':
                        BombermanGame.setEnemy(new Oneal(new Coordinates(j, i)));
                        target++;
                        break;
                    case '3':
                        BombermanGame.setEnemy(new Doll(new Coordinates(j, i)));
                        target++;
                        break;
                    case '5':
                        BombermanGame.setEnemy(new Kondoria(new Coordinates(j, i)));
                        target++;
                        break;
                    default:
                        break;
                }
            }
        }
        br.close();
        fr.close();
    }


    public static int getLevel() {
        return level;
    }

    public static int getTarget() {
        return target;
    }

    public static void setTarget(int target) {
        Map.target = target;
    }
}
