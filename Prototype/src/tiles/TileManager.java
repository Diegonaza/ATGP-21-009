/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import model.GamePanel;

/**
 *
 * @author valter
 */
public class TileManager {
    
    GamePanel gp;
    public Tiles[] tile;
    public int tileOreder[][];

    public TileManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        tile = new Tiles[12];
        tileOreder = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
         getTileImage();
         loadMap(); 
       
    }
    
    public void loadMap(){
        
       
           try {  
           FileReader fileReader = new FileReader("src/maps/map");          
           BufferedReader bufferedReader = new BufferedReader(fileReader);
           
                int col = 0;
                int row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {                    
                    
                    
                    
                    String line = bufferedReader.readLine();
                    
                    while (col < gp.maxWorldCol) {
                        
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        tileOreder[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                        
                    }
                }
            }
                
            
        
        catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    public void getTileImage(){
        
            tile[0] = new Tiles();
            tile[0].image = new ImageIcon("src/tilesSprite/sky.png");           
            tile[1] = new Tiles();
            tile[1].image =  new ImageIcon("src/tilesSprite/sky2.png");
            tile[2] = new Tiles();
            tile[2].image =  new ImageIcon("src/tilesSprite/tijolo_marrom.png");
            tile[3] = new Tiles();
            tile[3].image =  new ImageIcon("src/tilesSprite/cone.png");
            tile[4] = new Tiles();
            tile[4].image =  new ImageIcon("src/tilesSprite/window3.png");
            tile[5] = new Tiles();
            tile[5].image =  new ImageIcon("src/tilesSprite/tijolo_salmao.png");
            tile[6] = new Tiles();
            tile[6].image =  new ImageIcon("src/tilesSprite/simple_door_base.png");
            tile[7] = new Tiles();
            tile[7].image =  new ImageIcon("src/tilesSprite/simple_door_top.png");
            tile[8] = new Tiles();
            tile[8].image =  new ImageIcon("src/tilesSprite/path3.png");
            tile[9] = new Tiles();
            tile[9].image =  new ImageIcon("src/tilesSprite/glass_door_right_base.png");
            tile[10] = new Tiles();
            tile[10].image =  new ImageIcon("src/tilesSprite/glass_door_right_top.png");
            tile[11] = new Tiles();
            tile[11].image =  new ImageIcon("src/tilesSprite/wall.png");
            tile[11].colision = true;
    }
    public void drawTiles(Graphics2D graphics2D){
        
        int worldCol = 0;
        int WorldRow = 0;
        
       // int x = 0;deleted
        //int y = 0;
        
        while (worldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow)   {            
            
            int tileNumner = tileOreder[worldCol][WorldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = WorldRow * gp.tileSize;
            
            int screenX = worldX - gp.player.WorldX +  gp.player.screenX;
            int ScreenY = worldY - gp.player.WorldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.WorldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.WorldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.WorldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.WorldY +  gp.player.screenY) {
                
                    graphics2D.drawImage(tile[tileNumner].image.getImage(),screenX,ScreenY,gp.tileSize,gp.tileSize,null);
           
            }
             graphics2D.drawImage(tile[tileNumner].image.getImage(),screenX,ScreenY,gp.tileSize,gp.tileSize,null);
             worldCol++;
             
             // x += gp.tileSize;deleted
             if (worldCol == gp.maxWorldCol) {
                  worldCol = 0;
                // x = 0;deleted
                 WorldRow++;
                // y+= gp.tileSize;deleted
            
                }
    
        }
    }
}
