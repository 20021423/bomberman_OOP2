package uet.oop.bomberman;

import uet.oop.bomberman.entities.enemies.Balloon;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Wall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
    public static Map instance = null;
    public String LEVEL_TEMPLATE = "/levels/Level%d.txt";

    private int level;
    private int rows;
    private int cols;

    private Map() {

    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void loadMap(int level) throws IOException {
        InputStream is = this.getClass().getResourceAsStream(String.format(LEVEL_TEMPLATE, level));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String[] line = br.readLine().split("\\s+");
        this.level = Integer.parseInt(line[0]);
        rows = Integer.parseInt(line[1]);
        cols = Integer.parseInt(line[2]);
        for (int i = 0; i < rows; i++) {
            String map = br.readLine();
            //System.out.println(map);
            for (int j = 0; j < cols; j++) {
                BombermanGame.setGrass(new Grass(new Coordinates(j, i)));
                switch (map.charAt(j)) {
                    case '#':
                        BombermanGame.setWall(new Wall(new Coordinates(j, i)));
                        break;
                    case '*':
                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
                        break;
//                    case 'x':
//                        BombermanGame.setPortal(new Portal(new Coordinates(j, i)));
//                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
//                        break;
//                    case 'p':
//                        BombermanGame.setBomber(new Bomber(new Coordinates(j, i), BombermanGame.input));
//                        break;
//                    case 'f':
//                        BombermanGame.setItem(new FlamesItem(new Coordinates(j, i)));
//                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
//                        break;
//                    case 's':
//                        BombermanGame.setItem(new SpeedItem(new Coordinates(j, i)));
//                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
//                        break;
//                    case 'k':
//                        BombermanGame.setEnemy(new Boss1(new Coordinates(5, 5)));
//                        break;
//                    case 'b':
//                        BombermanGame.setItem(new BombsItem(new Coordinates(j, i)));
//                        BombermanGame.setBrick(new Brick(new Coordinates(j, i)));
//                        break;
                    case '1':
                        BombermanGame.setEnemy(new Balloon(new Coordinates(j, i)));
                        break;
                    case '2':
                        BombermanGame.setEnemy(new Oneal(new Coordinates(j, i)));
                        break;
//                    case '3':
//                        BombermanGame.setEnemy(new Ghost(new Coordinates(j,i)));
//                        break;
//                    case '4':
//                        BombermanGame.setEnemy(new Minvo(new Coordinates(j,i)));
//                        break;
//                    case '5':
//                        BombermanGame.setEnemy(new Kondoria(new Coordinates(j,i)));
//                        break;
                    default:
                        break;
                }
            }
        }
        br.close();
        is.close();
    }


    public int getLevel() {
        return level;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
