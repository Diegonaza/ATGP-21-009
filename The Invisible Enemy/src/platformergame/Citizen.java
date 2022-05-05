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
    double movementSpeed = 0;
   
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
    myThread cdThread ;
    ColourType colour;

    public Citizen(int x, int y, GamePanel gp) {
         super(x, y);
         
        cState = cState.Walking;
        this.x = x;
        this.y = y;
        this.id = id;
        this.startX = x;
        this.startY = y;
        this.xSpeed = 1;
        this.panel = gp;
        this.camPrevY = panel.cameraY;
        this.camPrevX = panel.cameraX;
        width = 16;
        height = 80;
        hitBox = new Rectangle(x,y,width,height);
        ledgeBox = new Rectangle(x,y,25,60);
        this.player = (Player) this.panel.handler.object.get(0);
        
        direction = direction.Right;
        colour = colour.red;
        cdThread = new myThread(player);
        
        ImportImage();
        //SetSpeedX(-1);      
    }
    
    
    enum Direction{
    Left(),Right();
    }
    Direction direction ;
    boolean faceLeft,faceRight;
    
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
      
      if(ySpeed != 0){
          cState = cState.Jumping;
      }// Jumping state overrides the walking state.
      
      if(xSpeed >0){
          direction = direction.Right;
      }
      
      if(xSpeed <0){
          direction = direction.Left;
      }
      /*
      //Citizen Movement
    
    if( x > (startX +500 - panel.cameraX)){
        Roam();
        direction = this.direction.Right;
    }
    if( x <(startX -500 - panel.cameraX)){
        Roam();
        direction = this.direction.Left;
    }
    */
     //Follow the player
     
     /*
        if(this.colour != this.colour.white)// If the citizen is not infected
        if(hitBox.x>=player.hitBox.x +10&& movementSpeed >= 0){
            x +=5;
            SetSpeedX(movementSpeed*-1);
        }else if (hitBox.x <= player.hitBox.x-10 && movementSpeed<0){
            x -= 5;
            SetSpeedX(movementSpeed*-1);
        }
       */
        
    
      
      //speed limit/smoothing
      if(xSpeed > 0 && xSpeed<0.75)xSpeed = 0;
      if(xSpeed<0 && xSpeed> -0.75)xSpeed =0;
      if(xSpeed > 1)xSpeed= 1;
      if(xSpeed< -1)xSpeed = -1;
      
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
                //xSpeed = 0;
                xSpeed = xSpeed *(-1);
                x = hitBox.x;
            }
        }
        
        y += ySpeed;
        hitBox.y = y;
        
      //Cause Damage to Player=====================================================================
      
        if(hitBox.intersects(player.hitBox)&& player.cState!= cState.Staggered && this.colour != colour.white){
           //Set Player State to Staggered
            player.cState = cState.Staggered;
            player.spriteIndex= 1;
            player.health = player.health-1;
            //Stop Player input
            player.isInputEnable = false;
            //Create a new thread to handle the event
            cdThread = new myThread(player);
            //How much force we apply to the player on the X vector
            player.maxWalkingSpeed =25;
            //How much force we apply to the player on the Y vector
            player.ySpeed = -5;
            //Which direction to apply the push force
            if(player.x >= x){
                player.xSpeed = player.maxWalkingSpeed;
            }else {
                player.xSpeed = -player.maxWalkingSpeed;;
            }
            //As we are Disabling the player input for both cases ( Press and Release )
            //set all key input variables to false;
            player.keyFire = false;
            player.keyJump = false;
            player.keyLeft = false;
            player.keyRight = false;
                
           
            
        }
        
        //============================================================================================
        
        //Updates Enemy X based on its Speed and ScreenScrolling
      
         
         if(camPrevX != panel.cameraX){
            
             x += (camPrevX-panel.cameraX);
             x += xSpeed;
             hitBox.x = x;
             camPrevX = panel.cameraX;
         }else{
             x+= xSpeed;
             hitBox.x = x;
         }
         
     
       
       
        // Character State Machine
        // this will check which state the character is , select the appropriated sprite sheet 
        switch(cState){
            case Walking:{
            
                spriteSheetIndex = 0;
                sheetLenght = 2;
                break;
            }
            
            case Jumping:{
            
                spriteSheetIndex=0;
                sheetLenght = 2;
                break;
            }
                      
        }
        
        
       
       Animation();
       
       
       for( int i = 0 ; i < panel.handler.projectiles.size(); i++  ){
           
           if(panel.handler.projectiles.get(i).hitBox.intersects(this.hitBox)){
               
               //Event ( dano)
               
               
               panel.handler.projectiles.remove(i);
               colour = colour.white;
               xSpeed ++;
               ImportImage();
    }           
              }
           
       //for each zenith particle
            for(int i = 0 ; i < panel.handler.zenith.size(); i++  ){
           
                //if the hitBox intersects with the citizen hitbox AND the citizen colour is white
           if(panel.handler.zenith.get(i).hitBox.intersects(this.hitBox) && colour == colour.white){
               
               //Infection Event               
               //copy the zenith particle colour to the citizen
               colour = panel.handler.zenith.get(i).ct;
               //remove the zenith particle from the array
               panel.handler.zenith.remove(i);
               //import the new citizen image
               ImportImage();
               
              }
       
           
       }
       
    }
        
    
    
    
    
    public void Roam(){
       
       xSpeed = xSpeed * -1;
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
       //gtd.fillRect(ledgeBox.x, ledgeBox.y, ledgeBox.width, ledgeBox.height); // Other hitbox Approach
       //these lines flips the image horizontally and adjust their x and y coordinates to match the object HitBox
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x-30, y + 5 ,75,75,panel);
                  //                                    /\ /\ 
//                                            (Size parameters, change both
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x+50, y + 5 ,-75,75,panel);
    }
            
    //Load all character sprite sheets into memory and saves them into an array.
     public void ImportImage(){
         
         InputStream is = null;
         
         switch(colour){
             case blue:
              is = getClass().getResourceAsStream("/Images/Citizen/Blue/blue_animation.png");
             break;
             case white:
             is = getClass().getResourceAsStream("/Images/Citizen/white/white_animation.png");
             break;
             case red:
             is = getClass().getResourceAsStream("/Images/Citizen/Red/red_animation.png");
             break;
             case yellow:
             is = getClass().getResourceAsStream("/Images/Citizen/Yellow/yellow_animation.png");
             break;
             case black:
             is = getClass().getResourceAsStream("/Images/Citizen/Black/black_animation.png");
             break;
             case pink:
             is = getClass().getResourceAsStream("/Images/Citizen/Pink/pink_animation.png");
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
        if(animPlayRate == 8){
            
           //Every x Ticks get the new sub image from the sprite sheet
            if(spriteIndex<=sheetLenght){
                UpdateImage(sprites[spriteSheetIndex],(spriteIndex* 16)-16,0);
                spriteIndex++;
                 
                
            }else{
                spriteIndex = 1;
            }
            animPlayRate = 0;
           }
     }
}
