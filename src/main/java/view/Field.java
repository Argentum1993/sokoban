package view;

import controller.EventListener;
import model.Direction;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view){
        this.view = view;
        view.addKeyListener(new KeyHandler());
        view.setFocusable(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        GameObjects gameObjects = view.getGameObjects();
        gameObjects.getAll().forEach(go -> go.draw(g));
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
            }
        }
    }
}
