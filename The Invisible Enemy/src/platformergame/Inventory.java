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

       
    public void initComponents(){
     weaponSelectedImg = new ImageIcon(Inventory.class.getResource("/Images/GunLvl1.png"));
     weaponImg = new ImageIcon(Inventory.class.getResource("/Images/GunLvl1icon.png"));
     maskSelectedImg = new ImageIcon(Inventory.class.getResource("/Images/maskSelected.png"));
     maskImg = new ImageIcon(Inventory.class.getResource("/Images/maskSelected.png"));
     skillImg = new ImageIcon(Inventory.class.getResource("/Images/skillIcon.png"));
     arrowUp = new ImageIcon(Inventory.class.getResource("/Images/ArrowUp.png"));
     arrowDown = new ImageIcon(Inventory.class.getResource("/Images/ArrowDown.png"));
    
     selected = new JLabel();
     weapon = new JLabel();
     skill = new JLabel();
     mask = new JLabel();
     descriptionLabel = new JLabel("Description");
     weaponUpIcon = new JLabel(arrowUp);
     weaponDownIcon = new JLabel(arrowDown);
     maskUpIcon = new JLabel(arrowUp);
     maskDownIcon = new JLabel(arrowDown);
     skillUpIcon = new JLabel(arrowUp);
     skillDownIcon = new JLabel (arrowDown);
     cardsArtifacts = new JLabel ("Cards & Artifacts");
     equipmentL = new JLabel("Equipment");
     detailedInfoL = new JLabel("Deitailed Info");
    
    
     weaponUp = new JButton();
     weaponDown = new JButton();
    
     equipment = new JPanel();
     cards = new JPanel();
     description = new JPanel();
     textArea = new JTextArea(20,20);
    }
       
    public Inventory(){
        initComponents();
       
       this.setBackground(Color.DARK_GRAY);
       this.setVisible(false);
       this.setLayout(null);
       
       selected.setIcon(weaponImg);
       selected.setVisible(true);
       selected.setBounds(100,0, 62, 62);
       
       weapon.setIcon(weaponImg);
       weapon.setVisible(true);
       weapon.setBounds(25, 58, 62, 62);
       weaponUpIcon.setBounds(30, 35, 50, 50);
       weaponDownIcon.setBounds(30, 95, 50, 50);
      
       mask.setIcon(maskImg);
       mask.setVisible(true);
       mask.setBounds(110,58,62,62);
       maskUpIcon.setBounds(105, 35, 50, 50);
       maskDownIcon.setBounds(105, 95, 50, 50);
       
       skill.setIcon(skillImg);
       skill.setVisible(true);
       skill.setBounds(185, 58, 62, 62);
       skillUpIcon.setBounds(180, 35, 50, 50);
       skillDownIcon.setBounds(180, 95, 50, 50);
       
       equipmentL.setBounds(350, 0, 100, 50);
       equipmentL.setForeground(Color.white);
      
       
       equipment.setLayout(null);
       equipment.setBackground(Color.gray);
       equipment.setBounds(250,25,250,135);
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
       this.add(equipmentL);
       this.add(equipment);
       
       
       cardsArtifacts.setBounds(570, 0, 250, 50);
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
       scrollPane.setBounds(500, 25, 250, 135);
       
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
       
       textPane.setBounds(0, 0, 250, 135);
       textPane.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       textPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
       textPane.getVerticalScrollBar().setBackground(Color.gray);
       textPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Color.DARK_GRAY;
    }
});
       description.setLayout(null);
       description.setBackground(Color.gray);
       description.setBounds(750, 25, 250, 135);
       description.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       description.add(descriptionLabel);
       descriptionLabel.setBounds(550,250, 150, 150);
       description.add(textPane);
       detailedInfoL.setBounds(835, 0, 100, 50);
       detailedInfoL.setForeground(Color.white);
      // detailedInfoL.setFont(new Font("", Font.PLAIN, 20));
       this.add(detailedInfoL);
       this.add(description);
       this.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
       
    }
    
    
private ImageIcon weaponSelectedImg;
    private ImageIcon weaponImg;
    private ImageIcon maskSelectedImg;
    private ImageIcon maskImg;
    private ImageIcon skillImg;
    private ImageIcon arrowUp;
    private ImageIcon arrowDown;    
    private JLabel selected;
    private JLabel weapon;
    private JLabel skill;
    private JLabel mask;
    private JLabel descriptionLabel;
    private JLabel weaponUpIcon;
    private JLabel weaponDownIcon;
    private JLabel maskUpIcon;
    private JLabel maskDownIcon;
    private JLabel skillUpIcon;
    private JLabel skillDownIcon;
    private JLabel cardsArtifacts;
    private JLabel equipmentL;
    private JLabel detailedInfoL;
    private JButton weaponUp;
    private JButton weaponDown;    
    private JPanel equipment;
    private JPanel cards;
    private JPanel description;
    private JTextArea textArea;
    
}
