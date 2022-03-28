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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.GamePanel;
import model.KeyHandler;
import model.UtilityTool;
import tiles.Tiles;

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
    
    //chaged
    public Player(GamePanel gp, KeyHandler keyHandler){
    super(gp);   //chaged
    this.keyHandler = keyHandler;
    
    //Screen Setting
    screenX = 300;
    screenY = 430;
    
    //collision
    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 16;
    solidAreaDefaultX= solidArea.x;
    solidAreaDefaultY= solidArea.y;
    solidArea.height = 32;
    solidArea.width = 32;
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
        worldX = gp.tileSize * 7;
        worldY = gp.tileSize * 9;
        
        //position to be fixed in the future for the player sprite
        speed = 4;
        direction = "idle";
        
    }
    
    @Override
    public void update(){
        
        if(keyHandler.downPressed == true || keyHandler.jumpingPressed == true || keyHandler.leftPressed == true ||   keyHandler.rightPressed == true || keyHandler.upPressed == true){
        //Starting the loop for the sprites in case the keyboard key is pressed. 
         
             
        /* if (keyHandler.upPressed == true) {
            direction = "up";
           
        }else if (keyHandler.downPressed == true) {
            direction = "down";
           
            
        }else*/ 
            if (keyHandler.leftPressed == true) {
            direction = "left";
            counter += 0.05;
            
                if (keyHandler.jumpingPressed == true){
                    gp.playSE(1);//sound player jumping
                worldY = worldY +(int)((Math.sin(counter)+ Math.cos(counter))*5); 
                if (counter >= 7){
                counter  = 4;    }
                if (worldY <= 430 && keyHandler.jumpingPressed == false){//define top
                worldY +=10;                                            }
                                                      }
                
            if (keyHandler.attackPressed == true){
                    direction = "attack_left";   }    
                                                }
            
            
            else if (keyHandler.rightPressed == true) {
            direction = "right";         
                 if (keyHandler.jumpingPressed == true){
                 gp.playSE(1);//sound player jumping
                counter += 0.05;
                worldY = worldY +(int)((Math.sin(counter)+ Math.cos(counter))*5); 
                 if (counter >= 7){
                counter  = 4;    }                    }
                 
                 
                 if(keyHandler.attackPressed == true){
                        direction = "attack_right";
                    }
                                                      }
            
            
            
            else if (keyHandler.jumpingPressed == true) {
           
            //makes the player jump
            counter += 0.05;
            worldY = worldY +(int)((Math.sin(counter)+ Math.cos(counter))*5);
             gp.playSE(1);//sound player jumping
            if (counter >= 7){
                counter  = 4;
            }
         
           //makes the player go back to the initial position after reased button
        }else if(keyHandler.jumpingPressed == false){
                gp.stopSound();
                counter = 4;
        }
        }else{
            direction = "idle";//Idle animation outside the "pressing key loop
        }
         
       
        if (worldY <= 430 && keyHandler.jumpingPressed == false){//define top
            worldY +=10;
        
        } 
        if (worldY >= 430){//define ground
            worldY = 430;
        }
        
        //colision checker
        isColisionOn = false;
        gp.colDet.checkColision(this);
        
        //check object collision
        int objectIndex = gp.colDet.objectChecker(this,true);
        pickObject(objectIndex);
        
        //if colision is false player can move
        if (isColisionOn == false) {
            
            switch(direction){
             /*case "up": WorldY -= speed;                  
             break;
             case "down": WorldY += speed;
             break;*/
             case "left": worldX -= speed;
             break;
             case "right": worldX += speed;
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
        if(i != 999){  //999 because it is a number that we don't use in the arrays
         
         
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
     public void paintComponent(Graphics2D g2d){
           
        
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
                   
         g2d.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);//changed

    
    } // Draw images into the screenX using the updated coordenate information from the player in the game panel, changes the image accordingly to spriteNumber value.
      
}
