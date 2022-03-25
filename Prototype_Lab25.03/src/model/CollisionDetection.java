/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import character.Characters;

/**
 *
 * @author valter
 */
public class CollisionDetection {
    GamePanel gp;
    
    public CollisionDetection(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkColision(Characters character){
        //World colision Detection
        int characterWorldLeftX = character.worldX + character.solidArea.x;
        int characterWorldRigthX = character.worldX + character.solidArea.x + character.solidArea.width;
        int characterWorldTopY = character.worldY + character.solidArea.y;
        int characterWorldBottonY = character.worldY + character.solidArea.y + character.solidArea.height;
      
         //World colision Detection Player 
         int characterLeftCol = characterWorldLeftX/gp.tileSize;
         int characterRigthCol = characterWorldRigthX/gp.tileSize;
         int characterTopRow = characterWorldTopY/gp.tileSize;
         int characterBottonRow = characterWorldBottonY/gp.tileSize;
         
         int TileNum1, TileNum2;
         
         switch(character.direction){
             
             case "up": characterTopRow = (characterWorldTopY - character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOrderer[characterLeftCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOrderer[characterRigthCol][characterTopRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                     
                 }
             break;
              case "down":characterBottonRow = (characterWorldBottonY + character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOrderer[characterLeftCol][characterBottonRow];
             TileNum2 = gp.tileManager.tileOrderer[characterRigthCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                     
                 }
             break;
              case "left": characterLeftCol= (characterWorldLeftX - character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOrderer[characterLeftCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOrderer[characterLeftCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                 }
             break;
              case "right": characterRigthCol= (characterWorldRigthX + character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOrderer[characterRigthCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOrderer[characterRigthCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                 }
             break;
         
         
         }
    }

    
    public int objectChecker(Characters character, boolean player){
        int index = 999;
        
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                
                //entity solid area location
                character.solidArea.x = character.worldX + character.solidArea.x;
                character.solidArea.y = character.worldY + character.solidArea.y;
                // object solid area location
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                switch(character.direction){
                    case "up":
                        character.solidArea.y -= character.speed;
                        //checks if the 2 rectangles are touching each other or not
                        if(character.solidArea.intersects(gp.obj[i].solidArea)){
                            //check if it is colliding with the object and with the player
                            if(gp.obj[i].collision == true){
                                character.isColisionOn = true;
                            }
                            if(player == true){
                                index =i;
                                
                            }
                        }
                        break;
                    case "down":
                        character.solidArea.y += character.speed;
                        if(character.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                character.isColisionOn = true;
                            }
                            if(player == true){
                                index =i;
                                
                            }
                            
                        }
                        break;
                    case "left":
                        character.solidArea.x -= character.speed;
                        if(character.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                character.isColisionOn = true;
                            }
                            if(player == true){
                                index =i;
                                
                            }
                            
                        }
                        break;
                    case "right":
                        character.solidArea.x += character.speed;
                        if(character.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                character.isColisionOn = true;
                            }
                            if(player == true){
                                index =i;
                                
                            }
                            
                        }
                        break;
                }
                //reset the numbers to default after the switch
                character.solidArea.x = character.solidAreaDefaultX;
                character.solidArea.y = character.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
            
            
        }
        
        
        return index;
        
    }
}
