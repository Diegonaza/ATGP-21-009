/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;

/**
 *
 * @author diego
 */
public class Platform {
    public int x,y,width,height;
    public Rectangle hitBox;
    int startX;
    public BufferedImage image;
    
    public Platform(int  x, int  y, int width, int height){
        this.x = x;
        this.y = y;
        startX = x;
        this.width = width;
        this.height = height;
       
        hitBox = new Rectangle(x ,y,width,height);
        
         try{
            image = ImageIO.read(getClass().getResourceAsStream("tijolo_marrom.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
       
        
        
        
    }
    
    
    public void draw(Graphics2D  graphics2D){
       
        graphics2D.drawImage(image,x+1,y+1, width-2,height-2,null);
           
        
    }
    
    
    public int set(int worldX){
    x = startX+ worldX;
    hitBox.x = x;
    
        return x;
    }
}
