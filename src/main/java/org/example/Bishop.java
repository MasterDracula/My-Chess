package org.example;

public class Bishop extends ChessPiece {

    // Конструктор принимающий цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    // Метод возвращающий символ фигур
    @Override
    public String getSymbol() {
        return "B";
    }

    // Метод, возвращающий цвет фигуры
    @Override
    public String getColor() {
        return color;
    }

    // Метод проверяющий возможность хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (!chessBoard.checkPos(startLine) || !chessBoard.checkPos(startColumn) || !chessBoard.checkPos(endLine) ||
                !chessBoard.checkPos(endColumn)) {
            return false;
        }
        int x = Math.abs(startLine - endLine);
        int y = Math.abs(startColumn - endColumn);
        return x == y;
    }
}
