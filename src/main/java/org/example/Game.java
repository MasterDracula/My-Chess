package org.example;

import javax.swing.text.Position;

public class Game {

    public boolean isInCheck(ChessPiece king, ChessBoard chessBoard) {
        Position1 kingPosition = findKingPosition(king.getColor(), chessBoard);
        for (int row = 0; row < chessBoard.board.length; row++) {
            for (int col = 0; col < chessBoard.board[row].length; col++) {
                ChessPiece piece = chessBoard.board[row][col];
                if (piece != null && !piece.getColor().equals(king.getColor())) {
                    if (piece.canMoveToPosition(chessBoard, row, col, kingPosition.getRow(), kingPosition.getColumn())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position1 findKingPosition(String color, ChessBoard chessBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return new Position1(i, j);
                }
            }
        }
        throw new RuntimeException("King not found, which should never happen.");
    }

    public boolean isCheckmate(ChessPiece king, ChessBoard chessBoard) {
        if (!isInCheck(king, chessBoard)) {
            return false; // Not in check, so cannot be checkmate
        }

        Position1 kingPosition = findKingPosition(king.getColor(), chessBoard);
        King king1 = (King) chessBoard.board[kingPosition.getRow()][kingPosition.getColumn()];

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }
                Position1 newPosition = new Position1(kingPosition.getRow() + rowOffset,
                        kingPosition.getColumn() + colOffset);

                if (isPositionOnBoard(newPosition, chessBoard) && king.canMoveToPosition(chessBoard, kingPosition.getRow(),
                        kingPosition.getColumn(), newPosition.getRow(), newPosition.getColumn())
                        && !wouldBeInCheckAfterMove(king1, kingPosition, newPosition, chessBoard)) {
                    return false; // Found a move that gets the king out of check, so it's not checkmate
                }
            }
        }
        return true; // No legal moves available to escape check, so it's checkmate
    }

    private boolean wouldBeInCheckAfterMove(ChessPiece king, Position1 from, Position1 to, ChessBoard chessBoard) {
        // Simulate the move temporarily
        ChessPiece temp = chessBoard.board[to.getRow()][to.getColumn()];
        chessBoard.board[to.getRow()][to.getColumn()]=chessBoard.board[from.getRow()][from.getColumn()];
        chessBoard.board[from.getRow()][from.getColumn()]=null;

        boolean inCheck = isInCheck(king, chessBoard);

        // Undo the move
        chessBoard.board[from.getRow()][from.getColumn()]=chessBoard.board[to.getRow()][to.getColumn()];
        chessBoard.board[from.getRow()][from.getColumn()]=temp;

        return inCheck;
    }

    private boolean isPositionOnBoard(Position1 position, ChessBoard chessBoard) {
        return position.getRow() >= 0 && position.getRow() < chessBoard.board.length &&
                position.getColumn() >= 0 && position.getColumn() < chessBoard.board.length;
    }
}