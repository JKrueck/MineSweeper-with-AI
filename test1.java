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


    private final JFrame mainframe= new JFrame("MineSweeper");
    private JLabel Tiles [][];
    private JLabel FlagLabel;
    private JLabel Gamelabel;

    private final Color OUTER_BG_COLOR = new Color(250, 248, 239);
	private final Color DEFAULT_FG_COLOR = new Color(119, 110, 101);
	private final Color STATE_BG_COLOR = new Color(187, 173, 160);
	private final Color EMPTY_TILE_COLOR = new Color(205, 192, 180);
	private final Color STATE_FG_COLOR = new Color(236, 225, 209);

    private JPanel textpanel;
    private JPanel labelpanel;

    public test1(){
        initialize();
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

    private void initialize(){
        JPanel MainPanel = createMainPanel();
        mainframe.getContentPane().add(MainPanel);

        JPanel HeaderPanel = createHeaderPanel();
        mainframe.getContentPane().add(HeaderPanel,BorderLayout.NORTH);

        Gamelabel = new JLabel("MineSweeper",SwingConstants.CENTER);
        HeaderPanel.add(Gamelabel);
    }

    private JPanel createMainPanel() {
		JPanel MainPanel = new JPanel();
		MainPanel.setLayout(new BorderLayout(0, 0));
		MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		MainPanel.setBackground(OUTER_BG_COLOR);
		return MainPanel;
	}

    private JPanel createHeaderPanel() {
		JPanel HeaderPanel = new JPanel();
		HeaderPanel.setLayout(new GridLayout(1, 0, 0, 0));
		HeaderPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		HeaderPanel.setBackground(OUTER_BG_COLOR);
		return HeaderPanel;
	}
}