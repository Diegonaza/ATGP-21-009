/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;

/**
 *
 * @author aliss
 */
public abstract class NPC {
    GamePanel gp;
    public BufferedImage image;
    public String name;
    
    public BufferedImage idle1, idle2, left1, left2, right1, right2, attack_right, attack_left; // all characters movement types.
    public int spriteNumber;
    public int spriteCounter;
    
    public boolean isColisionOn = false;
    public int worldX, worldY;
    public String direction;
    
    
    public NPC(GamePanel gp){
        this.gp = gp;
    }
    
    public void draw(Graphics2D g2, GamePanel gp){
        
        int screenX = worldX - gp.player.WorldX +  gp.player.screenX;
            int ScreenY = worldY - gp.player.WorldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.WorldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.WorldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.WorldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.WorldY +  gp.player.screenY) {
                
                    g2.drawImage(image ,screenX,ScreenY,gp.tileSize,gp.tileSize,null);
           
            }
    }
 
     public BufferedImage setup(String imagePath){
        
        
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        }catch(IOException e){
            System.out.println("Error reading image " + imagePath);
            //If any problem with image, we get exactly which one is it.
        }
        
        
        return image;
    }
     public abstract void update();
}

