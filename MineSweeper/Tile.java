package MineSweeper;

public class Tile {
    boolean mine;
    boolean flagged;
    boolean mined;
    public int [] coordinates;
    public int adjacent_mines;

    public Tile(boolean isMine,int[] coords){
        this.mine=isMine;
        this.coordinates=coords;
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
}
