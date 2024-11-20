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
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);
        boolean isValidLMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (!isValidLMove) {
            return false;
        }
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece == null) {
            return true;
        } else {
            return !targetPiece.getColor().equals(this.getColor());
        }
    }
}

