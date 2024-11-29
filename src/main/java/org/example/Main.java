package org.example;

import javax.swing.text.Position;
import java.util.Scanner;

public class Main {

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        board.board[1][0] = new Pawn("White");
        board.board[1][1] = new Pawn("White");
        board.board[1][2] = new Pawn("White");
        board.board[1][3] = new Pawn("White");
        board.board[1][4] = new Pawn("White");
        board.board[1][5] = new Pawn("White");
        board.board[1][6] = new Pawn("White");
        board.board[1][7] = new Pawn("White");

        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        board.board[6][0] = new Pawn("Black");
        board.board[6][1] = new Pawn("Black");
        board.board[6][2] = new Pawn("Black");
        board.board[6][3] = new Pawn("Black");
        board.board[6][4] = new Pawn("Black");
        board.board[6][5] = new Pawn("Black");
        board.board[6][6] = new Pawn("Black");
        board.board[6][7] = new Pawn("Black");
        return board;
    }

    public static void main(String[] args) {
        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
            Command in this game:
            'exit' - for exit from game
            'replay' - for restart game
            'castling0' or 'castling7' - castling for any lines
            'move 1 1 2 3' - for moving from 1 1 to 2 3(field is matrix from 0 to 7) 1st is row 2nd is colum
            Check that figure can move throw allies, correct fighting, can take cheсk `n castling at same time ?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Try again");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Castling Successfully");
                        board.printBoard();
                    } else {
                        System.out.println("Castling Unsuccessfully");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Castling Successfully");
                        board.printBoard();
                    } else {
                        System.out.println("Castling Unsuccessfully");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Successfully moving");
                            board.printBoard();
                            ChessPiece king = board.getKing("White");
                            isCheckmate(king, board);
                        } else {
                            System.out.println("Moving unsuccessfully");
                        }
                    } catch (Exception e) {
                        System.out.println("something goes wrong, try again");
                    }
                }
            }
        }
    }

    public static boolean isInCheck(ChessPiece king, ChessBoard chessBoard) {
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

    private static Position1 findKingPosition(String color, ChessBoard chessBoard) {
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

    public static boolean isCheckmate(ChessPiece king, ChessBoard chessBoard) {
        if (!isInCheck(king, chessBoard)) {
            return false;
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
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean wouldBeInCheckAfterMove(ChessPiece king, Position1 from, Position1 to, ChessBoard chessBoard) {
        // Simulate the move temporarily
        ChessPiece temp = chessBoard.board[to.getRow()][to.getColumn()];
        chessBoard.board[to.getRow()][to.getColumn()]=chessBoard.board[from.getRow()][from.getColumn()];
        chessBoard.board[from.getRow()][from.getColumn()]=null;

        boolean inCheck = isInCheck(king, chessBoard);

        chessBoard.board[from.getRow()][from.getColumn()]=chessBoard.board[to.getRow()][to.getColumn()];
        chessBoard.board[from.getRow()][from.getColumn()]=temp;

        return inCheck;
    }

    private static boolean isPositionOnBoard(Position1 position, ChessBoard chessBoard) {
        return position.getRow() >= 0 && position.getRow() < chessBoard.board.length &&
                position.getColumn() >= 0 && position.getColumn() < chessBoard.board.length;
    }
}