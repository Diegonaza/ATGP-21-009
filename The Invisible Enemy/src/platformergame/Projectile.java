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
 * @author diegof
 */
public class Projectile extends GameObject{
    //projecttile speed on the X vector
    int projectileSpeedX = 10;
    //Projectile Start position
    int startX;
    int projectileSpeedY;
    //reference to the handler class
    Handler handlerRef;
    Rectangle hitBox;
    
     public Projectile(int x, int y) {
        super(x, y);
    }

    public Projectile(int x, int y,int speedX, Handler h) {
        super(x, y);
        //set start position
        this.startX = x;
        //set speed on X vector
        this.projectileSpeedX = projectileSpeedX*speedX;
        //reference to the handler class
        this.handlerRef = h;
        this.hitBox = new Rectangle(2,2);
    }
    
    
    @Override
    public void tick() {
        this.x += projectileSpeedX;
        hitBox.x = x;
        hitBox.y = y;
       
        
      
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 2, 2);
        g.fillRect(x+1, y+1, 2, 2);
    }

   
    
}
