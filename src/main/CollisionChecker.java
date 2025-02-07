package main;

import Entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.getX()+entity.solidArea.x;
        int entityRightWorldX = entity.getX()+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY = entity.getY()+entity.solidArea.y;
        int entityBottomWorldY = entity.getY()+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.tileSize;
                tileNum1 = gp.tm.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.tm.mapTile[entityTopRow][entityRightCol];
                if(gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.tileSize;
                tileNum1 = gp.tm.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tm.mapTile[entityBottomRow][entityRightCol];
                if(gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.tileSize;
                tileNum1 = gp.tm.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.tm.mapTile[entityBottomRow][entityLeftCol];
                if(gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                
                break;
            case "right":
            entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.tileSize;
            tileNum1 = gp.tm.mapTile[entityTopRow][entityRightCol];
            tileNum2 = gp.tm.mapTile[entityBottomRow][entityRightCol];
            if(gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision){
                entity.collisionOn = true;
            }
                
                break;
            default:
                break;
        }
    }

}
