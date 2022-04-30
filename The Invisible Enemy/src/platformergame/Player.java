/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author diego
 */
public class Player extends GameObject {
    GamePanel panel;
    int fireRateCounter=0;
    int fireRate;
    int startX;
    int locationX;
    int locationY;
    int animCounter= 0;
    int spriteIndex = 1;
    int animPlayRate = 0;
    int spriteSheetIndex, sheetLenght;
    Rectangle hitBox;
    CharacterState cState;
    enum Direction{
    Left(),Right();
    }
    Direction direction ;
    boolean keyLeft,keyRight,keyJump,keyFire;
    
    BufferedImage[] sprites = new BufferedImage[10];
    BufferedImage characterSprite;
    
    Projectile projectile;
    
    
    public Player(int x, int y, GamePanel panel){
        super(x,y);
        this.x = x;
        this.y = y;
        this.startX = x;
        this.locationX = x;
        this.panel = panel;
        width = 20;
        height = 20;
        hitBox = new Rectangle(x,y,width,height);
        
        
        direction = direction.Right;
        
        ImportImage();
    }
    
    //tick  "update"
    @Override
    public void tick(){
       
      //Update Character State
      if(keyFire==true&&xSpeed==0){
          cState = cState.Shooting;
      }
       
       
      if(xSpeed!=0 && ySpeed==0 ){
          if(cState == cState.RunFiring && spriteIndex%2 !=0){
              cState = cState.RunFiring;
          }else{
              cState = cState.Walking;
          }
          
      }
      
      if(xSpeed==0 && ySpeed <=0.5 && keyFire!=true){
          if(cState == cState.Shooting && spriteIndex!=2){
              cState = cState.Shooting;
              
          }else{
              cState = cState.Idle;
          }
          
      }
      
      if(ySpeed>0.5 || ySpeed <0){
          if(cState == cState.Shooting && spriteIndex!=2){
              cState = cState.Shooting;
          }else{
              cState = cState.Jumping;
          }
      }
      
      if(cState == cState.Jumping && keyFire == true){
          cState = cState.Shooting;
      }
      
     
      if(keyFire==true&&cState==cState.Walking){
          cState = cState.RunFiring;
      }
      
       //Reset The variable the controls the fireRate
        if(cState!=cState.Shooting && cState!=cState.RunFiring){
            fireRateCounter=10;
        }
      
      
      //Update Player Movement
      if(keyLeft && keyRight || !keyLeft && !keyRight) xSpeed *= 0.8;
      else if(keyLeft && !keyRight){
          xSpeed --;
          
          
          direction = direction.Left;
          
      }
      
      else if(keyRight && !keyLeft){
          xSpeed ++;
          direction = direction.Right;
      }
      
      //speed limit/smoothing
      if(xSpeed > 0 && xSpeed<0.75)xSpeed = 0;
      if(xSpeed<0 && xSpeed> -0.75)xSpeed =0;
      if(xSpeed > 5)xSpeed = 5;
      if(xSpeed<-5)xSpeed = -5;
      
      
      //jump
      if(keyJump){
          hitBox.y++;
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
            
         //  y +=ySpeed;
         //hitBox.y = y; 

         
//         if(y<=200&&ySpeed<=0 || y>=300&&ySpeed>=0){
//            panel.cameraY+=ySpeed;
//            locationY+=ySpeed;
//            hitBox.y =y;
//           
//        }else{
//           y += ySpeed;
//           hitBox.y = y;
//        }
           
         y += ySpeed;
           hitBox.y = y;
       
        
        
        //updateLocation has the same functionality as the updatePlatforms method, but it is called for the background only
        panel.l.updateLocation(panel.cameraX);
        panel.handler.updatePlatforms(panel.cameraX, panel.cameraY);
        
        
        // Character State Machine
        //This will select which sprite sheet to use depending on which state the character is
        //and set the variables accordingly so the animation works correctly 
        switch(cState){
            case Walking:{
                //Sprite Sheet to use
                spriteSheetIndex = 1;
                //How many Frames we have in this animation
                sheetLenght = 2;
                //How fast should we play this animation
                animPlayRate = 4;
                //Call animation Method
                Animation();
                break;
            }
            
            case Idle:{
            
                spriteSheetIndex = 0;
                sheetLenght = 2;
                animPlayRate = 12;
                Animation();
                break;
            }
            
            case Jumping:{
               
                spriteSheetIndex = 0;
                sheetLenght = 2;
                animPlayRate = 2;
                Animation();
                break;
            }
            
            case Shooting:{
                
                spriteSheetIndex = 4;
                sheetLenght = 1;
                animPlayRate = 5;
                Shoot(10);
                Animation();
                
                break;
            }
             case RunFiring:{
                spriteSheetIndex = 4;
                sheetLenght = 1;
                animPlayRate=4;
                Shoot(10);
                Animation();
                break;
            }
             
//              sprites[0] = ImageIO.read(is); // idle (2 frames)
//         sprites[1] = ImageIO.read(is1); // run (2 frames)
//         sprites[2] = ImageIO.read(is2); // hit (2 frames)
//         sprites[3] = ImageIO.read(is3); // death (2 frames)
//         sprites[4] = ImageIO.read(is4); // attack (1 frame)
             
        }
         
    }
    
    //Spawn projectile is still a work in progress
    public void Shoot(int fr){
        this.fireRate = fr;
        if(fireRateCounter==fireRate){
            if(direction == direction.Right){
                 projectile = new Projectile(x+30,y,1,panel.handler);
                panel.handler.projectiles.add(projectile);
                fireRateCounter=0;
            }else{
                projectile = new Projectile(x+30,y,-1,panel.handler);
                panel.handler.projectiles.add(projectile);
                fireRateCounter=0;
                
            }
            
         
        }else{
            fireRateCounter++;
           
        }
    }
    
    @Override
    public void Draw(Graphics2D gtd){
      
        gtd.setColor(Color.red);
       //gtd.fillRect(x, y, width, height);  //Hitbox visible for testing
        //Flips the image based on the direction the character is facing
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x - 10 , y-30,50,50,panel);
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x + 35  , y-30,-50,50,panel);
        
       
    }
    
    //Responds to Player Input
     public void KeyPressed(KeyEvent e){
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
       /* if(e.getKeyChar() == 'i'){
            if(isVisible==true){
                this.setVisible(false);
                isVisible = false;
            }else{
                this.setVisible(true);
                isVisible = true;
            }
        }*/
    }
    
    public void KeyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key ==KeyEvent.VK_A) keyLeft = false;
        if(key ==KeyEvent.VK_D) keyRight = false;
        if(key ==KeyEvent.VK_F)keyFire = false;
        if(key ==KeyEvent.VK_SPACE)keyJump = false;
    }
    
    //Load all sprite Sheets into memory
    public void ImportImage(){
        
        InputStream is = getClass().getResourceAsStream("/Images/Character/berg_idle.png");
        InputStream is1 = getClass().getResourceAsStream("/Images/Character/berg_run.png");
        InputStream is2 = getClass().getResourceAsStream("/Images/Character/berg_hit.png");
        InputStream is3 = getClass().getResourceAsStream("/Images/Character/berg_dead.png");
        InputStream is4 = getClass().getResourceAsStream("/Images/Character/attack_right.png");
        
        try{
         sprites[0] = ImageIO.read(is); // idle (2 frames)
         sprites[1] = ImageIO.read(is1); // run (2 frames)
         sprites[2] = ImageIO.read(is2); // hit (2 frames)
         sprites[3] = ImageIO.read(is3); // death (2 frames)
         sprites[4] = ImageIO.read(is4); // attack (1 frame)
                  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Get a sub image from the sprite sheet
     public void updateImage(BufferedImage image,int x, int y){
        
        characterSprite = image.getSubimage(x, y, 16, 16);
        
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
            if(spriteIndex <= sheetLenght){
                //get a sub image from the sprite sheet the spriteIndex variable represents 
                //the current frame, 48 is the size of the sub image,which should be saved in a variable in the future (e.g int spriteSize)
                updateImage(sprites[spriteSheetIndex],(spriteIndex * 16)-16,0);
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
