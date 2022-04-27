/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author diego
 */
public class PlayerInput extends KeyAdapter{
    GamePanel panel;
    
    public PlayerInput(GamePanel p){
        panel = p;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        panel.player.KeyPressed(e);
       
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        panel.player.KeyReleased(e);
       
    }
    
   
}
