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
    
    
    public Player(int x, int y, GamePanel panel,ID id){
        super(x,y,id);
        this.x = x;
        this.y = y;
        this.id = id;
        this.startX = x;
        this.locationX = x;
        this.panel = panel;
        width = 32;
        height = 64;
        hitBox = new Rectangle(x,y,width,height);
        
        
        direction = direction.Right;
        
        ImportImage();
    }
    
    //tick
    @Override
    public void tick(){
       // System.out.println(cState);
      //Character States  
     
      
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
      
      
      //Player Movement
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
           // panel.handler.updateEnemies(panel.cameraX, panel.cameraY);
        }else{
           y += ySpeed;
           hitBox.y = y;
        }
           
         
       
        
        
        
        panel.l.updateLocation(panel.cameraX);
        panel.handler.updatePlatforms(panel.cameraX, panel.cameraY);
        
        
        // Character State Machine
        switch(cState){
            case Walking:{
            
                spriteSheetIndex=2;
                sheetLenght = 6;
                animPlayRate=4;
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
    
    public void Shoot(int fr){
        this.fireRate = fr;
        if(fireRateCounter==fireRate){
            if(direction == direction.Right){
                 projectile = new Projectile(x+50,y+30,1,panel.handler);
                panel.handler.projectiles.add(projectile);
                fireRateCounter=0;
            }else{
                projectile = new Projectile(x+50,y+30,-1,panel.handler);
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
        //gtd.fillRect(x, y, width, height);
        
        if(direction == direction.Right)
            gtd.drawImage(characterSprite, x-30, y-10,100,100,panel);
        if(direction == direction.Left)
            gtd.drawImage(characterSprite, x-20+100, y-10,-100,100,panel);
        
       
    }
    
     public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(e.getKeyChar()== 'a') {
            keyLeft = true;
            
        }
        if(e.getKeyChar() == 'd'){
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
        if(e.getKeyChar()== 'a') keyLeft = false;
        if(e.getKeyChar() == 'd') keyRight = false;
        if(e.getKeyChar()=='f')keyFire = false;
        if(key ==KeyEvent.VK_SPACE)keyJump = false;
    }
    
    public void ImportImage(){
        
        InputStream is = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Idle.png");
        InputStream is1 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Crouch.png");
        InputStream is2 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Run.png");
        InputStream is3 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Jump.png");
        InputStream is4 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Death.png");
        InputStream is5 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Firing2.png");
        InputStream is6 = getClass().getResourceAsStream("/Images/Character/Gunner_Blue_Run_Fire2.png");
        try{
         sprites[0] = ImageIO.read(is);
         sprites[1] = ImageIO.read(is1);
         sprites[2] = ImageIO.read(is2);
         sprites[3] = ImageIO.read(is3);
         sprites[4] = ImageIO.read(is4);
         sprites[5] = ImageIO.read(is5);
         sprites[6] = ImageIO.read(is6);
         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public void updateImage(BufferedImage image,int x, int y){
        
        characterSprite = image.getSubimage(x, y, 48, 48);
        
    }
     
     
     
     public void Animation(){
         
           animCounter++;
          if(animCounter>animPlayRate){
            
        animCounter=0;
        }
        if(animCounter==animPlayRate){
           
            if(spriteIndex<sheetLenght){
                updateImage(sprites[spriteSheetIndex],(spriteIndex* 48)-48,0);
                spriteIndex++;
                 
                
            }else{
                spriteIndex = 1;
            }
            animCounter = 0;
        }
        
         
     }
}
