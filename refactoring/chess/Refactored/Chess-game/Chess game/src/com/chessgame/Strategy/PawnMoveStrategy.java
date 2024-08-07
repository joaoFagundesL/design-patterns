package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Pawn;
import com.chessgame.Pieces.Piece;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(int x, int y, Board board, Piece piece) {
        Pawn pawn = (Pawn) piece;
        int xCord = pawn.getXcord();
        int yCord = pawn.getYcord();
        int direction = pawn.isWhite() ? -1 : 1;

        if (isEnPassantMove(x, y, board, pawn, xCord, yCord, direction)) {
            return true;
        }

        if (isBlocked(x, y, board, pawn)) {
            return false;
        }

        if (isValidDiagonalMove(x, y, board, pawn, xCord, yCord, direction)) {
            return true;
        }

        if (isValidForwardMove(x, y, board, pawn, xCord, yCord, direction)) {
            return true;
        }

        return false;
    }

    private boolean isEnPassantMove(int x, int y, Board board, Pawn pawn, int xCord, int yCord, int direction) {
        if (xCord > 0 && xCord < 7) {
            Pawn leftPawn = (Pawn) board.getPiece(xCord + 1, yCord);
            Pawn rightPawn = (Pawn) board.getPiece(xCord - 1, yCord);
            if (isValidEnPassant(x, y, leftPawn, direction) || isValidEnPassant(x, y, rightPawn, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEnPassant(int x, int y, Pawn adjacentPawn, int direction) {
        return adjacentPawn != null && adjacentPawn.isMoved2Squares() &&
               x == adjacentPawn.getXcord() && y == adjacentPawn.getYcord() + direction;
    }

    private boolean isBlocked(int x, int y, Board board, Pawn pawn) {
        return board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == pawn.isWhite();
    }

    private boolean isValidDiagonalMove(int x, int y, Board board, Pawn pawn, int xCord, int yCord, int direction) {
        return Math.abs(x - xCord) == 1 && y == yCord + direction && board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() != pawn.isWhite();
    }

    private boolean isValidForwardMove(int x, int y, Board board, Pawn pawn, int xCord, int yCord, int direction) {
        if (pawn.isFirstMove()) {
            return isValidFirstMove(x, y, board, xCord, yCord, direction);
        }
        return x == xCord && y == yCord + direction && board.getPiece(x, y) == null;
    }

    private boolean isValidFirstMove(int x, int y, Board board, int xCord, int yCord, int direction) {
        return x == xCord && (y == yCord + direction || y == yCord + 2 * direction) &&
               board.getPiece(x, y) == null && board.getPiece(x, yCord + direction) == null;
    }
}
