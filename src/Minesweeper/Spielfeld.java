package Minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import Minesweeper.Tile;

public class Spielfeld {
    Tile [][] feld;
    int dimension;
    private List<Tile> bombs = new ArrayList<Tile>();
    private int bombAmount;

    public Spielfeld(int dim, int boom){
        this.bombAmount = boom;
        Random rand=new Random(1337);
        int needsPlacement = boom;
        this.dimension=dim-1;
        this.feld= new Tile[dim][dim];

        for (int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                int[]coords={i,j};
                feld[i][j]=new Tile(false,coords);          
            }
        }

        while(needsPlacement!=0){
            int x = rand.nextInt(dim);
            int y = rand.nextInt(dim);
            if(!this.feld[x][y].mine){
                this.feld[x][y].mine = true;
                bombs.add(feld[x][y]);
                needsPlacement--;
            }
        }
        check();
    }
    public Tile getTile(int[]coords){
        return  feld[coords[0]][coords[1]];
    }

    //Returns all Tiles that have already been mined
    public ArrayList<Tile> getMined(){
        ArrayList<Tile> result = new ArrayList<>();
        for (int i=0;i<this.dimension+1;i++){
            for(int j=0;j<this.dimension+1;j++){
                int[]coords={i,j};
                if(getTile(coords).mined){
                    result.add(getTile(coords));
                }         
            }
        }
        return result;
    }

    //Returns all umined tiles
    public ArrayList<Tile> getHidden(){
        ArrayList<Tile> result = new ArrayList<>();
        for (int i=0;i<this.dimension+1;i++){
            for(int j=0;j<this.dimension+1;j++){
                int[]coords={i,j};
                if(!getTile(coords).mined){
                    result.add(getTile(coords));
                }         
            }
        }
        return result;
    }

    public List<Tile> getBombs(){
        return this.bombs;
    }
    private void check(){
        for (int i=0;i<=dimension;i++){
            for(int j=0;j<=dimension;j++){
                int[]coords={i,j};
                Tile aktuell = getTile(coords);
                aktuell.checkAdjacent(this,dimension,coords);
            }
        }
    }

   
    
}
