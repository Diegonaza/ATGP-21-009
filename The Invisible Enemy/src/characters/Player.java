/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import characters.CharacterState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import engine.GameObject;
import engine.GamePanel;
import map.Platform;
import display.Window;
import engine.myThreadEventDeath;

/**
 *
 * @author diego
 */
public class Player extends GameObject {
    
    public GamePanel panel;
    public double maxWalkingSpeed;
    public boolean isInputEnable = true;
    public int health;
    public int fireRateCounter=0;
    public int fireRate;
    public int startX;
    public int locationX;
    public int locationY;
    public int animCounter= 0;
    public int spriteIndex = 1;
    public int animPlayRate = 0;
    public int spriteSheetIndex, sheetLenght;
    public boolean isFalling = true;
    public boolean canJump = true;
    public Rectangle hitBox;
    public CharacterState cState;
    enum Direction{
    Left(),Right();
    }
    Direction direction ;
    public boolean keyLeft,keyRight,keyJump,keyFire;
    
    protected BufferedImage[] sprites = new BufferedImage[10];
    protected BufferedImage characterSprite;
    protected BufferedImage healthBar;
    public Window window;
    public Projectile projectile;
    
    
    public Player(int x, int y, GamePanel panel){
        super(x,y);
        this.x = x;
        this.y = y;
        this.startX = x;
        this.locationX = x;
        this.panel = panel;
        width = 32;
        height = 64;
        this.health = 3;
        hitBox = new Rectangle(x,y,width,height);
        this.maxWalkingSpeed = 4;
        
        direction = direction.Right;
        
        ImportImage();
    }
    
    //tick  "update"
    @Override
    public void tick(){
        
     
        //check if player is falling
        if(ySpeed!=0 && ySpeed !=0.5)isFalling = true;
        
      //Update Character State
      //Dead State when player heath is 0
      if(health==0&& cState!= cState.Dead){
          cState= cState.Dead;
      }
      
       //Shooting State
        //State used for animation and trigger the instanciation of projectiles, when not moving on X axis and in colision with a platform vertically.
       if(keyFire==true&&xSpeed==0&&cState!=cState.Shooting&&cState!=cState.Jumping && isFalling == false && cState != cState.Staggered && cState!=cState.Dead ){
          cState = cState.Shooting;
           spriteIndex = 1;
      }
       //Walking / Walking&Shooting State
        //state for movement over a platform
      if(xSpeed!=0 && isFalling == false ){
          //conditions for the transition between states
          if (keyFire== false && cState!=cState.Walking && isFalling == false && cState != cState.Staggered && cState!=cState.Dead ){
              //transition between states
              cState = cState.Walking;
              //set the animation to the first frame
              spriteIndex = 1;
          }
          //conditions for the transition between states
          else if(keyFire==true&&cState!=cState.RunFiring && cState == cState.Walking && cState != cState.Staggered && cState!=cState.Dead || 
                  keyFire==true&&cState!=cState.RunFiring && cState == cState.Shooting && cState != cState.Staggered && cState!=cState.Dead ||
                  keyFire==true&&cState!=cState.RunFiring && cState == cState.JumpingShooting && cState != cState.Staggered&& cState!=cState.Dead ){
           //transition between states
             cState = cState.RunFiring;
              spriteIndex = 1;
          }
          
      }
      
      // Idle State
      //state without movement
      //conditions for the transition between states
      if(isFalling == false && keyFire!=true && cState!= cState.Idle && xSpeed == 0 && cState != cState.Staggered && cState!=cState.Dead ){
         //transition between states
          cState = cState.Idle;
         //set the animation to the first frame
          spriteIndex = 1;
          
      }
      
      
      //Jump 
      //state no colision with platforms, applicable for when moving on X axis or not.
      //conditions for the transition between states
      if(isFalling == true && cState != cState.Jumping && keyFire == false && cState != cState.Staggered && cState!=cState.Dead  ){
          //transition between states
              cState = cState.Jumping;
          //set the animation to the first frame
              spriteIndex = 1;
          
      }
      
      // Jumping&Shooting State
      //state with no colision with platforms, applicable when moving on X axis or not but the character is also in shooting state.
      //conditions for the transition between states
      if(cState == cState.Jumping && keyFire == true && cState!=cState.JumpingShooting && isFalling == true && cState != cState.Staggered && cState!=cState.Dead || 
         cState == cState.Shooting  && cState!= cState.JumpingShooting && isFalling == true && cState != cState.Staggered && cState!=cState.Dead ||
         cState == cState.RunFiring && cState!= cState.JumpingShooting && isFalling == true && cState != cState.Staggered && cState!=cState.Dead ||
         cState == cState.Walking && cState!= cState.JumpingShooting && isFalling == true && cState != cState.Staggered && cState!=cState.Dead ){
           //transition between states
          cState = cState.JumpingShooting;
          //set the animation to the first frame
          spriteIndex = 1;
      }
      
     
      
       //Reset The variable the controls the fireRate
        if(cState!=cState.Shooting && cState!=cState.RunFiring && cState!= cState.JumpingShooting&& cState!=cState.Dead ){
            fireRateCounter=10;
        }
      
      
      //Update Player Movement
      //KeyInput conditions
      if(keyLeft && keyRight || !keyLeft && !keyRight) xSpeed *= 0.8;
      else if(keyLeft && !keyRight){
          xSpeed --;
          
          
          direction = direction.Left;
          
      }
      //KeyInput conditions
      else if(keyRight && !keyLeft){
          xSpeed ++;
          direction = direction.Right;
      }
      
      //speed limit/smoothing
      if(xSpeed > 0 && xSpeed<0.75)xSpeed = 0;
       //defines the speed in X to be equal to zero if smaller to 0.75 in module.
      if(xSpeed<0 && xSpeed> -0.75)xSpeed =0;
      //defines the speed in X to be equal to zero if smaller to 0.75 in module.
      if(xSpeed > maxWalkingSpeed)xSpeed= maxWalkingSpeed;
      //defines the speed in X to be equal to one if bigger to one in module.
      if(xSpeed<-maxWalkingSpeed)xSpeed = -maxWalkingSpeed;
      //defines the speed in X to be equal to one if bigger to one in module.
      
      
      
      //jump Logic
      //conditions
      if(keyJump && canJump){
          hitBox.y++;
          canJump = false;
          //collision check
          for(int i = 0; i<panel.handler.platforms.size(); i++){
              if(panel.handler.platforms.get(i).hitBox.intersects(hitBox))ySpeed = -12;
          }
          
         hitBox.y--;       
      }
      
      //gravity
        ySpeed += 0.5;
        
        
        //vertical collision
          hitBox.y += ySpeed;
        for(Platform p: panel.handler.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.y -= ySpeed;
                while(!p.hitBox.intersects(hitBox)) hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                if(Math.signum(ySpeed)>0){
                    isFalling = false;
                }else{
                    isFalling = true;
                }
                ySpeed = 0;
                y = hitBox.y;
            }
        }
        
        
        //horizontal collision
        hitBox.x += xSpeed;
        for(Platform p: panel.handler.platforms){
            if(hitBox.intersects(p.hitBox)){
                hitBox.x -= xSpeed;
               while(!p.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitBox.x;
            }
        }
        
        
        
        
        
        
           
          
        
       //Moves the camera based on the offSet in the X vector in this case 200 to the left and 400 to the right
       //So if the player X position is not within this offset the camera will  move or else we move the player instead of the camera
       //The Camera X value is stored in the GamePanel class, which will pass it as a parameter to a method in the Handler class 
       //this method updates the Objects location based on the camera movement// the method -->     handler.UpdatePlatforms();
        if(x<=200&&xSpeed<=0 || x>=400&&xSpeed>=0){
            panel.cameraX+=xSpeed;
            locationX+=xSpeed;
            hitBox.x =x;
            
        }else{
           x += xSpeed;
           hitBox.x = x;
        }
           
          // panel.cameraY+= ySpeed;
            
         y +=ySpeed;
         hitBox.y = y; 
      
           
         
       
        
        
        //updateLocation has the same functionality as the updatePlatforms method, but it is called for the background only
        panel.l.updateLocation(panel.cameraX);
        panel.handler.updatePlatforms(panel.cameraX, panel.cameraY);
        
        
        // Character State Machine
        //This will select which sprite sheet to use depending on which state the character is
        //and set the variables accordingly so the animation works correctly 
        switch(cState){
            case Dead:{
                 //Sprite Sheet to use
                spriteSheetIndex=4;
                //How many Frames we have in this animation
                sheetLenght = 3;
                //How fast should we play this animation
                animPlayRate=8;
                //Call animation Method
                Animation();
                //Death Event
                myThreadEventDeath myDeath = new myThreadEventDeath(this);
                
                break;
            }
            
            case Walking:{
                //Sprite Sheet to use
                spriteSheetIndex=2;
                //How many Frames we have in this animation
                sheetLenght = 8;
                //How fast should we play this animation
                animPlayRate=4;
                //Call animation Method
                Animation();
                break;
            }
            
            case Idle:{
                //Sprite Sheet to use
                spriteSheetIndex=0;
                //How many Frames we have in this animation
                sheetLenght = 3;
                //How fast should we play this animation
                animPlayRate=8;
                //Call animation Method
                Animation();
                break;
            }
            
             case Staggered:{
                //Sprite Sheet to use
                spriteSheetIndex=1;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=8;
                //Call animation Method
                Animation();
                break;
            }
            
            case Jumping:{
                //Sprite Sheet to use
                spriteSheetIndex =3;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=6;
                //Call animation Method
                Animation();
                break;
            }
            case JumpingShooting:{
                //Sprite Sheet to use
                spriteSheetIndex =7;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=6;
                //Call Shoot Method
                Shoot(10);
                //Call animation Method
                Animation();
                break;
            }
            
            
            case Shooting:{
                //Sprite Sheet to use
                spriteSheetIndex = 5;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=8;
                //Call Shoot Method
                Shoot(10);
                //Call animation Method
                Animation();
                
                break;
            }
             case RunFiring:{
                //Sprite Sheet to use 
                spriteSheetIndex = 6;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=4;
                //Call Shoot Method
                Shoot(10);
                //Call animation Method
                Animation();
                break;
            }
        }
          
    }
    
    //Spawn projectile
    public void Shoot(int fr){
        this.fireRate = fr;
        //FireRate determines how fast the character can shoot projectiles
        //FireRateCounter increases every tick
        if(fireRateCounter==fireRate){
            //projectile direction ( right )
            if(direction == direction.Right){
                 projectile = new Projectile(x+50,y+25,1,panel.handler);
                panel.handler.projectiles.add(projectile);
                fireRateCounter=0;
            }else{
                //projectile direction ( left )
                projectile = new Projectile(x-50,y+25,-1,panel.handler);
                panel.handler.projectiles.add(projectile);
                fireRateCounter=0;
                
            }
            
         
        }else{
            fireRateCounter++;
           
        }
    }
    
    @Override
    public void Draw(Graphics2D gtd){
      //Show Player character hitbox for debug purpose only
        gtd.setColor(Color.red);
      // gtd.fillRect(x, y, width, height);
        //Flips the image based on the direction the character is facing
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x-15, y-30,100,100,panel);
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x+45, y-30,-100,100,panel);
        
        //Draw Player's Health bar
      if(health >0)gtd.drawImage(healthBar.getSubimage(0, 0, (64*health), 64), 20,30,64*health,64, panel);
       
        
       
    }
    
    //Responds to Player Input
     public void KeyPressed(KeyEvent e){
       if(isInputEnable == true){
           int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) {
            keyLeft = true;
            
        }
        if(key == KeyEvent.VK_D){
            keyRight = true;
            
        }
        if(key ==KeyEvent.VK_SPACE){
            keyJump = true;
            
        }
        if(key ==KeyEvent.VK_F){
            keyFire = true;
            
       }
        
            
        }
     
    }
    
    public void KeyReleased(KeyEvent e){
       if(isInputEnable){
           int key = e.getKeyCode();
        if(key ==KeyEvent.VK_A) keyLeft = false;
        if(key ==KeyEvent.VK_D) keyRight = false;
        if(key ==KeyEvent.VK_F)keyFire = false;
        if(key ==KeyEvent.VK_SPACE){
            keyJump = false;
            canJump = true;
        }
       }
        
        
    }
    
    //Load all sprite Sheets into memory
    public void ImportImage(){
        
        InputStream is = getClass().getResourceAsStream("/Images/Character/BergIdle.png");
        InputStream is1 = getClass().getResourceAsStream("/Images/Character/BergHurt.png");
        InputStream is2 = getClass().getResourceAsStream("/Images/Character/BergWalk.png");
        InputStream is3 = getClass().getResourceAsStream("/Images/Character/BergJump.png");
        InputStream is4 = getClass().getResourceAsStream("/Images/Character/BergDeath2.png");
        InputStream is5 = getClass().getResourceAsStream("/Images/Character/BergShooting.png");
        InputStream is6 = getClass().getResourceAsStream("/Images/Character/BergWalkShoot.png");
        InputStream is7 = getClass().getResourceAsStream("/Images/Character/BergJumpShoot.png");
        InputStream is8 = getClass().getResourceAsStream("/Images/Character/HealthBar.png");
        try{
         sprites[0] = ImageIO.read(is);
         sprites[1] = ImageIO.read(is1);
         sprites[2] = ImageIO.read(is2);
         sprites[3] = ImageIO.read(is3);
         sprites[4] = ImageIO.read(is4);
         sprites[5] = ImageIO.read(is5);
         sprites[6] = ImageIO.read(is6);
         sprites[7] = ImageIO.read(is7);
         healthBar = ImageIO.read(is8);
         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Get a sub image from the sprite sheet
     public void updateImage(BufferedImage image,int x, int y){
        
        characterSprite = image.getSubimage(x, y, 64, 64);
        
    }
     
     
     
     public void Animation(){
         //every update increase this counter
           animCounter++;
           
          if(animCounter>animPlayRate){
            
        animCounter=0;
        }
          //if the counter is equal to the desired play rate, go to the next frame
          //the higher the play rate value the longer it will take to go to the next frame
        if(animCounter==animPlayRate){
           //if we are not in the last frame go to the next
            if(spriteIndex<=sheetLenght){
                //get a sub image from the sprite sheet the spriteIndex variable represents 
                //the current frame, 48 is the size of the sub image,which should be saved in a variable in the future (e.g int spriteSize)
                updateImage(sprites[spriteSheetIndex],(spriteIndex* 64)-64,0);
                spriteIndex++;
                 
                
            }else{
                //if is the last frame go back to the first frame
                spriteIndex = 1;
            }
            //resets the counter
            animCounter = 0;
        }
        
         
     }
}
