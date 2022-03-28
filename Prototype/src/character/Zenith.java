/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import model.GamePanel;
import java.util.*;

/**
 *
 * @author aliss
 */
public class Zenith extends Super_Projectile {
    
    
    
    
   public Zenith(GamePanel gp){
       super(gp);
       
        
        infection = 2;
        direction = "left";
        speed = 7;
        
        getZenithImage();
                    
   }
   
   
   
   public void getZenithImage(){
       
       switch(infection){
           case 1:
               //BLUE
                right1 = setup("/Zenith/Blue/right1.png");
                right2 = setup("/Zenith/Blue/right2.png");
                left1 = setup("/Zenith/Blue/right2.png");
                left2 = setup("/Zenith/Blue/right1.png");
                up1 = setup("/Zenith/Blue/up1.png");
                up2 = setup("/Zenith/Blue/up2.png");
                down1 = setup("/Zenith/Blue/up2.png");
                down2 = setup("/Zenith/Blue/up1.png");
                
               break;
           case 2:
               //RED
               right1 = setup("/Zenith/Red/right1.png");
                right2 = setup("/Zenith/Red/right2.png");
                left1 = setup("/Zenith/Red/right2.png");
                left2 = setup("/Zenith/Red/right1.png");
                up1 = setup("/Zenith/Red/up1.png");
                up2 = setup("/Zenith/Red/up2.png");
                down1 = setup("/Zenith/Red/up2.png");
                down2 = setup("/Zenith/Red/up1.png");
               
               break;
           case 3:
               //YELLOW
                right1 = setup("/Zenith/Yellow/right1.png");
                right2 = setup("/Zenith/Yellow/right2.png");
                left1 = setup("/Zenith/Yellow/right2.png");
                left2 = setup("/Zenith/Yellow/right1.png");
                up1 = setup("/Zenith/Yellow/up1.png");
                up2 = setup("/Zenith/Yellow/up2.png");
                down1 = setup("/Zenith/Yellow/up2.png");
                down2 = setup("/Zenith/Yellow/up1.png");
               
               break;
           case 4:
               //PINK
                right1 = setup("/Zenith/Pink/right1.png");
                right2 = setup("/Zenith/Pink/right2.png");
                left1 = setup("/Zenith/Pink/right2.png");
                left2 = setup("/Zenith/Pink/right1.png");
                up1 = setup("/Zenith/Pink/up1.png");
                up2 = setup("/Zenith/Pink/up2.png");
                down1 = setup("/Zenith/Pink/up2.png");
                down2 = setup("/Zenith/Pink/up1.png");
               
               break;
           case 5:
               //BLACK
                right1 = setup("/Zenith/Black/right1.png");
                right2 = setup("/Zenith/Black/right2.png");
                left1 = setup("/Zenith/Black/right2.png");
                left2 = setup("/Zenith/Black/right1.png");
                up1 = setup("/Zenith/Black/up1.png");
                up2 = setup("/Zenith/Black/up2.png");
                down1 = setup("/Zenith/Black/up2.png");
                down2 = setup("/Zenith/Black/up1.png");
               
               break;
   }
    
}
   
   
   public void setInfection(String infectionType){
    
       int i;
       switch(infectionType){
           case "Blue":
               this.infection = 1;
               break;
           case "Red":
               this.infection = 2;
               break;              
           case "Yellow":
               this.infection = 3;
               break;
           case "Pink":
               this.infection = 4;
               break;
           case "Black":
               this.infection = 5;
               break;
                  }
             
   }// Defines the infection type.


    @Override
    public void draw(Graphics2D g2) {
	
        int screenX = worldX - gp.player.worldX + gp.player.screenX + 200;
            int screenY = worldY - gp.player.worldY +  gp.player.screenY + 280;
            
            if ( worldX + gp.tileSize >  gp.player.worldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.worldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.worldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.worldY +  gp.player.screenY) {
        
        
        BufferedImage image = null;
             
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
                 
             case "up":
                 if(spriteNumber == 1){
                     image = up1;}
                 if(spriteNumber == 2){
                     image = up2;}
                 break;
                 
             case "down":
                 if(spriteNumber == 1){
                     image = down1;}
                 if(spriteNumber == 2){
                     image = down2;}
                 break;     
        }
       g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
       }
    }
                   
    @Override
    public void setAction(){
        int j  = 0;//comparasion integer
        actionLockCounter ++;
        if(actionLockCounter == 120){
        
        Random random = new Random(); 
        int i = random.nextInt(4);//pick up a ramdom  number from 1 to 100
        
        if(i != j){            
        switch(i){
            case 1:
            direction  = "right";
            j = i;
            break;
            
            case 2:
            direction  = "left";
            j = i;
            break;
            
            case 3:
            direction  = "up";
            j = i;
            break;
            
            case 4:
            direction  = "down";
            j = i;
            break;        }
        
        }else{ setAction(); }
        actionLockCounter = 0;
    
    }
    }
	
}
