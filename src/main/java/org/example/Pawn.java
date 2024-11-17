package org.example;

public class Pawn extends ChessPiece {

    // Конструктор принимающий цвет фигуры
    public Pawn(String color) {
        super(color);
    }

    // Метод, который возвращает символ фигур
    @Override
    public String getSymbol() {
        return "P";
    }

    // Метод, который возвращает цвет фигуры
    @Override
    public String getColor() {
        return color;
    }
    // Метод проверяющий возможнсть хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int Line, int Column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(Line) || !chessBoard.checkPos(Column) || !chessBoard.checkPos(toLine) ||
                !chessBoard.checkPos(toColumn)) {
            return false;
        }
        int colorXY = color.equals("White")? 1:-1;
        if(Line +colorXY == toLine && Column==toColumn){
            return true;
        }else if(Line ==(color.equals("White")?1:6)&& Line + 2*colorXY == toLine && Column == toColumn){
            return  true;
        }
        return false;
    }
}
