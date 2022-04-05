/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platform;

import Objects.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author mvini
 */
public class Platform1 extends SuperPlatform {
    
    public Platform1(){
        
        name = "platform";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("path3.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
        
        
        
    }
    
}
