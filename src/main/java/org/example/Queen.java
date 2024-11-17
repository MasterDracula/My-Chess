package org.example;

public class Queen extends ChessPiece {

    // Конструктор принимающий цвет фигуры
    public Queen(String color) {
        super(color);
    }

    // Метод возвращающий символ фигур
    @Override
    public String getSymbol() {
        return "Q";
    }

    // Метод, возвращающий цвет фигуры
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за доску
        if (line == 0 || line == 7 || column == 0 || column == 7 || toLine == 0 || toLine == 7 || toColumn == 0 || toColumn == 7) {
            return false;
        }

        // Проверка на возможность хода королевы
        if (Math.abs(line - toLine) == Math.abs(column - toColumn) || (line == toLine && Math.abs(column - toColumn) == 1)
                || (column == toColumn && Math.abs(line - toLine) == 1)) {
            return true;
        }

        // Проверка на невозможность хода в текущую позицию
        return line != toLine || column != toColumn;
    }
}
