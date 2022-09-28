package Minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Formula {
    
    ArrayList<Clause> clauses;
    Tile instatiatedFrom;


    public Formula (Tile inst, Set<Set<Tile>> proto, HashMap<Tile,Literal> literals){

        this.instatiatedFrom = inst;
        this.clauses = new ArrayList<>();

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
}
