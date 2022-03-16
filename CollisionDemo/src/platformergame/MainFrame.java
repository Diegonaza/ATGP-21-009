/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author diego
 */
public class MainFrame extends JFrame{
    GamePanel panel = new GamePanel();
    public MainFrame(){
        this.setSize(800,600);
        //Window will be in the midle of the screen
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Galaxy Chronicles");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
        
        this.add(panel);
        
        addKeyListener(new PlayerInput(panel));
        
    }
}
