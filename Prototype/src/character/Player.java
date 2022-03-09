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
import model.KeyHundle;

/**
 *
 * @author valter
 */
public class Player extends Character{
    
    GamePanel  gamePanel;//instanciates Game Panel Class
    KeyHundle keyHundle;
    private Image image;
    
    public Player(GamePanel  gamePanel, KeyHundle keyHundle){
    this.gamePanel = gamePanel;
    this.keyHundle = keyHundle;
    this.setDefaultvalues();
    this.getPlayerImg();
    }
    
    public void getPlayerImg() {
       
         stopped = new ImageIcon("src/playerSprite/BergStoped.png");
        }
    
    public void setDefaultvalues(){
    x = 100;
    y = 100;
    speed = 4;
    counter = 4;
    
    }
    
    public void update(){
        
        if (keyHundle.upPressed == true) {
            y -= speed;
        }else if (keyHundle.downPressed == true) {
            y += speed;
        }else if (keyHundle.leftPressed == true) {
            x -= speed;
        }else if (keyHundle.rightPressed == true) {
            x += speed;
        }else if (keyHundle.jumpingPressed) {
            y--;
        }
           
    }
    
     public void paintComponent(Graphics2D g2d){
           
         Image image = stopped.getImage();
     
        g2d.drawImage(image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);

    
    }
    
}
