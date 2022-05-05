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
public class myThreadEventDeath extends Thread {
    Player player;
    
    public myThreadEventDeath(Player p ){
        this.player = p;
        start();
    }
    
    @Override
    public void run(){
     
            try{
              player.isInputEnable = false;
              player.cState = player.cState.Dead;
              player.panel.gameUPS = 10;
              player.panel.gameFPS = 60;
             Thread.sleep(3000);       
          player.panel.running = false;
          player.panel.setVisible(false);
          player.panel.window.gamePanel.setBounds(0, 0, 1280, 800);
         
          player.panel.inv.setVisible(false);
         player.panel.gO.setVisible(true);
         player.panel.window.frame.pack();
         player.panel.window.frame.repaint();
         
          player.panel.window.repaint();
          
      
                
                //Time in milliseconds
                Thread.sleep(800);
                    player.isInputEnable = true;
                    player.cState = player.cState.Idle;
                    player.maxWalkingSpeed =5;
                   
                     interrupt();
                
               
            }catch(Exception e){
                
            }
        }
        
    
}
