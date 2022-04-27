package platformergame;


import java.awt.Color;
import javax.swing.JPanel;
import java.util.Timer;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import zMapEditor.TileMapper;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class GamePanel extends Canvas implements Runnable {
   
    private Thread thread;
    private boolean running = false;
    Player player ;
    Enemy enemy;
    boolean isVisible = true;
    Handler handler = new Handler();
    int cameraX;
    int cameraY;
    LevelOne l = new LevelOne(this);
    
    public GamePanel(){
        TileMapper tl = new TileMapper("Cave",handler);
        handler.setMapper(tl);
        player = new Player(250,200,this,ID.Player);
        this.addKeyListener(new PlayerInput(this));
        handler.addObject(player);
        
         enemy = new Enemy(400,250,this,ID.Enemy);
         handler.enemies.add(enemy);
         new Window(800,600,"PlatformerV2",this);
         Start();
         
      
    }
     public synchronized void Start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
      public synchronized void Stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
          e.printStackTrace();
      }
    }
    /*
    public void runOld(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running){
          
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            if(delta >= 1){
                handler.tick();
                
                delta--;
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            if(running)
               
                render();
                frames++;
                
                
                        
        }
        Stop();
    }
      */
    /*
    @Override
    public void run(){
        final int max_FPS = 60;
        final int max_UPS = 60;
        
        final double uOptimal_Time = 1000000000/ max_UPS;
        final double fOptimal_Time = 1000000000/ max_FPS;
        
        int ticks = 0, frames = 0;
        
        double uDeltaTime = 0, fDeltaTime = 0;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        
        while(running){
           
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;
            
            if(uDeltaTime >= uOptimal_Time){
                handler.tick();
                ticks++;
                uDeltaTime -= uOptimal_Time;
            }
            
            if(fOptimal_Time >= fOptimal_Time){
                
                frames++;
                fDeltaTime -= fOptimal_Time;
                render();
            }
            
            if(System.currentTimeMillis() - timer >=1000){
                System.out.println("Updates " + ticks + " FPS " + frames);
                
                ticks = 0;
                frames = 0;
                timer += 1000;
            }
            
        }
        
        Stop();
    }
    */
 
    
    @Override
    public  void run()
    {
        final int desiredFPS = 60;
        final int desiredUPS = 60;
        
        final long updateThreshold = 1000000000 / desiredUPS;
        final long drawThreshold = 1000000000 / desiredFPS;
        
        long lastFPS = 0, lastUPS = 0, lastFPSUPSOutput = 0;
        
        int fps = 0, ups = 0;
        
        loop:
        while(true)
        {
            if((System.nanoTime() - lastFPSUPSOutput) > 1000000000)
            {
                System.out.println("FPS: " + (double)fps);
                System.out.println("UPS: " + (double)ups);
                
                fps = 0;
                ups = 0;
                
                lastFPSUPSOutput = System.nanoTime();
            }
            
            if((System.nanoTime() - lastUPS) > updateThreshold)
            {
                lastUPS = System.nanoTime();
                handler.tick();
                ups++;
            }
            
            if((System.nanoTime() - lastFPS) > drawThreshold)
            {
                lastFPS = System.nanoTime();
                render();
                fps++;
            }
            
            // Calculate next frame, or skip if we are running behind
            if(!((System.nanoTime() - lastUPS) > updateThreshold || (System.nanoTime() - lastFPS) > drawThreshold))
            {
                long nextScheduledUP = lastUPS + updateThreshold;
                long nextScheduledDraw = lastFPS + drawThreshold;
                
                long minScheduled = Math.min(nextScheduledUP, nextScheduledDraw);
                
                long nanosToWait = minScheduled - System.nanoTime();
                
                // Just in case
                if(nanosToWait <= 0)
                    continue loop;
                    
                try
                {
                    Thread.sleep(nanosToWait / 1000000);
                } 
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D gtd = (Graphics2D)g;
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);
        l.Draw(gtd);
        handler.render(gtd);
        g.dispose();
        bs.show();
    }
    
  
    
   
    
}
