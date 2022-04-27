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
    BufferedImage imgRockTwo;
    BufferedImage img1;
    BufferedImage img2;
    BufferedImage img3;
    GamePanel p;
    int x;
    int rockSpeed;
    
    public LevelOne(GamePanel p){
        InputStream is = getClass().getResourceAsStream("/Images/Map1/rocks_2.png");
        InputStream is1 = getClass().getResourceAsStream("/Images/Map1/clouds_2.png");
        InputStream is2 = getClass().getResourceAsStream("/Images/Map1/rocks_1.png");
         InputStream is3 = getClass().getResourceAsStream("/Images/Map1/sky.png");
        p = p;
        try{
            imgRockTwo = ImageIO.read(is);
            img1 = ImageIO.read(is1);
            img2 = ImageIO.read(is2);
            img3 = ImageIO.read(is3);
            
        }catch(Exception e){
            
        }
        
    }
    
   
    public void Draw(Graphics2D gtd){
        gtd.setColor(Color.red);
        //gtd.fillRect(x, y, width, height);
        gtd.drawImage(img3,0,0,800,600,p);
            gtd.drawImage(img2,-50,0,800,600,p);
            gtd.drawImage(img1, x, 0,800,600,p);
            gtd.drawImage(imgRockTwo, rockSpeed-20, 0,800,600,p); 
            gtd.drawImage(img1, x+800, 0,800,600,p);
        gtd.drawImage(imgRockTwo, rockSpeed+800-20, 0,800,600,p);
    }
    
    public void updateLocation(int cam){
        x = -(cam/32);
        rockSpeed = -(cam/16);
    }
}
