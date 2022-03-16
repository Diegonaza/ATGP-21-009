package platformergame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class GamePanel extends JPanel implements ActionListener{
    Player player ;
    Timer gameTimer;
    ArrayList<Platform> platforms = new ArrayList<>();
    
    
    public GamePanel(){
        
        player = new Player(400,300,this);
        MakePlatforms();
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){
            
           //https://youtu.be/Icd2gAHDSfY?t=1134 detailed explanation of the schedule() method parameters.
           // 0 is how long we wait before we start our timer , 17 is the interval of each frame in milliseconds to achive a 60 fps game.
            @Override
            public void run() {
               player.UpdatePlayer();
               repaint();
            }
            
        },0,17);
    }
    
    public void MakePlatforms(){
        for(int i = 50; i<650; i+=50){
            platforms.add(new Platform(i,500,50,50));
        }
        
        platforms.add(new Platform(200,450,50,50));
        platforms.add(new Platform(200,400,50,50));
        platforms.add(new Platform(350,250,50,50));
        platforms.add(new Platform(400,200,50,50));
        platforms.add(new Platform(300,300,50,50));
        
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D gtd = (Graphics2D)g;
        player.Draw(gtd);        
        for(Platform p : platforms)p.draw(gtd);
       
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
    public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(e.getKeyChar()== 'a') player.keyLeft = true;
        if(e.getKeyChar() == 'd') player.keyRight = true;
        if(key ==KeyEvent.VK_SPACE)player.keyJump = true;
    }
    
    public void KeyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(e.getKeyChar()== 'a') player.keyLeft = false;
        if(e.getKeyChar() == 'd') player.keyRight = false;
        if(key ==KeyEvent.VK_SPACE)player.keyJump = false;
    }
    
}
