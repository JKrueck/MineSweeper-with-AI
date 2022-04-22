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
            return false;
        }else{
            this.mined=true;
            return true;
        }
    }

    public void checkAdjacent(Spielfeld feld,int dimension,int []coords){
        if(coords[0]-1>=0){
            coords[0]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]++;
        }
        if(coords[0]+1<=dimension){
            coords[0]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]--;
        }
        if(coords[1]-1>=0){
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[1]++;
        }
        if(coords[1]+1<=dimension){
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]-1>=0){
            coords[0]--;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]++;
            coords[1]++;
        }
        if(coords[0]+1<=dimension && coords[1]+1<=dimension){
            coords[0]++;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]--;
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]+1<=dimension){
            coords[0]--;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]++;
            coords[1]--;
        }
        if(coords[0]+1<=dimension && coords[1]-1>=0){
            coords[0]++;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.mine){
                this.adjacent_mines++;
            }
            coords[0]--;
            coords[1]++;
        }
    }

    public void mineAdjacent(Spielfeld feld,int dimension){
        int [] coords = this.coordinates;
        if(coords[0]-1>=0){
            coords[0]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]++;
        }
        if(coords[0]+1<dimension){
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
        if(coords[0]-1>=0 && coords[1]-1>=0){
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
        if(coords[0]+1<dimension && coords[1]+1<dimension){
            coords[0]++;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]--;
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]+1<dimension){
            coords[0]--;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]++;
            coords[1]--;
        }
        if(coords[0]+1<dimension && coords[1]-1>=0){
            coords[0]++;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(aktuell.adjacent_mines==0 && !aktuell.mined){
                aktuell.mined=true;
                aktuell.mineAdjacent(feld, dimension);
            }
            coords[0]--;
            coords[1]++;
        }
        mineAdjacent2(feld,dimension);
    }

    public void mineAdjacent2(Spielfeld feld,int dimension){
        int [] coords = this.coordinates;
        if(coords[0]-1>=0){
            coords[0]--;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]++;
        }
        if(coords[0]+1<dimension){
            coords[0]++;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]--;
        }
        if(coords[1]-1>=0){
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[1]++;
        }
        if(coords[1]+1<dimension){
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]-1>=0){
            coords[0]--;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]++;
            coords[1]++;
        }
        if(coords[0]+1<dimension && coords[1]+1<dimension){
            coords[0]++;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]--;
            coords[1]--;
        }
        if(coords[0]-1>=0 && coords[1]+1<dimension){
            coords[0]--;
            coords[1]++;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]++;
            coords[1]--;
        }
        if(coords[0]+1<dimension && coords[1]-1>=0){
            coords[0]++;
            coords[1]--;
            Tile aktuell=feld.getTile(coords);
            if(!aktuell.mine && !aktuell.mined){
                aktuell.mined=true;
            }
            coords[0]--;
            coords[1]++;
        }
    }
}
