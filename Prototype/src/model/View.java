/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import javax.swing.JFrame;


/**
 *
 * @author valter
 */
public class View {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String[] gameTitle = {"Galaxy Chronicles: Zenith, the invisible Enemy", "Galaxy Chronicles: Zenith, the invinsible Enemy", "Galaxy Chronicles: Zenith, the invincible Enemy" };
        //Titles to be shown accordingly to conclusion in game.
        int conclusion = 0;       
        //This variable will be modified in the future to be modified in case of loadings.
        int cases;
        //Variable to select the index for the gameTitle array.
        
        if(conclusion < 60){
            cases = 1;}
        else if(conclusion < 30){
            cases = 0;}
        else{cases = 2;  }
        //in case the conclusion is smaller than 30 and 60, selects the first case
        //if conclusion is smaler than 60 only, selects the second case
        // if conclusion is equal or bigger to 60, selects the third case.
        
        
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set default operation to finish the program if window is closed.
        window.setResizable(false);
        //Won't allow user to modify the window.
        window.setTitle(gameTitle[cases]);
        //Set the title accordingly to the game progression.
        
        GamePanel gamePanel = new GamePanel();
        gamePanel.GameSetUp();
        window.add(gamePanel);
        window.pack();
        
        //create, and add the Game Panel to the window, and display it.
        gamePanel.startChronos();
        //
        
        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
        
        
        
      
        
    }
        
}
