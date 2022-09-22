package Minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    //true if the tile is a mine
    boolean mine;
    //true if the tile has been flagged
    boolean flagged;
    //true if the tile has been mined
    boolean mined;
    public int [] coordinates;
    public int adjacent_mines;
    private ArrayList <Tile> adjacentTiles = new ArrayList<Tile>();
    private Clause possible;

    public Tile(boolean isMine,int[] coords){
        this.mine=isMine;
        this.coordinates=coords;
        this.flagged=false;
        this.adjacent_mines=0;
        this.mined=false;
    }

    public ArrayList <Tile> getAdjacentTiles(){
        return this.adjacentTiles;
    }

    public Clause getClause(){
        return this.possible;
    }

    public boolean flagTile(){
        if(!mined){
            this.flagged=true;
            return true;
        }
        return false;
    }

    public void unFlagTile(){
        if(!mined){
            this.flagged=false;
        }
    }

    public boolean mineTile(){
        if(mine){
            return false;
        }else{
            this.mined=true;
            return true;
        }
    }

    public void checkAdjacent(Spielfeld feld,int dimension,int []coords){
        ArrayList<Tile> save = new ArrayList<>();
        if(coords[0]-1>=0){
            coords[0]--;
            Tile aktuell=feld.getTile(coords);
            //Add the adjacent tile to a list so we need this hack only once
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]++;
        }
        if(coords[0]+1<=dimension){
            coords[0]++;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]--;
        }
        if(coords[1]-1>=0){
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[1]++;
        }
        if(coords[1]+1<=dimension){
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]-1>=0){
            coords[0]--;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]++;
            coords[1]++;
        }
        if(coords[0]+1<=dimension && coords[1]+1<=dimension){
            coords[0]++;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]--;
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]+1<=dimension){
            coords[0]--;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]++;
            coords[1]--;
        }
        if(coords[0]+1<=dimension && coords[1]-1>=0){
            coords[0]++;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            this.adjacentTiles.add(aktuell);
            if(aktuell.mine){
                save.add(aktuell);
                this.adjacent_mines++;
            }
            coords[0]--;
            coords[1]++;
        }

    }

    public void mineAdjacent(Spielfeld feld,int dimension){

        if(this.adjacentTiles.size()==0){
            throw new IllegalArgumentException("no adjacent blocks");
        }else{
            for(int i=0;i<this.adjacentTiles.size();i++){
                Tile check = adjacentTiles.get(i);
                if(check.adjacent_mines==0 && !check.mined && !check.mine){
                    check.mined=true;
                    check.mineAdjacent(feld, dimension);
                }else if(!check.mined && !check.mine){
                    check.mined=true;
                }
            }
        }
    }

       
}
