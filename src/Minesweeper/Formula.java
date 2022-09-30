package Minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Formula {
    
    ArrayList<Clause> clauses;
    Tile instatiatedFrom;
    int size;
    int minUndecided;


    public Formula (Tile inst, Set<Set<Tile>> proto, HashMap<Tile,Literal> literals){

        this.instatiatedFrom = inst;
        this.clauses = new ArrayList<>();
        this.size = proto.size();
        this.minUndecided = Integer.MAX_VALUE;

        Iterator<Set<Tile>> it = proto.iterator();
        while(it.hasNext()){
            Set<Tile> protoClause = it.next();
            Clause realClause = new Clause();
            Iterator<Tile> it2 = protoClause.iterator();
            while(it2.hasNext()){
                Tile key = it2.next();
                realClause.addLiteral(literals.get(key));
            }
            clauses.add(realClause);
        }

    }

    public void update(){
        this.size = 0;
        Iterator<Clause> it = this.clauses.iterator();
        while(it.hasNext()){
            Clause x = it.next();
            x.update();
            if(x.undecided!=0){
                size++;
            }
            if(x.undecided<this.minUndecided){
                this.minUndecided = x.undecided;
            }
        }
    }
}
