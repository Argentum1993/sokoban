package model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(this.getX() - this.getWidth() / 2, this.getY() - getHeight() / 2, this.getWidth(), this.getHeight());
        int x1 = this.getX()  + this.getWidth() / 2;
        int y1 = this.getY() + this.getHeight() / 2;
        graphics.drawLine(x1, y1, x1 - this.getWidth(), y1 - this.getHeight());
        graphics.drawLine(x1 - this.getWidth(), y1, x1, y1 - this.getHeight());
    }
}
