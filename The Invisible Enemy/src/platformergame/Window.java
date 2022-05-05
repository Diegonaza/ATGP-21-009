/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author diego
 */
public class Window extends Canvas{
    
    JFrame frame;
    GamePanel gamePanel;
    Inventory inv;
    
    public Window(int width, int height, String title, GamePanel game, Inventory inventory){
        frame = new JFrame(title);
        gamePanel = game;
        this.inv = inventory;
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        
        //frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        gamePanel.setBounds(0, 0, 1280, 600);
        inv.setBounds(0, 600, 1265, 161);
        inv.setVisible(true);
        frame.setLayout(null);
        frame.add(gamePanel);
        frame.add(inv);
        frame.pack();
        frame.setVisible(true);
      
    }
    
    public Window(int width, int height, String title, GameOver game){
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        
        //frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
      
    }
    
}
