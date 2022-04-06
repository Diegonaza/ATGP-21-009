/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import model.GamePanel;
import model.KeyHandler;
import platform.Platform;
import platform.Platform1;
import platform.SuperPlatform;


/**
 *
 * @author valter
 */
public class Player extends Characters{
    
    //instanciates Game Panel Class
    KeyHandler keyHandler;
    private Image image;
    public int screenX;
    public int screenY;
    int hasItem = 0;
    public int xspeed;
    public int yspeed;
    
    //chaged
    public int x = 200;
    public int y = 150;
    public int nx2;
    public int nx;
    //chaged
    public Player(GamePanel gp, KeyHandler keyHundle){
     super(gp);  //chaged
    this.keyHandler = keyHundle;
     nx2 = 900;
     nx = 0;
    //
    //chaged
    //Screen Setting
     //screenX = 300;
     //screenY = 100;
    
    //collision
    solidArea = new Rectangle(x,y,32,32);
 
    solidAreaDefaultX= solidArea.x;
    solidAreaDefaultY= solidArea.y;
   
    this.setDefaultvalues();
    this.getPlayerImg();
    }
    
    public void getPlayerImg() {
       
         idle1 = setup("/playerSprite/berg_down_1.png");
        idle2 = setup("/playerSprite/berg_down_2.png");
        left1 = setup("/playerSprite/berg_left_1.png");
        left2 = setup("/playerSprite/berg_left_2.png");
        right1 = setup("/playerSprite/berg_right_1.png");
        right2 = setup("/playerSprite/berg_right_2.png");
        attack_right = setup("/playerSprite/attack_right.png");
        attack_left = setup("/playerSprite/attack_left.png");
        
        //Set image path to an image variable.
        }
    
    public void setDefaultvalues(){
        //player first position
        
        
        //character moving position
        //worldX = gp.tileSize * 1;
        //worldY = gp.tileSize * 9;
        
        //position to be fixed in the future for the player sprite
        speed = 5;
        direction = "idle";
        
    }
    
    public void update(){
      
      if(keyHandler.leftPressed && keyHandler.rightPressed || !keyHandler.leftPressed && !keyHandler.rightPressed) xspeed *= 0.4;
      else if(keyHandler.leftPressed && !keyHandler.rightPressed){
          direction ="left";
        
          }
      else if(keyHandler.rightPressed && !keyHandler.leftPressed){
          direction = "right"  ; 
         
          
         }
      
      if(xspeed > 0 && xspeed<0.75)xspeed = 0;
      if(xspeed<0 && xspeed> -0.75)xspeed = 0;
      if(xspeed > 7)xspeed= 7;
      if(xspeed<-7)xspeed = -7;
        
       if(keyHandler.jumpingPressed){
       
         solidArea.y++;
          for(int i = 0; i<gp.platforms.size(); i++){
              if(solidArea.intersects(gp.platforms.get(i).hitBox))yspeed = -20;
          }
          
         solidArea.y--;     
       }
      
        yspeed += 1;
        
       
       
        //vertical collision
         solidArea.y += yspeed;
        for(Platform p: gp.platforms){
            if(solidArea.intersects(p.hitBox)){
                solidArea.y -= yspeed;
                while(!p.hitBox.intersects(solidArea)) solidArea.y += Math.signum(yspeed);
                solidArea.y -= Math.signum(yspeed);
                yspeed = 0;
                y = solidArea.y;
            }
        }
        
          
        //horizontal collision
         solidArea.x += yspeed;
        for(Platform p: gp.platforms){
            if(solidArea.intersects(p.hitBox)){
                solidArea.x -= xspeed;
                while(!p.hitBox.intersects(solidArea)) solidArea.x += Math.signum(yspeed);
                solidArea.x -= Math.signum(xspeed);
                gp.worldX =+ x - solidArea.x;//subtract player area from platform
                xspeed = 0;//stop player
                solidArea.x = x;
            }
        }
        
        gp.worldX -= xspeed; 
     
        y += yspeed;
        nx2 = nx2 + xspeed;
        nx = nx + xspeed;
        
        solidArea.x = x;
        solidArea.y = y;
        
        if (y > 800) {
            gp.reset();
        }
        
        //colision checker
        isColisionOn = false;
       //gp.colDet.checkColision(this);
        
        //check object collision
        //int objectIndex = gp.colDet.objectChecker(this,true);
      //  pickObject(objectIndex);
        
          //check platform collision
//        int patformIndex = gp.colDet.PlatformChecker(this,true);
        //collideAtPlatform(patformIndex);
        //if colision is false player can move
        if (isColisionOn == false) {
            
            switch(direction){
            /* case "up": worldY -= speed;                  
             break;
             case "down": worldY += speed;
             break;*/
             case "left": xspeed --;
             break;
             case "right": xspeed ++;
             break;
                     
         }
            
        }
        spriteCounter++;//increases sprite counter for sprite Number Change
        if(spriteCounter > 15)    {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;        
        }    
    
    }
    
    public void pickObject(int i){
        System.out.println(i);
        if(i != 999){  //999 because it is a number that we don't use in the arrays
         
           // System.out.println("colide");
         String objectName =   gp.obj[i].name;
         
         switch(objectName){
             case "gun":
                 hasItem++;
                 gp.obj[i] = null;
                 System.out.println("item"+hasItem);
                 break;
             case "mask":
                 gp.obj[i] = null;
                 hasItem++;        
                 break;
                 
             
             
         }
        }
        
    }
    
    //check colision at platform
    public void collideAtPlatform(int i){
            System.out.println(i);
     /*  if(i != 999){  //999 because it is a number that we don't use in the arrays
         
          System.out.println(i);
            System.out.println("it colide");
         String objectName =   gp.platform[i].name;
         
         switch(objectName){
             case "platform":
                 if (direction == "idle") {
                      gp.platform[i] = null;
                      gp.playSE(1);
                       worldY = -4;
                       
                       direction="idle";
                      
                 }
                /* if (direction == "idle") {
                      solidArea.y -= yspeed;
                      for (SuperPlatform object : gp.platform) {
                          while(!gp.platform[i].solidArea.intersects(solidArea)) solidArea.y += Math.signum(yspeed);
                                solidArea.y -= Math.signum(xspeed);
                                yspeed = 0 ;
                             
                       }
                      
                      if (direction == "left" ) {
                          solidArea.x -= xspeed;
                      for (SuperPlatform object : gp.platform) {
                          while(!gp.platform[i].solidArea.intersects(solidArea)) solidArea.x += Math.signum(xspeed);
                                solidArea.x -= Math.signum(xspeed);
                                xspeed = 0 ;
                       }
                      }
                 }*/
                 
                 
             
             
         }
        
        
    
     public void paintComponent(Graphics2D g2d,GamePanel panel){
           
        
         BufferedImage image = null;
       switch(direction){
             case "idle":
                 if(spriteNumber == 1){
                     image = idle1;}
                 if(spriteNumber == 2){
                     image = idle2;}                 
                 break;
                 
             case "left":
                 if(spriteNumber == 1){
                     image = left1;}
                 if(spriteNumber == 2){
                     image = left2;}
                 break;
                 
             case "right":
                 if(spriteNumber == 1){
                     image = right1;}
                 if(spriteNumber == 2){
                     image = right2;}
                 break;
             case "attack_left":
                 if(spriteNumber == 1){
                     image = attack_left;}
                 if(spriteNumber == 2){
                     image = left1;}
                 
                 break;
             case "attack_right":
                 if(spriteNumber == 1){
                     image = attack_right;}
                 if(spriteNumber == 2){
                     image = right1;}
                 break;    
       
       }
                   
         g2d.drawImage(image,x,y,gp.tileSize,gp.tileSize,panel);//changed

    
    } // Draw images into the screenX using the updated coordenate information from the player in the game panel, changes the image accordingly to spriteNumber value.
      
}
