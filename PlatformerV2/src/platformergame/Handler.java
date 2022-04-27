/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Graphics2D;
import java.util.LinkedList;
import zMapEditor.TileMapper;

/**
 *
 * @author diego
 */
public class Handler {
    
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<Platform> platforms = new LinkedList<Platform>();
    LinkedList<Platform> backgroundTiles = new LinkedList<Platform>();
    LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    TileMapper tl ;
    public void tick(){
        for (int i = 0; i<object.size(); i++){
          
            object.get(i).tick();
        }
        
        for(int i = 0; i<projectiles.size(); i++){
               
             projectiles.get(i).tick();
              
        }
        
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).tick();
        }
        
        for(int i = 0; i<platforms.size(); i++){
            platforms.get(i).tick(this);
            
        }
        
        
      
    }
    
    
    
    public void updatePlatforms(int camX, int camY){
         for(int i = 0; i<platforms.size(); i++){
           //  System.out.println(i);
             platforms.get(i).setLocation(camX,camY);
         }
         
         for(int i = 0; i<backgroundTiles.size();i++){
             backgroundTiles.get(i).setLocation(camX,camY);
         }
         
    }
    
   
    
    public void render(Graphics2D g){
        Player p = (Player)object.get(0);
        
        for(int i = 0; i<backgroundTiles.size(); i++){
            
            
           
            if(backgroundTiles.get(i).startX <= (p.locationX )&& backgroundTiles.get(i).startX>= p.locationX-300|| backgroundTiles.get(i).startX >= p.locationX && backgroundTiles.get(i).startX <= p.locationX+700){
                backgroundTiles.get(i).Draw(g);
            }
            
            
            
        }
       
         
        for (int i = 0; i<platforms.size(); i++){
          if(platforms.get(i).startX <= (p.locationX )&& platforms.get(i).startX>= p.locationX-300|| platforms.get(i).startX >= p.locationX && platforms.get(i).startX <= p.locationX+700)
            platforms.get(i).Draw(g);
        }
        
        tl.Draw(g);
        
        for(int i = 0; i<projectiles.size(); i++){
                
             projectiles.get(i).Draw(g);
            
        }
        
             for(int i= 0; i<object.size(); i++){
            
            object.get(i).Draw(g);
        }
        
       for(int i = 0; i<enemies.size(); i++){
           enemies.get(i).Draw(g);
       }
        
        
    }
    public void addPlatform(Platform platform){
        this.platforms.add(platform);
    }
    
    public void addTile(Platform p){
        this.backgroundTiles.add(p);
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    public void setMapper(TileMapper mapper){
        tl = mapper;
    }
}
