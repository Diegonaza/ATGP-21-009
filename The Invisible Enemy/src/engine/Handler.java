/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import characters.Player;
import characters.Zenith;
import characters.Citizen;
import java.awt.Graphics2D;
import java.util.LinkedList;
import map.Platform;
import characters.Projectile;
import map.TileMapper;

/**
 *
 * @author diego
 * This Class will Store all the objects in the game, it will save into lists for better performance
 * it will also call the tick ("Update") and render("Draw") method for each object and the frequency that these methods are called
 * is controlled by the game loop (run method ) in the GamePanel class.
 */
public class Handler {
    //It was initially design to store all objects into a single list, now its only saving the player
    // it will be update in the future for a single variable holding a reference to the player
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    //Stores all the zenith particles.
    public LinkedList<Zenith> zenith = new LinkedList<Zenith>();
    //Stores all the platforms that has collision checks on their tick() methods
    public LinkedList<Platform> platforms = new LinkedList<Platform>();
    //Stores all tiles that are only for visual purpose 
    public LinkedList<Platform> backgroundTiles = new LinkedList<Platform>();
    //Stores all Projectiles from the player Character
    public LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    //Stores all enemies in the level
    public LinkedList<Citizen> enemies = new LinkedList<Citizen>();
    //Reference to the TileMapper Class
    public TileMapper tl ;
    
    //Update all Objects in the level
    public void tick(){
        for (int i = 0; i<object.size(); i++){
            object.get(i).tick();
            //reads each object in the linkedList
        }
        
        for(int i = 0; i<projectiles.size(); i++){
             projectiles.get(i).tick();
             //reads each projectile in the linkedList
        }
        
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).tick();
            //reads each enemy in the linkedList
        }
        
        for(int i = 0; i<platforms.size(); i++){
            platforms.get(i).tick(this);
            //reads each platform in the linkedList
        }
        
        for(int i = 0; i<zenith.size(); i++){
            zenith.get(i).tick();
            //reads each zenith in the linkedList
        }
      
    }
    
    
    //This method will scroll the tiles according to the "camera" movement
    //This method is called from the Player class when there is a movement in the camera
    //it will call a method in the Platform class to correct the position of these objects
    public void updatePlatforms(int camX, int camY){
         for(int i = 0; i<platforms.size(); i++){
           //  System.out.println(i);
             platforms.get(i).setLocation(camX,camY);
         }
         
         for(int i = 0; i<backgroundTiles.size();i++){
             backgroundTiles.get(i).setLocation(camX,camY);
         }
         
    }
    
   
    //Draw objects on the screen, this is also controlled by the game loop in the GamePanel class
    public void render(Graphics2D g){
        //reference to the player character which will be used to calculate the offset where the objects should be rendered on the screen
        Player p = (Player)object.get(0);
        
        for(int i = 0; i<backgroundTiles.size(); i++){
            
            
           //Render Objects that are within the offSet , the offset is calculated based on the X position of the Player character on the screen
            if(backgroundTiles.get(i).startX <= (p.locationX )&& backgroundTiles.get(i).startX>= p.locationX-300|| backgroundTiles.get(i).startX >= p.locationX && backgroundTiles.get(i).startX <= p.locationX+1285){
                backgroundTiles.get(i).Draw(g);
            }
        //draws the backgroundTiles objects     
            
            
        }
       
         //Render Objects that are within the offSet , the offset is calculated based on the X position of the Player character on the screen
        for (int i = 0; i<platforms.size(); i++){
          if(platforms.get(i).startX <= (p.locationX )&& platforms.get(i).startX>= p.locationX-300|| platforms.get(i).startX >= p.locationX && platforms.get(i).startX <= p.locationX+1285)
            platforms.get(i).Draw(g);
        }
        //draws the platform objects 
        
    
        
        for(int i = 0; i<projectiles.size(); i++){
             projectiles.get(i).Draw(g);
             //draws the projectiles objects 
            
        }
        
          for(int i = 0; i<zenith.size(); i++){
             zenith.get(i).Draw(g);
            //draws the zenith objects 
        }
        
                    
       for(int i = 0; i<enemies.size(); i++){
           enemies.get(i).Draw(g);
           //draws the enemy objects 
       }
        
        for(int i= 0; i<object.size(); i++){
            object.get(i).Draw(g);
            //draws the other objects 
        }
        
    }
    public void addPlatform(Platform platform){
        this.platforms.add(platform);
    }
    //adds a platform into the Platform Linked list
    
    public void addTile(Platform p){
        this.backgroundTiles.add(p);
    }
    //adds a tile into the tile Linked list
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    //adds a object into the Object Linked list
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    //remove a object from the gameObject linked list
    public void setMapper(TileMapper mapper){
        tl = mapper;
    }
    //sets the mapper variable.
}
