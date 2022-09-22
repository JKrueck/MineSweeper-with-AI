package Minesweeper;

public class Literal {
    enum truth{
        TRUE,
        FALSE,
        IDK
    }

    Tile tile;
    truth value;
    byte wild;

    public Literal(Tile input){
        this.tile = input;
        this.value = truth.IDK;
        this.wild = Byte.MAX_VALUE;
    }
}
