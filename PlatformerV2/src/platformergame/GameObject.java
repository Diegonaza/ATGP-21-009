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
    protected ID id;
    protected double xSpeed, ySpeed;
    
    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
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

    public void setId(ID id) {
        this.id = id;
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

    public ID getId() {
        return id;
    }

    public double getSpeedX() {
        return xSpeed;
    }

    public double getSpeedY() {
        return ySpeed;
    }
    
}
