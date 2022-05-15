/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author diego
 */
public class LevelOne {
    BufferedImage Dublin;
    BufferedImage Clouds2;
    BufferedImage img2;
    BufferedImage Sky;
    GamePanel p;
    int x;
    int DublinSpeed;
    
    public LevelOne(GamePanel p){
        InputStream is = getClass().getResourceAsStream("/Images/Map1/dublin.png");
        InputStream is1 = getClass().getResourceAsStream("/Images/Map1/clouds_2.png");
        InputStream is2 = getClass().getResourceAsStream("/Images/Map1/sky.png");
        this.p = p;
        try{
            Dublin = ImageIO.read(is);
            Clouds2 = ImageIO.read(is1);
            Sky = ImageIO.read(is2);
            
        }catch(Exception e){
            
        }
        
    }
    
   
    public void Draw(Graphics2D gtd){
       
            gtd.drawImage(Sky,0,0,1280,800,p);
            gtd.drawImage(Clouds2, x, 0,1280,800,p);
            gtd.drawImage(Clouds2, x+800, 0,1280,800,p);
            gtd.drawImage(Dublin, DublinSpeed-20, -100,1280,800,p); 
            
            gtd.drawImage(Dublin, DublinSpeed+1280-20, 0,1280,800,p);
    }
    
    public void updateLocation(int cam){
        x = -(cam/6);
        DublinSpeed = -(cam/4);
    }
}
