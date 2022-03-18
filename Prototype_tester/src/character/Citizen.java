/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GamePanel;
/**
 *
 * @author aliss
 */
public class Citizen extends NPC {
    public int infection = 0; // States which tipe of infection the Citizen has. Default being zero or non infected.
    public int i = 0; //counter for movement
    public int citizenX;
    
    
    
    public Citizen(GamePanel gp){
        
        super(gp);
        getCitizenImg();
    }
    
      public void getCitizenImg() {
       
          //Infection table:
          //  0: Blank; 1:Blue  2:Red  3:Yellow;  4:Pink;  5:Black
          
          
        switch(infection){
            case 0:
                right1 = setup("/citizenSprites/Blank/blank_right_1.png");
                right2 = setup("/citizenSprites/Blank/blank_right_2.png");
                left1 = setup("/citizenSprites/Blank/blank_left_1.png");
                left2 = setup("/citizenSprites/Blank/blank_left_2.png");
                
                attack_right = setup("/citizenSprites/Blank/blank_right_1.png");
                attack_left = setup("/citizenSprites/Blank/blank_left_1.png");                        
                break;
                //Citizen is Blank or not infected.
            case 1:  
                right1 = setup("/citizenSprites/Blue/blue_right_1.png");
                right2 = setup("/citizenSprites/Blue/blue_right_2.png");
                left1 = setup("/citizenSprites/Blue/blue_left_1.png");
                left2 = setup("/citizenSprites/Blue/blue_left_2.png");
                
                attack_right = setup("/citizenSprites/Blue/blue_right_1.png");
                attack_left = setup("/citizenSprites/Blue/blue_left_1.png");
                break;
                //Citizen is infected with Blue
            case 2:  
                right1 = setup("/citizenSprites/Red/red_right_1.png");
                right2 = setup("/citizenSprites/Red/red_right_2.png");
                left1 = setup("/citizenSprites/Red/red_left_1.png");
                left2 = setup("/citizenSprites/Red/red_left_2.png");
                
                attack_right = setup("/citizenSprites/Red/red_right_1.png");
                attack_left = setup("/citizenSprites/Red/red_left_1.png");    
                break;
                //Citizen is infected with Red.
            case 3:  
                right1 = setup("/citizenSprites/Yellow/yellow_right_1.png");
                right2 = setup("/citizenSprites/Yellow/yellow_right_2.png");
                left1 = setup("/citizenSprites/Yellow/yellow_left_1.png");
                left2 = setup("/citizenSprites/Yellow/yellow_left_2.png");
                
                attack_right = setup("/citizenSprites/Yellow/yellow_right_1.png");
                attack_left = setup("/citizenSprites/Yellow/yellow_left_1.png");    
                break;    
                //Citizen is infected with Yellow
            case 4:  
                right1 = setup("/citizenSprites/Pink/pink_right_1.png");
                right2 = setup("/citizenSprites/Pink/pink_right_2.png");
                left1 = setup("/citizenSprites/Pink/pink_left_1.png");
                left2 = setup("/citizenSprites/Pink/pink_left_2.png");
                
                attack_right = setup("/citizenSprites/Pink/pink_right_1.png");
                attack_left = setup("/citizenSprites/Pink/pink_left_1.png");    
                break; 
                //Citizen is infected with Pink
                
            case 5:  
                right1 = setup("/citizenSprites/Black/black_right_1.png");
                right2 = setup("/citizenSprites/Black/black_right_2.png");
                left1 = setup("/citizenSprites/Black/black_left_1.png");
                left2 = setup("/citizenSprites/Black/black_left_2.png");
                
                attack_right = setup("/citizenSprites/Black/black_right_1.png");
                attack_left = setup("/citizenSprites/Black/black_left_1.png");    
                break;     
                //Citizen is infected with Black
                        }//end of switch
        
        }


    
    public void draw(Graphics2D g2, GamePanel gp){
    image = null;
       switch(direction){
                       
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
                   
         g2.drawImage(image,citizenX,434,gp.tileSize,gp.tileSize,null);
        
    }
    
    @Override
    public void update(){
        
        while(i < 500){
            if(direction == "right"){
                citizenX += 0.2;
               direction = "left";}
            else if(direction == "left"){
                direction = "right";
                citizenX -= 0.2;
            }
            i++;
            
            
        }
        
                   
          
        //if colision is false Citizen continues
        if (isColisionOn == false) {
            
            switch(direction){
             case "left": 
             break;
             case "right": 
             break;
                     
         }
            
        }
        spriteCounter++;//increases sprite counter for sprite Number Change
        if(spriteCounter > 15)    {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;        
        }
    
    
    }
    
    
       public int getInfection() {
        return infection;
    }

    public void setInfection(int infection) {
        this.infection = infection;
    } 
    
}
