package com.example.chessmanul;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

public class ChessSquare extends androidx.appcompat.widget.AppCompatImageView {
    public ChessPiece piece = null;
    public int h = 0, v = 0;

    public ChessSquare(Context context, int v, int h) {
        super(context);
        this.h = h;
        this.v = v;
        setDefaultBackground();
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        if (piece == null) {
            setImageDrawable(null);
        } else {
            setImageResource(piece.getImage());
        }
    }

    public void setDefaultBackground(){
        if ((h + v) % 2 == 0) {
            setBackgroundColor(Color.rgb(255,236,179));
        } else {
            setBackgroundColor(Color.DKGRAY);
        }
    }
}
