/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;


import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author valter
 */
public class Sound {
    
    //array of sounds
    URL url[] = new URL[2];
    Clip clip;
    public Sound() {
        
       url[0] = getClass().getResource("/Sound/WeAreAngels.wav");
       url[1] = getClass().getResource("/Sound/WeAreDead.wav");
       
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
     
     public float getVolume() {
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    return (float) Math.pow(10f, gainControl.getValue() / 20f);
}

public void setVolume(float volume) {
    if (volume < 0f || volume > 1f)
        throw new IllegalArgumentException("Volume not valid: " + volume);
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    gainControl.setValue(20f * (float) Math.log10(volume));
}
     
     
        
     public void stop(){
       clip.stop();
    }
    
    
}
