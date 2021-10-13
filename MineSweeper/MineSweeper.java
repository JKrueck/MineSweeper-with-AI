package MineSweeper;

import java.util.Scanner;

public class MineSweeper {
    Spielfeld feld;
    int flags;

    public MineSweeper(){
        this.flags=15;
        this.feld=new Spielfeld(5);//FEST
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
                    Tile aktuell=feld.getTile(coord);
                    if(!aktuell.mineTile()){
                        System.out.println("GAME OVER");
                        return; 
                    }
                    break;
                default:
                    throw new IllegalArgumentException("cok");
            }
            printGame();

        }

    }

    public void printGame(){
        for(int i=0;i<5;i++){
            System.out.print("|");
            for(int j=0;j<5;j++){
                int[]coords={i,j};
                Tile aktuell=feld.getTile(coords);
                if(aktuell.mined){
                    System.out.print(aktuell.adjacent_mines);
                }else{
                    System.out.print("X");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}
