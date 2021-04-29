package model;

import java.awt.*;

public class Wall extends CollisionObject{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(150, 75, 0));

        int xLeft = this.getX() - this.getWidth() / 2;
        int yTop = this.getY() - this.getHeight() / 2;

        graphics.fillRect(xLeft, yTop, this.getWidth(), this.getHeight());
    }
}
