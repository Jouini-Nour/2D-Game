package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int mapTile[][] ;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[20];
        mapTile = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("/res/maps/world1.txt");

    }
    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));
            
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/dirt.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(String mapFile){
        try{
            InputStream in = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String delimiters = " ";
            for(int row=0; row<gp.maxWorldRow; row++){
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for(int col=0; col<gp.maxWorldCol; col++){
                    mapTile[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
  
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tilenum = mapTile[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.getX() + gp.player.screenX;
            int screenY = worldY - gp.player.getY() + gp.player.screenY;

            if(worldX+ gp.tileSize> gp.player.getX() - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.getX() + gp.player.screenX &&    
            worldY + gp.tileSize > gp.player.getY() - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.getY() + gp.player.screenY){
                g2.drawImage(tiles[tilenum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); 
            }
            worldCol++;
            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;

            }
        }
    }
}
