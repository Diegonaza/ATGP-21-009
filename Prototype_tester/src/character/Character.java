/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;


/**
 *
 * @author valter
 */
public class Character {
    
  public int speed;
  public double counter = 4;//jump
  
  public int WorldX ,WorldY;
  public int screenX, screenY;
  
  public BufferedImage idle1, idle2, left1, left2, right1, right2, attack_right, attack_left; // all characters movement types.
  
  public String direction;
  public int spriteCounter;
  public int spriteNumber = 1;
  
  public Rectangle solidArea = new Rectangle(0,0,48,48);
  public boolean isColisionOn = false;
  
  public GamePanel gp;
  
  public Character(GamePanel gp){
      this.gp = gp;
  
  
  }
  public BufferedImage setup(String imagePath){
        
        
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        }catch(IOException e){
            System.out.println("Error reading image " + imagePath);
            //If any problem with image, we get exactly which one is it.
        }
        
        
        return image;
    }
}
