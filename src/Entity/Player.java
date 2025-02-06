package Entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public Player( GamePanel gamePanel, KeyHandler keyH) {
        super(100, 100, 4);
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        this.getPlayerImage();
        direction = "down";
        setDefaultValues(gamePanel);
        screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2; 
        screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;
    }
    public void setDefaultValues(GamePanel gp){ 
        this.setX(gp.tileSize*23);
        this.setY(gp.tileSize*21);
        this.setSpeed(4);
         
    }
    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
                this.setY(this.getY() - this.getSpeed());
            }
            else if(keyH.downPressed){
                direction = "down";
                this.setY(this.getY() + this.getSpeed());
            }
            else if(keyH.leftPressed){
                direction = "left";
                this.setX(this.getX() - this.getSpeed());
            }
            else if(keyH.rightPressed){
                direction = "right";
                this.setX(this.getX() + this.getSpeed());
            }
            spriteCount++;
            if(spriteCount > 12){
                if(spriteNum==1){
                    spriteNum = 2;
                }else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCount = 0; 
            } 
        }

        

    }
    public void draw(Graphics2D g2){
        BufferedImage img = null;
        switch(direction){
            case "up":
                if(spriteNum==1){
                    img = up1;
                }
                if(spriteNum==2){
                    img = up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    img = down1;
                    }
                if(spriteNum==2){
                    img = down2;
                    }
                break;
            case "left":
                if(spriteNum==1){
                    img = left1;
                    }
                if(spriteNum==2){
                    img = left2;
                    }
                break;
            case "right":
                if(spriteNum==1){
                    img = right1;
                }
                if(spriteNum==2){
                    img = right2;
                }
                break;
        }
        g2.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/back2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

