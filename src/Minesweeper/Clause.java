package Minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Minesweeper.Literal.truth;

import java.util.Iterator;

public class Clause {
    
    private Set<Literal> c;
    private Set<Tile> saveThis;
    int undecided;
    //Tile initiatedFrom;

    public Clause(){
        
        this.c = new HashSet<>();
        //c.add(input);
        this.undecided = 0;
        
        
        //this.saveThis = input;

        


    }

    public void addLiteral(Literal add){
        c.add(add);
        undecided++;
    }


    public void selfCheck(){
        this.undecided = 0;
        Iterator<Literal> it = this.c.iterator();
        while(it.hasNext()){
            Literal x = it.next();
            if(x.decision==truth.IDK){
                this.undecided++;
            }
        }
    }

    public Set<Literal> getLiterals(){
        return this.c;
    }

    public Literal getLiteral(){
        if(this.undecided==1){
            Iterator<Literal> it = this.c.iterator();
            while(it.hasNext()){
                Literal x = it.next();
                if(x.decision==truth.IDK){
                    return x;
                }
            }
            try {
                throw new Exception("no undecided literal left");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else{
            try {
                throw new Exception("more than one undecided Literal in this clause");
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
                if(x.represents.equals(inputTile)){
                    x.decision = inputValue;
                    update();
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

    private void update(){
        Iterator<Literal> it = this.c.iterator();
        int count=0;
        while(it.hasNext()){
            Literal x = it.next();
            if(x.decision==truth.IDK){
                count++;
            }
        }
        this.undecided = count;
    }
}

