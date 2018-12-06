package bean;

import java.awt.*;

/**
 * @author LuoMing luom@3vjia.com
 * 棋盘类
 * @since 2018-12-06 12:22
 */
public class Chess {
    private int x;
    private int y;
    private Color color;
    public Chess(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = "true".equals(color) ? Color.BLACK : Color.WHITE;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
