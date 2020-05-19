package com.example.chessmanul;

import android.graphics.drawable.Drawable;

import static java.lang.Math.abs;

public class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        int dh = endSquare.h - startSquare.h;
        int dv = endSquare.v - startSquare.v;
        if (abs(dh) != abs(dv)) return false;
        int hDirection = dh/abs(dh);
        int vDirection = dv/abs(dv);
        for(int v = startSquare.v + vDirection, h = startSquare.h + hDirection; v != endSquare.v; v+=vDirection, h+=hDirection){
            if (board.getSquare(v,h).piece != null) {
                return false;
            }
        }
        return (endSquare.piece == null) || (endSquare.piece.isWhite() != isWhite);
    }

    @Override
    int getImage() {
        if (isWhite){
            return R.drawable.bishop_white;
        }else {
            return R.drawable.bishop_black;
        }
    }
}
