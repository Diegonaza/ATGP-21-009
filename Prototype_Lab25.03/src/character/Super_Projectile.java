/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import model.GamePanel;

/**
 *
 * @author aliss
 */
public class Super_Projectile {
  public int infection;
  public int damage;
  
  public int speed;
  
  public int worldX ,worldY;
  
  public BufferedImage left1, left2, right1, right2, up1, up2, down1, down2;// projectile directions sprite
  
  public String direction ;
  public int spriteCounter;
  public int spriteNumber = 1;
  
  public Rectangle solidArea;
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean isColisionOn = true;
  public int actionLockCounter = 0;
  
  public GamePanel gp;
  
  public Super_Projectile(GamePanel gp){
      this.gp = gp;
  }
  
    
}
