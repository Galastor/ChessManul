package com.example.chessmanul;

import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PieceSelectDialog extends DialogFragment implements View.OnClickListener {
    public boolean white;
    public int a = 120;
    ChessBoard board;
    ChessSquare square;

    @Override
    public void onCancel(DialogInterface dialog) {
        square.setPiece(new Queen(white));
        super.onCancel(dialog);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        ChessSquare sq1 = new ChessSquare(getContext(), 1, 1);
        ChessSquare sq2 = new ChessSquare(getContext(), 1, 1);
        ChessSquare sq3 = new ChessSquare(getContext(), 1, 1);
        ChessSquare sq4 = new ChessSquare(getContext(), 1, 1);

        sq1.setPiece(new Queen(white));
        sq2.setPiece(new Rook(white));
        sq3.setPiece(new Bishop(white));
        sq4.setPiece(new Knight(white));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(a, a);

        linearLayout.addView(sq1, lp);
        linearLayout.addView(sq2, lp);
        linearLayout.addView(sq3, lp);
        linearLayout.addView(sq4, lp);

        sq1.setOnClickListener(this);
        sq2.setOnClickListener(this);
        sq3.setOnClickListener(this );
        sq4.setOnClickListener(this);
        return linearLayout;
    }

    @Override
    public void onClick(View view) {
        ChessSquare selectedSquare = (ChessSquare) view;
        square.setPiece(selectedSquare.piece);
        dismiss();
    }
}
