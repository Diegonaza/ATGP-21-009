/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import engine.Handler;
import engine.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author diego
 */
public class Platform extends GameObject{
    
    public Rectangle hitBox;
    public int startX;
    public int startY;
    public BufferedImage img;
    public boolean hasCollision;
    
    public Platform(int x, int y, int width, int height,BufferedImage image, boolean hasCollision){
        super(x,y);
        this.x  = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
        this.img = image;
        this.hasCollision = hasCollision;
        //The tileMapper class defines which platform will have collision
        //if collision is enabled create a hitbox
        if(hasCollision == true){
            
        hitBox = new Rectangle(x,y,width,height);
        }
        
    }
    
    //commented lines inside this method are for debug purposes, will draw the collision boxes for each platform
    @Override
    public void Draw(Graphics2D gtd){
       /* gtd.setColor(Color.BLACK);
          gtd.drawRect(x, y, width, height);
          gtd.setColor(Color.white);
          gtd.fillRect(x+1, y+1, width-2, height-2);
        */
        gtd.drawImage(img,x,y,width,height,null);
        
    }
    @Override
    public void tick(){
        
    }
    //check Collision against projectiles
     public void tick(Handler h){
         for(int i = 0; i<h.projectiles.size(); i++){
             if(hitBox.intersects(h.projectiles.get(i).hitBox)){
                 h.projectiles.remove(i);
             }
         }
    }
    
     //update location based on the camera scrolling/movement
    public void setLocation(int cameraX, int cameraY){
        this.x = startX-cameraX;
        this.y = startY-cameraY;
       if(hasCollision){
        hitBox.x = x;
        hitBox.y = y;
       }
    }
}
