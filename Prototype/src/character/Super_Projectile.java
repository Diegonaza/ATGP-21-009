/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author aliss
 */
public class Super_Projectile{
  public int infection;
  public int damage;
  
  public int speed;
  public int xSpeed;
  public int ySpeed;
  
  public int worldX ,worldY;
  
  public BufferedImage left1, left2, right1, right2, up1, up2, down1, down2;// projectile directions sprite
  
  public String direction ;
  public int spriteCounter;
  public int spriteNumber = 1;
  
  public Rectangle solidArea;

   
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean isColisionOn = true;
  public int actionLockCounter = 0;
  
  public GamePanel gp;
  
  public Super_Projectile(GamePanel gp){
      this.gp = gp;
           
      
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

public void update(){
  
      setAction();
      
       isColisionOn = false;
       
      // gp.colDet.checkColision(this);
        
        //if colision is false player can move
        if (isColisionOn == false) {
            
            switch(direction){
             case "up": worldY -= speed;                  
             break;
             case "down": worldY += speed;
             break;
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
    
     
     
     
     
     
     
     
     
  }
 
 public void setAction(){
  
  }
}
