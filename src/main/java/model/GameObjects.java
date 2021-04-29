package model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<GameObject> getAll(){
        Set<GameObject> setAll = new HashSet<>();
        setAll.addAll(walls);
        setAll.addAll(boxes);
        setAll.addAll(homes);
        setAll.add(player);
        return setAll;
    }
}
