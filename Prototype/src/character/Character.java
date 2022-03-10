/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.awt.image.BufferedImage;


/**
 *
 * @author valter
 */
public class Character {
  public int x;
  public int y;
  public int speed;
  
  
  
  public BufferedImage idle1, idle2, left1, left2, right1, right2, attack_right, attack_left; //
  
  public String direction;
  public int spriteCounter;
  public int spriteNumber = 1;
}
