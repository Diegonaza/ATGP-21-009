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
    
    GamePanel gamePanel;
    Tiles[] tile;
    int tileOreder[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tiles[10];
        tileOreder = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
         getTileImage();
         loadMap(); 
       
    }
    
    public void loadMap(){
        
       
           try {  
           FileReader fileReader = new FileReader("src/maps/map");          
           BufferedReader bufferedReader = new BufferedReader(fileReader);
           
                int col = 0;
                int row = 0;
                while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {                    
                    
                    
                    
                    String line = bufferedReader.readLine();
                    
                    while (col < gamePanel.maxScreenCol) {
                        
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        tileOreder[col][row] = num;
                        col++;
                    }
                    if (col == gamePanel.maxScreenCol) {
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
            tile[0].image = new ImageIcon("src/tilesSprite/grass.png");           
            tile[1] = new Tiles();
            tile[1].image =  new ImageIcon("src/tilesSprite/wall.png");
            tile[2] = new Tiles();
            tile[2].image =  new ImageIcon("src/tilesSprite/water.png");
            tile[3] = new Tiles();
            tile[3].image =  new ImageIcon("src/tilesSprite/earth.png");
            tile[4] = new Tiles();
            tile[4].image =  new ImageIcon("src/tilesSprite/caffee1.png");
            tile[5] = new Tiles();
            tile[5].image =  new ImageIcon("src/tilesSprite/caffee2.png");
            tile[6] = new Tiles();
            tile[6].image =  new ImageIcon("src/tilesSprite/caffee3.png");
          
    }
    public void drawTiles(Graphics2D graphics2D){
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow)   {            
            
            int tileNumner = tileOreder[col][row];
          
             graphics2D.drawImage(tile[tileNumner].image.getImage(),x,y,gamePanel.tileSize,gamePanel.tileSize,null);
             col++;
              x += gamePanel.tileSize;
             if (col == gamePanel.maxScreenCol) {
                  col = 0;
                 x = 0;
                 row++;
                 y+= gamePanel.tileSize;
            
                }
    
        }
    }
}
