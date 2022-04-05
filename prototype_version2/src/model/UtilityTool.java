/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 *
 * @author valter
 */
public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage originalImg, int width , int height ){
        
        BufferedImage scaledImage = new BufferedImage(width, height, originalImg.getType());
        Graphics2D g2D = scaledImage .createGraphics();
        g2D.drawImage(originalImg, 0, 0, width, height, null);
        g2D.dispose();
        return scaledImage;
        }
    
}
