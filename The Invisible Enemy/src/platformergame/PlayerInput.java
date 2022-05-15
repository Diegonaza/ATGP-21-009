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

//Class that captures the player's input, has a reference to the gamePanel class
//because the character is being spawned in the gamePanel class at this point, needs to be changed in the future
//as the player should be spawned from the map class
public class PlayerInput extends KeyAdapter{
    GamePanel panel;
    
    public PlayerInput(GamePanel p){
        //gamePanel reference
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
