/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;
import engine.GamePanel;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author diego
 */
public class Window extends Canvas{
    
    public JFrame frame;
    public GamePanel gamePanel;
    public Inventory inv;
    //Window Settings for instantiation
    public Window(int width, int height, String title, GamePanel game, Inventory inventory){
        frame = new JFrame(title);
        gamePanel = game;
        this.inv = inventory;
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
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
   
}
