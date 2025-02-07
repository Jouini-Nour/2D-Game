package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    private int worldX;
    private int worldY;
    private int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum=1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    

    public Entity(int x, int y, int speed) {
        this.worldX = x;
        this.worldY = y;
        this.speed = speed;
    }

    public void move(int dx, int dy) {
        worldX += dx * speed;
        worldY += dy * speed;
    }

    public int getX() {
        return worldX;
    }

    public int getY() {
        return worldY;
    }
    public void setX(int x) {
        this.worldX = x;
    }
    public void setY(int y) {
        this.worldY = y;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
