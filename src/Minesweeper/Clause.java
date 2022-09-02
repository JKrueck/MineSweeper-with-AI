package Minesweeper;

import java.util.ArrayList;

public class Clause {
    
    private ArrayList<Literal> c;
    int mines;

    public Clause(ArrayList<Tile> input, int boom){
        this.c = new ArrayList<Literal>();
        for(int i=0;i<input.size();i++){
            c.add(new Literal(input.get(i)));
        }
        this.mines=boom;

    }

    public ArrayList<Literal> getLiterals(){
        return this.c;
    }
}

