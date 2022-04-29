/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author diego
 */
public class Citizen extends GameObject {
    
    GamePanel panel;
    double movementSpeed;
   
    int startX;
    int startY;
    int camPrevY;
    int camPrevX;
    int spriteIndex = 1;
    int animPlayRate = 0;
    int spriteSheetIndex, sheetLenght;
    Rectangle hitBox;
    Rectangle ledgeBox;
    CharacterState cState;

    public Citizen(int x, int y, GamePanel gp, ID id, String type) {
         super(x, y,id);
         
        this.x = x;
        this.y = y;
        this.id = id;
        this.startX = x;
        this.startY = y;
        this.panel = gp;
        this.camPrevY = panel.cameraY;
        this.camPrevX = panel.cameraX;
        width = 16;
        height = 50;
        hitBox = new Rectangle(x,y,width,height);
        ledgeBox = new Rectangle(x,y,25,60);
        this.player = (Player) this.panel.handler.object.get(0);
        
        direction = direction.Left;
        
        ImportImage(type);
        SetSpeedX(-1);      
    }
    
    
    enum Direction{
    Left(),Right();
    }
    Direction direction ;
    boolean keyLeft,keyRight,keyJump;
    
    BufferedImage[] sprites = new BufferedImage[5];
    BufferedImage characterSprite;
    Player player;
    int aTimer;

     
   

    @Override
    public void tick() {
       //Character States  
      if(xSpeed!=0){
          cState = cState.Walking;
      }
      
      if(xSpeed==0 && ySpeed <=0.5){
          cState = cState.Idle;
      }
      
      if(ySpeed>0.5 || ySpeed <0)cState = cState.Jumping;
      
      
      //Player Movement
    xSpeed = 0 ;
      
      //speed limit/smoothing
      if(xSpeed > 0 && xSpeed<0.75)xSpeed = 0;
      if(xSpeed<0 && xSpeed> -0.75)xSpeed =0;
      if(xSpeed > 2)xSpeed= 2;
      if(xSpeed< -2)xSpeed = -2;
      
      //jump
    
      
      //gravity
        ySpeed += 0.5;
        
          //vertical collision
          hitBox.y += ySpeed;
        for(Platform p: panel.handler.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.y -= ySpeed;
                while(!p.hitBox.intersects(hitBox)){
                    hitBox.y += Math.signum(ySpeed);
                }
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
        
        //horizontal collision
        hitBox.x += xSpeed;
        
        for(Platform p: panel.handler.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.x -= xSpeed;
               while(!p.hitBox.intersects(hitBox)){
                   hitBox.x += Math.signum(xSpeed);
               }
                hitBox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitBox.x;
            }
        }
        
        y += ySpeed;
        x += xSpeed;
        hitBox.x = x;
        hitBox.y = y;
        
        //Updates Enemy X and Y based on its Speed and ScreenScrolling
         if(camPrevY != panel.cameraY){
             
             y += (camPrevY-panel.cameraY);
             y+= ySpeed;
             hitBox.y = y;
             camPrevY = panel.cameraY;
         }else{
             y+= ySpeed;
             hitBox.y = y;
         }
         
         if(camPrevX != panel.cameraX){
            
             x += (camPrevX-panel.cameraX);
             x += xSpeed;
             hitBox.x = x;
             camPrevX = panel.cameraX;
         }else{
             x+= xSpeed;
             hitBox.x = x;
         }
         
         
         //CollisionBox to verify that enemy is close to a ledge
         if(direction == direction.Right){
             ledgeBox.x = x+70;
         }else{
             ledgeBox.x = x-45;
         }
         
              ledgeBox.y = y+50;
       
       
       
        // Character State Machine
        // this will check which state the character is , select the appropriated sprite sheet 
        switch(cState){
            case Walking:{
            
                spriteSheetIndex=1;
                sheetLenght = 1;
                break;
            }
            
            case Idle:{
            
                spriteSheetIndex=0;
                sheetLenght = 2;
                break;
            }
                      
        }
        
        /*
        //Follow the player
        if(hitBox.x>=player.hitBox.x +10&& movementSpeed>0){
            x +=5;
            SetSpeedX(movementSpeed*-1);
        }else if (hitBox.x <= player.hitBox.x-10 && movementSpeed<0){
            x -= 5;
            SetSpeedX(movementSpeed*-1);
        }d
       */
        ledgeBox.height = 30;
       
       Animation();
        
        
    }
    
    
    
    public void Roam(){
       
    }
    
    public void SetSpeedX(double speed){
        movementSpeed =  speed;
        if(movementSpeed>0)direction = direction.Right;
           else if(movementSpeed<0)direction = direction.Left;
    }

    @Override
    public void Draw(Graphics2D gtd) {
     /// This rectangle represents the Object HitBox
        gtd.setColor(Color.red);
       gtd.fillRect(x, y, width, height); // Hitbox size
       gtd.fillRect(ledgeBox.x, ledgeBox.y, ledgeBox.width, ledgeBox.height);
       //these lines flips the image horizontally and adjust their x and y coordinates to match the object HitBox
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x-30, y + 2 ,50,50,panel);
                //                                    /\ /\ 
//                                            (Size parameters, change both
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x+30, y + 2 ,-50,50,panel);
    }
            
    //Load all character sprite sheets into memory and saves them into an array.
     public void ImportImage(String type){
         
         InputStream is = null;
         
         switch(type){
             case "blue":
              is = getClass().getResourceAsStream("/Images/Citizen/blue/blue_animation.png");
             break;
             case "white":
             is = getClass().getResourceAsStream("/Images/Citizen/white/white_animation.png");
             break;
             case "red":
             is = getClass().getResourceAsStream("/Images/Citizen/red/red_animation.png");
             break;
             case "yellow":
             is = getClass().getResourceAsStream("/Images/Citizen/yellow/yellow_animation.png");
             break;
             case "black":
             is = getClass().getResourceAsStream("/Images/Citizen/black/black_animation.png");
             break;
             case "pink":
             is = getClass().getResourceAsStream("/Images/Citizen/pink/pink_animation.png");
             break;    
         }
         
        
       
        try{
         sprites[0] = ImageIO.read(is);
                  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // this method get subImages from a sprite sheet based on the x and y input coordinates
     public void UpdateImage(BufferedImage image,int x, int y){
        characterSprite = image.getSubimage(x, y, 16, 16);
        
    }
     
     public void Animation(){
        //Animation play rate, increments this variable every tick, the IF statement is where the actual rate is set
        //in short words it makes the image transition faster.
        animPlayRate++;
        if(animPlayRate==8){
            
           //Every x Ticks get the new sub image from the sprite sheet
            if(spriteIndex<sheetLenght){
                UpdateImage(sprites[spriteSheetIndex],(spriteIndex* 16)-16,0);
                spriteIndex++;
                 
                
            }else{
                spriteIndex = 1;
            }
            animPlayRate = 0;
           }
     }
}