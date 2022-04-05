/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.GamePanel;
import model.UtilityTool;

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
        try{
            setup(0, "sky", false);
            setup(1, "sky2", false);
            setup(2, "tijolo_marrom", false);
            setup(3, "cone", false);
            setup(4, "window3", false);
            setup(5, "tijolo_salmao", false);
            setup(6, "simple_door_base", false);
            setup(7, "simple_door_top", false);
            setup(8, "path3", true);
            setup(9, "glass_door_right_base", false);
            setup(10, "glass_door_right_top", false);
            setup(11, "wall", true);
            setup(12, "caffe", false);
            setup(13,"caffe2", false);
            setup(14,"caffe3", false);
            setup(15,"caffe4", false);            
            setup(16, "caffe5", false);
            setup(17, "caffe6", false);
            setup(18, "caffe7", false);
            setup(19, "caffe8", false);
            setup(20, "caffe9", false);
            setup(21, "caffe10", false);
            setup(22, "caffe11", false);
            setup(23, "caffe12", false);
            setup(24, "caffe13", false);
            setup(25, "caffe14", false);
            setup(26, "caffe15", false);
            setup(27, "caffe16", false);
            
             }catch(Exception e){
                 e.printStackTrace();
             
             }
                
          
    }
    
    public void setup(int i, String imgPath, boolean colision){
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[i] = new Tiles();
            tile[i].image =ImageIO.read(getClass().getResourceAsStream("/tilesSprite/"+imgPath+".png"));
            tile[i].image = utilityTool.scaleImage(tile[i].image, gp.tileSize , gp.tileSize);
            tile[i].colision = colision;
        } catch (Exception e) {
        }
    
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
            
            int screenX = worldX - gp.player.worldX +  gp.player.screenX;
            int ScreenY = worldY - gp.player.worldY +  gp.player.screenY;
            
            if ( worldX + gp.tileSize >  gp.player.worldX -  gp.player.screenX &&
                 worldX - gp.tileSize <  gp.player.worldX +  gp.player.screenX &&
                 worldY + gp.tileSize >  gp.player.worldY -  gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.worldY +  gp.player.screenY) {
                
                    graphics2D.drawImage(tile[tileNumner].image,screenX,ScreenY,null);
           
            }
             graphics2D.drawImage(tile[tileNumner].image,screenX,ScreenY,null);
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
