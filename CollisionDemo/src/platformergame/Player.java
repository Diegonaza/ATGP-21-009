/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author diego
 */
public class Player {
    GamePanel panel;
    int x, y, width, height ;
    double xspeed;
    double yspeed;
       
    Rectangle hitBox;
    
    boolean keyLeft,keyRight,keyJump;
    
    public Player(int x, int y, GamePanel panel){
        this.x = x;
        this.y = y;
        this.panel = panel;
        width = 50;
        height = 100;
        hitBox = new Rectangle(x,y,width,height);
    }
    
    //tick
    public void UpdatePlayer(){
      if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
      else if(keyLeft && !keyRight)xspeed --;
      else if(keyRight && !keyLeft)xspeed ++;
      
      if(xspeed > 0 && xspeed<0.75)xspeed = 0;
      if(xspeed<0 && xspeed> -0.75)xspeed =0;
      if(xspeed > 7)xspeed= 7;
      if(xspeed<-7)xspeed = -7;
      
      if(keyJump){
          hitBox.y++;
          for(int i = 0; i<panel.platforms.size(); i++){
              if(panel.platforms.get(i).hitBox.intersects(hitBox))yspeed = -10;
          }
          
         hitBox.y--;       
      }
      
      //gravity
        yspeed += 0.5;
        
        //horizontal collision
        hitBox.x += xspeed;
        for(Platform p: panel.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.x -= xspeed;
               while(!p.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                xspeed = 0;
                x = hitBox.x;
            }
        }
        
        
        //vertical collision
          hitBox.y += yspeed;
        for(Platform p: panel.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.y -= yspeed;
                while(!p.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }
        
        x += xspeed;
        y += yspeed;
        
        hitBox.x = x;
        hitBox.y = y;
        
    }
    
    public void Draw(Graphics2D gtd){
        gtd.setColor(Color.red);
        gtd.fillRect(x, y, width, height);
        
    }
    
}
