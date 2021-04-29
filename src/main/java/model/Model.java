package model;

import controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;

    String levelsResources = "levels.txt";

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(levelsResources);

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel += 1;
        restart();
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction))
            return;
        move(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()){
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (!checkWallCollision(box, direction) && !checkBoxCollision(box, direction)) {
                    move(box, direction);
                    return false;
                }
                else
                    return true;
            }
        }
        return false;
    }

    public void checkCompletion(){
        boolean findBoxForHome;
        for (Home home : gameObjects.getHomes()){
            findBoxForHome = false;
            for (Box box : gameObjects.getBoxes()){
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    findBoxForHome = true;
            }
            if (!findBoxForHome){
                return;
            }
        }
        eventListener.levelCompleted(currentLevel);
    }

    private boolean checkBoxCollision(CollisionObject gameObject, Direction direction) {
        for (Box box : gameObjects.getBoxes()){
            if (gameObject.isCollision(box, direction))
                return true;
        }
        return false;
    }

    private void move(Movable movable, Direction direction){
        switch (direction){
            case LEFT:
                movable.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                movable.move(0,  -FIELD_CELL_SIZE);
                break;
            case DOWN:
                movable.move(0, FIELD_CELL_SIZE);
        }
    }
}
