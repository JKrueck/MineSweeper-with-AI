package Minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Clause {
    
    private Set<Literal> c;
    int mines;

    public Clause(Set<Tile> input, int boom){
        this.c = new HashSet<>();

        Iterator<Tile> it = input.iterator();
        while(it.hasNext()){
            c.add(new Literal(it.next()));
        }
        this.mines=boom;

    }

    public Set<Literal> getLiterals(){
        return this.c;
    }
}

