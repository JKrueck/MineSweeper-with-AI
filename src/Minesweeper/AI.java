package Minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


import javax.swing.JPanel;

import Minesweeper.Literal.truth;

public class AI {
    GUI gui;
    MineSweeper game;
    HashMap<Integer[],Boolean> isBomb;
    ArrayList<Clause> dnf;
    ArrayList<Tile> prepare;

    public AI(GUI gui, MineSweeper game){
        this.gui = gui;
        this.game = game;

        /*this.dnf = game.getClauses();
        Iterator<Clause> it = dnf.iterator();
       while(it.hasNext()){
        Clause x = it.next();
        if(x.mines==0){
            it.remove();
        }
       }*/

        run();
    }

    

    public void run(){
        //When starting the game always do a random move first
        randMove();

        //get all mined tiles and then remove the tiles without adjacent mines
        this.prepare = game.feld.getMined();
        Iterator<Tile> it = prepare.iterator();
        while(it.hasNext()){
            Tile next = it.next();
            if(next.adjacent_mines==0){
                it.remove();
            }
        }
        System.out.println(prepare.size());

        //main AI loop
        while(!gui.end){
            //iterator for the tiles with adjacent mines
            it = prepare.iterator();
            //HashSet for the "DNF"
            Set<Set<Set<Tile>>> dnf = new HashSet<>();
            //generate all possible mine combinations for the tiles with adjacent mines
            while(it.hasNext()){
                Tile next = it.next();
                dnf.add(kSubset(next, next.adjacent_mines));
            }
            
            Iterator<Set<Set<Tile>>> it2 = dnf.iterator();
            //real DNF-> change variable names to be less confusing pls
            ArrayList<ArrayList<Clause>> store = new ArrayList<>();
            while(it2.hasNext()){
                //Clause iterator
                Set<Set<Tile>> x = it2.next();
                //Literal iterator
                Iterator<Set<Tile>> it3 = x.iterator();
                ArrayList<Clause> DNF = new ArrayList<>();
                //Create a new Clause object
                while(it3.hasNext()){
                    Set<Tile> y = it3.next();
                    Clause clause= new Clause(y, y.size());
                    DNF.add(clause);
                }
                store.add(DNF);
            }
            ArrayList<Integer> sizeList = new ArrayList<>();
            ArrayList<Clause> unary = new ArrayList<>();
            for(int i=0;i<store.size();i++){
                int size = store.get(i).size();
                if(size==1){
                    if(store.get(i).get(0).mines==1){
                        unary.add(store.get(i).get(0));
                    }
                    sizeList.add(store.get(i).size());
                }else{
                    sizeList.add(store.get(i).size());
                }
            }
            //if there are unary Clauses determine them as mines
            if(unary.size()!=0){
                for(int i=0;i<unary.size();i++){
                    Literal flag = unary.get(i).getLiteral();
                    if(flag.value == truth.IDK){
                        flag.value = truth.FALSE;
                        flag.wild = 0;


                        newFlag(store,truth.FALSE,flag.tile);
                        
                        
                        gui.flagTile(flag.tile.coordinates);
                        gui.updateGame(game);
                        //System.out.println(flag.wild);
                    }  
                }
            }
           int skip = 0;

           Byte test1 = 0;
           Byte test2 = 1;
           System.out.println(Integer.toBinaryString(test1&test2));
            
        }
    }

    private void newFlag(ArrayList<ArrayList<Clause>> dnf, truth inputValue, Tile flaggedTile){
        Iterator<ArrayList<Clause>> it = dnf.iterator();
        while(it.hasNext()){
            ArrayList<Clause> x = it.next();
            //NEED TO FIND OUT TO WHICH TILE THIS BELONGS SO I CAN ONLY REMOVE IT IFF flaggedTile IS ADJACENT TO IT
            Iterator<Clause> fuckNames = x.iterator();
            while(fuckNames.hasNext()){
                Clause y = fuckNames.next();
                if(!y.setFlag(flaggedTile, inputValue)&&y.){
                    fuckNames.remove();
                    System.out.println("removed");
                }
            }
        }
    }

    private boolean findUnary(ArrayList<Integer> sizes, ArrayList<ArrayList<Clause>> dnf ){
        //sizes.find

        return true;
    }

    private void randMove(){
        Random rand = new Random();
        int x = rand.nextInt(game.dim);
        int y = rand.nextInt(game.dim);
        int[] coords = {0,0};
        if(game.feld.getTile(coords).mineTile()){
            if(game.feld.getTile(coords).adjacent_mines==0){
                game.feld.getTile(coords).mineAdjacent(game.feld, game.dim);
            }else{
                game.feld.getTile(coords).mineTile();
            }
        }else{
            JPanel need = gui.TileLocInv.get(coords);
            this.gui.endGame(coords,need);
        }
        gui.updateGame(game);
    }

    public Set<Set<Tile>> kSubset(Tile input,int size){
        Set<Set<Tile>> result = new HashSet<>();

        if(size==1){
            Iterator<Tile> it = input.getAdjacentTiles().iterator();
            while(it.hasNext()){
                Tile x = it.next();
                if(!x.mined){
                    Set<Tile> tmp = new HashSet<>();
                    tmp.add(x);
                    result.add(tmp);
                }
            }
        }else {
            ArrayList<Tile> save = input.getAdjacentTiles();
            Iterator<Tile> it = save.iterator();
            while(it.hasNext()){
                Tile x = it.next();
                if(x.mined){
                    it.remove();
                }
            }
            int[] s = new int[size];
            Set<Tile> subset = new HashSet<>();
            if(size<=save.size()){
                for(int i=0;(s[i]=i)<size-1;i++);
                Set<Tile> tmp = getSubset(save,s); 
                result.add(tmp);
                for(;;) {
                    int i;
                    for (i = size - 1; i >= 0 && s[i] == save.size() - size + i; i--); 
                    if (i < 0) {
                        break;
                    }
                    s[i]++;                    // increment this item
                    for (++i; i < size; i++) {    // fill up remaining items
                        s[i] = s[i - 1] + 1; 
                    }
                    tmp = getSubset(save,s);
                    result.add(tmp);
                }
                
            }
        }

        return result;
    }


    private Set<Tile> getSubset(ArrayList<Tile> input, int[] subset) {
        Set<Tile> result = new HashSet<>(); 
        for (int i = 0; i < subset.length; i++){
            result.add(input.get(subset[i]));
            //System.out.print(subset[i]+" ");
        }
        //System.out.println("\n");
        return result;
    }
}
