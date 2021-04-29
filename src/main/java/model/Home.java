package model;

import java.awt.*;

public class Home extends GameObject{
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        int xLeft = this.getX() - this.getWidth() / 2;
        int yTop = this.getY() - this.getHeight() / 2;
        graphics.drawOval(xLeft, yTop, this.getWidth(), this.getHeight());
    }
}
