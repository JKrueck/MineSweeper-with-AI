package Minesweeper;

public class Literal {
    enum truth{
        TRUE,
        FALSE,
        IDK
    }

    Tile represents;
    truth decision;
    byte comparison;

    public Literal(Tile input){
        this.represents = input;
        this.decision = truth.IDK;
        this.comparison= Byte.MAX_VALUE;
    }

    public void setFlag(truth input){
        if(input==truth.TRUE){
            this.decision = truth.TRUE;
            this.comparison = 1; 
        }else{
            this.decision = truth.FALSE;
            this.comparison = 0;
        }
    }
}
