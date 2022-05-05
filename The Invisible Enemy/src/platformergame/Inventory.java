/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformergame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author diego
 */
public class Inventory extends JPanel  {

   
    
    
    ImageIcon weaponSelectedImg = new ImageIcon(Inventory.class.getResource("/Images/GunLvl1.png"));
    ImageIcon weaponImg = new ImageIcon(Inventory.class.getResource("/Images/GunLvl1icon.png"));
    ImageIcon maskSelectedImg = new ImageIcon(Inventory.class.getResource("/Images/maskSelected.png"));
    ImageIcon maskImg = new ImageIcon(Inventory.class.getResource("/Images/maskIcon.png"));
    ImageIcon skillImg = new ImageIcon(Inventory.class.getResource("/Images/skillIcon.png"));
    ImageIcon arrowUp = new ImageIcon(Inventory.class.getResource("/Images/ArrowUp.png"));
    ImageIcon arrowDown = new ImageIcon(Inventory.class.getResource("/Images/ArrowDown.png"));
    
    JLabel selected = new JLabel();
    JLabel weapon = new JLabel();
    JLabel skill = new JLabel();
    JLabel mask = new JLabel();
    JLabel descriptionLabel = new JLabel("Description");
    JLabel weaponUpIcon = new JLabel(arrowUp);
    JLabel weaponDownIcon = new JLabel(arrowDown);
    JLabel maskUpIcon = new JLabel(arrowUp);
    JLabel maskDownIcon = new JLabel(arrowDown);
    JLabel skillUpIcon = new JLabel(arrowUp);
    JLabel skillDownIcon = new JLabel (arrowDown);
    JLabel cardsArtifacts = new JLabel ("Cards & Artifacts");
    JLabel equipmentL = new JLabel("Equipment");
    JLabel detailedInfoL = new JLabel("Deitailed Info");
    
    
    JButton weaponUp = new JButton();
    JButton weaponDown = new JButton();
    
    JPanel equipment = new JPanel();
    JPanel cards = new JPanel();
    JPanel description = new JPanel();
    JTextArea textArea = new JTextArea(20,20);
       
    public Inventory(){
        
       this.setSize(800,600);
       this.setBackground(Color.DARK_GRAY);
       this.setVisible(false);
       this.setLayout(null);
       
       selected.setIcon(weaponSelectedImg);
       selected.setVisible(true);
       selected.setBounds(0,0, 250, 250);
       
       weapon.setIcon(weaponImg);
       weapon.setVisible(true);
       weapon.setBounds(10, 350, 100, 100);
       weaponUpIcon.setBounds(20, 315, 50, 50);
       weaponDownIcon.setBounds(20, 435, 50, 50);
      
       mask.setIcon(maskImg);
       mask.setVisible(true);
       mask.setBounds(85,350,100,100);
       maskUpIcon.setBounds(95, 315, 50, 50);
       maskDownIcon.setBounds(90, 435, 50, 50);
       
       skill.setIcon(skillImg);
       skill.setVisible(true);
       skill.setBounds(160, 350, 100, 100);
       skillUpIcon.setBounds(170, 315, 50, 50);
       skillDownIcon.setBounds(170, 435, 50, 50);
       
       equipmentL.setBounds(75, 0, 100, 50);
       equipmentL.setForeground(Color.white);
      
       
       equipment.setLayout(null);
       equipment.setBackground(Color.gray);
       equipment.setBounds(0,50,250,510);
       equipment.setVisible(true);
       equipment.add(selected);
       equipment.add(weapon);
       equipment.add(mask);
       equipment.add(skill);
       equipment.add(weaponUpIcon);
       equipment.add(weaponDownIcon);
       equipment.add(maskUpIcon);
       equipment.add(maskDownIcon);
       equipment.add(skillUpIcon);
       equipment.add(skillDownIcon);
       equipment.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       this.add(equipment);
       this.add(equipmentL);
       
       cardsArtifacts.setBounds(320, 0, 250, 50);
       cardsArtifacts.setForeground(Color.white);
       this.add(cardsArtifacts);
       cards.setBackground(Color.gray);
       cards.setLayout(new GridLayout(43,2,0,0));
     //  cards.setPreferredSize(new Dimension(30,1900));
       JScrollPane scrollPane = new JScrollPane(cards);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
       scrollPane.getVerticalScrollBar().setBackground(Color.gray);
       scrollPane.setBounds(250, 50, 250, 510);
       
       scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Color.DARK_GRAY;
    }
});
       
       for(int i = 0; i< 85; i++){
           String s = ""+i;
           JLabel temp = new JLabel(s);
           temp.setIcon(weaponImg);
           temp.setFocusable(false);
           temp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
          // temp.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
           temp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(temp.getText());
                }

            });
           cards.add(temp);
           
       }
       this.add(scrollPane);
      
       
       
       JScrollPane textPane = new JScrollPane(textArea);
       textArea.setEditable(false);
       textArea.setFocusable(false);
       textArea.setLineWrap(true);
       textArea.setWrapStyleWord(true);
       textArea.setBackground(Color.LIGHT_GRAY);
       textPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       textPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       textArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et magna eget quam semper bibendum in eget sapien. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent vestibulum, nulla quis pharetra luctus, nunc nulla congue mauris, quis rutrum metus ipsum non ante. Suspendisse tincidunt eu libero et lobortis. Maecenas nisi risus, rutrum a fermentum vitae, aliquet a dolor. Morbi porttitor sagittis volutpat. Praesent eleifend sed nibh non vehicula. Integer id varius lorem, sed elementum nisl. Vivamus sit amet molestie nunc. Curabitur lobortis dolor ut lectus dignissim efficitur. Proin lorem metus, sagittis non rutrum ac, condimentum at libero.\n" +
"\n" +
"Proin commodo arcu metus, ac bibendum nulla feugiat aliquam. Fusce sed neque consectetur, vestibulum nulla ut, euismod urna. Maecenas maximus posuere libero quis faucibus. Donec sed ipsum vel purus cursus euismod ut sed enim. Suspendisse sed faucibus dui. Sed lectus ex, commodo eget auctor in, commodo quis lectus. Duis porttitor odio a tristique sagittis. Cras non enim varius, vehicula dui vitae, malesuada enim.");
       
       textPane.setBounds(500, 300, 250, 250);
       textPane.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       textPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
       textPane.getVerticalScrollBar().setBackground(Color.gray);
       textPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Color.DARK_GRAY;
    }
});
       
       description.setBackground(Color.gray);
       description.setBounds(500, 50, 283, 510);
       description.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       description.add(descriptionLabel);
       descriptionLabel.setBounds(550,250, 150, 150);
       description.add(textPane);
       detailedInfoL.setBounds(600, 0, 100, 50);
       detailedInfoL.setForeground(Color.white);
      // detailedInfoL.setFont(new Font("", Font.PLAIN, 20));
       
       this.add(description);
       this.add(detailedInfoL);
       
    }
    
    

    
}
