/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author aliss
 */
public class Zenith extends GameObject{

    
    
    int speedX = 10;
    int speedY = 10;
    int zenithSpeedX;
    int zenithSpeedY;
    
    int startX;
    
    
    
    
    Handler handlerRef;
    Rectangle hitBox;
    
    
    
    
     public Zenith(int x, int y, int speedX, Handler handler) {
        super(x, y);
        ct = ct.white; // there is no white Zenith, If white, please check this part for this wasn't initialized.
        
        this.startX = x;
        this.speedX = speedX* zenithSpeedX;
        this.handlerRef = handler;
        this.hitBox = new Rectangle(2,2);
        
        
        
        
    }
    
    
    
    @Override
    public void tick() {
        
        
        this.x += zenithSpeedX;
        this.y += zenithSpeedY;
        
        hitBox.x = x;
        hitBox.y = y;
        
        
    }

    @Override
    public void Draw(Graphics2D g) {
        
        
        switch(ct){
            case blue:
            g.setColor(Color.BLUE);
                break;
            case red:
            g.setColor(Color.RED);
                break;
            case yellow:
            g.setColor(Color.YELLOW);
                break; 
            case pink:
            g.setColor(Color.PINK);
                break;
            case black:
            g.setColor(Color.BLACK);
                break;
            case white:
            g.setColor(Color.WHITE);
                break;
        }
        
        g.drawRect(x, y, 3, 3);
        g.fillRect(x+1, y+1, 2, 2);
        
        
        
    }
    
}
