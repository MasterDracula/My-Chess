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

        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);
        boolean straightLine = line == toLine || column == toColumn;
        boolean diagonal = rowDiff == colDiff;

        if (!straightLine && !diagonal) {
            return false;
        }

        int rowDirection = Integer.compare(toLine, line);
        int colDirection = Integer.compare(toColumn, column);
        int currentRow = line + rowDirection;
        int currentCol = column + colDirection;

        while (currentRow != toLine || currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
        return destinationPiece == null || !destinationPiece.getColor().equals(this.getColor());
    }
}