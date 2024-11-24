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
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line != toLine && column != toColumn) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }
        int rowStep = (toLine > line) ? 1 : -1;
        int colStep = (toColumn > column) ? 1 : -1;
        if (line == toLine) {
            for (int i = column + colStep; i != toColumn; i += colStep) {
                if (chessBoard.board[line][i] != null) {
                    return false;
                }
            }
        } else {
            for (int i = line + rowStep; i != toLine; i += rowStep) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        }
        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
        if (destinationPiece == null) {
            return true;
        } else {
            return !destinationPiece.getColor().equals(this.getColor());
        }
    }
}
