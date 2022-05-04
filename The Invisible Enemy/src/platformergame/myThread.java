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
public class myThread extends Thread {
    Player player;
    
    public myThread(Player p ){
        this.player = p;
        start();
    }
    
    @Override
    public void run(){
     
            try{
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
