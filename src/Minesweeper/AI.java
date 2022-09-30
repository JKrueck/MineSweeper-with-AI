package Minesweeper;

import java.text.Normalizer.Form;
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
    HashMap<Tile,Literal> literals;
    ArrayList<Clause> dnf;
    ArrayList<Tile> prepare;

    public AI(GUI gui, MineSweeper game){
        this.gui = gui;
        this.game = game;
        this.literals = new HashMap<>();

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
        //Generate literals for every Tile in the game
        for(int x=0;x<game.dim;x++){
            for(int y=0;y<game.dim;y++){
                int[] coords = {x,y};
                Tile get = game.feld.getTile(coords);
                this.literals.put(get,new Literal(get));
            }
        }

        //When starting the game always do a random move first
        randMove();

        //get all mined tiles and then remove the tiles without adjacent mines
        this.prepare = game.feld.getMined();
        ArrayList<Tile> unmined = game.feld.getHidden();
        Set<Set<Set<Tile>>> protoClauses = new HashSet<>();
        ArrayList<Formula> DNF = new ArrayList<>();
        
        Iterator<Tile> it = prepare.iterator();
        while(it.hasNext()){
            Tile next = it.next();
            //As these have all been succesfully mined we can conclude trivially that they are not bombs
            Literal notABomb = literals.get(next);
            notABomb.setFlag(truth.FALSE);
            //remove the tiles without adjacent tiles from our consideration
            if(next.adjacent_mines==0){
                it.remove();
            }else{
                DNF.add(new Formula(next, kSubset(next, next.adjacent_mines), literals));
                //protoClauses.add(kSubset(next, next.adjacent_mines));
            }
        }
        while(true){
            //List to store all literals which need to be bombs
            Set<Literal> markThis = new HashSet<>();
            ArrayList<Formula> unary = findUnary(DNF,markThis);
            if(unary.size()!=0){
            newFlag(DNF, markThis);
            
            int stuff = 1335;
            }else{
                int stuff = 1336;
            }

            System.out.println(prepare.size());
        }
        
        /*it = unmined.iterator();
        HashMap<Tile,Literal> minLiteralList = new HashMap<>();
        while(it.hasNext()){
            Tile x = it.next();
            minLiteralList.put(x,new Literal(x));
        }

        //Absolutely atrocious for now; pls pls fix later 
        Iterator<Set<Set<Tile>>> outerIt = protoClauses.iterator();
        ArrayList<Clause> DNF = new ArrayList<>();
        while(outerIt.hasNext()){
            Set<Set<Tile>> outer = outerIt.next();
            Iterator<Set<Tile>> middleIt = outer.iterator();
            while(middleIt.hasNext()){
                Set<Tile> middle = middleIt.next();
                Clause realClause = new Clause();
                Iterator<Tile> innerIt = middle.iterator();
                while(innerIt.hasNext()){
                    Tile innerLiteral = innerIt.next();
                    Literal toAdd = minLiteralList.get(innerLiteral);
                    realClause.addLiteral(toAdd);
                }
                if(identifyDupes(DNF, realClause)){
                    DNF.add(realClause);
                }
                
            }
        }

        ArrayList<Clause> unaryC = new ArrayList<>();
        for(int i=0;i<DNF.size();i++){
            if(DNF.get(i).undecided==1){
                unaryC.add(DNF.get(i));
            }
        }

        HashMap<Literal,Boolean> decisionMemory = new HashMap<>();



        //I HAVE A DNF NOT A CNF -> PROBABLY NEED A CLASS FOR DNF FOR ÜBERSICHT 
        // use better inference

        for(int i=0;i<unaryC.size();i++){
            unaryC.get(i).getLiteral().decision = Literal.truth.TRUE;
            ArrayList<Clause> toEvaluate = new ArrayList<>();
            evalCheck(DNF,toEvaluate);
            boolean conflict = evalClauses(toEvaluate);
        }
*/

        //int stopHere=0;

        














        //main AI loop
        /*while(!gui.end){
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
            ArrayList<Clause> unary = findUnary(store);
            //if there are unary Clauses determine them as mines
            while(unary.size()!=0){
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
                unary = findUnary(store);
            }
           int skip = 0;

           Byte test1 = 0;
           Byte test2 = 1;
           System.out.println(Integer.toBinaryString(test1&test2));
            
        }*/
    }


    private boolean evalClauses(ArrayList<Clause> list){

        for(int i=0;i<list.size();i++){
            Set<Literal> eval = list.get(i).getLiterals();
            if(eval.size() == 1){
                if(list.get(i).getLiteral().decision == Literal.truth.TRUE){
                    return false;
                }else{
                    return true;
                }
            }
        }

        return true;
    }

    private void evalCheck(ArrayList<Clause> dnf, ArrayList<Clause> list){
        for(int i=0;i<dnf.size();i++){
            Clause check= dnf.get(i);
            check.selfCheck();
            if(check.undecided==0){
                list.add(check);
            }
        }
    }

    private boolean identifyDupes(ArrayList<Clause> dnf, Clause check){
        for(int i=0;i<dnf.size();i++){
            Clause compare = dnf.get(i);
            Set<Literal> compareDNF = compare.getLiterals();
            Set<Literal> compareClause = check.getLiterals();
            if(compareClause.equals(compareDNF)){
                return false;
            }
        }
        return true;

    }


    private void newFlag(ArrayList<Formula> DNF, Set<Literal> markThis){
        
        
        Iterator<Literal> litIt = markThis.iterator();
        while(litIt.hasNext()){
            //change the literal values
            Literal bomb = litIt.next();
            bomb.decision = truth.TRUE;
            bomb.comparison = 1;
            //flag the tile in the gui
            this.gui.flagTile(bomb.represents.coordinates);
            //update the literals for every formula
            DNF.forEach((dnf)->dnf.update());

            //remove true and false formulas by logic
            Iterator<Formula> formIt = DNF.iterator();
            while(formIt.hasNext()){
                Formula x = formIt.next();
                if(x.size == 0){
                    formIt.remove();
                }
                //So future me here is my best explanation: if the tile a dnf formula is instantiated from, 
                //contains the tile we have determined as a bomb, we then go trough all clauses and delete those
                //that don't contain the right literal. We do this by checking if the "undecided" variable has decreased
                //i hope this actually works-> seems to; still horrible because three fucking loops 
                if(x.instatiatedFrom.getAdjacentTiles().contains(bomb.represents)){
                    Iterator<Clause> claIt = x.clauses.iterator();
                    while(claIt.hasNext()){
                        Clause checkMYASS = claIt.next();
                        if(checkMYASS.undecided > x.minUndecided){
                            claIt.remove();
                            x.update();
                        }
                    }
                }
            }
        }
        
        
        
        
        /*Iterator<ArrayList<Clause>> it = dnf.iterator();
        while(it.hasNext()){
            ArrayList<Clause> x = it.next();
            //NEED TO FIND OUT TO WHICH TILE THIS BELONGS SO I CAN ONLY REMOVE IT IFF flaggedTile IS ADJACENT TO IT
            Iterator<Clause> fuckNames = x.iterator();
            while(fuckNames.hasNext()){
                Clause y = fuckNames.next();
                
                Set<Literal> containsCheck = y.getLiterals();
                Iterator<Literal> checkIterator = containsCheck.iterator();
                boolean flag = false;
                
                while(checkIterator.hasNext()){
                    Literal adjacencyCheck = checkIterator.next();
                    if(adjacencyCheck.represents.getAdjacentTiles().contains(flaggedTile)){
                        flag = true;
                        break;
                    }else{
                        //return;
                    }
                }

                boolean test = !y.setFlag(flaggedTile, inputValue);
                if(test && flag){
                    fuckNames.remove();
                    System.out.println("removed");
                }
            }
        }*/
        int comehere=10;
    }

    private ArrayList<Formula> findUnary(ArrayList<Formula> dnf, Set<Literal> save ){  
        ArrayList<Formula> result = new ArrayList<>();

        for(int i=0;i<dnf.size();i++){
            Formula toCheck = dnf.get(i);
            if(toCheck.size==1){
                //make a function for this in formula class
                Clause checkClause = toCheck.clauses.get(0);
                if(checkClause.undecided==1){
                    result.add(toCheck);
                    save.add(checkClause.getLiteral());
                }
            }
        }

        return result;
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
