package org.example;

public class King extends ChessPiece {

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
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) {
            return false;
        }
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);
        if (rowDiff <= 1 && colDiff <= 1) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null) {
                return true;
            } else {
                return !targetPiece.getColor().equals(this.getColor());
            }
        }
        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
        return destinationPiece == null || !destinationPiece.getColor().equals(this.getColor());
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

