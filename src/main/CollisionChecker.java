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

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i <gp.objs.length; i++){
            if(gp.objs[i]!=null){
                //get Entity's solid area position
                entity.solidArea.x = entity.solidArea.x+entity.getX();
                entity.solidArea.y = entity.solidArea.y+entity.getY();

                //get the object's solid area position
                gp.objs[i].solidArea.x = gp.objs[i].solidArea.x+gp.objs[i].worldX;
                gp.objs[i].solidArea.y = gp.objs[i].solidArea.y+gp.objs[i].worldY;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objs[i].solidArea.x = gp.objs[i].solideAreaDefaultX;
                gp.objs[i].solidArea.y = gp.objs[i].solideAreaDefaultY;
            }
        }
        return index;



        
    }

}
