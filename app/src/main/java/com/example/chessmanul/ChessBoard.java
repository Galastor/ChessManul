package com.example.chessmanul;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ChessBoard extends RelativeLayout {
    public boolean whitesMove = true;
    public ChessPiece lastMovedPiece = null;
    public ChessSquare[][] board = new ChessSquare[8][8];

    public ChessBoard(Context context) {
        super(context);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessSquare square = new ChessSquare(context, i + 1, j + 1);
                board[i][j] = square;
                addView(square);
            }
        }
    }

    public boolean checkMove(ChessSquare startSquare, ChessSquare endSquare) {
        if (startSquare == endSquare) return false;
        ChessPiece piece = startSquare.piece;
        if (piece == null) return false;
        if (whitesMove != piece.isWhite()) return false;
        if (piece.checkMove(this, startSquare, endSquare)) {
            ChessPiece endSquarePiece = endSquare.piece;
            endSquare.piece = piece;
            startSquare.piece = null;
            boolean result = !checkCheck(whitesMove);
            startSquare.piece = piece;
            endSquare.piece = endSquarePiece;
            return result;
        }
        return false;
    }

    public void makeMove(ChessSquare startSquare, ChessSquare endSquare) {
        if (checkMove(startSquare, endSquare)) {
            startSquare.piece.move(this, startSquare, endSquare);
            if (endSquare.h == 1 || endSquare.h == 8) {
                if (endSquare.piece instanceof Pound) {
                    PieceSelectDialog dlg = new PieceSelectDialog();
                    dlg.white = whitesMove;
                    dlg.board = this;
                    dlg.square = endSquare;
                    AppCompatActivity activity = (AppCompatActivity) getContext();
                    dlg.show(activity.getSupportFragmentManager(), "");
                }
            }
            whitesMove = !whitesMove;
            lastMovedPiece = endSquare.piece;
            if (checkCheck(whitesMove)) {
                String s;
                if (checkMate(whitesMove)) {
                    s = "Mate!";
                } else {
                    s = "Check!";
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ChessSquare getSquare(int v, int h) {
        if (h > 0 && v > 0 && h < 9 && v < 9) {
            return board[v - 1][h - 1];
        } else {
            return null;
        }
    }

    public void startPosition() {
        whitesMove = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getSquare(i + 1, j + 1).setPiece(null);
            }
        }
        for (int i = 0; i < 8; i++) {
            getSquare(i + 1, 2).setPiece(new Pound(true));
            getSquare(i + 1, 7).setPiece(new Pound(false));
        }
        getSquare(1, 1).setPiece(new Rook(true));
        getSquare(2, 1).setPiece(new Knight(true));
        getSquare(3, 1).setPiece(new Bishop(true));
        getSquare(4, 1).setPiece(new Queen(true));
        getSquare(5, 1).setPiece(new King(true));
        getSquare(6, 1).setPiece(new Bishop(true));
        getSquare(7, 1).setPiece(new Knight(true));
        getSquare(8, 1).setPiece(new Rook(true));

        getSquare(1, 8).setPiece(new Rook(false));
        getSquare(2, 8).setPiece(new Knight(false));
        getSquare(3, 8).setPiece(new Bishop(false));
        getSquare(4, 8).setPiece(new Queen(false));
        getSquare(5, 8).setPiece(new King(false));
        getSquare(6, 8).setPiece(new Bishop(false));
        getSquare(7, 8).setPiece(new Knight(false));
        getSquare(8, 8).setPiece(new Rook(false));
    }

    public boolean checkCheck(boolean toWhite) {
        ChessSquare kingsSquare = null;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessSquare square = getSquare(i, j);
                ChessPiece piece = square.piece;
                if (piece instanceof King
                        && (piece.isWhite() == toWhite)) {
                    kingsSquare = square;
                    break;
                }
            }
        }
        if (kingsSquare == null) {
            return false;
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessSquare square = getSquare(i, j);
                ChessPiece piece = square.piece;
                if (piece != null
                        && (piece.isWhite() != toWhite)
                        && piece.checkMove(this, square, kingsSquare)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkMate(boolean toWhite) {
        if (checkCheck(toWhite)) {
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    ChessSquare square1 = getSquare(i, j);
                    ChessPiece piece = square1.piece;
                    if (piece != null
                            && piece.isWhite() == toWhite) {
                        for (int k = 1; k < 9; k++) {
                            for (int l = 1; l < 9; l++) {
                                ChessSquare square2 = getSquare(k, l);
                                if (checkMove(square1, square2)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
