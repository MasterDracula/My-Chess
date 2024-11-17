package org.example;

public abstract class King extends ChessPiece{

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка корректности ввода
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        // Проверка на выход за доску
        if (line == 0 || line == 7 || column == 0 || column == 7 || toLine == 0 || toLine == 7 || toColumn == 0 || toColumn == 7) {
            return false;
        }

        // Проверка на возможность хода короля
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            return true;
        }

        // Проверка на невозможность хода в текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        return true;
    }
}
