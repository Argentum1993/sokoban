package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private String levels;

    public LevelLoader(String levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        int levelToLoad = level < 61 ? level : level % 60;
        Header header;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(levels);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((header = readHeader(bufferedReader)).level != levelToLoad){
                bufferedReader.skip(header.sizeX * header.sizeY + header.sizeY + 1);
            }
            return readGameObjects(bufferedReader, header);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Header{
        int level;
        int sizeX;
        int sizeY;
    }

    private Header readHeader(BufferedReader reader) throws IOException {
        Header header = new Header();
        String line;
        for (int i = 1; i <= 8; i++) {
            line = reader.readLine();
            if (i == 2)
                header.level = Integer.parseInt(line.split(" ")[1]);
            else if (i == 4)
                header.sizeX = Integer.parseInt(line.split(" ")[2]);
            else if (i == 5)
                header.sizeY = Integer.parseInt(line.split(" ")[2]);
        }
        return header;
    }

    private GameObjects readGameObjects(BufferedReader reader, Header header) throws IOException {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        String line;

        int y0 = Model.FIELD_CELL_SIZE / 2;
        int x0 = Model.FIELD_CELL_SIZE / 2;

        int currentY = y0;
        int currentX;

        for (int i = 0; i < header.sizeY; i++) {
            line = reader.readLine();
            currentX = x0;
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)){
                    case 'X':
                        walls.add(new Wall(currentX, currentY));
                        break;
                    case '*':
                        boxes.add(new Box(currentX, currentY));
                        break;
                    case '.':
                        homes.add(new Home(currentX, currentY));
                        break;
                    case '&':
                        boxes.add(new Box(currentX, currentY));
                        homes.add(new Home(currentX, currentY));
                        break;
                    case '@':
                        player = new Player(currentX, currentY);
                }
                currentX += Model.FIELD_CELL_SIZE;
            }
            currentY += Model.FIELD_CELL_SIZE;
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
