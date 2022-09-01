package Minesweeper;
import javax.swing.*;

import java.util.HashMap;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Dimension;


import java.awt.Color;
public class test1 extends JFrame implements ActionListener {

    JPanel MainPanel;
    JPanel HeaderPanel;


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
	//private final Color DEFAULT_FG_COLOR = new Color(119, 110, 101);
	private final Color STATE_BG_COLOR = new Color(187, 173, 160);
	private final Color EMPTY_TILE_COLOR = new Color(205, 192, 180);
    private final Color MINED_TILE_COLOR = new Color(205, 180, 193);
	//private final Color STATE_FG_COLOR = new Color(236, 225, 209);

    private JPanel textpanel;
    private JPanel labelpanel;

    String chosenOption;

    test1 test;

    int W,H;
    
    JDialog selectStuff;

    JPopupMenu pop = new JPopupMenu();
    JRadioButtonMenuItem radioButtonItem;
    JRadioButtonMenuItem radioButtonItem2;

    HashMap<JPanel,int[]> TileLoc = new HashMap<JPanel,int[]>();

    public test1(){
        
        this.selectStuff = createSelectDialog(); // create the game option dialog
        addStuff(selectStuff);                   // add everything to the dialog
        this.radioButtonItem = new JRadioButtonMenuItem //not used yet
            ("Mine",false);
        this.radioButtonItem2 = new JRadioButtonMenuItem//not used yet
            ("Flag",false);
        pop.add(radioButtonItem); //add "mine" button to a popup menu
        pop.add(radioButtonItem2);//add "flag" button to popup menu
        initializeGameFrame();  //initialize the Main JFrame
        //game.gameloop();
    }
    public static void main(String[] args) {
        test1 select = new test1(); //create new test1 class instance
        select.setVisible(true);    // make it visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {    //action listener for settings menu
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
        
        //open option dialog
        selectStuff.setVisible(true);

        //open game window
        this.MainPanel = createMainPanel();
        mainframe.getContentPane().add(MainPanel);

        this.HeaderPanel = createHeaderPanel();
        mainframe.getContentPane().add(HeaderPanel,BorderLayout.NORTH);

        Gamelabel = new JLabel("MineSweeper",SwingConstants.CENTER);
        HeaderPanel.add(Gamelabel);

        this.game=new MineSweeper(W);
       

        JPanel BoardPanel = createBoardPanel(W,H);
        MainPanel.add(BoardPanel, BorderLayout.CENTER);

        Tiles = new JLabel[W][H];
        for (int i=0;i<H;i++){            //create Tiles on GUI
            for(int j=0;j<W;j++){

                JPanel TilePanel = createTilePanel();   //create the Panel

                int[] save = new int[]{j,i};            //create an array to safe the coordinates of the Panel
                TileLoc.put(TilePanel,save);            // safe it in a Hashmap

                JLabel TileLabel = new JLabel(" ",SwingConstants.CENTER);   //create Label for the Panel

                TileLabel.addMouseListener(new MouseAdapter(){  	//create event listener
                    @Override
                    public void mouseReleased(MouseEvent e){
                        if(e.getButton()== MouseEvent.BUTTON1){

                            int[] chosen =  TileLoc.get(TilePanel); // get coordinates of clicked Tile

                            if(game.feld.getTile(chosen).mineTile()){
                                if(game.feld.getTile(chosen).adjacent_mines==0){
                                    game.feld.getTile(chosen).mineAdjacent(game.feld, game.dim);
                                }else{
                                    game.feld.getTile(chosen).mineTile();
                                }
                            }else{
                                endGame(chosen,TilePanel);
                            }
                            
                            updateGame(game);
                            
                          
                        }else if(e.getButton()== MouseEvent.BUTTON3){
                            //FLAG
                            int[] chosen =  TileLoc.get(TilePanel);
                            Tiles[chosen[0]][chosen[1]].getParent().setBackground(Color.green);
                            game.feld.getTile(chosen).flagTile();
                        }

                        if(game.checkWin()){
                            winGame(TilePanel);
                        }
                    }

                });


                Tiles[j][i] = TileLabel;        //No clue

                TilePanel.add(TileLabel);       //add the Label to the Panel
                BoardPanel.add(TilePanel);      //add the Panel to the Gameboard
            }
        }

        mainframe.pack();

        mainframe.setSize(new Dimension(600, 700));
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainframe.setLocationRelativeTo(null);

        mainframe.setVisible(true);
    }


    public void updateGame(MineSweeper game){
        for(int x=0;x<game.dim;x++){
            for(int y=0;y<game.dim;y++){
                int [] coords = {y,x};
                Tile aktuell = game.feld.getTile(coords);
                if(!aktuell.mine & aktuell.mined){
                    if(aktuell.adjacent_mines==0){
                        Tiles[y][x].getParent().setBackground(MINED_TILE_COLOR);
                    }else{
                        Tiles[y][x].setText(Integer.toString(aktuell.adjacent_mines));
                        Tiles[y][x].getParent().setBackground(MINED_TILE_COLOR);
                    }
                }else{
                    //
                }
                
            }
        }
    }

    private void endGame(int[] Bomb, JPanel back){
        Tiles[Bomb[0]][Bomb[1]].getParent().setBackground(Color.red);
        back.getParent().setBackground(Color.orange);
        this.Gamelabel.setText("GAME OVER");
        this.HeaderPanel.revalidate();
    }

    private void winGame(JPanel back){
        back.getParent().setBackground(Color.green);
        this.Gamelabel.setText("YOU WON");
        this.HeaderPanel.revalidate();
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

    
}