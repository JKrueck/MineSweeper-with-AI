package Minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

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
        randMove();

        this.prepare = game.feld.getMined();
        Iterator<Tile> it = prepare.iterator();
        while(it.hasNext()){
            Tile next = it.next();
            if(next.adjacent_mines==0){
                it.remove();
            }
        }
        System.out.println(prepare.size());


        while(!gui.end){
            it = prepare.iterator();
            while(it.hasNext()){
                Tile next = it.next();
                powerSet(next, next.adjacent_mines);
            }
        }
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

    public Set<Set<Tile>> powerSet(Tile input,int size){
        Set<Set<Tile>> result = new HashSet<>();

        //Hacky for now -- will think of something smart later
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
            int [] test ={10,15};
            int test2 = test[0];

            int[] s = new int[size];
            Set<Tile> subset = new HashSet<>();
            if(size<=save.size()){
                for(int i=0;(s[i]=i)<size-1;i++);
                result.add(getSubset(save,s));
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
                    result.add(getSubset(save, s));
                }
                
            }
        }

        return result;
    }


    private Set<Tile> getSubset(ArrayList<Tile> input, int[] subset) {
        Set<Tile> result = new HashSet<>(); 
        for (int i = 0; i < subset.length; i++) 
            result.add(input.get(i));
        return result;
    }
}
