package com.example.chessmanul;

import static java.lang.Math.abs;

public class Knight extends ChessPiece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        int dh = abs(endSquare.h - startSquare.h);
        int dv = abs(endSquare.v - startSquare.v);
        if ((dh == 2) && (dv == 1) || ((dh == 1) && (dv == 2))){
            return endSquare.piece == null || endSquare.piece.isWhite() != isWhite;
        }
        return false;
    }

    @Override
    int getImage() {
        if (isWhite){
            return R.drawable.knight_white;
        }else {
            return R.drawable.knight_black;
        }
    }
}
