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
    int Speed;
    
       
    public LevelOne(GamePanel p){
        InputStream is = getClass().getResourceAsStream("/Images/Map1/dublin.png");
        //loads the dublin backgound image from the resources and saves it as a variable
        InputStream is1 = getClass().getResourceAsStream("/Images/Map1/clouds_2.png");
       // InputStream is2 = getClass().getResourceAsStream("/Images/Map1/rocks_1.png");//remored mountain
         InputStream is3 = getClass().getResourceAsStream("/Images/Map1/sky.png");
         // import the sky image from resources and save it as a variable.
         
        this.p = p;
        // writes the gamePanel variable with the actual gamePanel.
        
        try{
            img1 = ImageIO.read(is);
            img2 = ImageIO.read(is1);
            img3 = ImageIO.read(is3);
            
        }catch(Exception e){
            
        }
        
    }
    
   
    public void Draw(Graphics2D gtd){
        gtd.setColor(Color.red);
        //gtd.fillRect(x, y, width, height);
        gtd.drawImage(img3,0,0,1280,800,p);
            gtd.drawImage(img1,-50,0,1280,800,p);
            gtd.drawImage(img2, x, 0,1280,800,p);
            gtd.drawImage(img3, Speed-20, 0,1280,800,p); 
            gtd.drawImage(img1, x+800, 0,1280,800,p);
        gtd.drawImage(img2, Speed+1280-20, 0,1280,800,p);
    }
    
    public void updateLocation(int cam){
        x = -(cam/32);
        Speed = -(cam/16);
    }
}
