/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import character.Citizen;

/**
 *
 * @author aliss
 */
public class AssetDeployer {
    
    GamePanel gp;

public AssetDeployer(GamePanel gp){
this.gp = gp;
    
}

public void SetObject(){
    
}
public void SetNPC(){
    gp.npc[0] = new Citizen(gp);
    gp.npc[0].worldX = gp.tileSize*10;
    gp.npc[0].worldY = gp.tileSize*9;
    gp.npc[0].direction = "left";  
}


}
