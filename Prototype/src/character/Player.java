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

/**
 *
 * @author valter
 */
public class Player extends Character{
    
    //instanciates Game Panel Class
    KeyHandler keyHandler;
    private Image image;
    public int screenX;
    public int screenY;
    //chaged
    public Player(GamePanel gp, KeyHandler keyHundle){
    super(gp);   //chaged
    this.keyHandler = keyHundle;
    
    //Screen Setting
    screenX = 98;
    screenY = 430;
    
    //collision
    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 16;
    solidArea.height = 32;
    solidArea.width = 32;
    this.setDefaultvalues();
    this.getPlayerImg();
    }
    
    public void getPlayerImg() {
       
        try{
        idle1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_down_1.png"));
        idle2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_down_2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_left_1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_left_2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_right_1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/berg_right_2.png"));
        //Set image path to an image variable.
        }catch(IOException e){
            e.printStackTrace();
        }
        }
    
    public void setDefaultvalues(){
    //player first position 
 
    
    //character moving position
    WorldX = gp.tileSize * 3;
    WorldY = gp.tileSize * 9;
    
    //position to be fixed in the future for the player sprite
    speed = 4;
    direction = "idle";
    
    }
    
    public void update(){
        
        if(keyHandler.downPressed == true || keyHandler.jumpingPressed == true || keyHandler.leftPressed == true ||           keyHandler.rightPressed == true || keyHandler.upPressed == true){
        //Starting the loop for the sprites in case the keyboard key is pressed. 
    
             
        /* if (keyHandler.upPressed == true) {
            direction = "up";
           
        }else if (keyHandler.downPressed == true) {
            direction = "down";
           
            
        }else*/ if (keyHandler.leftPressed == true) {
            direction = "left";
         
             counter += 0.05;
            if (keyHandler.jumpingPressed == true){
               
            WorldY = WorldY +(int)((Math.sin(counter)+ Math.cos(counter))*5); 
            if (counter >= 7){
                counter  = 4;
                 
            }if (WorldY <= 430 && keyHandler.jumpingPressed == false){//define top
           WorldY +=10;
        
              }
            }
        }else if (keyHandler.rightPressed == true) {
            direction = "right";         
          
            if (keyHandler.jumpingPressed == true){
            
            counter += 0.05;
            WorldY = WorldY +(int)((Math.sin(counter)+ Math.cos(counter))*5); 
            
            if (counter >= 7){
                
                counter  = 4;
               }
            }
            
        }else if (keyHandler.jumpingPressed == true) {
           
            //makes the player jump
            counter += 0.05;
            WorldY = WorldY +(int)((Math.sin(counter)+ Math.cos(counter))*5); 
            if (counter >= 7){
                counter  = 4;
            }
         
           //makes the player go back to the initial position after reased button
        }else if(keyHandler.jumpingPressed == false){
                counter = 4;
        }
        }else{
            direction = "idle";//Idle animation outside the "pressing key loop
        }
         
       
        if (WorldY <= 430 && keyHandler.jumpingPressed == false){//define top
            WorldY +=10;
        
        } 
        if (WorldY >= 430){//define ground
            WorldY = 430;
        }
        
        //colision checker
        isColisionOn = false;
        gp.colDet.checkColision(this);
        
        //if colision is false player can move
        if (isColisionOn == false) {
            
            switch(direction){
             /*case "up": WorldY -= speed;                  
             break;
             case "down": WorldY += speed;
             break;*/
             case "left": WorldX -= speed;
             break;
             case "right": WorldX += speed;
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
                 break;}
                   
         g2d.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);//changed

    
    } // Draw images into the screenX using the updated coordenate information from the player in the game panel, changes the image accordingly to spriteNumber value.
      
}
