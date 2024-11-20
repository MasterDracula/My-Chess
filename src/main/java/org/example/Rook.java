package org.example;

public class Rook extends ChessPiece{

    public Rook(String color) {
        super(color);
    }

    // Метод возвращающий символ фигур
    @Override
    public String getSymbol() {
        return "R";
    }

    // Метод, возвращающий цвет фигуры
    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за доску
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка на возможность хода ладьи
        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            return true;
        }

        // Проверка на невозможность хода в текущую позицию
        return line != toLine || column != toColumn;
    }
}
