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
        if (line == 0 || line == 7 || column == 0 || column == 7 || toLine == 0 || toLine == 7 || toColumn == 0 || toColumn == 7) {
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
