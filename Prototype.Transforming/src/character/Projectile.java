/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import Objects.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import model.Handler;

/**
 *
 * @author diegof
 */
public class Projectile extends GameObject{

    int projectileSpeedX = 10;
    int startX;
    int projectileSpeedY;
    Handler handlerRef;
    public Rectangle hitBox;
    
     public Projectile(int x, int y) {
        super(x, y);
    }

    public Projectile(int x, int y,int speedX, Handler h) {
        super(x, y);
        this.startX = x;
        this.projectileSpeedX = projectileSpeedX*speedX;
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
        g.setColor(Color.white);
        g.fillRect(x+1, y+1, 2, 2);
    }

   
    
}
