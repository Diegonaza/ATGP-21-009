/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author valter
 */
public class Character {
  public int x;
  public int y;
  public int speed;
  public double counter;
  
  
  public ImageIcon stopped,up1, up2, down1, down2, left1, left2, right1, right2;
  public String direction;
}
