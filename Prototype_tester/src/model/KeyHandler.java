/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author valter
 */
public class KeyHandler implements KeyListener{
       public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean jumpingPressed = false;
    public boolean attackPressed = false;
    public long jumpingTime = 200;
   
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
       
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
            
            
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
             
        }
        if (code == KeyEvent.VK_W ) {
            jumpingPressed = true;  }
        
        if (code == KeyEvent.VK_SPACE ) {
            attackPressed = true;  }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        downPressed = false;            
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
            
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            
        }
        if (code == KeyEvent.VK_W) {
            jumpingPressed = false;}
        
        if (code == KeyEvent.VK_SPACE ) {
            attackPressed = false;  }
      
    }
     
}
    
    

