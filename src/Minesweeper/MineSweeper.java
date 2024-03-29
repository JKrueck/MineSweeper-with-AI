package Minesweeper;

import java.util.ArrayList;
import java.util.List;

public class MineSweeper {
    public Spielfeld feld;
    int flags;
    int bombs;
    public int dim;
    int tiles_left;

    public MineSweeper(int gameDimension, int boom){
        this.bombs = boom;
        this.flags = this.bombs;
        this.tiles_left=gameDimension*gameDimension;
        this.dim=gameDimension;
        this.feld=new Spielfeld(dim, bombs);
    }
    
    

    public void printGame(){
        for(int i=0;i<dim;i++){
            System.out.print("|");
            for(int j=0;j<dim;j++){
                int[]coords={i,j};
                Tile aktuell=feld.getTile(coords);
                if(aktuell.mined){
                    System.out.print(aktuell.adjacent_mines);
                }else if(aktuell.flagged){
                    System.out.print("F");
                }else{
                    System.out.print("X");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
    private void checkRemainingTiles(){
        int dummy = this.dim*this.dim;
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                int coords []= {i,j};
                Tile aktuell = feld.getTile(coords);
                if(aktuell.mined || aktuell.flagged){
                    dummy--;
                }
            }
        }
        this.tiles_left=dummy;
        System.out.println(tiles_left);
    }

    public boolean checkWin(){
        checkRemainingTiles();
        if(this.tiles_left==0){
            List<Tile> toCheck = this.feld.getBombs();
            boolean flag = true;
            for(int i=0;i<toCheck.size();i++){
                if(!toCheck.get(i).flagged){
                    flag = false;
                    break;
                }
            }

            if(flag){
                return true;
            }else{
                return false;
            }
        }
        return false; 
    }

    public ArrayList<Clause> getClauses(){
        ArrayList<Clause> result = new ArrayList<>();
        for(int y=0;y<dim;y++){
            for(int x=0;x<dim;x++){
                int[] coords = {x,y};
                result.add(this.feld.getTile(coords).getClause());
            }
        }
        return result;
    }
}
