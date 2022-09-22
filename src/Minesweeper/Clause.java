package Minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Clause {
    
    private Set<Literal> c;
    private Set<Tile> saveThis;
    int mines;

    public Clause(Set<Tile> input, int boom){
        this.c = new HashSet<>();
        this.saveThis = input;

        Iterator<Tile> it = input.iterator();
        while(it.hasNext()){
            c.add(new Literal(it.next()));
        }
        this.mines=boom;

    }

    public Set<Literal> getLiterals(){
        return this.c;
    }

    public Literal getLiteral(){
        if(this.c.size()==1){
            Iterator<Literal> it = this.c.iterator();
            return it.next();
        }else{
            try {
                throw new Exception("more than one Literal in this clause");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public boolean setFlag(Tile inputTile, Literal.truth inputValue){
        if(saveThis.contains(inputTile)){
            Iterator<Literal> it = this.c.iterator();
            while(it.hasNext()){
                Literal x = it.next();
                if(x.tile.equals(inputTile)){
                    x.value = inputValue;
                    //System.out.println("Set flag on "+ x.tile.coordinates.toString());
                }
            }
            return true;
        }else{
            return false;
        }

        
    }

    public int getSize(){
        return this.c.size();
    }
}

