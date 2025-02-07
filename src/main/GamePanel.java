package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
    // Sreen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int fps = 60;

    TileManager tm = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);

    //World Settings
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public CollisionChecker cc = new CollisionChecker(this);


    //Player Position
    

    public GamePanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double dt = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawcount= 0;

        while(gameThread!=null){
            currentTime = System.nanoTime();
            dt += (currentTime - lastTime) / drawInterval;
            timer+= currentTime - lastTime;
            lastTime = currentTime;

            if(dt >= 1){
                update();
                repaint();
                dt--;
                drawcount++;
            }
            if (timer >= 1000000000){
                
                drawcount = 0;
                timer = 0;
            }

            
        }
        
    }

    public void update(){
        player.update();

    }
     public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tm.draw(g2);
        player.draw(g2);

        g2.dispose();
        
    }
}
