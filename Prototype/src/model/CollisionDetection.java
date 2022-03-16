/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import character.Character;
/**
 *
 * @author valter
 */
public class CollisionDetection {
    GamePanel gp;
    
    public CollisionDetection(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkColision(Character character){
        //World colision Detection
        int characterWorldLeftX = character.WorldX + character.solidArea.x;
        int characterWorldRigthX = character.WorldX + character.solidArea.x + character.solidArea.width;
        int characterWorldTopY = character.WorldY + character.solidArea.y;
        int characterWorldBottonY = character.WorldY + character.solidArea.y + character.solidArea.height;
      
         //World colision Detection Player 
         int characterLeftCol = characterWorldLeftX/gp.tileSize;
         int characterRigthCol = characterWorldRigthX/gp.tileSize;
         int characterTopRow = characterWorldTopY/gp.tileSize;
         int characterBottonRow = characterWorldBottonY/gp.tileSize;
         
         int TileNum1, TileNum2;
         
         switch(character.direction){
             
             case "up": characterTopRow = (characterWorldTopY - character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOreder[characterLeftCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOreder[characterRigthCol][characterTopRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                     
                 }
             break;
              case "down":characterBottonRow = (characterWorldBottonY + character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOreder[characterLeftCol][characterBottonRow];
             TileNum2 = gp.tileManager.tileOreder[characterRigthCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                     
                 }
             break;
              case "left": characterLeftCol= (characterWorldLeftX - character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOreder[characterLeftCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOreder[characterLeftCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                 }
             break;
              case "right": characterRigthCol= (characterWorldRigthX + character.speed)/gp.tileSize;
             TileNum1 = gp.tileManager.tileOreder[characterRigthCol][characterTopRow];
             TileNum2 = gp.tileManager.tileOreder[characterRigthCol][characterBottonRow];
                 if (gp.tileManager.tile[TileNum1].colision == true || gp.tileManager.tile[TileNum2].colision == true) {
                    character.isColisionOn = true;
                 }
             break;
         
         
         }
    }
    
}
