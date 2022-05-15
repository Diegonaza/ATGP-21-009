/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author alisson
 */
public class Zenith extends GameObject{

    
    
   
    GamePanel panelRef;
    int camPrevX;
    int startX;
    int dRandom;
    Handler handlerRef;
    Rectangle hitBox;
    
    
    
    
     public Zenith(int x, int y, int speedX, Handler handler,GamePanel p) {
        super(x, y);
        this.panelRef = p;
        this.camPrevX = p.cameraX;
        ct = ct.white; // there is no white Zenith, If white, please check this part for this wasn't initialized.
        this.startX = x;
        this.xSpeed = 6;
        this.ySpeed = 2;
        this.handlerRef = handler;
        this.hitBox = new Rectangle(2,2);
        
        
        
        
    }
    
    
    
    @Override
    public void tick() {
        
        //collision
        
          //vertical collision
          hitBox.y += ySpeed;
        for(Platform p: handlerRef.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.y -= ySpeed;
                while(!p.hitBox.intersects(hitBox)) hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = ySpeed * -1;
                dRandom = ThreadLocalRandom.current().nextInt(0,2);
                if(dRandom == 0) xSpeed = xSpeed * -1;
                y = hitBox.y;
            }
        }
        
        
        
        //horizontal collision
        hitBox.x += xSpeed;
        for(Platform p: handlerRef.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.x -= xSpeed;
               while(!p.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                xSpeed = xSpeed * -1;
                dRandom = ThreadLocalRandom.current().nextInt(0,2);
                if(dRandom == 0) ySpeed = ySpeed * -1;
                x = hitBox.x;
            }
        }
        
      //Adjust the Object X position to the Camera Movement
        if(camPrevX != panelRef.cameraX){
             x += (camPrevX-panelRef.cameraX);
             x += xSpeed;
             hitBox.x = x;
             camPrevX = panelRef.cameraX;
         }else{
            //If there is no camera movement
             x+= xSpeed;
             hitBox.x = x;
         }
        
        y += ySpeed;
        hitBox.y = y;
        
        
        
        
       
        
       
        
        
    }

    @Override
    public void Draw(Graphics2D g) {
        
        
        switch(ct){
            case blue:
            g.setColor(Color.BLUE);
            //Changes the zenith's color instatiation to blue
                break;
            case red:
            g.setColor(Color.RED);
             //Changes the zenith's color instatiation to red
                break;
            case yellow:
            g.setColor(Color.YELLOW);
             //Changes the zenith's color instatiation to Yellow
                break; 
            case pink:
            g.setColor(Color.PINK);
             //Changes the zenith's color instatiation to Pink
                break;
            case black:
            g.setColor(Color.BLACK);
             //Changes the zenith's color instatiation to Black
                break;
            case white:
            g.setColor(Color.WHITE);
             //Changes the zenith's color instatiation to White
                break;
        }
        
        g.drawRect(x,y, 3, 3);
        g.fillRect(x+1, y+1, 2, 2);
        
        
        
    }
    
}
