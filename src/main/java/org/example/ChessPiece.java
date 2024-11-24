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

    public boolean canMoveToPosition(ChessBoard chessBoard, int Line, int Column, int toLine, int toColumn) {
        return true;
    }

    public abstract String getSymbol();

}
    

