package org.example;

public class King extends ChessPiece{

    public King(String color) {
        super(color);
    }

    // Метод, возвращающий цвет фигуры
    @Override
    public String getColor() {
        return super.getColor();
    }

    // Метод возвращающий символ фигур
    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за доску
        if (line == 0 || line == 7 || column == 0 || column == 7 || toLine == 0 || toLine == 7 || toColumn == 0 || toColumn == 7) {
            return false;
        }

        // Проверка на возможность хода короля
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            return true;
        }

        // Проверка на невозможность хода в текущую позицию
        return line != toLine || column != toColumn;
    }
}
