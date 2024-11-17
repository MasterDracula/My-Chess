package org.example;

public class Horse extends ChessPiece {

    // Конструктор принимающий цвет фигуры
    public Horse(String color) {
        super(color);
    }

    // Реализуем метод, который возвращает символ фигур
    @Override
    public String getSymbol() {
        return "H";
    }

    // Реализуем метод, который возвращает цвет фигуры
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (!chessBoard.checkPos(startLine) || !chessBoard.checkPos(startColumn) || !chessBoard.checkPos(endLine) ||
                !chessBoard.checkPos(endColumn)) {
            return false;
        }
        int x = Math.abs(startLine-endLine);
        int y = Math.abs(startColumn-endColumn);
        return  (x ==2 && y==1)||(x==1 && y==2);
    }
}
