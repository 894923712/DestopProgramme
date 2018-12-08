package bean;

import java.awt.*;

/**
 * @author LuoMing luom@3vjia.com
 * 棋盘客户端类
 * @since 2018-12-06 14:16
 */
public class Chess implements Comparable<Chess>{
    private int x; // x, y 坐标
    private int y;
    private int color; // 棋子颜色 (1：黑色 -1：白色)
    private double score; // 得分

    public Chess() {
    }
    public Chess(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.color = isBlack ? 1 : -1;
    }

    public Chess(int x, int y, double score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }

    public Chess(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Color getColors() {
        return color == 1 ? Color.BLACK : Color.WHITE;
    }

    public int compareTo(Chess o) {
        return (int)(o.getScore()-this.score);
    }
}
