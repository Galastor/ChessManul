package com.example.chessmanul;

import static java.lang.Math.abs;

public class Queen extends ChessPiece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        int dh = endSquare.h - startSquare.h;
        int dv = endSquare.v - startSquare.v;
        if (abs(dh) == abs(dv)) {
            int hDirection = dh / abs(dh);
            int vDirection = dv / abs(dv);
            for (int v = startSquare.v + vDirection, h = startSquare.h + hDirection; v != endSquare.v; v += vDirection, h += hDirection) {
                if (board.getSquare(v, h).piece != null) return false;
            }
            return (endSquare.piece == null) || (endSquare.piece.isWhite() != isWhite);
        }
        if (dh == 0) {
            int h = startSquare.h;
            int direction = dv / abs(dv);
            for (int v = startSquare.v + direction; v != endSquare.v; v += direction) {
                if (board.getSquare(v, h).piece != null) return false;
            }
            return (endSquare.piece == null) || (endSquare.piece.isWhite() != isWhite);
        }
        if (dv == 0) {
            int v = startSquare.v;
            int direction = dh / abs(dh);
            for (int h = startSquare.h + direction; h != endSquare.h; h += direction) {
                if (board.getSquare(v, h).piece != null) return false;
            }
            return (endSquare.piece == null) || (endSquare.piece.isWhite() != isWhite);
        }
        return false;
    }

    @Override
    int getImage() {
        if (isWhite) {
            return R.drawable.queen_white;
        } else {
            return R.drawable.queen_black;
        }
    }
}
