package MineSweeper;

public class Tile {
    boolean mine;
    boolean flagged;
    boolean mined;
    public int [] coords;
    public int adjacent_mines;

    public Tile(boolean isMine,int[] coords){
        this.mine=isMine;
        this.coords=coords;
        this.flagged=false;
        this.adjacent_mines=0;
        this.mined=false;
    }

    public boolean flagTile(){
        if(!mined){
            this.flagged=true;
            return true;
        }
        return false;
    }

    public boolean mineTile(){
        if(mine){
            //SEND GAME OVER
            return false;
        }else{
            this.mined=true;
            return true;
        }
    }

    public void mineAdjacent(Spielfeld feld,int dimension){
        if(coords[0]-1>=0){
            coords[0]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]++;
        }
        if(coords[0]+1<=dimension){
            coords[0]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]--;
        }
        if(coords[1]-1>=0){
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[1]++;
        }
        if(coords[1]+1<=dimension){
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[1]--;
        }
        /*if(coords[0]-1>=0 && coords[1]-1>=0){
            coords[0]--;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]++;
            coords[1]++;
        }
        if(coords[0]+1<=dimension && coords[1]+1<=dimension){
            coords[0]++;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]--;
            coords[1]--;
        }*/
    }
}
