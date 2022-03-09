/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    final int orinalTileSize = 16;
    final int scale = 3;
    public int tileSize = orinalTileSize * scale;
    public final int  maxScreenCol = 16;
    public final int  maxScreenRow = 12;
    public final int  screenWidth = tileSize * maxScreenCol;
    public final int  screenHigth = tileSize * maxScreenRow;
    public final long jumpingTime = 200;
    TileManager tileManager = new TileManager(this);
    KeyHundle keyHundle = new KeyHundle();
    Thread gameThread;
    Player player = new Player(this, keyHundle);
    
    //FPS
    int FPS = 60;
    
    public GamePanel(){
    this.setFocusable(true);
    this.setDoubleBuffered(true);
    
    this.setPreferredSize(new Dimension(screenWidth,screenHigth));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHundle);
    this.setFocusable(true);
    
    }
    
    public void startGameThread(){
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
        
    }*/
    
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
                
                System.out.println("FPS"+drawCounter); 
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
        tileManager.drawTiles(g2d);
        player.paintComponent( g2d);//calls method player.paintComponent
        g2d.dispose();
    
    }


}
    