package platformergame;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import zMapEditor.TileMapper;
import java.util.concurrent.ThreadLocalRandom;


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
    Citizen enemy;
    boolean isVisible = true;
    Handler handler = new Handler();
    int cameraX;
    int cameraY;
    LevelOne l = new LevelOne(this);
    Sound music = new Sound();
       
    
    public GamePanel(){
        //Instantiate the TileMapper Object, the constructor takes 2 parameters the map name and a reference to the handler object.
        TileMapper tl = new TileMapper("firstmap",handler);
        
       AdventureMusic();
               
        //this method set a reference to the TileMapper class into the handler class
        handler.setMapper(tl);
        //Spawn a playing into the level
        player = new Player(100,400,this);
        // this will be changed in the future as the player doesn't need to be stored in a list, it will be better to store him into a variable
        //adds the player to handler object List
        handler.addObject(player);
        
        int cicle = 0;
        
        for(int i = 0 ; i<200; i++){
            int randomX = ThreadLocalRandom.current().nextInt(250,451);
            int randomY = ThreadLocalRandom.current().nextInt(30,100);
            Zenith z = new Zenith(randomX,randomY,5,handler, this);
            cicle ++;
            
            switch(cicle){
                case 1: 
                z.ct = z.ct.blue;
                break;
                
                case 2: 
                z.ct = z.ct.red;
                break;
                
                case 3: 
                z.ct = z.ct.yellow;
                break;
                
                case 4:
                z.ct = z.ct.pink;
                break;
                
                case 5:
                z.ct = z.ct.black;
                break;
                
                        }
            //applying the switch to create a equally distribuited Zenith particles in the map
            
            if(cicle >= 5){
                cicle = 0;}
            
            
            // add the Zenith instance into the zenith array for the handler class 
            handler.zenith.add(z);
        }
        //Adds the key listener 
        this.addKeyListener(new PlayerInput(this));
        
        
        
        //Spawn a Enemy into the level
             enemy = new Citizen(400,250,this);
         //adds the Enemy to the handler Enemies List
         handler.enemies.add(enemy);
         new Window(1280,800,"The Invisible Enemy",this);
         //Start the game Loop
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
      //GameLoop code Reference
     //https://stackoverflow.com/questions/66079677/how-to-improve-the-framerate-in-simple-java-game-loop
      
    @Override
    public  void run()
    {
        //Desired  updates/Frames per second 
        final int desiredFPS = 60;
        final int desiredUPS = 60;
        
        //Time is in nanoseconds, 1 second has 1 billion nano seconds so we divide 1 billion by the amount of times we want the game to upate
        // this will result in 1 update every 33 million nanoseconds which is equivalent to 60 updates per second
       
        final long updateThreshold = 1000000000 / desiredUPS;
        final long drawThreshold = 1000000000 / desiredFPS;
        
        long lastFPS = 0, lastUPS = 0, lastFPSUPSOutput = 0;
        
        int fps = 0, ups = 0;
        
        loop:
        while(running)
        {
            if((System.nanoTime() - lastFPSUPSOutput) > 1000000000)
            {
              //  System.out.println("FPS: " + (double)fps);
             //   System.out.println("UPS: " + (double)ups);
                
                fps = 0;
                ups = 0;
                
                lastFPSUPSOutput = System.nanoTime();
            }
            //IF the CURRENT time minus the time of the last update is bigger than the updateThreshold than we can update again
            if((System.nanoTime() - lastUPS) > updateThreshold)
            {
                lastUPS = System.nanoTime();
                handler.tick();
                ups++;
            }
            //same logic as for updates per second , but this is for the frames per second
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
                
                //this how much the thread should " sleep " before the next " run "
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
    
    private void AdventureMusic(){
        
        //Loads the sound clip
        music.setSound(0);
        //Set's the sound volume to 10% of the total
        music.setVolume((float) 0.10);
        //Play the clip
        music.play();
        //Set the clip as a loop.
        music.loop();
        
    }
  
    
   
    
}
