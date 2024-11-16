package org.example;

import java.awt.*;

public class ChessPiece {
    String color;
    String typeOfFigure;
    Boolean check = true;

    public ChessPiece() {
        color = new Color();
    }


    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        return true;
    }

    public String getSymbol() {
        return typeOfFigure;
    }

    

