/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import java.awt.image.BufferedImage;


/**
 *
 * @author diego
 * @comment: Alisson
 */
public class Animation {
    
    private BufferedImage image;
    
    
    public Animation(BufferedImage image){
        this.image = image;
    }
    //the is a constructor that works as a Setter method.
    
    public BufferedImage updateImage(int x, int y, int width, int height){
        BufferedImage img = image.getSubimage(x, y, 32, 32);
        return img;
    }
    //Method reads a sub image starting from a point in a bigger image for a fixed image size on 32x32
    
}
