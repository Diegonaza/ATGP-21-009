/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Alisson, Diego
 * @comment Alisson, Diego
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
    
   BufferedImage[] sprites = new BufferedImage[6];
    CharacterState cState;
    myThread cdThread ;
    ColourType colour;
    
      enum Direction{
    Left(),Right();
    }
      //enum for indication of direction for character movement and animation sprite selection.
      
    Direction direction;
    boolean faceLeft,faceRight;
    
    BufferedImage characterSprite;
    Player player;
    int aTimer;

    public Citizen(int x, int y, GamePanel gp) {
         super(x, y);
         
        cState = cState.Walking;
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.xSpeed = 1;
        this.panel = gp;
        this.camPrevY = panel.cameraY;
        this.camPrevX = panel.cameraX;
        width = 32;
        height = 64;
        hitBox = new Rectangle(x,y,width,height);
        
        this.player = (Player) this.panel.handler.object.get(0);
        direction = direction.Right;
        colour = colour.red;
        
        
        ImportImage();
             
    }
    //instanciation of the constructor, to be used in GamePanel's instanciation on the game environment.
    
   

    @Override
    public void tick() {
        
        
       //Character States  
       
      if(xSpeed!=0){
          cState = cState.Walking;
          //when speed on axis X is not zero, this character will be in position walking
      }
      
      if(ySpeed != 0){
          cState = cState.Jumping;
      }// Jumping state overrides the walking state as it comes after it, 
       // When the speed on axis Y is different to Zero, the character will be in Jumping State
      
      if(xSpeed >0){
          direction = direction.Right;
          //This sets up the enum state where the character is facing, if positive speed, the right position is selected.
      }
      
      if(xSpeed <0){
          direction = direction.Left;
          //This sets up the enum state where the character is facing, if negative speed, the left position is selected.
      }
      
      
      //speed limit - smoothing
      if(xSpeed > 0 && xSpeed<0.75)xSpeed = 0;
      //defines the speed in X to be equal to zero if smaller to 0.75 in module.
      if(xSpeed<0 && xSpeed> -0.75)xSpeed =0;
      //defines the speed in X to be equal to zero if smaller to 0.75 in module.
      if(xSpeed > 1)xSpeed= 1;
      //defines the speed in X to be equal to one if bigger to one in module.
      if(xSpeed< -1)xSpeed = -1;
      //defines the speed in X to be equal to one if bigger to one in module.
      
      
      
      
      
      //jump    
      
      //gravity
        ySpeed += 0.5;
        //constantly the y Speed increases, simulating gravity.
        
          //vertical collision
          hitBox.y += ySpeed;
          for(Platform p: panel.handler.platforms){
              //for each platform in the handler object, collision will be checked.
            if(hitBox.intersects(p.hitBox)){
                hitBox.y -= ySpeed;
                
                while(!p.hitBox.intersects(hitBox)){
                    hitBox.y += Math.signum(ySpeed);
                }
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
            //when there is intersection between the character hitbox and a platform hitbox on axis Y, the speed on Y will be decreased until it reaches zero.
            // the hitbox will be repositioned to start from the platform hitbox axis Y; The gravity will keep creating the colisions and the character will be constantly positioned on the intersection point.
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
                xSpeed = xSpeed *(-1);
                x = hitBox.x;
            }
        }
        //when there is intersection between the character hitbox and a platform hitbox on axis X, the speed on X will be inverted
        
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
            //set all key input variables to false, finishig all actions leaving the obejct open for any event again;
            player.keyFire = false;
            player.keyJump = false;
            player.keyLeft = false;
            player.keyRight = false;
                
           
            
        }
        
        //============================================================================================
        
        //Updates Enemy X based on its Speed and ScreenScrolling
      
         
         if(camPrevX != panel.cameraX){
            //checks if the camera state is different, which would mean the camera have moved.
             x += (camPrevX-panel.cameraX);
             //changes the position relative to the camera position
             x += xSpeed;
             // add the normal movement change to it.
             hitBox.x = x;
             //moves the hitbox accordingly
             camPrevX = panel.cameraX;
             //sets the camera state again for the next tick event.
         }else{
             x+= xSpeed;
             hitBox.x = x;
             //if the camera haven't moved, the normal movement state happens normally.
         }
         
     
       
        // Character State Machine
        // this will check which state the character is , select the appropriated sprite sheet 
        switch(cState){
            case Walking:{
                sheetLenght = 6;
                break;
            }
            
            case Jumping:{
                sheetLenght = 2;
                break;
            }
                      
        }
        //this will be responsible to pass on information to the artefact to load the correct sprite accordignly.
        
       
       Animation();
       
       //Check Collision vs Projectiles
       for( int i = 0 ; i < panel.handler.projectiles.size(); i++  ){
           //checks every projectile existent in the handler to be checked for colision.
           if(panel.handler.projectiles.get(i).hitBox.intersects(this.hitBox) && colour!= colour.white){
               //if the hitbox of the citizen intersects with a projectile shot by the player
               
               
               //Event (damage)
               panel.handler.projectiles.remove(i);
               //removes the projectile from the game
               colour = colour.white;
               //changes the Citizen state to white.
               spriteSheetIndex = 1;
               //reset the spriteSheet for animation for white type sprite.
              
    }           
              }
           
       //for each zenith particle
            for(int i = 0 ; i < panel.handler.zenith.size(); i++  ){
           
                //if the hitBox intersects with the citizen hitbox AND the citizen colour is white
           if(panel.handler.zenith.get(i).hitBox.intersects(this.hitBox) && colour == colour.white){
               
               //Infection Event               
               //copy the zenith particle colour to the citizen
               colour = panel.handler.zenith.get(i).ct;
               //remove the zenith particle from the game
               panel.handler.zenith.remove(i);
               //import the new citizen image
               switch(colour){
                   case blue:{
                       spriteSheetIndex=0;
                       //selects the spriteSheet refferent to the colour blue
                       break;
                   }
                   case white:{
                       spriteSheetIndex=1;
                       //selects the spriteSheet refferent to the colour white
                       break;
                   }
                   case red:{
                       spriteSheetIndex=2;
                       //selects the spriteSheet refferent to the colour red
                       break;
                   }
                   case yellow:{
                       spriteSheetIndex=3;
                       //selects the spriteSheet refferent to the colour yellow
                       break;
                   }
                   case black:{
                       spriteSheetIndex=4;
                       //selects the spriteSheet refferent to the colour black
                       break;
                   }
                   case pink:{
                       spriteSheetIndex=5;
                       //selects the spriteSheet refferent to the colour pink
                       break;
                   }
               }
               
              }
       
           
       }
       
    }
        
    
    
    
    
    public void Roam(){
       
       xSpeed = xSpeed * -1;
    }
 

    @Override
    public void Draw(Graphics2D gtd) {
     /// This commented part is code to see the invisible hitbox for the Citizen
      // gtd.setColor(Color.red);
      // gtd.fillRect(x, y, width, height); // Hitbox size
       //gtd.fillRect(ledgeBox.x, ledgeBox.y, ledgeBox.width, ledgeBox.height);
       
       
       //these lines flips the image horizontally and adjust their x and y coordinates to match the object HitBox
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x-30, y-30,100,100,panel);
                  //                                    /\ /\ 
//                                            (Size parameters)
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x+65, y-30,-100,100,panel);
    }
            
    //Load all character sprite sheets into memory and saves them into an array.
     public void ImportImage(){
         
         InputStream is = null;
         try{
         for(int i = 0; i<=5; i++){
             switch(i){
             case 0:
              is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenBlue.png");
              sprites[i] = ImageIO.read(is);
              //loads the character sprites to a position in the array 
             break;
             case 1:
             is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenWhite.png");
             sprites[i] = ImageIO.read(is);
             //loads the character sprites to a position in the array
             break;
             case 2:
             is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenRed.png");
             sprites[i] = ImageIO.read(is);
             //loads the character sprites to a position in the array
             break;
             case 3:
             is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenGreen.png");
             sprites[i] = ImageIO.read(is);
             //loads the character sprites to a position in the array
             break;
             case 4:
             is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenBlack.png");
             sprites[i] = ImageIO.read(is);
             //loads the character sprites to a position in the array
             break;
             case 5:
             is = getClass().getResourceAsStream("/Images/CitizenNew/CitizenPink.png");
             sprites[i] = ImageIO.read(is);
             //loads the character sprites to a position in the array
             break;    
         }
            
         }
         }catch(Exception e){
                  e.printStackTrace();
                 }
       /*
        try{
         sprites[i] = ImageIO.read(is);
                  
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
     
    // this method get subImages from a sprite sheet based on the x and y input coordinates
     public void UpdateImage(BufferedImage image,int x, int y){
        characterSprite = image.getSubimage(x, y, 64, 64);
        
    }
     
     public void Animation(){
        //Animation play rate, increments this variable every tick, the IF statement is where the actual rate is set
        //in short words it makes the image transition faster.
        animPlayRate++;
        if(animPlayRate == 8){
            
           //Every x Ticks get the new sub image from the sprite sheet
            if(spriteIndex<=sheetLenght){
                UpdateImage(sprites[spriteSheetIndex],(spriteIndex* 64)-64,0);
                spriteIndex++;
                //choses the next sprite index 
                 
                
            }else{
                spriteIndex = 1;
                //resets the spriteIndex when the sheetLenght is reached.
            }
            animPlayRate = 0;
            //resets the animation play rate.
           }
     }
}
