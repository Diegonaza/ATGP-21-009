/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author valter
 */
public class Sound {
    
    //array of sounds
    URL url[] = new URL[30];
    Clip clip;
    public Sound() {
        
       url[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
       url[1] = getClass().getResource("/sound/berg_jump.wav");
    }
    
    public void setSound(int i){
        
        try {
            
            
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url[i]);
            clip = AudioSystem.getClip(null);
            clip.open(audioIn);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public void play(){
        
     clip.start();
    }
    
     public void loop(){
       clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
     public void stop(){
       clip.stop();
    }
    
    
}
