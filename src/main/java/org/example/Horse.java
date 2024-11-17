package org.example;

public class Horse extends ChessPiece {

    // Конструктор принимающий цвет фигуры
    public Horse(String color) {
        super(color);
    }

    // Метод возвращающий символ фигур
    @Override
    public String getSymbol() {
        return "H";
    }

    // Метод, возвращающий цвет фигуры
    @Override
    public String getColor() {
        return color;
    }

    // Метод, проверяющий возможность хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) ||
                !chessBoard.checkPos(toColumn)) {
            return false;
        }
        int x = Math.abs(line-toLine);
        int y = Math.abs(column-toColumn);
        return  (x ==2 && y==1)||(x==1 && y==2);
    }
}
