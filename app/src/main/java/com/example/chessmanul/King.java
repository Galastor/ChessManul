package com.example.chessmanul;

import static java.lang.Math.abs;

public class King extends ChessPiece {
    boolean moved = false;

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        int dh = abs(endSquare.h - startSquare.h);
        int dv = abs(endSquare.v - startSquare.v);
        if ((dh < 2) && (dv < 2)) {
            return endSquare.piece == null || endSquare.piece.isWhite() != isWhite;
        }

        if (dh == 0 && dv == 2) {
            if (moved) {
                return false;
            }
            if (board.checkCheck(isWhite)) {
                return false;
            }
            int direction = (endSquare.v - startSquare.v) / 2;
            int v;
            int h = startSquare.h;
            for (v = 5 + direction; v > 1 && v < 8; v += direction) {
                ChessSquare square = board.getSquare(v, h);
                if (square.piece != null) {
                    return false;
                }
            }
            ChessSquare square = board.getSquare(v, h);
            if (!(square.piece instanceof Rook)) {
                return false;
            }
            Rook rook = (Rook) square.piece;
            if (rook.isWhite() != isWhite) {
                return false;
            }
            if (rook.moved) {
                return false;
            }
            square = board.getSquare(5 + direction, h);
            square.piece = this;
            startSquare.piece = null;
            boolean result = !board.checkCheck(isWhite);
            square.piece = null;
            startSquare.piece = this;
            return result;
        }

        return false;
    }

    @Override
    int getImage() {
        if (isWhite) {
            return R.drawable.king_white;
        } else {
            return R.drawable.king_black;
        }
    }

    @Override
    public void move(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        moved = true;
        int dv = abs(startSquare.v - endSquare.v);
        if (dv == 2) {
            int direction = (endSquare.v - startSquare.v) / 2;
            int v = 5 + direction;
            ChessSquare endRookSquare = board.getSquare(v, startSquare.h);
            while (v > 1 && v < 8) {
                v += direction;
            }
            ChessSquare startRookSquare = board.getSquare(v, startSquare.h);
            ChessPiece rook = startRookSquare.piece;
            rook.move(board, startRookSquare, endRookSquare);
        }
        super.move(board, startSquare, endSquare);
    }
}
