/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platform;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;
import sun.font.TrueTypeFont;

/**
 *
 * @author mvini
 */
public class SuperPlatform {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle (worldX,worldY,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    GamePanel gp;
    
     
    public void drawPlatform(Graphics2D graphics2D, GamePanel gp){
            int screenX = worldX - gp.player.worldX +  gp.player.screenX;
            int ScreenY = worldY - gp.player.worldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.worldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.worldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.worldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.worldY +  gp.player.screenY) {
                
                    graphics2D.drawImage(image,screenX,ScreenY,gp.tileSize,gp.tileSize,null);
           
            }
        
    }
}
