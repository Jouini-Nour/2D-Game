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
        mapTile = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap("/res/maps/Map1.txt");

    }
    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));
            
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(String mapFile){
        try{
            InputStream in = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String delimiters = " ";
            for(int row=0; row<gp.maxScreenRow; row++){
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for(int col=0; col<gp.maxScreenCol; col++){
                    mapTile[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col<gp.maxScreenCol && row<gp.maxScreenRow){
            int tilenum = mapTile[row][col];
            g2.drawImage(tiles[tilenum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+=gp.tileSize;
            if(col==gp.maxScreenCol){
                col=0;
                row++;
                x=0;
                y+=gp.tileSize;
            }
        }
    }
}
