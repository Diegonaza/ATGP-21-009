/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;


import java.awt.Graphics2D;
import java.awt.Image;
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
    
    GamePanel  gamePanel;//instanciates Game Panel Class
    KeyHandler keyHandler;
    private Image image;
    
    public Player(GamePanel  gamePanel, KeyHandler keyHundle){
    this.gamePanel = gamePanel;
    this.keyHandler = keyHundle;
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
        }
        }
    
    public void setDefaultvalues(){
    
    x = 98;
    y = 430;
    //position to be fixed in the future for the player sprite
    speed = 4;
    direction = "idle";
    
    }
    
    public void update(){
        
        if(keyHandler.downPressed == true || keyHandler.jumpingPressed == true || keyHandler.leftPressed == true ||           keyHandler.rightPressed == true || keyHandler.upPressed == true){
        //Starting the loop for the sprites in case the keyboard key is pressed.            
        
        if (keyHandler.upPressed == true) {
            direction = "idle";
            y -= speed;
        }else if (keyHandler.downPressed == true) {
            direction = "idle";
            y += speed;
        }else if (keyHandler.leftPressed == true) {
            direction = "left";
            x -= speed;
        }else if (keyHandler.rightPressed == true) {
            direction = "right";
            x += speed;
        }else if (keyHandler.jumpingPressed) {
            direction = "idle";
            y--;
        }
        }else{
            direction = "idle";//Idle animation outside the "pressing key loop
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
                   
         g2d.drawImage(image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);

    
    } // Draw images into the screen using the updated coordenate information from the player in the game panel, changes the image accordingly to spriteNumber value.
    
}
