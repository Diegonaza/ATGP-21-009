
package character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import model.GamePanel;

/**
 *
 * @author aliss
 */
public class Citizen extends Characters{
    public int infection = 1;
    
    
    public Citizen(GamePanel gp){
        
        super(gp);
        direction = "right";
        speed = 1;
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
    
    
//     public void draw(Graphics2D g2, GamePanel gp){
//     BufferedImage image = null;
//       switch(direction){
//                       
//             case "left":
//                 if(spriteNumber == 1){
//                     image = left1;}
//                 if(spriteNumber == 2){
//                     image = left2;}
//                 break;
//                 
//             case "right":
//                 if(spriteNumber == 1){
//                     image = right1;}
//                 if(spriteNumber == 2){
//                     image = right2;}
//                 break;
//             case "attack_left":
//                 if(spriteNumber == 1){
//                     image = attack_left;}
//                 if(spriteNumber == 2){
//                     image = left1;}
//                 
//                 break;
//             case "attack_right":
//                 if(spriteNumber == 1){
//                     image = attack_right;}
//                 if(spriteNumber == 2){
//                     image = right1;}
//                 break;    
//       
//       }
//                   
//         g2.drawImage(image,0,400,gp.tileSize,gp.tileSize,null);
//        
//    }// Method is not utilized.
     
     
    @Override
    public void setAction(){
        
        actionLockCounter ++;
        if(actionLockCounter == 120){
        
        Random random = new Random(); 
        int i = random.nextInt(100)+1;//pick up a random  number from 1 to 100
        
        if (i <= 50 ) {
            direction  = "right";
            
        }else if (i >= 50) {
            direction  = "left";
        }
        actionLockCounter = 0;
    
    }
    }
}
