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
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) {
            return false;
        }

        if (Math.abs(line - toLine) == Math.abs(column - toColumn) || (line == toLine && Math.abs(column - toColumn) == 1)
                || (column == toColumn && Math.abs(line - toLine) == 1)) {
            return true;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.getColor());
    }
}