package com.example.chessmanul;

import android.graphics.drawable.Drawable;

public abstract class ChessPiece {
    protected boolean isWhite;

    abstract boolean checkMove(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare);

    public boolean isWhite(){
        return isWhite;
    }

    public ChessPiece(boolean isWhite){
        this.isWhite = isWhite;
    }

    abstract int getImage();

    public void move(ChessBoard board, ChessSquare startSquare, ChessSquare endSquare){
        endSquare.setPiece(this);
        startSquare.setPiece(null);
    }
}
