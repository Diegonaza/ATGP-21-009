/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author mvini
 */
public class objKey extends SuperObject {
    
    public objKey(){
        
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("arma1.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
