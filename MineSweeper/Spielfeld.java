package MineSweeper;

import MineSweeper.Tile;
import java.util.Random;

public class Spielfeld {
    Tile [][] feld;
    int dimension;
    int bombs;

    public Spielfeld(int dim){
        Random rand=new Random(167);
        this.bombs=15;
        int needsPlacement=15;
        this.dimension=dim-1;
        this.feld= new Tile[dim][dim];

        for (int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                int number=rand.nextInt(101);
                if(number>=60 && needsPlacement!=0){
                    int[]coords={i,j};
                    feld[i][j]=new Tile(true,coords);
                    needsPlacement--;
                }else{
                    int[]coords={i,j};
                    feld[i][j]=new Tile(false,coords);
                }            
            }
        }
        check();
    }
    public Tile getTile(int[]coords){
        return  feld[coords[0]][coords[1]];
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
