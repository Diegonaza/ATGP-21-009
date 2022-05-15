/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

/**
 *
 * @author diego
 */
//This Class will handle the event when the player character dies
public class myThreadEventDeath extends Thread {
    Player player;
    
    public myThreadEventDeath(Player p ){
        //saves reference to the player character
        this.player = p;
        //starts the thread
        start();
    }
    
    @Override
    public void run(){
     
            try{
                //stop music
                player.panel.music.stop();
                //play player character death music
                player.panel.deathMusic.loop();
              //disable player's input
              player.isInputEnable = false;
              //transition between states
              player.cState = player.cState.Dead;
              //set the frequency the game will be updateded
              //this will give a slow motion effect
              player.panel.gameUPS = 10;
              player.panel.gameFPS = 60;
              //wait 3 seconds
             Thread.sleep(3000);       
          player.panel.running = false;
          player.panel.setVisible(false);
          //adjust the screen
          player.panel.window.gamePanel.setBounds(0, 0, 1280, 800);
         
          player.panel.inv.setVisible(false);
          //set  the game over panel visible
         player.panel.gO.setVisible(true);
         player.panel.window.frame.pack();
         player.panel.window.frame.repaint();
         
          player.panel.window.repaint();
          
      
                
                //Time in milliseconds
                //wait 0.8 seconds
                Thread.sleep(800);
                //re enable player's input
                    player.isInputEnable = true;
                    //transition between states
                    player.cState = player.cState.Idle;
                    player.maxWalkingSpeed =5;
                   //this kills the "thread"
                    interrupt();
                
               
            }
            
            
            catch(Exception e){
                
            }
            
           
        }
        
    
}
