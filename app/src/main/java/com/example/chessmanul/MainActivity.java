package com.example.chessmanul;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnTouchListener {
    int max_width, max_high;
    int a = 120;
    ChessSquare selectedSquare;
    ChessBoard board;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new ChessBoard(this);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(new ViewGroup.LayoutParams(a, a));
                lp.setMargins(i * a, (8 - j) * a, 0, 0);
                ChessSquare square = board.getSquare(i + 1, j + 1);
                square.setLayoutParams(lp);
                square.setOnTouchListener(this);
            }
        }

        board.startPosition();

        setContentView(board);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ChessSquare newSquare = (ChessSquare) view;
        if (selectedSquare == null) {
            if (newSquare.piece != null
                    && newSquare.piece.isWhite == board.whitesMove) {
                selectedSquare = newSquare;
                selectedSquare.setBackgroundColor(Color.GREEN);
            }
        } else {
            board.makeMove(selectedSquare, newSquare);
            selectedSquare.setDefaultBackground();
            selectedSquare = null;
        }
        return false;
    }

}
