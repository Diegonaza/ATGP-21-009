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
   
    boolean musicLoop = false;
    Window window;
    GameOver gO = new GameOver(this);
    private Thread thread;
    public boolean running = false;
    Player player ;
    Citizen enemy;
    boolean isVisible = true;
    Handler handler = new Handler();
    int cameraX;
    int cameraY;
    LevelOne l = new LevelOne(this);
    Inventory inv = new Inventory();
    Sound music = new Sound();
    Sound deathMusic = new Sound();
    int gameUPS;
    int gameFPS;
    
    
    public GamePanel(){
        gameFPS = 60;
        gameUPS = 60;
         //Start the game Loop
    
          window = new Window(1280,800,"The Invisible Enemy",this, inv);
         StartGame();
         
         window.frame.setLayout(null);
         this.setVisible(true);
       
         gO.setBounds(0, 0, 1280,800);
         gO.setVisible(false);
         window.frame.add(gO);
         window.frame.add(inv);
         
         Start();
         
      
    }
    
    public GamePanel(int i){
      
        
      
    }
    
    public void StartGame(){
        this.setVisible(true);
        deathMusic.setSound(1);
        handler = new Handler();
         //Instantiate the TileMapper Object, the constructor takes 2 parameters the map name and a reference to the handler object.
        TileMapper tl = new TileMapper("Cave4",handler);
        cameraX = 0;
       AdventureMusic();
               
        //this method set a reference to the TileMapper class into the handler class
        handler.setMapper(tl);
        //Spawn a playing into the level
        player = new Player(250,200,this);
        // this will be changed in the future as the player doesn't need to be stored in a list, it will be better to store him into a variable
        //adds the player to handler object List
        handler.addObject(player);
        
        int cicle = 0;
        
        for(int i = 0 ; i<200; i++){
            //creates 200 instances of Zenith particles
            int randomX = ThreadLocalRandom.current().nextInt(250,451);
            //for each particle, the position X will be positioned randomply between the coordinates 250 and 451
            int randomY = ThreadLocalRandom.current().nextInt(30,100);
            //for each particle, the position X will be positioned randomply between the coordinates 30 and 100
            Zenith z = new Zenith(randomX,randomY,5,handler, this);
            //creates a new Zenith Object
            cicle ++;
            //increments the cicle variable
            
            switch(cicle){
                case 1: 
                z.ct = z.ct.blue;
                //depending on the cicle number, it chooses the accodingly 
                break;
                
                case 2: 
                z.ct = z.ct.red;
                //depending on the cicle number, it chooses the accodingly 
                break;
                
                case 3: 
                z.ct = z.ct.yellow;
                //depending on the cicle number, it chooses the accodingly 
                break;
                
                case 4:
                z.ct = z.ct.pink;
                //depending on the cicle number, it chooses the accodingly 
                break;
                
                case 5:
                z.ct = z.ct.black;
                //depending on the cicle number, it chooses the accodingly 
                break;
                
                        }
            //applying the switch to create a equally distribuited Zenith particles in the map
            
            if(cicle >= 5){
                cicle = 0;}
            //if the cicle reached the final index, it resets the counter.
            
            
            // add the Zenith instance into the zenith array for the handler class 
            handler.zenith.add(z);
        }
        //Adds the key listener 
        this.addKeyListener(new PlayerInput(this));
        
        
        
        //Spawn a Enemy into the level
             enemy = new Citizen(400,250,this);
         //adds the Enemy to the handler Enemies List
         handler.enemies.add(enemy);
         
    }
    
    
    
     public synchronized void Start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
     //Starts the thread as a new thread.
     //changes the running state to true.
     
      public synchronized void Stop(){
        try{
            thread.interrupt();
            running = false;
        }
        catch(Exception e){
          e.printStackTrace();
      }
        //interupts the thread and change the running state to false.
    }
  
    @Override
    public  void run()
    {
        //Define updates and frames per second 
         int desiredFPS = gameFPS;
         int desiredUPS = gameUPS;
        
        //Time is in nanoseconds, 1 second has 1 billion nano seconds so we divide 1 billion by the amount of times we want the game to upate
        // this will result in 1 update every 33 million nanoseconds which is equivalent to 60 updates per second
       
         long updateThreshold = 1000000000 / desiredUPS;
         long drawThreshold = 1000000000 / desiredFPS;
        
        long lastFPS = 0, lastUPS = 0, lastFPSUPSOutput = 0;
        
        int fps = 0, ups = 0;
        
        loop:
        while(running)
        {
            desiredFPS = gameFPS;
            desiredUPS = gameUPS;
            updateThreshold = 1000000000 / desiredUPS;
            drawThreshold = 1000000000 / desiredFPS;
            
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
            //if this BufferStrategy is null, it creates a new one.
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        // Instanciates the Graphics g using the BufferStrategy item.
        Graphics2D gtd = (Graphics2D)g;
        // define the graphs 2d variable ocnverted from g.
        g.setColor(Color.black);
        // Buffer has a background colour not to conflit with the tiles
        l.Draw(gtd);
        // draws the level one using the graphs object.
        handler.render(gtd);
        // loads this decorated objecto to handler
        g.dispose();
        //removes g from memory as it won't be used again.
        bs.show();
        //presents the BufferStrategy item
    }
    
    private void AdventureMusic(){
        
        //Loads the sound clip for the adventure
        music.setSound(0);
        //Set's the sound volume to 10% of the total
        music.setVolume((float) 0.10);
        //Play the clip
        music.play();
        //Set the clip as a loop.
        music.loop();
        //Set's the Death sound volume to 10% of the total
        deathMusic.setVolume((float)0.10);
        
        
    }
}
