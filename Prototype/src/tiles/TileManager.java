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
    public int tileOrderer[][];

    public TileManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        tile = new Tiles[30];
        tileOrderer = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
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
                        tileOrderer[col][row] = num;
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
            tile[12] = new Tiles();
            tile[12].image =  new ImageIcon("src/tilesSprite/caffe.png");
            tile[13] = new Tiles();
            tile[13].image =  new ImageIcon("src/tilesSprite/caffe2.png");
            tile[14] = new Tiles();
            tile[14].image =  new ImageIcon("src/tilesSprite/caffe3.png");
            tile[15] = new Tiles();
            tile[15].image =  new ImageIcon("src/tilesSprite/caffe4.png");
            tile[16] = new Tiles();
            tile[16].image =  new ImageIcon("src/tilesSprite/caffe5.png");
            tile[17] = new Tiles();
            tile[17].image =  new ImageIcon("src/tilesSprite/caffe6.png");
            tile[18] = new Tiles();
            tile[18].image =  new ImageIcon("src/tilesSprite/caffe7.png");
            tile[19] = new Tiles();
            tile[19].image =  new ImageIcon("src/tilesSprite/caffe8.png");
            tile[20] = new Tiles();
            tile[20].image =  new ImageIcon("src/tilesSprite/caffe9.png");
            tile[21] = new Tiles();
            tile[21].image =  new ImageIcon("src/tilesSprite/caffe10.png");
            tile[22] = new Tiles();
            tile[22].image =  new ImageIcon("src/tilesSprite/caffe11.png");
            tile[23] = new Tiles();
            tile[23].image =  new ImageIcon("src/tilesSprite/caffe12.png");
            tile[24] = new Tiles();
            tile[24].image =  new ImageIcon("src/tilesSprite/caffe13.png");
            tile[25] = new Tiles();
            tile[25].image =  new ImageIcon("src/tilesSprite/caffe14.png");
            tile[26] = new Tiles();
            tile[26].image =  new ImageIcon("src/tilesSprite/caffe15.png");
            tile[27] = new Tiles();
            tile[27].image =  new ImageIcon("src/tilesSprite/caffe16.png");
          
          
    }
    public void drawTiles(Graphics2D graphics2D){
        
        int worldCol = 0;
        int WorldRow = 0;
        
       // int x = 0;deleted
        //int y = 0;
        
        while (worldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow)   {            
            
            int tileNumner = tileOrderer[worldCol][WorldRow];
            
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
