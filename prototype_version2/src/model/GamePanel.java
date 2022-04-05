/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Objects.SuperObject;
import Objects.objMask;

import character.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tiles.TileManager;
import character.Characters;
import java.util.ArrayList;
import java.util.Random;
import platform.Platform;
import platform.SuperPlatform;
import platform.Platform1;
import tiles.World;

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
    Sound sound = new Sound();
    
    public ArrayList<Platform> platforms = new ArrayList<>();
    public Player player = new Player(this, keyHandler);
    Thread gameThread;
    //FPS
    int FPS = 60;
    //Setting Frames per second at 60
    
    //World Settings
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 15;
    public final int worldWidth = tileSize * maxWorldCol; 
    public final int worldHight = tileSize * maxWorldRow; 
    public final int screenHight = tileSize * 15;
    public final int screenWidth = tileSize * 20;
    public  int worldX;
    public CollisionDetection colDet =  new CollisionDetection(this);;
    public SetAssets setA = new SetAssets(this);
    public SuperObject obj[] = new SuperObject[10]; 
    public SuperObject objt = new objMask(); 
    public Characters npc[] = new Characters[10]; 
    public int offset;
    
   World world = new World();
    
    
    
    
    
    
    public GamePanel(){
        
    this.setFocusable(true);
    this.setDoubleBuffered(true); 
    //Eases on rendenzing processing
    
    
    //make Platforms
     reset();
    // setPlatform();
    
    this.setPreferredSize(new Dimension(screenWidth,screenHight)); // set dimentions for the screen
    this.addKeyListener(keyHandler); // Adds the keyboard movement controllet
    
    colDet =  new CollisionDetection(this);
    }
    
    public void reset(){
    player.x = 200;
    player.y = 150;
    worldX = 150;
    player.xspeed = 0;
    player.yspeed = 0;
    platforms.clear();
    offset= -150;
  
    makePlatforms(offset);
    
    
  
    
   
    } 
      public void makePlatforms(int offset ){
        int size = 52;
        Random random = new Random();
        int index = random.nextInt(3);
        
          if (index==0) {
              for(int i = 0; i<10; i++){
                  platforms.add(new Platform(offset + i*50,600,size,size));
            }
         
          }else if (index==1) {
                  for(int i = 0; i< 6; i++){ platforms.add(new Platform(offset + i*50,450,size,size));}
                  platforms.add(new Platform(offset + 400,600,size,size));
                  platforms.add(new Platform(offset + 450,550,size,size));
                  platforms.add(new Platform(offset + 500,500,size,size));
                  platforms.add(new Platform(offset + 550,450,size,size));
                 // platforms.add(new Platform(offset + 600,400,size,size));
                 
          }else if (index==2) {
                  for(int i = 0; i< 5; i++){ platforms.add(new Platform(offset + i*50,450,size,size));}
                  platforms.add(new Platform(offset + 400,600,size,size));
                  platforms.add(new Platform(offset + 400,550,size,size));
                  platforms.add(new Platform(offset + 400,500,size,size));
                  platforms.add(new Platform(offset + 400,450,size,size));
                  platforms.add(new Platform(offset + 350,600,size,size));
                  platforms.add(new Platform(offset + 350,550,size,size));
                  platforms.add(new Platform(offset + 350,500,size,size));
                  platforms.add(new Platform(offset + 350,450,size,size));
                  
                  platforms.add(new Platform(offset + 450,600,size,size));
                  platforms.add(new Platform(offset + 450,550,size,size));
                  platforms.add(new Platform(offset + 450,500,size,size));
                  platforms.add(new Platform(offset + 450,450,size,size));
             
          }
      }

          
        
   
    public void GameSetUp(){
      // setA.setPlatform();
       setA.setObjects();
       setA.setNPC();
      
       
       //playSound(0);//play sound
        
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
        //update player
        player.update();//calls method 
       
        //update citizen
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
                
            }
            
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;  
        
        //Debug
        long drawStart = 0;
        drawStart = System.nanoTime();
        
        
       
        // world1.paintWorld(g);
        // Draw Background
        //tiles
       // tileManager.drawTiles(g2d);
        
        //debuging
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        System.out.println(passed);
        
       
         //objects
        for(int i = 0; i < obj.length; i++){
           if(obj[i] != null){ //check if the slot is empty (avoid nullpointer)
               obj[i].drawObjects(g2d, this);
           } 
            
            
        }
        
          //Draw NPC
        for(int i = 0; i < npc.length; i++){
           if(npc[i] != null){ //check if the slot is empty (avoid nullpointer)
             npc[i].draw(g2d);
           } 
            
               
        }
        
        if (platforms.get(platforms.size()-1).x <900) {
            offset += 700;
            makePlatforms(offset);
             
        }
         
         //paint platform 
         for(Platform p: platforms)p.set(worldX);
         
         
        for (int i = 0; i < platforms.size(); i++) {

             if (platforms.get(platforms.size()-1).x <-900) {
                 
            platforms.remove(i);
        }
         
        }
        

      //backgroun moviments
        
      
            world.paintWorld(g2d,player,player.nx2);      
          
        
      
       player.paintComponent( g2d,this); 
      
        
        
         //make the world move
         for(Platform p : platforms)p.draw(g2d); 

       
        
       //calls method player.paintComponent
      
         

        
        
            
          
        
        //player
       
       
        g2d.dispose();
    
    }
    
    //sound player
    public void playSound(int i){
    
    sound.setSound(i);
    sound.play();
    sound.loop();
    }
    
      public void stopSound(){
    
   
    sound.stop();
  
    }
    
    public void playSE(int i){
    
    sound.setSound(i);
    sound.play();
   
    }



}
    