/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import characters.Player;

/**
 *
 * @author diego
 */
//This class handles a specific transition between states in the character state machine
public class myThread extends Thread {
    Player player;
    double temp;
    
    public myThread(Player p ){
        //saves reference to the player character
        this.player = p;
        this.temp = p.maxWalkingSpeed;
        //starts the thread
        start();
    }
    
    @Override
    public void run(){
     
            try{
                
                //Time in milliseconds
                //wait 0.8 seconds
                Thread.sleep(800);
                    //reenable player's input
                    player.isInputEnable = true;
                    //transition between states
                    player.cState = player.cState.Idle;
                    //player movement speed
                    player.maxWalkingSpeed =temp;
                   //"kills" the thread
                     interrupt();
                
               
            }catch(Exception e){
                
            }
        }
        
    
}
