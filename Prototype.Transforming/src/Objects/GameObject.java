/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 *
 * @author diego
 */
public abstract class GameObject {
    
    protected int x, y, width, height;
    protected double xSpeed, ySpeed;
    protected BufferedImage image, idle1, idle2, left1, left2, right1, right2, attack_right, attack_left;
    

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    
    public abstract void tick();
    public abstract void Draw(Graphics2D g);
    

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setSpeedX(int velX) {
        this.xSpeed = xSpeed;
    }

    public void setSpeedY(int velY) {
        this.ySpeed = ySpeed;
    }

    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public double getSpeedX() {
        return xSpeed;
    }

    public double getSpeedY() {
        return ySpeed;
    }
 
    
    
    
}
