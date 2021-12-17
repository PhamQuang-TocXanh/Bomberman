package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Wall;

import java.util.*;

public class AutoBot3 implements AutoBot {
    private final Map map = Map.getMap();
    private final Random random;
    private long changeDirectionTime;
    private final Entity[][] entities = map.tiles;
    private Queue<Point> position = new LinkedList<>();
    private Queue<String> path = new LinkedList<>();
    private final String[] dir = { "R","D","L","U" };
    private final int[] y = { 0,1,0,-1 };
    private final int[] x = { 1,0,-1,0 };
    private int preDi = -1;

    public AutoBot3() {
        random = new Random();
    }

    @Override
    public int calculateDirection(int curDirection, Enemy myEnemy) {
        Bomber bomber = Map.bomber;
        if (bomber == null) return random.nextInt(4);
        try {
            if (myEnemy.can_move) {
                preDi = curDirection;
                changeDirectionTime = System.currentTimeMillis();
            }
            else if (bomber.timeAfter == 50) {
                //sau khi bomber song lai neu enemy ko di chuyen dc
                if (System.currentTimeMillis() - changeDirectionTime >= 500) {
                    changeDirectionTime = System.currentTimeMillis();
                    return random.nextInt(4);
                }
                return preDi;
            }
            position.clear();
            path.clear();
            boolean[][] check = new boolean[entities.length][entities[0].length];
            for (int i = 0; i < check.length; i++) {
                for (int j = 0; j < check[i].length; j++) {//true: da di/ ko di duoc; false: chua di/ di duoc
                    if (entities[i][j] instanceof Wall) {
                        check[i][j] = true;
                    } else if (entities[i][j] instanceof Bomb) {
                        check[i][j] = !myEnemy.isBombPass();
                    } else if (entities[i][j] instanceof Brick) {
                        check[i][j] = !myEnemy.isWallPass();
                    } else {
                        check[i][j] = false;
                    }
                }
            }

            String myPath = "hihi";
            position.add(new Point(myEnemy.getXTile(), myEnemy.getYTile()));
            path.add("S");
            check[myEnemy.getYTile()][myEnemy.getXTile()] = true;
            while (!position.isEmpty()) {
                Point p = position.poll(); String direction = path.poll();
                int i = p.xTile, j = p.yTile;
                if (i == bomber.getXTile() && j == bomber.getYTile()) {
                    assert direction != null;
                    if (direction.length() > 1) myPath = direction;
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    if (validMove(i + x[k], j + y[k]) && !check[j + y[k]][i + x[k]]) {
                        position.add(new Point(i + x[k], j + y[k])); path.add(direction + dir[k]);
                        check[j+y[k]][i+x[k]] = true;
                    }
                }
            }

            switch (Objects.requireNonNull(myPath).charAt(1)) {
                case 'R':
                    return 1;
                case 'L':
                    return 3;
                case 'U':
                    return 0;
                case 'D':
                    return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if (!myEnemy.can_move) return random.nextInt(4);
        return curDirection;//neu khong co duong di
    }

    private boolean validMove(int x, int y) {
        return x>=0 && y>=0 && x < entities[0].length && y < entities.length;
    }

    private static class Point {
        int xTile;
        int yTile;

        public Point(int xTile, int yTile) {
            this.xTile = xTile;
            this.yTile = yTile;
        }
    }
}
