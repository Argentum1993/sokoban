package view;

import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
        field.paint(this.getGraphics());
    }

    public void setEventListener(EventListener eventListener){
        this.field.setEventListener(eventListener);
    }

    public void update(){
        field.repaint();
    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void completed(int level){
        update();
        String msg = String.format("Level %d passed!", level);
        String title = "Congratulations!";
        JOptionPane.showMessageDialog(this,msg, title, JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
