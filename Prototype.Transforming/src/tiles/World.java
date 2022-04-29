/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import character.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author valter
 */
public class World {
    public BufferedImage image;
  

    public World(){
       
        try{
            image = ImageIO.read(getClass().getResourceAsStream("dublin.png"));
           
        }catch(IOException e){
            e.printStackTrace();
        }
       
        
        
        
        }
    
    public void paintWorld(Graphics2D g2d, Player player,int x){
 
       
       g2d.drawImage(image,920-x,0,null);        
        
    
   
   // g2d.dispose();
    
    }
        
        
    }
    

