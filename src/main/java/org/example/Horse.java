package org.example;

public class Horse extends ChessPiece {
    String color =  this.getColor();

    public String colorOfFigure() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (startColumn)
    }
}
