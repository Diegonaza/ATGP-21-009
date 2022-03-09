/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;


import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import model.GamePanel;
import model.KeyHandler;

/**
 *
 * @author valter
 */
public class Player extends Character{
    
    GamePanel  gamePanel;//instanciates Game Panel Class
    KeyHandler keyHandler;
    private Image image;
    
    public Player(GamePanel  gamePanel, KeyHandler keyHundle){
    this.gamePanel = gamePanel;
    this.keyHandler = keyHundle;
    this.setDefaultvalues();
    this.getPlayerImg();
    }
    
    public void getPlayerImg() {
       
         stopped = new ImageIcon("src/playerSprite/BergStoped.png");
        }
    
    public void setDefaultvalues(){
    x = 144;
    y = 384;
    speed = 4;
    counter = 4;
    
    }
    
    public void update(){
        
        if (keyHandler.upPressed == true) {
            y -= speed;
        }else if (keyHandler.downPressed == true) {
            y += speed;
        }else if (keyHandler.leftPressed == true) {
            x -= speed;
        }else if (keyHandler.rightPressed == true) {
            x += speed;
        }else if (keyHandler.jumpingPressed) {
            y--;
        }
           
    }
    
     public void paintComponent(Graphics2D g2d){
           
         Image image = stopped.getImage();
     
        g2d.drawImage(image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);

    
    }
    
}
