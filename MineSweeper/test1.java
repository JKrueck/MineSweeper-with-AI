package MineSweeper;
import javax.swing.*;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;


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

    MineSweeper game;


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

    String chosenOption;

    test1 test;

    int W,H;
    
    JDialog selectStuff;

    JPopupMenu pop = new JPopupMenu();
    JRadioButtonMenuItem radioButtonItem;
    JRadioButtonMenuItem radioButtonItem2;

    public test1(){
        this.game=new MineSweeper();
        this.selectStuff = createSelectDialog();
        addStuff(selectStuff);
        this.radioButtonItem = new JRadioButtonMenuItem
            ("Mine",false);
        this.radioButtonItem2 = new JRadioButtonMenuItem
            ("Flag",false);
        pop.add(radioButtonItem);
        pop.add(radioButtonItem2);
        initializeGameFrame();
        //game.gameloop();
    }
    public static void main(String[] args) {
        test1 select = new test1();
        select.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.fertig){
            String dims = (String) dropdown.getSelectedItem();

            switch(dims){
                case("5x5"):
                    W = 5;
                    H = 5;
                    break;
                case("10x10"):
                    W = 10;
                    H = 10;
                    break;
                default:
                    throw new IllegalArgumentException("Arsch verfickt");
            }
        }
        this.selectStuff.dispose();
        
    }
    private void initializeGameFrame(){
        
        selectStuff.setVisible(true);


        
        

        JPanel MainPanel = createMainPanel();
        mainframe.getContentPane().add(MainPanel);

        JPanel HeaderPanel = createHeaderPanel();
        mainframe.getContentPane().add(HeaderPanel,BorderLayout.NORTH);

        Gamelabel = new JLabel("MineSweeper",SwingConstants.CENTER);
        HeaderPanel.add(Gamelabel);


       

        JPanel BoardPanel = createBoardPanel(W,H);
        MainPanel.add(BoardPanel, BorderLayout.CENTER);

        Tiles = new JLabel[W][H];
        for (int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                int [] coords={j,i};
                JPanel TilePanel = createTilePanel();
                JLabel TileLabel = new JLabel("", SwingConstants.CENTER);
                TileLabel.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseReleased(MouseEvent e){
                        if(e.isPopupTrigger() || e.getButton()== MouseEvent.BUTTON3){
                            pop.setLocation(TilePanel.getLocation());
                            pop.setVisible(true);
                            if(radioButtonItem.isSelected()){
                                //SEND STATUS
                                game.feld.getTile(coords).mineTile();
                                updateGame(game);
                                pop.setVisible(false);
                            }
                            if(radioButtonItem2.isSelected()){
                                //SEND STATUS
                                pop.setVisible(false);
                            }
                        }
                    }

                });
                Tiles[j][i] = TileLabel;
                TilePanel.add(TileLabel);
                BoardPanel.add(TilePanel);
            }
        }

        mainframe.pack();

        mainframe.setSize(new Dimension(600, 700));
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainframe.setLocationRelativeTo(null);

        mainframe.setVisible(true);
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

    private JPanel createBoardPanel(int W, int H) {
		JPanel BoardPanel = new JPanel();
		BoardPanel.setLayout(new GridLayout(H, W, 10, 10));
		BoardPanel.setBackground(STATE_BG_COLOR);
		BoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 7, 9));
		return BoardPanel;
	}

    private JPanel createTilePanel() {
		JPanel TilePanel = new JPanel();
		TilePanel.setLayout(new GridLayout(1, 1, 10, 10));
		TilePanel.setBackground(EMPTY_TILE_COLOR);
		return TilePanel;
	}

    private JDialog createSelectDialog(){
        JDialog select = new JDialog();
        select.setTitle("MineSweeper");
        select.setSize(200,300);
        select.setModal(true);
        return select;
    }

    private void addStuff(JDialog dialog){
        textpanel = new JPanel();
        labelpanel = new JPanel();
        dialog.setLayout(new BorderLayout());
        textpanel.setLayout(new GridLayout(3,1));
        labelpanel.setLayout(new GridLayout(2,1));
        
        fertig = new JButton("Fertig");
        label1 = new JLabel("Dimensionen:");
        KI = new JCheckBox("KI");
        String alter []={"5x5","10x10"};
        dropdown = new JComboBox(alter);

        fertig.addActionListener(this);
    
        dialog.add(fertig,BorderLayout.PAGE_END);
        textpanel.add(dropdown);
        textpanel.add(KI);
        labelpanel.add(label1);

        dialog.add(labelpanel,BorderLayout.WEST);
        dialog.add(textpanel,BorderLayout.CENTER);
    }

    public void updateGame(MineSweeper game){
        for(int x=0;x<game.dim;x++){
            for(int y=0;y<game.dim;y++){
                int [] coords = {y,x};
                Tile aktuell = game.feld.getTile(coords);
                if(!aktuell.mine){
                    Tiles[y][x].setText(Integer.toString(aktuell.adjacent_mines));
                }else{
                    return;
                }
                
            }
        }
    }

    
}