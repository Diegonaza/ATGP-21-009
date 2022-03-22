/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Objects.objGun;
import Objects.objMask;
import character.Citizen;


/**
 *
 * @author mvini
 */
public class SetAssets {
    
    GamePanel gp;
    
    public SetAssets(GamePanel gp){
        this.gp = gp;
        
     
    }
  
    //instanciate the object keys and set them in the specific place of the map
    public void setObjects(){
      gp.obj[0] = new objGun();  
      gp.obj[0].worldX = 20 * gp.tileSize;
      gp.obj[0].worldY = 9 * gp.tileSize;  
      
      gp.obj[1] = new objGun();
      gp.obj[1].worldX = 17 * gp.tileSize;
      gp.obj[1].worldY = 9 * gp.tileSize;  
      
         gp.obj[2] = new objMask();
      gp.obj[2].worldX = 13 * gp.tileSize;
      gp.obj[2].worldY = 9 * gp.tileSize;     
      
        
    }
    
    public void setNPC(){
    
    gp.npc[0] = new Citizen(gp);
    gp.npc[0] .worldX = gp.tileSize * 20;
    gp.npc[0] .worldY = gp.tileSize * 9;
    
    }
}
