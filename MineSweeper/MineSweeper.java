package MineSweeper;

import java.util.Scanner;

public class MineSweeper {
    Spielfeld feld;
    int flags;
    int dim;

    public MineSweeper(){
        this.flags=15;
        dim=10;
        this.feld=new Spielfeld(dim);//FEST
    }
    
    public void gameloop(){
        int test=0;
        printGame();
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Choose a  x-coordinate:");
            int x=input.nextInt();
            System.out.println("Choose a  y-coordinate:");
            int y=input.nextInt();
            int [] coord ={x,y};
            System.out.println("Mine or flag?");
            String in=input.next();
            switch(in){
                case("Mine"):
                    Tile aktuell1=feld.getTile(coord);
                    if(!aktuell1.mineTile()){
                        System.out.println("GAME OVER");
                        return; 
                    }
                    aktuell1.mineAdjacent(feld,dim);
                    break;
                case("Flag"):
                    Tile aktuell2=feld.getTile(coord);
                    aktuell2.flagTile();
                    break;
                default:
                    throw new IllegalArgumentException("cok");
            }
            printGame();

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
}
