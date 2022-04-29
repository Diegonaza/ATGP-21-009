/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;


import Objects.GameObject;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.GamePanel;
import model.KeyHandler;
import model.UtilityTool;
import platform.Platform;
import platform.Platform1;
import platform.SuperPlatform;


/**
 *
 * @author valter
 */
public class Player extends GameObject{
    
    //instanciates Game Panel Class
    
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
        width = 32;
        height = 64;
        hitBox = new Rectangle(x,y,width,height);
        
        
        direction = direction.Right;
        
        getPlayerImg();
    }
   
    
    public void getPlayerImg() {
       
         idle1 = setup("/playerSprite/berg_down_1.png");
        idle2 = setup("/playerSprite/berg_down_2.png");
        left1 = setup("/playerSprite/berg_left_1.png");
        left2 = setup("/playerSprite/berg_left_2.png");
        right1 = setup("/playerSprite/berg_right_1.png");
        right2 = setup("/playerSprite/berg_right_2.png");
        attack_right = setup("/playerSprite/attack_right.png");
        attack_left = setup("/playerSprite/attack_left.png");
        
         try{
         sprites[0] = idle1;
         sprites[1] = idle2;
         sprites[2] = left1;
         sprites[3] = left2;
         sprites[4] = right1;
         sprites[5] = right2;
         sprites[6] = attack_right;
         sprites[7] = attack_left;
         
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Set image path to an image variable.
        }
    
    
        
    public BufferedImage setup(String imagePath){
      
        UtilityTool utilityTool = new UtilityTool();
                
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = utilityTool.scaleImage(image, 48, 48);
        }catch(IOException e){
            System.out.println("Error reading image " + imagePath);
            //If any problem with image, we get exactly which one is it.
        }
        
        
        return image;
    }
    

    
    public void update(){
      
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
      if(xSpeed > 3)xSpeed= 3;
      if(xSpeed<-3)xSpeed = -3;
      
      
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
         hitBox.y = y; 
      if(y<=200&&ySpeed<=0 || y>=300&&ySpeed>=0){
            panel.cameraY+=ySpeed;
            locationY+=ySpeed;
            hitBox.y =y;
           
        }else{
           y += ySpeed;
           hitBox.y = y;
        }
           
         
       
        
        
        //updateLocation has the same functionality as the updatePlatforms method, but it is called for the background only
        panel.l.updateLocation(panel.cameraX);
        panel.handler.updatePlatforms(panel.cameraX, panel.cameraY);
        
        
        // Character State Machine
        //This will select which sprite sheet to use depending on which state the character is
        //and set the variables accordingly so the animation works correctly 
        switch(cState){
            case Walking:{
                //Sprite Sheet to use
                spriteSheetIndex=2;
                //How many Frames we have in this animation
                sheetLenght = 6;
                //How fast should we play this animation
                animPlayRate=4;
                //Call animation Method
                Animation();
                break;
            }
            
            case Idle:{
            
                spriteSheetIndex=0;
                sheetLenght = 5;
                animPlayRate=4;
                Animation();
                break;
            }
            
            case Jumping:{
               
                spriteSheetIndex =3;
                sheetLenght = 2;
                animPlayRate=4;
                Animation();
                break;
            }
            
            case Shooting:{
                
                spriteSheetIndex = 5;
                sheetLenght = 3;
                animPlayRate=5;
                Shoot(10);
                Animation();
                
                break;
            }
             case RunFiring:{
                spriteSheetIndex = 6;
                sheetLenght = 6;
                animPlayRate=4;
                Shoot(10);
                Animation();
                break;
            }
        }
    
    }
    
    public void pickObject(int i){
        System.out.println(i);
        if(i != 999){  //999 because it is a number that we don't use in the arrays
         
           // System.out.println("colide");
         String objectName =   gp.obj[i].name;
         
         switch(objectName){
             case "gun":
                 hasItem++;
                 gp.obj[i] = null;
                 System.out.println("item"+hasItem);
                 break;
             case "mask":
                 gp.obj[i] = null;
                 hasItem++;        
                 break;
                 
             
             
         }
        }
        
    }
    
    //check colision at platform
    public void collideAtPlatform(int i){
            System.out.println(i);
     /*  if(i != 999){  //999 because it is a number that we don't use in the arrays
         
          System.out.println(i);
            System.out.println("it colide");
         String objectName =   gp.platform[i].name;
         
         switch(objectName){
             case "platform":
                 if (direction == "idle") {
                      gp.platform[i] = null;
                      gp.playSE(1);
                       worldY = -4;
                       
                       direction="idle";
                      
                 }
                /* if (direction == "idle") {
                      solidArea.y -= yspeed;
                      for (SuperPlatform object : gp.platform) {
                          while(!gp.platform[i].solidArea.intersects(solidArea)) solidArea.y += Math.signum(yspeed);
                                solidArea.y -= Math.signum(xspeed);
                                yspeed = 0 ;
                             
                       }
                      
                      if (direction == "left" ) {
                          solidArea.x -= xspeed;
                      for (SuperPlatform object : gp.platform) {
                          while(!gp.platform[i].solidArea.intersects(solidArea)) solidArea.x += Math.signum(xspeed);
                                solidArea.x -= Math.signum(xspeed);
                                xspeed = 0 ;
                       }
                      }
                 }*/
                 
                 
             
             
         }
        
        
    
     public void paintComponent(Graphics2D g2d,GamePanel panel){
           
        
         BufferedImage image = null;
       switch(direction){
             case "idle":
                 if(spriteNumber == 1){
                     image = idle1;}
                 if(spriteNumber == 2){
                     image = idle2;}                 
                 break;
                 
             case "left":
                 if(spriteNumber == 1){
                     image = left1;}
                 if(spriteNumber == 2){
                     image = left2;}
                 break;
                 
             case "right":
                 if(spriteNumber == 1){
                     image = right1;}
                 if(spriteNumber == 2){
                     image = right2;}
                 break;
             case "attack_left":
                 if(spriteNumber == 1){
                     image = attack_left;}
                 if(spriteNumber == 2){
                     image = left1;}
                 
                 break;
             case "attack_right":
                 if(spriteNumber == 1){
                     image = attack_right;}
                 if(spriteNumber == 2){
                     image = right1;}
                 break;    
       
       }
                   
         g2d.drawImage(image,x,y,gp.tileSize,gp.tileSize,panel);//changed

    
    } // Draw images into the screenX using the updated coordenate information from the player in the game panel, changes the image accordingly to spriteNumber value.

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Draw(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
