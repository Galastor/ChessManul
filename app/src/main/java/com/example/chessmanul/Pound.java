package com.example.chessmanul;

import static java.lang.Math.abs;

public class Pound extends ChessPiece {
    private boolean moved = false;
    public boolean jumped = false;

    public Pound(boolean isWhite) {
        super(isWhite);
    }

    @Override
    boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        int direction = isWhite ? 1 : -1;
        if ((startSquare.v == endSquare.v)
                && (endSquare.h - startSquare.h == direction)
                && (endSquare.piece == null)) {
            return true;
        }
        if ((startSquare.v == endSquare.v)
                && (endSquare.h - startSquare.h == 2 * direction)
                && (endSquare.piece == null)
                && (board.getSquare(startSquare.v, startSquare.h + direction).piece == null)) {
            return !moved;
        }
        if ((abs(startSquare.v - endSquare.v) == 1)
                && (endSquare.h - startSquare.h == direction)
                && (endSquare.piece != null)
                && (endSquare.piece.isWhite() != isWhite)) {
            return true;
        }
        if ((abs(startSquare.v - endSquare.v) == 1)
                && (endSquare.h - startSquare.h == direction)) {
            ChessPiece lastMovedPiece = board.lastMovedPiece;
            ChessSquare square = board.getSquare(endSquare.v, endSquare.h - direction);
            if (lastMovedPiece != null
                    && (square.piece == lastMovedPiece)
                    && (lastMovedPiece instanceof Pound)
                    && (lastMovedPiece.isWhite() != isWhite)) {
                Pound lastMovedPound = (Pound) lastMovedPiece;
                if (lastMovedPound.jumped) {
                    endSquare.piece = this;
                    startSquare.piece = null;
                    square.piece = null;
                    boolean result = !board.checkCheck(isWhite);
                    endSquare.piece = null;
                    startSquare.piece = this;
                    square.piece = lastMovedPiece;
                    return result;
                }
            }
        }
        return false;
    }

    @Override
    int getImage() {
        if (isWhite) {
            return R.drawable.pound_white;
        } else {
            return R.drawable.pound_black;
        }
    }

    @Override
    public void move(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare) {
        moved = true;
        int direction = isWhite ? 1 : -1;
        jumped = (endSquare.h - startSquare.h == direction * 2);
        if (endSquare.v - startSquare.v != 0
                && endSquare.piece == null) {
            board.getSquare(endSquare.v, endSquare.h - direction).setPiece(null);
        }
        super.move(board, startSquare, endSquare);
    }
}
