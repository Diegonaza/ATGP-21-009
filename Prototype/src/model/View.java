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
        // TODO code application logic here
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Galaxy Chronicles:  Zenith, the Invisible Enemy (The invincible Enemy)");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
        
      
        
    }
        
}
