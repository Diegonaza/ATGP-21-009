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
public class Platform {
    int x,y,width,height;
    Rectangle hitBox;
    
    public Platform(int x, int y, int width, int height){
        this.x  = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        hitBox = new Rectangle(x,y,width,height);
    }
    
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x, y, width, height);
        gtd.setColor(Color.white);
        gtd.fillRect(x+1, y+1, width-2, height-2);
    }
}
