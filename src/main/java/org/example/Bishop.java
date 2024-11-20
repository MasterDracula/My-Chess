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
        int rowDiff = Math.abs(startLine - endLine);
        int colDiff = Math.abs(startColumn - endColumn);

        if (rowDiff != colDiff) {
            return false;
        }

        int rowStep = (endLine > startLine) ? 1 : -1;
        int colStep = (endColumn > startColumn) ? 1 : -1;
        int steps = rowDiff - 1;
        for (int i = 1; i <= steps; i++) {
            if (chessBoard.board[startLine + i * rowStep][startColumn + i * colStep] != null) {
                return false;
            }
        }
        ChessPiece destinationPiece = chessBoard.board[endLine][endColumn];
        if (destinationPiece == null) {
            return true;
        } else {
            return !destinationPiece.getColor().equals(this.getColor());
        }
    }
}

