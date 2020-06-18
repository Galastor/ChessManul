package com.example.chessmanul.ui.slideshow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.chessmanul.ChessBoard;
import com.example.chessmanul.ChessSquare;
import com.example.chessmanul.R;

public class SlideshowFragment extends Fragment implements View.OnTouchListener {

    private SlideshowViewModel slideshowViewModel;
    int a = 120;
    ChessSquare selectedSquare;
    ChessBoard board;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        board = new ChessBoard(getContext());

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

        Button newGameButton = new Button(getContext());
        newGameButton.setText("Start new game");
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(new ViewGroup.LayoutParams(a, a));
        lp.setMargins(0, 8 * a, 0, 0);
        board.addView(newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.startPosition();
            }
        });


        return board;
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