/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;
import model.UtilityTool;


/**
 *
 * @author valter
 */
public class Characters {
    
  public int speed;
  public double counter = 4;//jump
  
  public int worldX ,worldY;
  
  public BufferedImage idle1, idle2, left1, left2, right1, right2, attack_right, attack_left; //
  
  public String direction ;
  public int spriteCounter;
  public int spriteNumber = 1;
  
  public Rectangle solidArea;
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean isColisionOn = false;
  public int actionLockCounter = 0;
  
  public GamePanel gp;
  
  public Characters(GamePanel gp){
      this.gp = gp;
  
  
  
  }
  public void setAction(){
  
  
  
  }
  public void update(){
  
      setAction();
      
       isColisionOn = false;
       
      // gp.colDet.checkColision(this);
        
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
  
  public void draw(Graphics2D g2){
    BufferedImage image = null;
    
            int screenX = worldX - gp.player.worldX +  gp.player.screenX;
            int screenY = worldY - gp.player.worldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.worldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.worldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.worldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.worldY +  gp.player.screenY) {
          
       switch(direction){
                       
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
                   
         g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

            }
  
  
  }
  public BufferedImage setup(String imagePath){
      
        UtilityTool utilityTool = new UtilityTool();
                
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            System.out.println("Error reading image " + imagePath);
            //If any problem with image, we get exactly which one is it.
        }
        
        
        return image;
    }
  
}
