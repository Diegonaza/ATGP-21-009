/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Objects.SuperObject;
import character.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tiles.TileManager;

/**
 *
 * @author valter
 */
public class GamePanel extends JPanel implements Runnable{
   //Screen Settings
    final int orinalTileSize = 16; // 16x16 tile, basic pixel-art size.
    final int scale = 3; // The scale for pixel art will redeem it to 48 pixels.
    public int tileSize = orinalTileSize * scale; // Final tile size
   // public final int  maxScreenCol = 20;delet
    //public final int  maxScreenRow = 15;
    //  4/3 - proportion from width and height.
    
   // public final int  screenWidth = tileSize * maxScreenCol;delet
   // public final int  screenHigth = tileSize * maxScreenRow;
    //960 x 720 resolution
    
    
    //public final long jumpingTime = 200;  - To be implemented in the future
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    
    //FPS
    int FPS = 60;
    //Setting Frames per second at 60
    
    //World Settings
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 15;
    public final int worldWidth = tileSize * maxWorldCol; 
    public final int  worldHight = tileSize * maxWorldRow; 
    public final int screenHight = tileSize * 15;
    public final int screenWidth = tileSize * 20;
    
    public CollisionDetection colDet =  new CollisionDetection(this);;
    public SetAssets setA = new SetAssets(this);
    public SuperObject obj[] = new SuperObject[10]; 
    
    
    
    
    
    
    public GamePanel(){
    this.setFocusable(true);
    this.setDoubleBuffered(true); 
    //Eases on rendenzing processing
    
    this.setPreferredSize(new Dimension(screenWidth,screenHight)); // set dimentions for the screen
    this.addKeyListener(keyHandler); // Adds the keyboard movement controllet
    
    colDet =  new CollisionDetection(this);
    }
    
    public void GameSetUp(){
       setA.setObjects();
        
    }
    
    public void startChronos(){
    gameThread = new Thread(this);
    gameThread.start();
    }

   /* @Override
    public void run() {
        //takes the current time in nano sec
        double drawInterval = 1000000000/FPS;
        double nextDrawInterval = System.nanoTime()+ drawInterval;
        
        while (gameThread != null) {             
       
         update();
         repaint();
         
             try {
                double remainingTime = nextDrawInterval - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                 if (remainingTime < 0) {
                     
                     remainingTime = 0;
                     
                 }
       
                Thread.sleep((long) remainingTime);
                
                nextDrawInterval+= drawInterval; 
                
            } catch (InterruptedException ex) {
                
                ex.printStackTrace();
            }
            
        }
        
    }
    secondary gameloop*/
    
   @Override
    public void run() {
        //takes the current time in nano sec
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;
       
       
        while (gameThread != null) {   
            
     
           
            
        currentTime = System.nanoTime();
        
        delta += (currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime); 
        lastTime = currentTime;
        
         
            if (delta >= 1) {

               update();
               repaint();
               delta--;
               drawCounter++;

            }
            if (timer >= 1000000000) {
                
                System.out.println("Running at 60FPS"+ drawCounter); 
                drawCounter = 0;
                timer = 0;
            }
       
        }
    }
    
    public void update(){
        
        player.update();//calls method 
              
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;  
        //tiles
        tileManager.drawTiles(g2d);
        
        //objects
        for(int i = 0; i < obj.length; i++){
           if(obj[i] != null){ //check if the slot is empty (avoid nullpointer)
               obj[i].drawObjects(g2d, this);
           } 
            
            
        }
        
        //player
        player.paintComponent( g2d);//calls method player.paintComponent
        g2d.dispose();
    
    }


}
    