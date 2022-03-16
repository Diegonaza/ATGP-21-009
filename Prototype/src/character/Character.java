/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import model.GamePanel;


/**
 *
 * @author valter
 */
public class Character {
    
  public int speed;
  public double counter = 4;//jump
  
  public int WorldX ,WorldY;
  
  public BufferedImage idle1, idle2, left1, left2, right1, right2, attack_right, attack_left; //
  
  public String direction;
  public int spriteCounter;
  public int spriteNumber = 1;
  
  public Rectangle solidArea;
  public boolean isColisionOn = false;
  
  public GamePanel gp;
  
  public Character(GamePanel gp){
      this.gp = gp;
  
  
  
  }
}
