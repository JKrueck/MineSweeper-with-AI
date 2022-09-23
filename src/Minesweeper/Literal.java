package Minesweeper;

public class Literal {
    enum truth{
        TRUE,
        FALSE,
        IDK
    }

    Tile tile;
    truth value;
    byte comparison;

    public Literal(Tile input){
        this.tile = input;
        this.value = truth.IDK;
        this.comparison= Byte.MAX_VALUE;
    }

    public void setFlag(truth input){
        if(input==truth.TRUE){
            this.value = truth.TRUE;
            this.comparison = 1; 
        }else{
            this.value = truth.FALSE;
            this.comparison = 0;
        }
    }
}
