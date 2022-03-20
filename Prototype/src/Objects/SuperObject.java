/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GamePanel;

/**
 *
 * @author mvini
 */
public class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    
    
    public void drawObjects(Graphics2D graphics2D, GamePanel gp){
            int screenX = worldX - gp.player.WorldX +  gp.player.screenX;
            int ScreenY = worldY - gp.player.WorldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.WorldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.WorldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.WorldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.WorldY +  gp.player.screenY) {
                
                    graphics2D.drawImage(image,screenX,ScreenY,gp.tileSize,gp.tileSize,null);
           
            }
        
    }
}
