import javax.swing.*;

import MineSweeper.MineSweeper;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;


import java.awt.Color;
public class test1 extends JFrame implements ActionListener {
    JButton fertig;
    JTextField text1;
    JTextField text2;
    JPanel panel;
    JLabel label1;
    JLabel label2;
    JCheckBox KI;
    JComboBox dropdown;

    private JPanel textpanel;
    private JPanel labelpanel;

    public test1(){

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        textpanel = new JPanel();
        labelpanel = new JPanel();
        panel.setLayout(new BorderLayout());
        textpanel.setLayout(new GridLayout(3,1));
        labelpanel.setLayout(new GridLayout(2,1));
        

        this.setTitle("hewwo");
        this.setSize(300,300);

        
        fertig = new JButton("Fertig");
        label1 = new JLabel("Dimensionen:");
        KI = new JCheckBox("KI");
        String alter []={"5x5","10x10"};
        dropdown = new JComboBox(alter);

        fertig.addActionListener(this);
    
        panel.add(fertig,BorderLayout.PAGE_END);
        textpanel.add(dropdown);
        textpanel.add(KI);
        labelpanel.add(label1);

        panel.add(labelpanel,BorderLayout.WEST);
        panel.add(textpanel,BorderLayout.CENTER);

        this.add(panel);
        //pack();

    }
    public static void main(String[] args) {
        test1 test = new test1();
       //test.setVisible(true);
        MineSweeper game=new MineSweeper();
        game.gameloop();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.fertig){
            changeToGame(dropdown.getSelectedItem());
        }
        
    }

    private void changeToGame(Object dim){
        System.out.println(dim);
    }
}