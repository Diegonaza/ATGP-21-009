/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.image.BufferedImage;


/**
 *
 * @author diego
 */
public class Animation {
    
    private BufferedImage image;
    
    
    public Animation(BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage updateImage(int x, int y, int width, int height){
        BufferedImage img = image.getSubimage(x, y, 32, 32);
        return img;
    }
}
