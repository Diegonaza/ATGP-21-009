/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Graphics2D;

/**
 *
 * @author diego
 */
public abstract class GameObject {
    
    protected int x, y, width, height;
    protected double xSpeed, ySpeed;
    ColourType ct;
    
    
    
     
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }
    //constructor for position
    
    public abstract void tick();
    public abstract void Draw(Graphics2D g);

    public void setX(int x) {
        this.x = x;
    }
    //writes the x coordinate to this X

    public void setY(int y) {
        this.y = y;
    }
//writes the Y coordinate to this Y
    

    public void setSpeedX(int velX) {
        this.xSpeed = velX;
    }
    //writes the Speed to xSpeed
    

    public void setSpeedY(int velY) {
        this.ySpeed = velY;
    }
    //writes the Speed to ySpeed

    
    public int getX() {
        return x;
    }
    //returns x

    public int getY() {
        return y;
    }
//returns y

    public double getSpeedX() {
        return xSpeed;
    }
    //returns xSpeed

    public double getSpeedY() {
        return ySpeed;
    }
    //returns ySpeed
}
