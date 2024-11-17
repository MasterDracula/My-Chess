package org.example;

public  abstract class ChessPiece {
    String color;
    Boolean check = true;
    public ChessPiece[][] board = new ChessPiece[8][8];
    public ChessPiece(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        return true;
    }

    public abstract String getSymbol();

}
    

