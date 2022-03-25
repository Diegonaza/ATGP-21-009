/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;

import model.GamePanel;

/**
 *
 * @author aliss
 */
public class Zenith extends Super_Projectile {
    
    
    
    
   public Zenith(GamePanel gp, int infection){
       super(gp);
             
       
       
   }
   
   public void getZenithImage(){
       
       switch(infection){
           case 1:
               //BLUE
                right1 = setup("/Zenith/Blue/right1.png");
                right2 = setup("/Zenith/Blue/right2.png");
                left1 = setup("/Zenith/Blue/right2.png");
                left2 = setup("/Zenith/Blue/right1.png");
                up1 = setup("/Zenith/Blue/up1.png");
                up2 = setup("/Zenith/Blue/up2.png");
                down1 = setup("/Zenith/Blue/up2.png");
                down2 = setup("/Zenith/Blue/up1.png");
                
               break;
           case 2:
               //RED
               right1 = setup("/Zenith/Red/right1.png");
                right2 = setup("/Zenith/Red/right2.png");
                left1 = setup("/Zenith/Red/right2.png");
                left2 = setup("/Zenith/Red/right1.png");
                up1 = setup("/Zenith/Red/up1.png");
                up2 = setup("/Zenith/Red/up2.png");
                down1 = setup("/Zenith/Red/up2.png");
                down2 = setup("/Zenith/Red/up1.png");
               
               break;
           case 3:
               //YELLOW
                right1 = setup("/Zenith/Yellow/right1.png");
                right2 = setup("/Zenith/Yellow/right2.png");
                left1 = setup("/Zenith/Yellow/right2.png");
                left2 = setup("/Zenith/Yellow/right1.png");
                up1 = setup("/Zenith/Yellow/up1.png");
                up2 = setup("/Zenith/Yellow/up2.png");
                down1 = setup("/Zenith/Yellow/up2.png");
                down2 = setup("/Zenith/Yellow/up1.png");
               
               break;
           case 4:
               //PINK
                right1 = setup("/Zenith/Pink/right1.png");
                right2 = setup("/Zenith/Pink/right2.png");
                left1 = setup("/Zenith/Pink/right2.png");
                left2 = setup("/Zenith/Pink/right1.png");
                up1 = setup("/Zenith/Pink/up1.png");
                up2 = setup("/Zenith/Pink/up2.png");
                down1 = setup("/Zenith/Pink/up2.png");
                down2 = setup("/Zenith/Pink/up1.png");
               
               break;
           case 5:
               //BLACK
                right1 = setup("/Zenith/Black/right1.png");
                right2 = setup("/Zenith/Black/right2.png");
                left1 = setup("/Zenith/Black/right2.png");
                left2 = setup("/Zenith/Black/right1.png");
                up1 = setup("/Zenith/Black/up1.png");
                up2 = setup("/Zenith/Black/up2.png");
                down1 = setup("/Zenith/Black/up2.png");
                down2 = setup("/Zenith/Black/up1.png");
               
               break;
   }
    
}
}
