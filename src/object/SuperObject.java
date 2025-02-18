package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX, worldY ;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solideAreaDefaultX=0;
    public int solideAreaDefaultY=0;


    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.getX() + gp.player.screenX;
            int screenY = worldY - gp.player.getY() + gp.player.screenY;

            if(worldX+ gp.tileSize> gp.player.getX() - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.getX() + gp.player.screenX &&    
            worldY + gp.tileSize > gp.player.getY() - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.getY() + gp.player.screenY){
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); 
            }

    }
}
