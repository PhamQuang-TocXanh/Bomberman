package uet.oop.bomberman.level;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class LevelLoader {
    private int WIDTH = 20;
    private int HEIGHT = 15;
    private int LEVEL = 1;
    private char[][] map;

    public void loadLevel(int level) {
        try {
            ClassLoader c = ClassLoader.getSystemClassLoader();
            File file = new File(Objects.requireNonNull(c.getResource("levels/Level" + level + ".txt")).getFile());
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            Scanner sc = new Scanner(bf);
            LEVEL = sc.nextInt();
            HEIGHT = sc.nextInt();
            WIDTH = sc.nextInt();
            sc.nextLine();
            map = new char[HEIGHT][WIDTH];
            for (int i = 0; i < HEIGHT; i++){
                String s = sc.nextLine();
                for (int j = 0; j < WIDTH; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not found file level " + level);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public char[][] getMap() {
        return map;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getLEVEL() {
        return LEVEL;
    }

    public static void main(String[] args) {
        LevelLoader l = new LevelLoader();
        l.loadLevel(2);
        for (int i = 0; i < l.getHEIGHT(); i++) {
            for(int j=0;j<l.getWIDTH();j++){
                System.out.print(l.getMap()[i][j]);
            }
            System.out.println("");
        }
    }
}
