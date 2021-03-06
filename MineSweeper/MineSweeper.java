package MineSweeper;

import java.util.Scanner;

public class MineSweeper {
    public Spielfeld feld;
    int flags;
    public int dim;
    int tiles_left;

    public MineSweeper(int gameDimension){
        this.flags=15;
        this.tiles_left=100;
        this.dim=gameDimension;
        this.feld=new Spielfeld(dim);//FEST
    }
    
    public void gameloop(){
        int test=0;
        //printGame();
        Scanner input = new Scanner(System.in);
        while(true){
            /*switch(in){
                case("Mine"):
                    Tile aktuell1=feld.getTile(coord);
                    if(!aktuell1.mineTile()){
                        System.out.println("GAME OVER");
                        return; 
                    }
                    aktuell1.mineAdjacent(feld,feld.dimension);
                    break;
                case("Flag"):
                    Tile aktuell2=feld.getTile(coord);
                    aktuell2.flagTile();
                    flags--;
                    break;
                default:
                    throw new IllegalArgumentException("cok");
            }*/
            //printGame();

        }

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
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                int coords []= {i,j};
                Tile aktuell = feld.getTile(coords);
                if(aktuell.mined || aktuell.flagged){
                    this.tiles_left--;
                }
                if(tiles_left==0){
                    checkWin();
                }
            }
        }
    }

    public void checkWin(){
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                int coords []= {i,j};
                Tile aktuell = feld.getTile(coords);
                if(aktuell.flagged && !aktuell.mine){
                    System.out.println("GAME OVER");
                    return; 
                }
            }
        }
        System.out.println("WIN");
        return; 
    }
}
